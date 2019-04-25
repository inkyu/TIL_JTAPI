package com.avaya.jtapi.tsapi.impl.events.terminal;

import javax.telephony.Terminal;
import javax.telephony.callcenter.Agent;
import javax.telephony.callcenter.events.AgentTermWorkReadyEv;

@SuppressWarnings("deprecation")
public final class TsapiTermWorkReadyEv extends TsapiAgentTermEv implements
		AgentTermWorkReadyEv {
	public int getID() {
		return 315;
	}

	public TsapiTermWorkReadyEv(Terminal _device, Agent _agent, int _cause,
			int _metaCode, Object _privateData) {
		super(_device, _agent, _cause, _metaCode, _privateData);
	}
}