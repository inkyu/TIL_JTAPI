package javax.telephony;

public abstract interface Event {
	public static final int CAUSE_NORMAL = 100;
	public static final int CAUSE_UNKNOWN = 101;
	public static final int CAUSE_CALL_CANCELLED = 102;
	public static final int CAUSE_DEST_NOT_OBTAINABLE = 103;
	public static final int CAUSE_INCOMPATIBLE_DESTINATION = 104;
	public static final int CAUSE_LOCKOUT = 105;
	public static final int CAUSE_NEW_CALL = 106;
	public static final int CAUSE_RESOURCES_NOT_AVAILABLE = 107;
	public static final int CAUSE_NETWORK_CONGESTION = 108;
	public static final int CAUSE_NETWORK_NOT_OBTAINABLE = 109;
	public static final int CAUSE_SNAPSHOT = 110;

	public abstract int getCause();

	public abstract int getID();

	public abstract Object getSource();

	public abstract MetaEvent getMetaEvent();
}