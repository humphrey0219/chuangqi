/**
 * 
 */
package com.chuangqi.config.cache;

import java.io.Serializable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;

/**
 * @author wmk
 *
 */
@Configuration
public class ConfigCache {
	  /*1.x版本暂时不支持
	   * @Bean
	  public RedisConnectionFactory redisConnectionFactory() {
	    return new LettuceConnectionFactory();
	  }*/
	   
	  @Bean
	  public RedisTemplate<String, Serializable> redisCacheTemplate(JedisConnectionFactory redisConnectionFactory) {
		  //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值（默认使用JDK的序列化方式） 
		    RedisSerializer<?> valSeri=new FastJsonRedisSerializer<>(Object.class);
		    //默认就是StringRedisSerializer
		    //RedisSerializer<String> keySeri=new StringRedisSerializer();
		    
		  	RedisTemplate<String, Serializable> template = new RedisTemplate<>();
		  	
	        //template.setKeySerializer(keySeri);
	        template.setValueSerializer(valSeri);

	        //template.setHashKeySerializer(keySeri);
	        template.setHashValueSerializer(valSeri);
	        
	        template.setConnectionFactory(redisConnectionFactory);
	        return template;
	  }
}
