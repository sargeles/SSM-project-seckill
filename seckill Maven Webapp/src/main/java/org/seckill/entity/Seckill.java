package org.seckill.entity;

import java.util.Date;


public class Seckill implements java.io.Serializable {

	// Fields

	private Integer seckillId;
	private String name;
	private Integer number;
	private Date startTime;
	private Date endTime;
	private Date createTime;

	// Constructors

	/** default constructor */
	public Seckill() {
	}

	/** full constructor */
	public Seckill(Integer seckillId, String name, Integer number,
			Date startTime, Date endTime, Date createTime) {
		this.seckillId = seckillId;
		this.name = name;
		this.number = number;
		this.startTime = startTime;
		this.endTime = endTime;
		this.createTime = createTime;
	}

	// Property accessors

	public Integer getSeckillId() {
		return this.seckillId;
	}

	public void setSeckillId(Integer seckillId) {
		this.seckillId = seckillId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNumber() {
		return this.number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "Seckill [seckillId=" + seckillId + ", name=" + name
				+ ", number=" + number + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", createTime=" + createTime + "]";
	}

	
}