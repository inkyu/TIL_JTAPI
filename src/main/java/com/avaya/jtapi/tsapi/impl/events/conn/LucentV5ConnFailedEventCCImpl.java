package com.avaya.jtapi.tsapi.impl.events.conn;

import com.avaya.jtapi.tsapi.LucentV5CallInfo;

public final class LucentV5ConnFailedEventCCImpl extends
		LucentConnFailedEventCCImpl implements LucentV5CallInfo {
	public LucentV5ConnFailedEventCCImpl(ConnEventParams params) {
		super(params);
	}
}