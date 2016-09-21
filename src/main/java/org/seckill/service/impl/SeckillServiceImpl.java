package org.seckill.service.impl;

import java.util.Date;
import java.util.List;

import org.seckill.dao.SeckillDao;
import org.seckill.dao.SuccessKilledDao;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.entity.SuccessKilled;
import org.seckill.enums.SeckillStateEnum;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
/**
 * 接口实现
 * @author sargeles
 */
@Service
public class SeckillServiceImpl implements SeckillService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// 由mapper实现，由Spring来注入实体
	@Autowired
	private SeckillDao seckillDao;
	@Autowired
	private SuccessKilledDao successKilledDao;

	// 盐值字符串
	private String slat = "lakjg!#$453afa·048-==!FAFaa_1dfafsb";

	@Override
	public List<Seckill> getSeckillList() {
		return seckillDao.queryAll(0, 4);
	}

	@Override
	public Seckill getById(int seckillId) {
		return seckillDao.queryById(seckillId);
	}

	@Override
	public Exposer exportSeckillUrl(int seckillId) {
		Seckill seckill = seckillDao.queryById(seckillId);
		if (seckill == null) {
			return new Exposer(false, seckillId);
		}
		Date startTime = seckill.getStartTime();
		Date endTime = seckill.getEndTime();
		Date nowTime = new Date();
		if ((nowTime.getTime() < startTime.getTime())
				|| (nowTime.getTime() > endTime.getTime())) {
			return new Exposer(false, seckillId, nowTime.getTime(),
					startTime.getTime(), endTime.getTime());
		}
		String md5 = getMd5(seckillId);
		return new Exposer(true, md5, seckillId);
	}

	private String getMd5(int seckillId) {
		String base = seckillId + "-" + slat;
		String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
		return md5;
	}

	@Override
	@Transactional
	/**
	 * 使用注解控制事务方法的优点：
	 * 1：开发团队达成一致约定，明确标注事务方法的编程风格。
	 * 2：保证事务方法的执行时间尽可能短，不要穿插其他网络操作RPC（缓存）/HTTP请求，或者剥离到事物方法外部。
	 * 3：不是所有的方法都需要事务，如一个操作只需要一条修改操作，只读操作，不需要并发控制的操作。就不需要事务控制。
	 */
	public SeckillExecution executeSeckill(int seckillId, String userPhone,
			String md5) throws SeckillException, RepeatKillException,
			SeckillCloseException {

		try {
			if (md5 == null || !md5.equals(getMd5(seckillId))) {
				throw new SeckillException("seckill data rewrite");
			}
			// 执行秒杀逻辑：减库存，纪录购买行为
			Date nowtime = new Date();
			int updateCount = seckillDao.reduceNumber(seckillId, nowtime);
			if (updateCount <= 0) {
				// 没有更新到纪录，秒杀结束
				throw new SeckillCloseException("seckill is closed");
			} else {
				// 纪录购买行为
				int insertCount = successKilledDao.insertsuccessKilled(
						seckillId, userPhone);
				if (insertCount <= 0) {
					// 重复秒杀
					throw new RepeatKillException("seckill repeated");
				} else {
					// 秒杀成功
					SuccessKilled successKilled = successKilledDao
							.queryByIdWithSeckill(seckillId, userPhone);
					return new SeckillExecution(seckillId, SeckillStateEnum.SUCCESS,
							successKilled);
				}
			}
		}catch(SeckillCloseException e1){
			throw e1;
		}catch(RepeatKillException e2){
			throw e2;
		}catch (Exception e) {
			logger.error(e.getMessage(),e);
			//所有编译期异常，转化为运行期异常
			throw new SeckillException("seckill inner error:"+e.getMessage());
		}

	}

}
