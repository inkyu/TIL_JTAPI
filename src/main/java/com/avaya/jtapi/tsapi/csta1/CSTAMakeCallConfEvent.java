package com.avaya.jtapi.tsapi.csta1;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;

public final class CSTAMakeCallConfEvent extends CSTAConfirmation {
	CSTAConnectionID newCall;
	public static final int PDU = 24;

	public CSTAMakeCallConfEvent() {
	}

	public CSTAMakeCallConfEvent(CSTAConnectionID _newCall) {
		this.newCall = _newCall;
	}

	public void encodeMembers(OutputStream memberStream) {
		CSTAConnectionID.encode(this.newCall, memberStream);
	}

	public static CSTAMakeCallConfEvent decode(InputStream in) {
		CSTAMakeCallConfEvent _this = new CSTAMakeCallConfEvent();
		_this.doDecode(in);

		return _this;
	}

	public void decodeMembers(InputStream memberStream) {
		this.newCall = CSTAConnectionID.decode(memberStream);
	}

	public Collection<String> print() {
		Collection<String> lines = new ArrayList<String>();
		lines.add("CSTAMakeCallConfEvent ::=");
		lines.add("{");

		String indent = "  ";

		lines.addAll(CSTAConnectionID.print(this.newCall, "newCall", indent));

		lines.add("}");
		return lines;
	}

	public int getPDU() {
		return 24;
	}

	public CSTAConnectionID getNewCall() {
		return this.newCall;
	}
}