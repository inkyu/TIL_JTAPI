package com.avaya.jtapi.tsapi.csta1;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;

public final class LucentPrivateRouteUsedEvent extends LucentPrivateData {
	String destRoute_asn;
	public static final int PDU = 44;

	static LucentPrivateRouteUsedEvent decode(InputStream in) {
		LucentPrivateRouteUsedEvent _this = new LucentPrivateRouteUsedEvent();
		_this.doDecode(in);

		return _this;
	}

	public void decodeMembers(InputStream memberStream) {
		this.destRoute_asn = DeviceID.decode(memberStream);
	}

	public void encodeMembers(OutputStream memberStream) {
		DeviceID.encode(this.destRoute_asn, memberStream);
	}

	public Collection<String> print() {
		Collection<String> lines = new ArrayList<String>();
		lines.add("LucentPrivateRouteUsedEvent ::=");
		lines.add("{");

		String indent = "  ";

		lines.addAll(DeviceID.print(this.destRoute_asn, "destRoute", indent));

		lines.add("}");
		return lines;
	}

	public int getPDU() {
		return 44;
	}

	public String getDestRoute_asn() {
		return this.destRoute_asn;
	}

	public void setDestRoute_asn(String destRoute_asn) {
		this.destRoute_asn = destRoute_asn;
	}
}