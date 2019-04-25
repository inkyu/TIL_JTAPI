package com.avaya.jtapi.tsapi.impl;

import com.avaya.jtapi.tsapi.LucentCallEx2;
import com.avaya.jtapi.tsapi.csta1.CSTAConnectionID;
import com.avaya.jtapi.tsapi.impl.core.TSCall;
import com.avaya.jtapi.tsapi.impl.core.TSProviderImpl;
import com.avaya.jtapi.tsapi.util.TsapiTrace;

class LucentCallEx2Impl extends LucentCallExImpl implements LucentCallEx2 {
	public boolean equals(Object obj) {
		if ((obj instanceof LucentCallEx2Impl)) {
			this.tsCall = this.tsCall.getHandOff();
			return this.tsCall.equals(((LucentCallEx2Impl) obj).tsCall);
		}

		return false;
	}

	LucentCallEx2Impl(LucentProviderImpl _provider) {
		super(_provider, 0);
		TsapiTrace.traceConstruction(this, LucentCallEx2Impl.class);
	}

	LucentCallEx2Impl(LucentProviderImpl _provider, CSTAConnectionID connID) {
		super(_provider, connID);
		TsapiTrace.traceConstruction(this, LucentCallEx2Impl.class);
	}

	LucentCallEx2Impl(LucentProviderImpl _provider, int callID) {
		super(_provider, callID);
		TsapiTrace.traceConstruction(this, LucentCallEx2Impl.class);
	}

	LucentCallEx2Impl(TSProviderImpl _provider, CSTAConnectionID connID) {
		super(_provider, connID);
		TsapiTrace.traceConstruction(this, LucentCallEx2Impl.class);
	}

	LucentCallEx2Impl(TSCall _tscall) {
		super(_tscall);
		TsapiTrace.traceConstruction(this, LucentCallEx2Impl.class);
	}

	protected void finalize() throws Throwable {
		super.finalize();
		TsapiTrace.traceDestruction(this, LucentCallEx2Impl.class);
	}
}