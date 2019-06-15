package org.suifeng.baseframework.api.common.domain;

public enum ErrorType {

	SUCCESS("成功","000"),
	SERVER_ERROR("服务器出问题","999"),
	PARAM_ERROR("参数错误","101"),
	EXIST_NAME("用户账号已存在","333"),
	RESREGBAK_NOT_FOUND("资源注册备份信息未找到","102"),
//	METADATA_NOT_FOUND("元数据没找到","001"),
//	METADATA_PARAM_ERROR("元数据参数错误","002");
	COLLTASKINFO_NOT_FOUND("采集任务基本信息未找到","111"),
	COLLEVENT_NOT_FOUND("采集事件信息未找到","112"),
	RESINFO_NOT_FOUND("资源详情信息未找到","113"),
	APPROVE_FAILURE("审核资源数据失败","114"),
	SYNCEVENTEXCEPTION_NOT_FOUND("共享服务事件异常信息未找到","115"),
	SYNCTASK_NOT_FOUND("共享服务任务信息未找到","116"),
	SYNCORDER_NOT_FOUND("共享订单信息未找到","117"),
	LOADCORRELATION_NOT_FIND("资源关联信息信息未找到","118"),
	CHANGE_TASK_FAILURE("变更任务状态失败","888");
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 值
	 */
	private String value;

	private ErrorType(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}
