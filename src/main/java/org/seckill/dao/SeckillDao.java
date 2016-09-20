package org.seckill.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.Seckill;


public interface SeckillDao {
	/**
	 * 减库存，
	 * @param seckillId
	 * @param killTime
	 * @return 数值>1表示返回影响的行数，返回0表示操作没有影响数据。
	 */
	int reduceNumber(@Param("seckillId")int seckillId,@Param("killTime") Date killTime);	
	/**
	 * 根据id查询秒杀对象
	 * @param seckillId
	 * @return
	 */
	Seckill queryById(int seckillId);
	
	/**
	 * 根据偏移量查询秒杀商品列表
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<Seckill> queryAll	(@Param("offset")int offset,@Param("limit")int limit);
	//在参数前面加上注解，告诉Mybatis，这个形参叫什么名字。
	//不然在java编译的时候，这两个形参会被另外命名成arg0和arg1，java不对多个形参保存纪录。
	
}
