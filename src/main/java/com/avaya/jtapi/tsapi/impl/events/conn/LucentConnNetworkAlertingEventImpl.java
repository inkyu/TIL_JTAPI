package com.avaya.jtapi.tsapi.impl.events.conn;

import com.avaya.jtapi.tsapi.LucentCallInfo;

public class LucentConnNetworkAlertingEventImpl extends
		TsapiConnNetworkAlertingEvent implements LucentCallInfo {
	public LucentConnNetworkAlertingEventImpl(ConnEventParams params) {
		super(params);
	}
}