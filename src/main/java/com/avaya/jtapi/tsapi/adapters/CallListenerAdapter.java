package com.avaya.jtapi.tsapi.adapters;

import javax.telephony.CallEvent;
import javax.telephony.CallListener;
import javax.telephony.MetaEvent;

public class CallListenerAdapter implements CallListener {
	public void callActive(CallEvent event) {
	}

	public void callEventTransmissionEnded(CallEvent event) {
	}

	public void callInvalid(CallEvent event) {
	}

	public void multiCallMetaMergeEnded(MetaEvent event) {
	}

	public void multiCallMetaMergeStarted(MetaEvent event) {
	}

	public void multiCallMetaTransferEnded(MetaEvent event) {
	}

	public void multiCallMetaTransferStarted(MetaEvent event) {
	}

	public void singleCallMetaProgressEnded(MetaEvent event) {
	}

	public void singleCallMetaProgressStarted(MetaEvent event) {
	}

	public void singleCallMetaSnapshotEnded(MetaEvent event) {
	}

	public void singleCallMetaSnapshotStarted(MetaEvent event) {
	}
}