package com.avaya.jtapi.tsapi.csta1;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;

public final class LucentV6ClearConnection extends LucentClearConnection {
	static final int PDU = 108;

	public LucentV6ClearConnection() {
	}

	public LucentV6ClearConnection(short _dropResource,
			LucentUserToUserInfo _userInfo) {
		super(_dropResource, _userInfo);
	}

	public static LucentClearConnection decode(InputStream in) {
		LucentV6ClearConnection _this = new LucentV6ClearConnection();
		_this.doDecode(in);

		return _this;
	}

	public void decodeMembers(InputStream memberStream) {
		super.decodeMembers(memberStream);
	}

	public void encodeMembers(OutputStream memberStream) {
		super.encodeMembers(memberStream);
	}

	public Collection<String> print() {
		Collection<String> lines = new ArrayList<String>();

		lines.add("LucentV6ClearConnection ::=");
		lines.add("{");

		String indent = "  ";

		lines.addAll(LucentDropResource.print(this.dropResource,
				"dropResource", indent));
		lines.addAll(LucentUserToUserInfo.print(this.userInfo, "userInfo",
				indent));

		lines.add("}");
		return lines;
	}

	public int getPDU() {
		return 108;
	}
}