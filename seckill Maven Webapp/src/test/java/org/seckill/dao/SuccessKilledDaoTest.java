package org.seckill.dao;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.SuccessKilled;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
//提供spring配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKilledDaoTest {

	@Resource
	private SuccessKilledDao successKilledDao;
	
	@Test
	public final void testInsertsuccessKilled() {
		int seckillId=1001;
		String userPhone="13855555555";
		int insertCount = successKilledDao.insertsuccessKilled(seckillId, userPhone);
		System.out.println("insertCount="+insertCount);
	}

	@Test
	public final void testQueryByIdWithSeckill() {
		int seckillId=1001;
		String userPhone="13855555555";
		SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
		if(successKilled!=null){
		System.out.println(successKilled);
		System.out.println(successKilled.getSeckill());}
	}

}
