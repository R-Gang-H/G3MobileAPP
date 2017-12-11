package com.yycloud.app.utils;

public class ErrorCode {
	
	public static final int	ERROR_EXCEPTION_NO_ERROR = 0;
	static int	ERROR_EXCEPTION = 1000;
	
	/** 连接服务器超时 */
	static int	ERROR_EXCEPTION_CONNECT_TIMER_OUT = ERROR_EXCEPTION + 1;
	
	/** DNS 错误 */
	static int	ERROR_EXCEPTION_DNS_ERROR = ERROR_EXCEPTION + 2;
	
	/** 数据加密传输错误 */ 
	static int	ERROR_EXCEPTION_ENCRYPTION_ERROR = ERROR_EXCEPTION + 3;

	/** 文件不存在异常 */
	static int	ERROR_EXCEPTION_FILE_NOT_FOUND = ERROR_EXCEPTION + 4;

	/** 证书认证错误 */
	static int	ERROR_EXCEPTION_INVALID_CERTIFICATE = ERROR_EXCEPTION + 5;
	
	/** 无效文件异常，一般文件为0字节时为无效 */
	static int	ERROR_EXCEPTION_INVALID_FILE = ERROR_EXCEPTION + 6;
	
	/** 密钥认证错误 */
	static int	ERROR_EXCEPTION_INVALID_KEYSTORE = ERROR_EXCEPTION + 7;
	
	/** 用户名或密码错误 */
	static int	ERROR_EXCEPTION_INVALID_PASSWORD_USERNAME = ERROR_EXCEPTION + 8;
	
	/** 数据读取错误 */
	static int	ERROR_EXCEPTION_IO_EXCEPTION = ERROR_EXCEPTION + 9;

	static int	ERROR_EXCEPTION_LOGOFFINPROGRESS_ERROR = ERROR_EXCEPTION + 10; 

	/** 网络不可用 */
	static int	ERROR_EXCEPTION_NONETWORK_ERROR = ERROR_EXCEPTION + 11;
	
	/** 无法连接到服务器 */
	static int	ERROR_EXCEPTION_UNABLE_CONNECT_TO_SERVER = ERROR_EXCEPTION + 12;
	
	/** 无法识别服务器返回值 */
	static int	ERROR_EXCEPTION_UNKNOWN_SERVER_ERROR = ERROR_EXCEPTION + 13;
	
	static int	ERROR_FILE_NOT_FOUND = ERROR_EXCEPTION + 14;
	static int	ERROR_SEND = ERROR_EXCEPTION + 15;
	
	/** session timeout */
	static int	ERROR_SESSION_TIMEOUT = ERROR_EXCEPTION + 30;
	
	/** param error */
	static int	ERROR_LOCAL_PARAM = ERROR_EXCEPTION + 31;
	
	
}
