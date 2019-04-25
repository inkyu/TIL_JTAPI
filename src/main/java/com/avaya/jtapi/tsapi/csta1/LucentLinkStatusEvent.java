package com.avaya.jtapi.tsapi.csta1;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;

public class LucentLinkStatusEvent extends LucentPrivateData {
	private CSTALinkStatus linkStatus;
	static final int PDU = 73;

	public static LucentLinkStatusEvent decode(InputStream in) {
		LucentLinkStatusEvent _this = new LucentLinkStatusEvent();
		_this.doDecode(in);

		return _this;
	}

	public void decodeMembers(InputStream memberStream) {
		this.linkStatus = CSTALinkStatus.decode(memberStream);
	}

	public void encodeMembers(OutputStream memberStream) {
		CSTALinkStatus.encode(this.linkStatus, memberStream);
	}

	public Collection<String> print() {
		Collection<String> lines = new ArrayList<String>();

		lines.add("LucentLinkStatusEvent ::=");
		lines.add("{");

		String indent = "  ";
		lines.addAll(CSTALinkStatus
				.print(this.linkStatus, "linkStatus", indent));

		lines.add("}");
		return lines;
	}

	public int getPDU() {
		return 73;
	}

	public CSTALinkStatus getLinkStatus() {
		return this.linkStatus;
	}

	public void setLinkStatus(CSTALinkStatus linkStatus) {
		this.linkStatus = linkStatus;
	}
}