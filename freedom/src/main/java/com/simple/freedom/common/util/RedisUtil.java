package com.simple.freedom.common.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.simple.freedom.common.aop.Redis;

public class RedisUtil {
	private static ApplicationContext app;
	private static Redis redisService;
	static
	{
		app = new ClassPathXmlApplicationContext("classpath:spring-redis.xml");   
        redisService = (Redis) app.getBean("redisService");  
        String ping = redisService.ping();//测试是否连接成功,连接成功输出PONG  
        System.out.println(ping);
	}
	
	public static String getValue(String key)
	{
		return redisService.get(key);
	}
	
	public static void setValue(String key,String value)
	{
		redisService.set(key, value);
	}
	
	public static void deleteValue(String key)
	{
		redisService.del(key);
	}
	
	public static boolean existsKey(String key)
	{
		return redisService.exists(key);
	}
	
	public static void updataValue(String key,String value)
	{
		redisService.del(key);
		redisService.set(key, value);
	}
}
