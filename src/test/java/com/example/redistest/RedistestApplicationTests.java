package com.example.redistest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedistestApplicationTests {
	@Autowired
	StringRedisTemplate stringRedisTemplate;

	@Autowired
	RedisTemplate redisTemplate;

	@Test
	public void contextLoads() {
		stringRedisTemplate.opsForValue().set("test", "100",60*10, TimeUnit.SECONDS);//向redis里存入数据和设置缓存时间
		stringRedisTemplate.boundValueOps("test").increment(-1);//val做-1操作
		String s = stringRedisTemplate.opsForValue().get("test");//根据key获取缓存中的val
		System.out.println(s);
		Long aLong = stringRedisTemplate.getExpire("test");//根据key获取过期时间戳
		System.out.println(aLong);
		Long aLong1 = stringRedisTemplate.getExpire("test", TimeUnit.SECONDS);//根据key获取过期时间并换算成指定单位
		System.out.println(aLong1);
		System.out.println("操作成功");
	}

	@Test
	public void contextest(){
		redisTemplate.opsForValue().set("name","tom");
		String name =(String) redisTemplate.opsForValue().get("name");//  输出结果为tom
		System.out.println(name);
	}

}
