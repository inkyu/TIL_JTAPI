package com.avaya.jtapi.tsapi.adapters;

import javax.telephony.callcenter.AgentTerminalEvent;
import javax.telephony.callcenter.AgentTerminalListener;

public abstract class AgentTerminalListenerAdapter extends
		TerminalListenerAdapter implements AgentTerminalListener {
	public void agentTerminalBusy(AgentTerminalEvent event) {
	}

	public void agentTerminalLoggedOff(AgentTerminalEvent event) {
	}

	public void agentTerminalLoggedOn(AgentTerminalEvent event) {
	}

	public void agentTerminalNotReady(AgentTerminalEvent event) {
	}

	public void agentTerminalReady(AgentTerminalEvent event) {
	}

	public void agentTerminalUnknown(AgentTerminalEvent event) {
	}

	public void agentTerminalWorkNotReady(AgentTerminalEvent event) {
	}

	public void agentTerminalWorkReady(AgentTerminalEvent event) {
	}
}