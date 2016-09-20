package org.seckill.service;

import java.util.List;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;

/**
 * 业务接口：站在“使用者”角度设计接口
 * 三个方面：
 * 方法定义的目的要明确（粒度）
 * 参数（简练直接）
 * 返回类型（包括异常）
 * @author sargeles
 *
 */
public interface SeckillService {
	
	/**
	 * 查询所有秒杀纪录
	 * @return
	 */
	List<Seckill> getSeckillList();
	
	/**
	 * 查询单个秒杀纪录
	 * @param seckillId
	 * @return
	 */
	Seckill getById(int seckillId);
	
	/**
	 * 秒杀开启时输出秒杀接口地址
	 * 否则输出系统时间和秒杀时间
	 * @param seckillId
	 */
	Exposer exportSeckillUrl(int seckillId);
	
	/**
	 * 执行秒杀操作
	 * @param seckillId
	 * @param userPhone
	 * @param md5
	 */
	SeckillExecution executeSeckill(int seckillId,String userPhone,String md5)
			throws SeckillException,RepeatKillException,SeckillCloseException;
	

}
