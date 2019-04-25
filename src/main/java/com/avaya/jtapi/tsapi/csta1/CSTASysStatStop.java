package com.avaya.jtapi.tsapi.csta1;

import com.avaya.jtapi.tsapi.asn1.ASNNull;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;

public class CSTASysStatStop extends CSTARequest {
	public static final int PDU = 102;

	public int getPDU() {
		return 102;
	}

	public static CSTASysStatStop decode(InputStream in) {
		CSTASysStatStop _this = new CSTASysStatStop();
		_this.doDecode(in);

		return _this;
	}

	public void encodeMembers(OutputStream memberStream) {
		ASNNull.encode(memberStream);
	}

	public void decodeMembers(InputStream memberStream) {
		ASNNull.decode(memberStream);
	}

	public Collection<String> print() {
		Collection<String> lines = new ArrayList<String>();
		lines.add("CSTASysStatStop ::=");
		lines.add("{");

		String indent = "  ";

		lines.addAll(ASNNull.print(indent));

		lines.add("}");
		return lines;
	}
}