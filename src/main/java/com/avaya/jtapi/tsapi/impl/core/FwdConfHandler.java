package com.avaya.jtapi.tsapi.impl.core;

import com.avaya.jtapi.tsapi.csta1.CSTAEvent;
import com.avaya.jtapi.tsapi.csta1.CSTAForwardingInfo;
import com.avaya.jtapi.tsapi.csta1.CSTAQueryFwdConfEvent;
import com.avaya.jtapi.tsapi.impl.monitor.TsapiAddressMonitor;
import com.avaya.jtapi.tsapi.tsapiInterface.ConfHandler;
import java.util.Vector;

final class FwdConfHandler implements ConfHandler {
	TSDevice device;
	int pdu;
	CSTAForwardingInfo[] fwdInfo;

	FwdConfHandler(TSDevice _device, CSTAForwardingInfo[] _fwdInfo) {
		this.device = _device;
		this.pdu = 48;
		this.fwdInfo = _fwdInfo;
	}

	FwdConfHandler(TSDevice _device) {
		this.device = _device;
		this.pdu = 32;
	}

	public void handleConf(CSTAEvent event) {
		if ((event == null) || (event.getEventHeader().getEventClass() != 5)
				|| (event.getEventHeader().getEventType() != this.pdu)) {
			return;
		}

		if (this.pdu == 32) {
			this.fwdInfo = ((CSTAQueryFwdConfEvent) event.getEvent())
					.getForward();
		}

		this.device.replyAddrPriv = event.getPrivData();

		Vector<TSEvent> eventList = new Vector<TSEvent>();
		this.device.updateForwarding(this.fwdInfo, eventList);

		if (eventList.size() > 0) {
			Vector<?> observers = this.device.getAddressObservers();
			for (int j = 0; j < observers.size(); j++) {
				TsapiAddressMonitor callback = (TsapiAddressMonitor) observers
						.elementAt(j);
				callback.deliverEvents(eventList, false);
			}
		}
	}
}