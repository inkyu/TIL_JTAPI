package javax.telephony;

public class JtapiPeerFactory {
	public static synchronized JtapiPeer getJtapiPeer(String jtapiPeerName)
			throws JtapiPeerUnavailableException {
		if ((jtapiPeerName == null) || (jtapiPeerName.length() == 0)) {
			jtapiPeerName = getDefaultJtapiPeerName();
		}

		if (jtapiPeerName == null) {
			throw new JtapiPeerUnavailableException();
		}
		try {
			Class<?> jtapiPeerClass = Class.forName(jtapiPeerName);

			return (JtapiPeer) jtapiPeerClass.newInstance();
		} catch (Exception e) {
			String errmsg = "JtapiPeer: " + jtapiPeerName
					+ " could not be instantiated.";

			throw new JtapiPeerUnavailableException(errmsg);
		}
	}

	private static String getDefaultJtapiPeerName() {
		String JtapiPeerName = "DefaultJtapiPeer";
		return JtapiPeerName;
	}
}