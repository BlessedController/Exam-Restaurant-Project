package com.texnoera.restaurant_service.service.implementations;

import com.texnoera.restaurant_service.dto.request.CreateRestaurantRequest;
import com.texnoera.restaurant_service.dto.response.RestaurantResponse;
import com.texnoera.restaurant_service.exception.NotFoundException;
import com.texnoera.restaurant_service.mapper.RestaurantMapper;
import com.texnoera.restaurant_service.model.Restaurant;
import com.texnoera.restaurant_service.repository.RestaurantRepository;
import com.texnoera.restaurant_service.service.abstraction.RestaurantService;
import com.texnoera.restaurant_service.util.RedisUtil;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static com.texnoera.restaurant_service.mapper.RestaurantMapper.toEntity;
import static com.texnoera.restaurant_service.mapper.RestaurantMapper.toResponse;
import static java.time.temporal.ChronoUnit.HOURS;
import static lombok.AccessLevel.PRIVATE;

@Service
@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor
@Slf4j
public class RestaurantServiceHandler implements RestaurantService {

    RestaurantRepository restaurantRepository;
    RedisUtil redisUtil;
    KafkaTemplate<String, Restaurant> kafkaTemplate;
    static String TOPIC = "create-restaurant-topic";

    private String getKey(Long id) {
        return "restaurant_" + id;
    }

    @Override
    public RestaurantResponse save(CreateRestaurantRequest request) {
        Restaurant restaurant = toEntity(request);

        Restaurant saved = restaurantRepository.save(restaurant);

        kafkaTemplate.send(TOPIC, restaurant);
        log.info("Restaurant sent to kafka with id {}", restaurant.getId());

        String key = getKey(saved.getId());

        redisUtil.saveToCache(key, restaurant, 1L, HOURS);
        log.info("Data DB'yə save edildi və Redis'ə yazıldı");

        return toResponse(restaurant);
    }

    @Override
    public RestaurantResponse findById(Long id) {
        Restaurant dataFromCache = redisUtil.getDataFromCache(getKey(id));

        if (dataFromCache != null) {
            log.info("Data Redis'dən alındı");
            return toResponse(dataFromCache);
        }

        Restaurant restaurant = restaurantRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Restaurant not found"));

        redisUtil.saveToCache(getKey(id), restaurant, 1L, HOURS);
        log.info("Data DB'dən alındı və Redis'ə yazıldı");
        return toResponse(restaurant);
    }

    @Override
    public Page<RestaurantResponse> findAll(Pageable pageable) {

        Page<Restaurant> allRedis = redisUtil.getAllDataFromCache("all");

        if (allRedis != null) {
            log.info("Data Redis'dən alındı");
            return allRedis.map(RestaurantMapper::toResponse);
        }

        Page<Restaurant> allDB = restaurantRepository.findAll(pageable);
        redisUtil.saveToCache("all", allDB, 1L, HOURS);
        log.info("Data DB'dən alındı və Redis'ə yazıldı");

        return allDB.map(RestaurantMapper::toResponse);
    }

    @Override
    public Boolean existsById(Long id) {
        return restaurantRepository.existsById(id);
    }
}
