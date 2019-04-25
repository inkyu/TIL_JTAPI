package com.avaya.jtapi.tsapi.impl;

import com.avaya.jtapi.tsapi.LucentV7CallInfo;
import com.avaya.jtapi.tsapi.LucentV7RouteSession;
import com.avaya.jtapi.tsapi.impl.core.TSRouteSession;
import com.avaya.jtapi.tsapi.util.TsapiTrace;

final class LucentV7RouteSessionImpl extends LucentRouteSessionImpl implements
		LucentV7RouteSession, LucentV7CallInfo {
	public boolean equals(Object obj) {
		if ((obj instanceof LucentV7RouteSessionImpl)) {
			return this.tsRouteSession
					.equals(((LucentV7RouteSessionImpl) obj).tsRouteSession);
		}

		return false;
	}

	LucentV7RouteSessionImpl(TSRouteSession _tsRouteSession) {
		super(_tsRouteSession);
		TsapiTrace.traceConstruction(this, LucentV7RouteSessionImpl.class);
	}

	protected void finalize() throws Throwable {
		super.finalize();
		TsapiTrace.traceDestruction(this, LucentV7RouteSessionImpl.class);
	}
}