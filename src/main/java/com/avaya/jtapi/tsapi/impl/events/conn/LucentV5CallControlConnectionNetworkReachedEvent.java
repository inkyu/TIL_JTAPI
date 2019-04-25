package com.avaya.jtapi.tsapi.impl.events.conn;

import com.avaya.jtapi.tsapi.LucentV5Call;
import com.avaya.jtapi.tsapi.LucentV5CallInfo;
import com.avaya.jtapi.tsapi.impl.events.call.CallEventParams;
import javax.telephony.MetaEvent;

public class LucentV5CallControlConnectionNetworkReachedEvent extends
		LucentCallControlConnectionNetworkReachedEvent implements
		LucentV5CallInfo {
	public LucentV5CallControlConnectionNetworkReachedEvent(
			CallEventParams params, MetaEvent event, int eventId,
			int numInQueue, String digits) {
		super(params, event, eventId, numInQueue, digits);
	}

	public boolean canSetBillRate() {
		return this.callEventParams.isFlexibleBilling();
	}

	public int getCallOriginatorType() {
		return this.callEventParams.getCallOriginatorType();
	}

	public String getUCID() {
		String ucid = null;
		if (this.callEventParams.getUcid() != null) {
			ucid = this.callEventParams.getUcid();
		} else if ((getCall() instanceof LucentV5Call)) {
			ucid = ((LucentV5Call) getCall()).getUCID();
		}
		return ucid;
	}

	public boolean hasCallOriginatorType() {
		return this.callEventParams.hasCallOriginatorType();
	}
}