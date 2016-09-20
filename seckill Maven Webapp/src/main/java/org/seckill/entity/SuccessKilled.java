package org.seckill.entity;

import java.util.Date;


/**
 * SuccessKilled entity. @author MyEclipse Persistence Tools
 */

public class SuccessKilled implements java.io.Serializable {

	// Fields
	private Integer seckillId;
	private String userPhone;
	private Integer state;
	private Date createTime;
	
	private Seckill seckill;

	// Constructors
	/** default constructor */
	public SuccessKilled() {
	}
	
	public Seckill getSeckill() {
		return seckill;
	}

	public void setSeckill(Seckill seckill) {
		this.seckill = seckill;
	}

	public Integer getSeckillId(){
		return seckillId;
	}

	public void setSeckillId(Integer seckillId) {
		this.seckillId = seckillId;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "SuccessKilled [seckillId=" + seckillId + ", userPhone="
				+ userPhone + ", state=" + state + ", createTime=" + createTime
				+ "]";
	}

}