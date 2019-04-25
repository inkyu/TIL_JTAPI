package com.avaya.jtapi.tsapi.csta1;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;

public final class CSTAConsultationCallConfEvent extends CSTAConfirmation {
	CSTAConnectionID newCall;
	public static final int PDU = 14;

	public CSTAConsultationCallConfEvent() {
	}

	public CSTAConsultationCallConfEvent(CSTAConnectionID _newCall) {
		this.newCall = _newCall;
	}

	public void encodeMembers(OutputStream memberStream) {
		CSTAConnectionID.encode(this.newCall, memberStream);
	}

	public static CSTAConsultationCallConfEvent decode(InputStream in) {
		CSTAConsultationCallConfEvent _this = new CSTAConsultationCallConfEvent();
		_this.doDecode(in);

		return _this;
	}

	public void decodeMembers(InputStream memberStream) {
		this.newCall = CSTAConnectionID.decode(memberStream);
	}

	public Collection<String> print() {
		Collection<String> lines = new ArrayList<String>();
		lines.add("CSTAConsultationCallConfEvent ::=");
		lines.add("{");

		String indent = "  ";

		lines.addAll(CSTAConnectionID.print(this.newCall, "newCall", indent));

		lines.add("}");
		return lines;
	}

	public int getPDU() {
		return 14;
	}

	public CSTAConnectionID getNewCall() {
		return this.newCall;
	}

	public void setNewCall(CSTAConnectionID newCall) {
		this.newCall = newCall;
	}
}