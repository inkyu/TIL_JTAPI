package javax.telephony.callcontrol.events;

import javax.telephony.Address;
import javax.telephony.Terminal;
import javax.telephony.events.CallEv;

/** @deprecated */
public abstract interface CallCtlCallEv extends CallCtlEv, CallEv {
	public abstract Address getCallingAddress();

	public abstract Terminal getCallingTerminal();

	public abstract Address getCalledAddress();

	public abstract Address getLastRedirectedAddress();
}