package com.yitian.sq.cache;

import com.yitian.sq.security.EmailRunnable;
import com.yitian.sq.servlet.Kaptcha;
import com.yitian.sq.webService.UserWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class RedisCacheTransfer {
    @Autowired
    public  void setJedisConnectionFactory(JedisConnectionFactory jedisConnectionFactory){
        RedisCache.setJedisConnectionFactory(jedisConnectionFactory);
    }
    @Autowired
    public  void setRedisTemplate(RedisTemplate redisTemplate){
        Kaptcha.setRedisTemplate(redisTemplate);
    }

    @Autowired
    public  void setSimpleMailMessage(JavaMailSenderImpl mailSender){
        EmailRunnable.setMailSender(mailSender);
    }

    @Autowired
    public void setUserWebService(UserWebService userWebService){
        Kaptcha.setUserWebService(userWebService);
    }

}
