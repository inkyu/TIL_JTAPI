package com.avaya.jtapi.tsapi.csta1;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;

public class LucentFailedEvent extends LucentPrivateData {
	LucentDeviceHistoryEntry[] deviceHistory;
	static final int PDU = 137;

	LucentFailedEvent() {
	}

	LucentFailedEvent(LucentDeviceHistoryEntry[] _deviceHistory) {
		this.deviceHistory = _deviceHistory;
	}

	static LucentFailedEvent decode(InputStream in) {
		LucentFailedEvent _this = new LucentFailedEvent();
		_this.doDecode(in);

		return _this;
	}

	public void decodeMembers(InputStream memberStream) {
		this.deviceHistory = CSTADeviceHistoryData.decode(memberStream);
	}

	public void encodeMembers(OutputStream memberStream) {
		CSTADeviceHistoryData.encode(this.deviceHistory, memberStream);
	}

	public Collection<String> print() {
		Collection<String> lines = new ArrayList<String>();

		lines.add("LucentFailedEvent ::=");
		lines.add("{");

		String indent = "  ";

		lines.addAll(CSTADeviceHistoryData.print(this.deviceHistory,
				"deviceHistory", indent));

		lines.add("}");
		return lines;
	}

	public int getPDU() {
		return 137;
	}

	public LucentDeviceHistoryEntry[] getDeviceHistory() {
		return this.deviceHistory;
	}

	public void setDeviceHistory(LucentDeviceHistoryEntry[] entry) {
		this.deviceHistory = entry;
	}
}