package com.avaya.jtapi.tsapi.csta1;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;

public final class CSTAQueryDnd extends CSTARequest {
	String device;
	public static final int PDU = 29;

	public CSTAQueryDnd() {
	}

	public CSTAQueryDnd(String _device) {
		this.device = _device;
	}

	public static CSTAQueryDnd decode(InputStream in) {
		CSTAQueryDnd _this = new CSTAQueryDnd();
		_this.doDecode(in);

		return _this;
	}

	public void decodeMembers(InputStream memberStream) {
		this.device = DeviceID.decode(memberStream);
	}

	public void encodeMembers(OutputStream memberStream) {
		DeviceID.encode(this.device, memberStream);
	}

	public Collection<String> print() {
		Collection<String> lines = new ArrayList<String>();

		lines.add("CSTAQueryDnd ::=");
		lines.add("{");

		String indent = "  ";

		lines.addAll(DeviceID.print(this.device, "device", indent));

		lines.add("}");
		return lines;
	}

	public int getPDU() {
		return 29;
	}

	public String getDevice() {
		return this.device;
	}
}