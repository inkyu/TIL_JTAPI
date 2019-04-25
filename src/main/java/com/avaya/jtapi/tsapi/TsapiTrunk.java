package com.avaya.jtapi.tsapi;

import javax.telephony.Call;
import javax.telephony.Connection;
import javax.telephony.callcenter.CallCenterTrunk;

public abstract class TsapiTrunk implements CallCenterTrunk {
	public abstract String getName();

	public abstract int getState();

	public abstract int getType();

	public abstract Call getCall();

	public abstract int hashCode();

	public abstract boolean equals(Object paramObject);

	public abstract Connection getConnection();

	public abstract String getGroupName();

	public abstract String getMemberName();

	public static String makeTrunkName(String groupName, String memberName) {
		if (groupName == null)
			return null;
		if (memberName == null) {
			return groupName + ":0";
		}
		return groupName + ":" + memberName;
	}
}