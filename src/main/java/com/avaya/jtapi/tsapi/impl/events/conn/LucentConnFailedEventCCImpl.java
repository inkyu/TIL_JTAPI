package com.avaya.jtapi.tsapi.impl.events.conn;

import com.avaya.jtapi.tsapi.LucentCallInfo;

public class LucentConnFailedEventCCImpl extends TsapiConnFailedEventCC
		implements LucentCallInfo {
	public LucentConnFailedEventCCImpl(ConnEventParams params) {
		super(params);
	}
}