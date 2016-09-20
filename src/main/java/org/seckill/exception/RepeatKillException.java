package org.seckill.exception;

/**
 * 重复秒杀异常（运行期异常）
 * 编译时异常可以try/catch
 * 事物回滚接收的都是运行期异常
 * @author sargeles
 */
public class RepeatKillException extends SeckillException{
	
	public RepeatKillException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public RepeatKillException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	
}
