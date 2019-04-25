package com.avaya.jtapi.tsapi.csta1;

import com.avaya.jtapi.tsapi.asn1.ASNEnumerated;
import java.util.Collection;

public final class LucentLocalCallState extends ASNEnumerated {
	static final short ATT_CS_INITIATED = 1;
	static final short ATT_CS_ALERTING = 2;
	static final short ATT_CS_CONNECTED = 3;
	static final short ATT_CS_HELD = 4;
	static final short ATT_CS_BRIDGED = 5;
	static final short ATT_CS_OTHER = 6;

	static Collection<String> print(short value, String name, String indent) {
		String str;
		switch (value) {
		case 1:
			str = "ATT_CS_INITIATED";
			break;
		case 2:
			str = "ATT_CS_ALERTING";
			break;
		case 3:
			str = "ATT_CS_CONNECTED";
			break;
		case 4:
			str = "ATT_CS_HELD";
			break;
		case 5:
			str = "ATT_CS_BRIDGED";
			break;
		case 6:
			str = "ATT_CS_OTHER";
			break;
		default:
			str = "?? " + value + " ??";
		}

		return print(value, str, name, indent);
	}
}