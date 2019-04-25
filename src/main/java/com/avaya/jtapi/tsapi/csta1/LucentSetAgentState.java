package com.avaya.jtapi.tsapi.csta1;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;

public class LucentSetAgentState extends LucentPrivateData {
	short workMode;
	static final int PDU = 10;

	public LucentSetAgentState() {
	}

	public LucentSetAgentState(short _workMode) {
		this.workMode = _workMode;
	}

	public static LucentSetAgentState decode(InputStream in, CSTATSProvider prov) {
		LucentSetAgentState _this = new LucentSetAgentState();
		_this.doDecode(in);

		return _this;
	}

	public void decodeMembers(InputStream memberStream) {
		this.workMode = LucentWorkMode.decode(memberStream);
	}

	public void encodeMembers(OutputStream memberStream) {
		LucentWorkMode.encode(this.workMode, memberStream);
	}

	public Collection<String> print() {
		Collection<String> lines = new ArrayList<String>();

		lines.add("LucentSetAgentState ::=");
		lines.add("{");

		String indent = "  ";

		lines.addAll(LucentWorkMode.print(this.workMode, "workMode", indent));

		lines.add("}");
		return lines;
	}

	public int getPDU() {
		return 10;
	}
}