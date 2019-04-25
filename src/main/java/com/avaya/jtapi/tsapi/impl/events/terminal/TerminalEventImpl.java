package com.avaya.jtapi.tsapi.impl.events.terminal;

import com.avaya.jtapi.tsapi.impl.events.TsapiListenerEvent;
import javax.telephony.Terminal;
import javax.telephony.TerminalEvent;

public class TerminalEventImpl extends TsapiListenerEvent implements
		TerminalEvent {
	private Terminal terminal;

	public TerminalEventImpl(int eventId, int _cause, Terminal _device,
			Object privateData) {
		super(eventId, _cause, null, _device, privateData);
		this.terminal = _device;
	}

	public Terminal getTerminal() {
		return this.terminal;
	}
}