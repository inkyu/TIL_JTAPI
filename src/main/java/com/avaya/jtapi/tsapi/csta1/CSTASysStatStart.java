package com.avaya.jtapi.tsapi.csta1;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;

public class CSTASysStatStart extends CSTARequest {
	private int statusFilter;
	public static final int PDU = 100;

	public CSTASysStatStart() {
		this.statusFilter = 0;
	}

	public CSTASysStatStart(int filter) {
		this.statusFilter = filter;
	}

	public int getPDU() {
		return 100;
	}

	public void encodeMembers(OutputStream memberStream) {
		SystemStatusFilter.encode(this.statusFilter, memberStream);
	}

	public static CSTASysStatStart decode(InputStream in) {
		CSTASysStatStart _this = new CSTASysStatStart();
		_this.doDecode(in);
		return _this;
	}

	public void decodeMembers(InputStream memberStream) {
		this.statusFilter = SystemStatusFilter.decode(memberStream);
	}

	public Collection<String> print() {
		Collection<String> lines = new ArrayList<String>();
		lines.add("CSTASysStatStart ::=");
		lines.add("{");

		String indent = "  ";

		lines.addAll(SystemStatusFilter.print(this.statusFilter,
				"statusFilter", indent));

		lines.add("}");
		return lines;
	}
}