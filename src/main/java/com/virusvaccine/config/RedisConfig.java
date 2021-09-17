package com.virusvaccine.config;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableCaching
public class RedisConfig {

  @Bean
  public JedisConnectionFactory connectionFactory() {
    RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
    return new JedisConnectionFactory(configuration);
  }

  @Bean
  public RedisTemplate<String, Object> template() {
    RedisTemplate<String, Object> template = new RedisTemplate<>();
    template.setConnectionFactory(connectionFactory());
    return template;
  }

}
