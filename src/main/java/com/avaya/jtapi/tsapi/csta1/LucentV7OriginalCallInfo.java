package com.avaya.jtapi.tsapi.csta1;

import com.avaya.jtapi.tsapi.asn1.ASNBoolean;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;

public final class LucentV7OriginalCallInfo extends LucentV5OriginalCallInfo {
	private LucentDeviceHistoryEntry[] asn_deviceHistory;

	public LucentDeviceHistoryEntry[] getDeviceHistory() {
		return this.asn_deviceHistory;
	}

	public void setDeviceHistory(LucentDeviceHistoryEntry[] asn_deviceHistory) {
		this.asn_deviceHistory = asn_deviceHistory;
	}

	public boolean hasDeviceHistory() {
		return this.asn_deviceHistory != null;
	}

	public static LucentOriginalCallInfo decode(InputStream in) {
		LucentV7OriginalCallInfo _this = new LucentV7OriginalCallInfo();
		_this.doDecode(in);
		if ((_this.callingDevice_asn == null)
				&& (_this.calledDevice_asn == null)
				&& (_this.trunkGroup == null) && (_this.trunkMember == null)
				&& (_this.lookaheadInfo == null)
				&& (_this.userEnteredCode == null) && (_this.userInfo == null)
				&& (_this.ucid == null) && (_this.callOriginatorInfo == null)
				&& (_this.asn_deviceHistory == null)) {
			return null;
		}
		return _this;
	}

	public void decodeMembers(InputStream memberStream) {
		super.decodeMembers(memberStream);
		this.asn_deviceHistory = CSTADeviceHistoryData.decode(memberStream);
	}

	public void encodeMembers(OutputStream memberStream) {
		super.encodeMembers(memberStream);
		CSTADeviceHistoryData.encode(this.asn_deviceHistory, memberStream);
	}

	public static Collection<String> print(LucentV7OriginalCallInfo _this,
			String name, String _indent) {
		Collection<String> lines = new ArrayList<String>();

		if (_this == null) {
			lines.add(_indent + name + " <null>");
			return lines;
		}
		if (name != null) {
			lines.add(_indent + name);
		}
		lines.add(_indent + "{");

		String indent = _indent + "  ";

		lines.addAll(ReasonForCallInfo.print(_this.reason, "reason", indent));
		lines.addAll(CSTAExtendedDeviceID.print(_this.callingDevice_asn,
				"callingDevice", indent));
		lines.addAll(CSTAExtendedDeviceID.print(_this.calledDevice_asn,
				"calledDevice", indent));
		lines.addAll(DeviceID.print(_this.trunkGroup, "trunkGroup", indent));
		lines.addAll(DeviceID.print(_this.trunkMember, "trunkMember", indent));
		lines.addAll(LucentV5LookaheadInfo.print(
				(LucentV5LookaheadInfo) _this.lookaheadInfo, "lookaheadInfo",
				indent));
		lines.addAll(LucentUserEnteredCode.print(_this.userEnteredCode,
				"userEnteredCode", indent));
		lines.addAll(LucentUserToUserInfo.print(_this.userInfo, "userInfo",
				indent));
		lines.addAll(UCID.print(_this.ucid, "ucid", indent));
		lines.addAll(CSTACallOriginatorInfo.print(_this.callOriginatorInfo,
				"callOriginatorInfo", indent));
		lines.addAll(ASNBoolean.print(_this.flexibleBilling, "flexibleBilling",
				indent));
		lines.addAll(CSTADeviceHistoryData.print(_this.asn_deviceHistory,
				"deviceHistoryEntry", indent));

		lines.add(_indent + "}");
		return lines;
	}
}