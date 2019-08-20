/**
 * 
 */
package com.chuangqi.config.cache;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

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
	  public RedisTemplate<String, ?> redisCacheTemplate(JedisConnectionFactory redisConnectionFactory) {
		    //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值（默认使用JDK的序列化方式） 
		    //使用Fast对象，存储对象的时候转换成json格式存储，反向查询的时候返回也是json格式，再转换成对象
		    RedisSerializer<?> valSeri=new FastJsonRedisSerializer<>(Object.class);
		    //字符串作为key StringRedisSerializer
		    RedisSerializer<String> keySeri=new StringRedisSerializer();
		    
		  	RedisTemplate<String, ?> template = new RedisTemplate<>();
		  	
	        template.setKeySerializer(keySeri);
	        template.setValueSerializer(valSeri);

	        template.setHashKeySerializer(keySeri);
	        template.setHashValueSerializer(valSeri);
	        template.setStringSerializer(keySeri);
	        
	        template.setConnectionFactory(redisConnectionFactory);

	        return template;
	  }
}
