package com.avaya.jtapi.tsapi.csta1;

import com.avaya.jtapi.tsapi.asn1.ASNEnumerated;
import java.util.Collection;

public final class LucentTalkState extends ASNEnumerated {
	public static final short TS_ON_CALL = 0;
	public static final short TS_IDLE = 1;

	static Collection<String> print(short value, String name, String indent) {
		String str;
		switch (value) {
		case 0:
			str = "TS_ON_CALL";
			break;
		case 1:
			str = "TS_IDLE";
			break;
		default:
			str = "?? " + value + " ??";
		}

		return print(value, str, name, indent);
	}
}