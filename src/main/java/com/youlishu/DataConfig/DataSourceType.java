package com.youlishu.DataConfig;

public enum DataSourceType {


	LOT("lot_device_data");
	//ywtgxt
//	STHJJ("shengtai"),
	//sthjj
//	STHJJ("sthjj"),
//	orderp预警事件
//	ORDERP("orderp"),
	//数据接口库
//	SJJK("sjjk");

	private String name;

	private DataSourceType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
