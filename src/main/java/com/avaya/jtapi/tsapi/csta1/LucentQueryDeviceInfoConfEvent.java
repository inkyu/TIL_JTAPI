package com.avaya.jtapi.tsapi.csta1;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;

public class LucentQueryDeviceInfoConfEvent extends LucentPrivateData {
	short extensionClass;
	static final int PDU = 20;

	public static LucentQueryDeviceInfoConfEvent decode(InputStream in) {
		LucentQueryDeviceInfoConfEvent _this = new LucentQueryDeviceInfoConfEvent();
		_this.doDecode(in);

		return _this;
	}

	public void decodeMembers(InputStream memberStream) {
		this.extensionClass = LucentExtensionClass.decode(memberStream);
	}

	public void encodeMembers(OutputStream memberStream) {
		LucentExtensionClass.encode(this.extensionClass, memberStream);
	}

	public Collection<String> print() {
		Collection<String> lines = new ArrayList<String>();

		lines.add("LucentQueryDeviceInfoConfEvent ::=");
		lines.add("{");

		String indent = "  ";

		lines.addAll(LucentExtensionClass.print(this.extensionClass,
				"extensionClass", indent));

		lines.add("}");
		return lines;
	}

	public int getPDU() {
		return 20;
	}

	public short getExtensionClass() {
		return this.extensionClass;
	}

	public void setExtensionClass(short extensionClass) {
		this.extensionClass = extensionClass;
	}
}