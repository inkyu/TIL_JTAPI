package com.avaya.jtapi.tsapi.impl.events.conn;

import com.avaya.jtapi.tsapi.LucentCallInfo;

public class LucentConnInProgressEventCCImpl extends TsapiConnInProgressEventCC
		implements LucentCallInfo {
	public LucentConnInProgressEventCCImpl(ConnEventParams params) {
		super(params);
	}
}