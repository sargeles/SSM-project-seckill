package org.seckill.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
//提供spring配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml"})
public class SeckillServiceTest {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SeckillService seckillService;
	
	@Test
	public final void testGetSeckillList() {
		List<Seckill> list = seckillService.getSeckillList();
		logger.info("list={}",list);//花括号代表占位符，打印时list内容会替代占位符。
	}

	@Test
	public final void testGetById() {
		int id=1000;
		Seckill seckill = seckillService.getById(id);
		logger.info("seckill={}",seckill);
	}
	
	/**
	 * 捕捉感兴趣的异常。
	 * 把一件事务中的方法集合测试。
	 * 业务覆盖要完整。
	 * 测试可重复
	 */
	@Test
	public final void testSeckillLogic() {
		int id=1000;
		Exposer exposer = seckillService.exportSeckillUrl(id);
		if(exposer.isExposed()){
			logger.info("exposer={}",exposer);
			String phone="13588585686";
			String md5=exposer.getMd5();
			try{
				SeckillExecution execution = seckillService.executeSeckill(id, phone, md5);
				logger.info("execution={}",execution);
			}catch(RepeatKillException e1){
				logger.error(e1.getMessage());
			}catch(SeckillCloseException e2){
				logger.error(e2.getMessage());
			}
		}else{
			logger.warn("exposer={}",exposer);
		}
	}
	
	
	

/*	@Test
	public final void testExportSeckillUrl() {
		int id=1000;
		Exposer exposer = seckillService.exportSeckillUrl(id);
		logger.info("exposer={}",exposer);
	}

	@Test
	public final void testExecuteSeckill() {
		int id=1000;
		String phone="13588585686";
		String md5="42d06a856c2bbe9e62b6be410719d160";
		try{
			SeckillExecution execution = seckillService.executeSeckill(id, phone, md5);
			logger.info("execution={}",execution);
		}catch(RepeatKillException e1){
			logger.error(e1.getMessage());
		}catch(SeckillCloseException e2){
			logger.error(e2.getMessage());
		}
	}*/

}
