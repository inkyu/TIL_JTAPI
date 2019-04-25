package com.avaya.jtapi.tsapi.csta1;

import com.avaya.jtapi.tsapi.asn1.ASNNull;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;

public final class CSTAAlternateCallConfEvent extends CSTAConfirmation {
	public static final int PDU = 2;

	public static CSTAAlternateCallConfEvent decode(InputStream in) {
		CSTAAlternateCallConfEvent _this = new CSTAAlternateCallConfEvent();
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
		lines.add("CSTAAlternateCallConfEvent ::=");
		lines.add("{");

		String indent = "  ";

		lines.addAll(ASNNull.print(indent));

		lines.add("}");
		return lines;
	}

	public int getPDU() {
		return 2;
	}
}