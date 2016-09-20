package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.SuccessKilled;


public interface SuccessKilledDao {
	/**
	 * 插入购买明细，可过滤重复。
	 * @param seckillId
	 * @param userPhone
	 * @return 插入的行数
	 */
	int insertsuccessKilled(@Param("seckillId")int seckillId, @Param("userPhone")String userPhone);
	//在参数前面加上注解，告诉Mybatis，这个形参叫什么名字。
	//不然在java编译的时候，这两个形参会被另外命名成arg0和arg1，java不对多个形参保存纪录。
	
	/**
	 * 根据id查询SuccessKilled并携带秒杀产品对象实体。
	 * @param seckillid
	 * @return
	 */
	SuccessKilled queryByIdWithSeckill(@Param("seckillId")int seckillId,@Param("userPhone")String userPhone);
	
}
