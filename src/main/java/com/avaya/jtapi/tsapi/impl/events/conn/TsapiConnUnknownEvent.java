package com.avaya.jtapi.tsapi.impl.events.conn;

import javax.telephony.events.ConnUnknownEv;

public final class TsapiConnUnknownEvent extends TsapiConnEvent implements
		ConnUnknownEv {
	public int getID() {
		return 110;
	}

	public TsapiConnUnknownEvent(ConnEventParams params) {
		super(params);
	}
}