package com.avaya.jtapi.tsapi.acs;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;

public final class ACSNameSrvRequest extends ACSRequest {
	short streamType;
	public static final int PDU = 10;

	public ACSNameSrvRequest(short _streamType) {
		this.streamType = _streamType;
	}

	public ACSNameSrvRequest() {
	}

	public void encodeMembers(OutputStream memberStream) {
		StreamType.encode(this.streamType, memberStream);
	}

	public static ACSNameSrvRequest decode(InputStream in) {
		ACSNameSrvRequest _this = new ACSNameSrvRequest();
		_this.doDecode(in);

		return _this;
	}

	public void decodeMembers(InputStream memberStream) {
		StreamType.decode(memberStream);
	}

	public Collection<String> print() {
		Collection<String> lines = new ArrayList<String>();
		lines.add("ACSNameSrvRequest ::=");
		lines.add("{");

		String indent = "  ";

		lines.addAll(StreamType.print(this.streamType, "streamType", indent));

		lines.add("}");
		return lines;
	}

	public int getPDU() {
		return 10;
	}
}