package javax.telephony;

import javax.telephony.capabilities.ConnectionCapabilities;

public abstract interface Connection {
	public static final int IDLE = 48;
	public static final int INPROGRESS = 49;
	public static final int ALERTING = 50;
	public static final int CONNECTED = 51;
	public static final int DISCONNECTED = 52;
	public static final int FAILED = 53;
	public static final int UNKNOWN = 54;

	public abstract int getState();

	public abstract Call getCall();

	public abstract Address getAddress();

	public abstract TerminalConnection[] getTerminalConnections();

	public abstract void disconnect() throws PrivilegeViolationException,
			ResourceUnavailableException, MethodNotSupportedException,
			InvalidStateException;

	public abstract ConnectionCapabilities getCapabilities();

	/** @deprecated */
	public abstract ConnectionCapabilities getConnectionCapabilities(
			Terminal paramTerminal, Address paramAddress)
			throws InvalidArgumentException, PlatformException;
}