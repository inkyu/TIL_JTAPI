package com.avaya.jtapi.tsapi.impl.events.call;

import javax.telephony.callcenter.events.CallCentTrunkValidEv;

@SuppressWarnings("deprecation")
public final class TsapiTrunkValidEv extends TsapiCallCtrTrunkEvent implements
		CallCentTrunkValidEv {
	public int getID() {
		return 317;
	}

	public TsapiTrunkValidEv(CallEventParams params) {
		super(params);
	}
}