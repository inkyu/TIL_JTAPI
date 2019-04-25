package com.avaya.jtapi.tsapi.csta1;

import com.avaya.jtapi.tsapi.asn1.ASNEnumerated;
import java.util.Collection;

public final class Priority extends ASNEnumerated {
	public static final short LAI_NOT_IN_QUEUE = 0;
	public static final short LAI_LOW = 1;
	public static final short LAI_MEDIUM = 2;
	public static final short LAI_HIGH = 3;
	public static final short LAI_TOP = 4;

	static Collection<String> print(short value, String name, String indent) {
		String str;
		switch (value) {
		case 0:
			str = "LAI_NOT_IN_QUEUE";
			break;
		case 1:
			str = "LAI_LOW";
			break;
		case 2:
			str = "LAI_MEDIUM";
			break;
		case 3:
			str = "LAI_HIGH";
			break;
		case 4:
			str = "LAI_TOP";
			break;
		default:
			str = "?? " + value + " ??";
		}

		return print(value, str, name, indent);
	}
}