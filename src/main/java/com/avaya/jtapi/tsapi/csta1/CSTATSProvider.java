package com.avaya.jtapi.tsapi.csta1;

public final class CSTATSProvider {
	private static long handleCSTAEventTimeThreshold = Integer.parseInt("250");

	public static void setHandleCSTAEventTimeThreshold(
			long handleCSTAEventTimeThreshold) {
		CSTATSProvider.handleCSTAEventTimeThreshold = handleCSTAEventTimeThreshold;
	}

	public static long getHandleCSTAEventTimeThreshold() {
		return handleCSTAEventTimeThreshold;
	}
}