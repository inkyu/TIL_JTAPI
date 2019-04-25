package com.avaya.jtapi.tsapi.csta1;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;

public final class CSTARouteRegisterCancelConfEvent extends CSTAConfirmation {
	int routeRegisterReqID;
	public static final int PDU = 81;

	public static CSTARouteRegisterCancelConfEvent decode(InputStream in) {
		CSTARouteRegisterCancelConfEvent _this = new CSTARouteRegisterCancelConfEvent();
		_this.doDecode(in);

		return _this;
	}

	public void decodeMembers(InputStream memberStream) {
		this.routeRegisterReqID = RouteRegisterReqID.decode(memberStream);
	}

	public void encodeMembers(OutputStream memberStream) {
		RouteRegisterReqID.encode(this.routeRegisterReqID, memberStream);
	}

	public Collection<String> print() {
		Collection<String> lines = new ArrayList<String>();

		lines.add("CSTARouteRegisterCancelConfEvent ::=");
		lines.add("{");

		String indent = "  ";

		lines.addAll(RouteRegisterReqID.print(this.routeRegisterReqID,
				"routeRegisterReqID", indent));

		lines.add("}");
		return lines;
	}

	public int getPDU() {
		return 81;
	}

	public int getRouteRegisterReqID() {
		return this.routeRegisterReqID;
	}

	public void setRouteRegisterReqID(int routeRegisterReqID) {
		this.routeRegisterReqID = routeRegisterReqID;
	}
}