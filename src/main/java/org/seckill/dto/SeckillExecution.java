package org.seckill.dto;

import org.seckill.entity.SuccessKilled;
import org.seckill.enums.SeckillStateEnum;

/**
 * 封装秒杀执行后结果
 * @author sargeles
 */
public class SeckillExecution {

	private int seckillId;

	// 秒杀执行结果状态
	private int state;

	// 状态标识
	private String stateInfo;

	// 秒杀成功对象
	private SuccessKilled successKilled;

	/**
	 * 执行秒杀失败
	 * @param seckillId
	 * @param state
	 * @param stateInfo
	 */
	public SeckillExecution(int seckillId, SeckillStateEnum stateEnum) {
		super();
		this.seckillId = seckillId;
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}

	/**
	 * 执行秒杀成功
	 * @param seckillId
	 * @param state
	 * @param stateInfo
	 * @param successKilled
	 */
	public SeckillExecution(int seckillId, SeckillStateEnum stateEnum,
			SuccessKilled successKilled) {
		super();
		this.seckillId = seckillId;
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.successKilled = successKilled;
	}

	public int getSeckillId() {
		return seckillId;
	}

	public void setSeckillId(int seckillId) {
		this.seckillId = seckillId;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	public SuccessKilled getSuccessKilled() {
		return successKilled;
	}

	public void setSuccessKilled(SuccessKilled successKilled) {
		this.successKilled = successKilled;
	}

	@Override
	public String toString() {
		return "SeckillExecution [seckillId=" + seckillId + ", state=" + state
				+ ", stateInfo=" + stateInfo + ", successKilled="
				+ successKilled + "]";
	}

}
