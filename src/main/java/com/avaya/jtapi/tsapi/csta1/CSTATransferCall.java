package com.avaya.jtapi.tsapi.csta1;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;

public final class CSTATransferCall extends CSTARequest {
	CSTAConnectionID heldCall;
	CSTAConnectionID activeCall;
	public static final int PDU = 51;

	public CSTATransferCall() {
	}

	public CSTATransferCall(CSTAConnectionID _heldCall,
			CSTAConnectionID _activeCall) {
		this.heldCall = _heldCall;
		this.activeCall = _activeCall;
	}

	public void encodeMembers(OutputStream memberStream) {
		CSTAConnectionID.encode(this.heldCall, memberStream);
		CSTAConnectionID.encode(this.activeCall, memberStream);
	}

	public static CSTATransferCall decode(InputStream in) {
		CSTATransferCall _this = new CSTATransferCall();
		_this.doDecode(in);

		return _this;
	}

	public void decodeMembers(InputStream memberStream) {
		this.heldCall = CSTAConnectionID.decode(memberStream);
		this.activeCall = CSTAConnectionID.decode(memberStream);
	}

	public Collection<String> print() {
		Collection<String> lines = new ArrayList<String>();

		lines.add("CSTATransferCall ::=");
		lines.add("{");

		String indent = "  ";

		lines.addAll(CSTAConnectionID.print(this.heldCall, "heldCall", indent));
		lines.addAll(CSTAConnectionID.print(this.activeCall, "activeCall",
				indent));

		lines.add("}");
		return lines;
	}

	public int getPDU() {
		return 51;
	}

	public CSTAConnectionID getActiveCall() {
		return this.activeCall;
	}

	public CSTAConnectionID getHeldCall() {
		return this.heldCall;
	}
}