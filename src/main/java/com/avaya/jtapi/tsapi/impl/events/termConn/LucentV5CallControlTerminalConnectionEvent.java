package com.avaya.jtapi.tsapi.impl.events.termConn;

import com.avaya.jtapi.tsapi.LucentV5CallInfo;
import com.avaya.jtapi.tsapi.impl.events.call.CallEventParams;
import javax.telephony.MetaEvent;

public class LucentV5CallControlTerminalConnectionEvent extends
		LucentCallControlTerminalConnectionEvent implements LucentV5CallInfo {
	public LucentV5CallControlTerminalConnectionEvent(CallEventParams params,
			MetaEvent event, int eventId) {
		super(params, event, eventId);
	}

	public boolean canSetBillRate() {
		return this.callEventParams.isFlexibleBilling();
	}

	public int getCallOriginatorType() {
		return this.callEventParams.getCallOriginatorType();
	}

	public String getUCID() {
		return this.callEventParams.getUcid();
	}

	public boolean hasCallOriginatorType() {
		return this.callEventParams.hasCallOriginatorType();
	}
}