package com.avaya.jtapi.tsapi.impl.core;

import com.avaya.jtapi.tsapi.csta1.CSTAEvent;
import com.avaya.jtapi.tsapi.csta1.CSTAQueryDndConfEvent;
import com.avaya.jtapi.tsapi.impl.monitor.TsapiAddressMonitor;
import com.avaya.jtapi.tsapi.impl.monitor.TsapiTerminalMonitor;
import com.avaya.jtapi.tsapi.tsapiInterface.ConfHandler;
import java.util.Vector;

final class DNDConfHandler implements ConfHandler {
	TSDevice device;
	int pdu;
	boolean enable;

	DNDConfHandler(TSDevice _device, boolean _enable) {
		this.device = _device;
		this.pdu = 46;
		this.enable = _enable;
	}

	DNDConfHandler(TSDevice _device) {
		this.device = _device;
		this.pdu = 30;
	}

	public void handleConf(CSTAEvent event) {
		if ((event == null) || (event.getEventHeader().getEventClass() != 5)
				|| (event.getEventHeader().getEventType() != this.pdu)) {
			return;
		}

		if (this.pdu == 30) {
			this.enable = ((CSTAQueryDndConfEvent) event.getEvent())
					.isDoNotDisturb();
		}

		this.device.replyAddrPriv = event.getPrivData();
		this.device.replyTermPriv = event.getPrivData();

		Vector<TSEvent> eventList = new Vector<TSEvent>();

		this.device.updateDNDState(this.enable, eventList);

		if (eventList.size() > 0) {
			Vector<?> observers = this.device.getAddressObservers();
			for (int j = 0; j < observers.size(); j++) {
				TsapiAddressMonitor callback = (TsapiAddressMonitor) observers
						.elementAt(j);
				callback.deliverEvents(eventList, false);
			}
			Vector<?> terminalObservers = this.device.getTerminalObservers();
			for (int j = 0; j < terminalObservers.size(); j++) {
				TsapiTerminalMonitor callback = (TsapiTerminalMonitor) terminalObservers
						.elementAt(j);
				callback.deliverEvents(eventList, false);
			}
		}
	}
}