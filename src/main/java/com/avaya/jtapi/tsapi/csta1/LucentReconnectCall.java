package com.avaya.jtapi.tsapi.csta1;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;

public class LucentReconnectCall extends LucentPrivateData {
	short dropResource;
	LucentUserToUserInfo userInfo;
	static final int PDU = 7;

	LucentReconnectCall() {
	}

	LucentReconnectCall(short _dropResource, LucentUserToUserInfo _userInfo) {
		this.dropResource = _dropResource;
		this.userInfo = _userInfo;
	}

	public static LucentReconnectCall decode(InputStream in) {
		LucentReconnectCall _this = new LucentReconnectCall();
		_this.doDecode(in);

		return _this;
	}

	public void decodeMembers(InputStream memberStream) {
		this.dropResource = LucentDropResource.decode(memberStream);
		this.userInfo = LucentUserToUserInfo.decode(memberStream);
	}

	public void encodeMembers(OutputStream memberStream) {
		LucentDropResource.encode(this.dropResource, memberStream);
		LucentUserToUserInfo.encode(this.userInfo, memberStream);
	}

	public Collection<String> print() {
		Collection<String> lines = new ArrayList<String>();

		lines.add("LucentReconnectCall ::=");
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
		return 7;
	}
}