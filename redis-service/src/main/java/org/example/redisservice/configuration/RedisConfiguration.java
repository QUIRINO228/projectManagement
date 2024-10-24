package org.example.redisservice.configuration;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.time.Duration;

@Configuration
@EnableCaching
public class RedisConfiguration implements CachingConfigurer {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.cache.time-to-live}")
    private int cacheTtlSeconds;

    @Bean
    public RedisStandaloneConfiguration redisStandaloneConfiguration() {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName(host);
        configuration.setPort(port);
        return configuration;
    }

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory(redisStandaloneConfiguration());
    }

    @Bean
    public RedisSerializer<Object> redisSerializer() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL,
                JsonTypeInfo.As.WRAPPER_ARRAY);
        return new GenericJackson2JsonRedisSerializer(objectMapper);
    }

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .disableCachingNullValues()
                .entryTtl(Duration.ofSeconds(cacheTtlSeconds))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer()));

        return RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(redisCacheConfiguration)
                .build();
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(JedisConnectionFactory jedisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory);
        template.setDefaultSerializer(redisSerializer());
        template.setKeySerializer(redisSerializer());
        template.setValueSerializer(redisSerializer());
        template.setHashKeySerializer(redisSerializer());
        template.setHashValueSerializer(redisSerializer());
        return template;
    }
}
