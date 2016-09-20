package org.seckill.dao;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.Seckill;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 配置spring和junit整合，junit启动时加载springioc容器。
 * junit,spring-test依赖
 * @author sargeles
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
//提供spring配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {
	
	@Resource
	private SeckillDao seckillDao;
	
	@Before
	public void init(){
		/*System.out.println("------------------"+new Date());*/
	}

	@Test
	public final void testReduceNumber() {
		Date killTime = new Date();
		int seckillId = 1000;
		int updateCount = seckillDao.reduceNumber(seckillId, killTime);
		System.out.println("updateCount="+updateCount);
	}

	@Test
	public final void testQueryById() {
		int id=1000;
		Seckill seckill = seckillDao.queryById(id);
		System.out.println(seckill.getName());
		System.out.println(seckill);
	}

	@Test
	public final void testQueryAll() {
		List<Seckill> seckills = seckillDao.queryAll(0, 100);
		for(Seckill seckill:seckills){
			System.out.println(seckill);
		}
		
	}

}
