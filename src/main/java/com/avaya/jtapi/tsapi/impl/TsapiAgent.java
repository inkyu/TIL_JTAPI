package com.avaya.jtapi.tsapi.impl;

import com.avaya.jtapi.tsapi.ITsapiAgent;
import com.avaya.jtapi.tsapi.LucentAgentStateInfoEx;
import com.avaya.jtapi.tsapi.TsapiInvalidArgumentException;
import com.avaya.jtapi.tsapi.TsapiInvalidStateException;
import com.avaya.jtapi.tsapi.TsapiPlatformException;
import com.avaya.jtapi.tsapi.impl.core.TSAgent;
import com.avaya.jtapi.tsapi.impl.core.TSDevice;
import com.avaya.jtapi.tsapi.util.TsapiTrace;
import javax.telephony.Address;
import javax.telephony.callcenter.ACDAddress;
import javax.telephony.callcenter.AgentTerminal;

class TsapiAgent implements ITsapiAgent {
	TSAgent tsAgent;

	public final void setState(int state) throws TsapiInvalidArgumentException,
			TsapiInvalidStateException {
		TsapiTrace.traceEntry("setState[int state]", this);
		setState(state, 0);
		TsapiTrace.traceExit("setState[int state]", this);
	}

	public void setState(int state, int workMode)
			throws TsapiInvalidArgumentException, TsapiInvalidStateException {
		TsapiTrace.traceEntry("setState[int state, int workMode]", this);
		setState(state, workMode, 0);
		TsapiTrace.traceExit("setState[int state, int workMode]", this);
	}

	public void setState(int state, int workMode, int reasonCode)
			throws TsapiInvalidArgumentException, TsapiInvalidStateException {
		TsapiTrace.traceEntry(
				"setState[int state, int workMode, int reasonCode]", this);
		setState(state, workMode, reasonCode, false);
		TsapiTrace.traceExit(
				"setState[int state, int workMode, int reasonCode]", this);
	}

	public boolean setState(int state, int workMode, int reasonCode,
			boolean enablePending) throws TsapiInvalidArgumentException,
			TsapiInvalidStateException {
		TsapiTrace
				.traceEntry(
						"setState[int state, int workMode, int reasonCode, boolean enablePending]",
						this);
		if ((state != 4) && (state != 3) && (state != 6) && (state != 5)) {
			throw new TsapiInvalidArgumentException(
					3,
					0,
					"state '"
							+ state
							+ "' not valid. Valid states are Agent.READY, Agent.NOT_READY"
							+ ", Agent.WORK_READY and Agent.WORK_NOT_READY");
		}

		if ((workMode != 0) && (workMode != 1) && (workMode != 2)) {
			throw new TsapiInvalidArgumentException(
					3,
					0,
					"workMode '"
							+ workMode
							+ "' not valid. Valid work modes are LucentAgent.MODE_NONE, LucentAgent.MODE_AUTO_IN and "
							+ "LucentAgent.MODE_MANUAL_IN");
		}

		if (this.tsAgent.getTSProviderImpl().isLucentV7()) {
			if ((reasonCode < 0) || (reasonCode > 99)) {
				throw new TsapiInvalidArgumentException(3, 0,
						"reasonCode not valid - must be in range 0-99 ["
								+ reasonCode + "]");
			}

		} else if ((reasonCode < 0) || (reasonCode > 9)) {
			throw new TsapiInvalidArgumentException(3, 0,
					"reasonCode not valid - must be in range 0-9 ["
							+ reasonCode + "]");
		}

		boolean done = this.tsAgent.setState(state, workMode, reasonCode,
				enablePending);
		TsapiTrace
				.traceExit(
						"setState[int state, int workMode, int reasonCode, boolean enablePending]",
						this);
		return done;
	}

	public final int getState() {
		TsapiTrace.traceEntry("getState[]", this);
		int state = this.tsAgent.getState();
		TsapiTrace.traceExit("getState[]", this);
		return state;
	}

	public final LucentAgentStateInfoEx getStateInfo() {
		TsapiTrace.traceEntry("getStateInfo[]", this);
		LucentAgentStateInfoEx state = this.tsAgent.getStateInfo();
		TsapiTrace.traceExit("getStateInfo[]", this);
		return state;
	}

	public final String getAgentID() {
		TsapiTrace.traceEntry("getAgentID[]", this);
		String id = this.tsAgent.getAgentID();
		TsapiTrace.traceExit("getAgentID[]", this);
		return id;
	}

	public final ACDAddress getACDAddress() {
		TsapiTrace.traceEntry("getACDAddress[]", this);
		TSDevice tsDevice = this.tsAgent.getTSACDDevice();
		if (tsDevice != null) {
			ACDAddress addr = (ACDAddress) TsapiCreateObject.getTsapiObject(
					tsDevice, true);
			TsapiTrace.traceExit("getACDAddress[]", this);
			return addr;
		}

		TsapiTrace.traceExit("getACDAddress[]", this);
		return null;
	}

	public final Address getAgentAddress() {
		TsapiTrace.traceEntry("getAgentAddress[]", this);
		TSDevice tsDevice = this.tsAgent.getTSAgentDevice();
		if (tsDevice != null) {
			Address addr = (Address) TsapiCreateObject.getTsapiObject(tsDevice,
					true);
			TsapiTrace.traceExit("getAgentAddress[]", this);
			return addr;
		}

		throw new TsapiPlatformException(4, 0, "could not locate address");
	}

	public final AgentTerminal getAgentTerminal() {
		TsapiTrace.traceEntry("getAgentTerminal[]", this);
		TSDevice tsDevice = this.tsAgent.getTSAgentDevice();
		if (tsDevice != null) {
			AgentTerminal term = (AgentTerminal) TsapiCreateObject
					.getTsapiObject(tsDevice, false);
			TsapiTrace.traceExit("getAgentTerminal[]", this);
			return term;
		}

		throw new TsapiPlatformException(4, 0, "could not locate terminal");
	}

	final TSAgent getTSAgent() {
		TsapiTrace.traceEntry("getTSAgent[]", this);
		TsapiTrace.traceExit("getTSAgent[]", this);
		return this.tsAgent;
	}

	public final int hashCode() {
		return this.tsAgent.hashCode();
	}

	public boolean equals(Object obj) {
		if ((obj instanceof TsapiAgent)) {
			return this.tsAgent.equals(((TsapiAgent) obj).tsAgent);
		}

		return false;
	}

	TsapiAgent(TSAgent _tsAgent) {
		this.tsAgent = _tsAgent;
		if (this.tsAgent != null) {
			this.tsAgent.referenced();
		}
		TsapiTrace.traceConstruction(this, TsapiAgent.class);
	}

	protected void finalize() throws Throwable {
		try {
			if (this.tsAgent != null) {
				this.tsAgent.unreferenced();
				this.tsAgent = null;
			}
		} finally {
			super.finalize();
		}
		TsapiTrace.traceDestruction(this, TsapiAgent.class);
	}
}