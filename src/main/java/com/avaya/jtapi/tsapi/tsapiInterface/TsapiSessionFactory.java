package com.avaya.jtapi.tsapi.tsapiInterface;

import com.avaya.jtapi.tsapi.TsapiPlatformException;
import com.avaya.jtapi.tsapi.acs.ACSNameAddr;
import com.avaya.jtapi.tsapi.acs.ACSNameSrvReply;
import com.avaya.jtapi.tsapi.acs.ACSNameSrvRequest;
import com.avaya.jtapi.tsapi.asn1.TsapiRequest;
import com.avaya.jtapi.tsapi.csta1.CSTAEvent;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Vector;
import org.apache.log4j.Logger;

public abstract class TsapiSessionFactory {
	private static Logger log = Logger.getLogger(TsapiSessionFactory.class);
	public static final String FACTORY_KEY = "com.avaya.jtapi.tsapi.tsapiInterface.TsapiSessionFactory.sessionFactory";

	public static TsapiSessionFactory getTsapiSessionFactory(Properties props) {
		String className = "com.avaya.jtapi.tsapi.tsapiInterface.oio.TsapiSessionFactoryOio";

		if ((props != null)
				&& (props
						.get("com.avaya.jtapi.tsapi.tsapiInterface.TsapiSessionFactory.sessionFactory") != null)) {
			className = (String) props
					.get("com.avaya.jtapi.tsapi.tsapiInterface.TsapiSessionFactory.sessionFactory");
		}
		TsapiSessionFactory factory = null;
		try {
			Class<?> theClass = Class.forName(className);
			factory = (TsapiSessionFactory) theClass.newInstance();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("class not found", e);
		} catch (InstantiationException e) {
			throw new RuntimeException("Could not instantiate", e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException("Could not access", e);
		}

		factory.configure(props);

		return factory;
	}

	protected abstract void configure(Properties paramProperties);

	public abstract TsapiSession getTsapiSession(
			InetSocketAddress paramInetSocketAddress) throws IOException;

	public abstract TsapiSession getTsapiSession(
			InetSocketAddress paramInetSocketAddress, String paramString)
			throws IOException;

	public abstract TsapiSession getLightweightTsapiSession(
			InetSocketAddress paramInetSocketAddress) throws IOException;

	public abstract void setDebugID(String paramString);

	public Vector<ACSNameAddr> enumServices(Vector<InetSocketAddress> servers,
			boolean useTLinkIP) {
		return enumServices(servers, useTLinkIP, Tsapi.getGetServicesTimeout());
	}

	public Vector<ACSNameAddr> enumServices(Vector<InetSocketAddress> servers,
			boolean useTLinkIP, int timeout) {
		Enumeration<InetSocketAddress> eserv = servers.elements();
		Vector<ACSNameAddr> services = new Vector<ACSNameAddr>();
		boolean isIpv4 = false;
		while (eserv.hasMoreElements()) {
			InetSocketAddress addr;
			try {
				addr = (InetSocketAddress) eserv.nextElement();
				TsapiSession session = null;
				try {
					session = getLightweightTsapiSession(addr);
					
					TsapiRequest req = new ACSNameSrvRequest((short) 1);
					
					byte[] inetAddr = addr.getAddress().getAddress();
					if ((addr.getAddress() instanceof Inet4Address)) {
						isIpv4 = true;
					}
					ACSNameSrvReply reply;
					do {
						CSTAEvent event = session.send(req, null, timeout);
						
						if (!(event.getEvent() instanceof ACSNameSrvReply)) {
							log.info("unexpected reply from name server <" + addr
									+ ">");
							throw new TsapiPlatformException(4, 0,
									"unexpected reply from name server");
						}
						
						reply = (ACSNameSrvReply) event.getEvent();
						ACSNameAddr[] replyList = reply.getList();
						log.debug("Listing services available on server <" + addr
								+ ">");
						for (int i = 0; i < replyList.length; i++) {
							byte[] serverAddr = replyList[i].getServerAddr();
							if (!useTLinkIP) {
								if (isIpv4) {
									serverAddr[4] = inetAddr[0];
									serverAddr[5] = inetAddr[1];
									serverAddr[6] = inetAddr[2];
									serverAddr[7] = inetAddr[3];
								} else {
									System.arraycopy(inetAddr, 0, serverAddr, 8, 16);
								}
							}
							log.debug("SERVICE[" + i + "]: "
									+ replyList[i].getServerName());
							services.addElement(replyList[i]);
						}
					} while (reply.isMore());
				} catch (Exception e) {
					log.error("enumServices() <" + addr + ">: " + e);
				} finally {
					if (session != null) {
						session.close();
					}
				}
			} catch (NoSuchElementException e) {
				log.error(e.getMessage(), e);
			}
			continue;

		}
		if (services.size() == 0) {
			log.warn("No valid telephony servers found");
		}
		return services;
	}

	public ACSNameAddr findTlink(String tlink,
			Vector<InetSocketAddress> servers, boolean useTLinkIP) {
		TsapiAlternateTlinkEntriesList alternateTlinkEntriesList = TsapiAlternateTlinkEntriesList
				.Instance();

		boolean foundAlternate = false;
		int alternateIndex = -1;
		ACSNameAddr alternateACSNameAddr = new ACSNameAddr();

		Enumeration<?> services = enumServices(servers, useTLinkIP).elements();
		while (services.hasMoreElements()) {
			ACSNameAddr nameAddr;
			try {
				nameAddr = (ACSNameAddr) services.nextElement();
				if (nameAddr.getServerName().equalsIgnoreCase(tlink)) {
					return nameAddr;
				}
				
				int index = alternateTlinkEntriesList.getAlternateTlinkIndex(tlink,
						nameAddr.getServerName());
				
				if (index >= 0) {
					if ((!foundAlternate) || (index < alternateIndex)) {
						foundAlternate = true;
						alternateIndex = index;
						alternateACSNameAddr = nameAddr;
					}
				}
			} catch (NoSuchElementException e) {
				log.error(e.getMessage(), e);
			}
			continue;

		}

		if (foundAlternate == true) {
			return alternateACSNameAddr;
		}

		throw new TsapiPlatformException(4, 0, "server "
				+ new ArrayList<InetSocketAddress>(servers) + " with tlink '" + tlink
				+ "' not found");
	}
}