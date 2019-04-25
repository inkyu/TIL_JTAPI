package com.avaya.jtapi.tsapi.impl.events.conn;

import javax.telephony.events.ConnDisconnectedEv;

public final class TsapiConnDisconnectedEvent extends TsapiConnEvent implements
		ConnDisconnectedEv {
	public int getID() {
		return 107;
	}

	public TsapiConnDisconnectedEvent(ConnEventParams params) {
		super(params);
	}
}