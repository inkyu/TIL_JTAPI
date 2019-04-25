package javax.telephony.callcenter.capabilities;

import javax.telephony.capabilities.ProviderCapabilities;

public abstract interface CallCenterProviderCapabilities extends
		ProviderCapabilities {
	public abstract boolean canGetRouteableAddresses();

	public abstract boolean canGetACDAddresses();

	public abstract boolean canGetACDManagerAddresses();
}