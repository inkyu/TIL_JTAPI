package com.avaya.jtapi.tsapi.impl.events.termConn;

import com.avaya.jtapi.tsapi.LucentV5CallInfo;

public final class LucentV5TermConnTalkingEventImpl extends
		LucentTermConnTalkingEventImpl implements LucentV5CallInfo {
	public LucentV5TermConnTalkingEventImpl(TermConnEventParams params) {
		super(params);
	}
}