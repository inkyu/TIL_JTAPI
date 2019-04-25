package com.avaya.jtapi.tsapi;

import javax.telephony.Connection;
import javax.telephony.callcenter.ACDAddress;

public abstract interface LucentCallEx extends LucentCall {
	public abstract Connection[] connectDirectAgent(
			LucentTerminal paramLucentTerminal,
			LucentAddress paramLucentAddress, LucentAgent paramLucentAgent,
			boolean paramBoolean, UserToUserInfo paramUserToUserInfo,
			ACDAddress paramACDAddress)
			throws TsapiResourceUnavailableException,
			TsapiPrivilegeViolationException, TsapiInvalidPartyException,
			TsapiInvalidArgumentException, TsapiInvalidStateException,
			TsapiMethodNotSupportedException;

	public abstract Connection[] consultDirectAgent(
			LucentTerminalConnection paramLucentTerminalConnection,
			LucentAgent paramLucentAgent, boolean paramBoolean,
			UserToUserInfo paramUserToUserInfo, ACDAddress paramACDAddress)
			throws TsapiInvalidStateException, TsapiInvalidArgumentException,
			TsapiMethodNotSupportedException,
			TsapiResourceUnavailableException, TsapiPrivilegeViolationException;
}