package com.avaya.jtapi.tsapi.acs;

import com.avaya.jtapi.tsapi.asn1.TsapiRequest;

public abstract class ACSConfirmation extends TsapiRequest {
	public int getPDUClass() {
		return 2;
	}
}