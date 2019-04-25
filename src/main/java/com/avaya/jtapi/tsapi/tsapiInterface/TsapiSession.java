package com.avaya.jtapi.tsapi.tsapiInterface;

import com.avaya.jtapi.tsapi.TSProvider;
import com.avaya.jtapi.tsapi.TsapiInvalidArgumentException;
import com.avaya.jtapi.tsapi.TsapiInvalidPartyException;
import com.avaya.jtapi.tsapi.TsapiInvalidStateException;
import com.avaya.jtapi.tsapi.TsapiPlatformException;
import com.avaya.jtapi.tsapi.TsapiPrivilegeViolationException;
import com.avaya.jtapi.tsapi.TsapiProviderUnavailableException;
import com.avaya.jtapi.tsapi.TsapiResourceUnavailableException;
import com.avaya.jtapi.tsapi.TsapiSocketException;
import com.avaya.jtapi.tsapi.TsapiUnableToSendException;
import com.avaya.jtapi.tsapi.acs.ACSAbortStream;
import com.avaya.jtapi.tsapi.acs.ACSAuthReply;
import com.avaya.jtapi.tsapi.acs.ACSAuthReplyTwo;
import com.avaya.jtapi.tsapi.acs.ACSClientHeartbeatEvent;
import com.avaya.jtapi.tsapi.acs.ACSCloseStreamConfEvent;
import com.avaya.jtapi.tsapi.acs.ACSConfirmation;
import com.avaya.jtapi.tsapi.acs.ACSEventHeader;
import com.avaya.jtapi.tsapi.acs.ACSKeyReply;
import com.avaya.jtapi.tsapi.acs.ACSKeyRequest;
import com.avaya.jtapi.tsapi.acs.ACSNameSrvReply;
import com.avaya.jtapi.tsapi.acs.ACSOpenStream;
import com.avaya.jtapi.tsapi.acs.ACSOpenStreamConfEvent;
import com.avaya.jtapi.tsapi.acs.ACSRequestPrivilegesConfEvent;
import com.avaya.jtapi.tsapi.acs.ACSSetHeartbeatIntervalConfEvent;
import com.avaya.jtapi.tsapi.acs.ACSSetPrivilegesConfEvent;
import com.avaya.jtapi.tsapi.acs.ACSUniversalFailureConfEvent;
import com.avaya.jtapi.tsapi.acs.ACSUniversalFailureEvent;
import com.avaya.jtapi.tsapi.asn1.TsapiPDU;
import com.avaya.jtapi.tsapi.asn1.TsapiRequest;
import com.avaya.jtapi.tsapi.csta1.CSTAAlternateCallConfEvent;
import com.avaya.jtapi.tsapi.csta1.CSTAAnswerCallConfEvent;
import com.avaya.jtapi.tsapi.csta1.CSTACallClearedEvent;
import com.avaya.jtapi.tsapi.csta1.CSTACallCompletionConfEvent;
import com.avaya.jtapi.tsapi.csta1.CSTAClearCallConfEvent;
import com.avaya.jtapi.tsapi.csta1.CSTAClearConnectionConfEvent;
import com.avaya.jtapi.tsapi.csta1.CSTAConferenceCallConfEvent;
import com.avaya.jtapi.tsapi.csta1.CSTAConferencedEvent;
import com.avaya.jtapi.tsapi.csta1.CSTAConfirmation;
import com.avaya.jtapi.tsapi.csta1.CSTAConnectionClearedEvent;
import com.avaya.jtapi.tsapi.csta1.CSTAConsultationCallConfEvent;
import com.avaya.jtapi.tsapi.csta1.CSTADeflectCallConfEvent;
import com.avaya.jtapi.tsapi.csta1.CSTADeliveredEvent;
import com.avaya.jtapi.tsapi.csta1.CSTADivertedEvent;
import com.avaya.jtapi.tsapi.csta1.CSTADoNotDisturbEvent;
import com.avaya.jtapi.tsapi.csta1.CSTAEscapeSvcConfEvent;
import com.avaya.jtapi.tsapi.csta1.CSTAEstablishedEvent;
import com.avaya.jtapi.tsapi.csta1.CSTAEvent;
import com.avaya.jtapi.tsapi.csta1.CSTAFailedEvent;
import com.avaya.jtapi.tsapi.csta1.CSTAForwardingEvent;
import com.avaya.jtapi.tsapi.csta1.CSTAGetAPICapsConfEvent;
import com.avaya.jtapi.tsapi.csta1.CSTAGetDeviceListConfEvent;
import com.avaya.jtapi.tsapi.csta1.CSTAGroupPickupCallConfEvent;
import com.avaya.jtapi.tsapi.csta1.CSTAHeldEvent;
import com.avaya.jtapi.tsapi.csta1.CSTAHoldCallConfEvent;
import com.avaya.jtapi.tsapi.csta1.CSTALoggedOffEvent;
import com.avaya.jtapi.tsapi.csta1.CSTALoggedOnEvent;
import com.avaya.jtapi.tsapi.csta1.CSTAMakeCallConfEvent;
import com.avaya.jtapi.tsapi.csta1.CSTAMakePredictiveCallConfEvent;
import com.avaya.jtapi.tsapi.csta1.CSTAMessageWaitingEvent;
import com.avaya.jtapi.tsapi.csta1.CSTAMonitorConfEvent;
import com.avaya.jtapi.tsapi.csta1.CSTAMonitorEndedEvent;
import com.avaya.jtapi.tsapi.csta1.CSTAMonitorStopConfEvent;
import com.avaya.jtapi.tsapi.csta1.CSTANetworkReachedEvent;
import com.avaya.jtapi.tsapi.csta1.CSTANotReadyEvent;
import com.avaya.jtapi.tsapi.csta1.CSTAOriginatedEvent;
import com.avaya.jtapi.tsapi.csta1.CSTAPickupCallConfEvent;
import com.avaya.jtapi.tsapi.csta1.CSTAPrivate;
import com.avaya.jtapi.tsapi.csta1.CSTAPrivateEventReport;
import com.avaya.jtapi.tsapi.csta1.CSTAPrivateStatusEvent;
import com.avaya.jtapi.tsapi.csta1.CSTAQueryAgentStateConfEvent;
import com.avaya.jtapi.tsapi.csta1.CSTAQueryCallMonitorConfEvent;
import com.avaya.jtapi.tsapi.csta1.CSTAQueryDeviceInfoConfEvent;
import com.avaya.jtapi.tsapi.csta1.CSTAQueryDndConfEvent;
import com.avaya.jtapi.tsapi.csta1.CSTAQueryFwdConfEvent;
import com.avaya.jtapi.tsapi.csta1.CSTAQueryMwiConfEvent;
import com.avaya.jtapi.tsapi.csta1.CSTAQueuedEvent;
import com.avaya.jtapi.tsapi.csta1.CSTAReRouteRequest;
import com.avaya.jtapi.tsapi.csta1.CSTAReadyEvent;
import com.avaya.jtapi.tsapi.csta1.CSTAReconnectCallConfEvent;
import com.avaya.jtapi.tsapi.csta1.CSTARetrieveCallConfEvent;
import com.avaya.jtapi.tsapi.csta1.CSTARetrievedEvent;
import com.avaya.jtapi.tsapi.csta1.CSTARouteEndEventReport;
import com.avaya.jtapi.tsapi.csta1.CSTARouteRegisterAbortEventReport;
import com.avaya.jtapi.tsapi.csta1.CSTARouteRegisterCancelConfEvent;
import com.avaya.jtapi.tsapi.csta1.CSTARouteRegisterReqConfEvent;
import com.avaya.jtapi.tsapi.csta1.CSTARouteRequestEv;
import com.avaya.jtapi.tsapi.csta1.CSTARouteRequestExtEv;
import com.avaya.jtapi.tsapi.csta1.CSTARouteUsedEventReport;
import com.avaya.jtapi.tsapi.csta1.CSTARouteUsedExtEventReport;
import com.avaya.jtapi.tsapi.csta1.CSTAServiceInitiatedEvent;
import com.avaya.jtapi.tsapi.csta1.CSTASetAgentStateConfEvent;
import com.avaya.jtapi.tsapi.csta1.CSTASetDndConfEvent;
import com.avaya.jtapi.tsapi.csta1.CSTASetFwdConfEvent;
import com.avaya.jtapi.tsapi.csta1.CSTASetMwiConfEvent;
import com.avaya.jtapi.tsapi.csta1.CSTASnapshotCallConfEvent;
import com.avaya.jtapi.tsapi.csta1.CSTASnapshotDeviceConfEvent;
import com.avaya.jtapi.tsapi.csta1.CSTASysStatEventReport;
import com.avaya.jtapi.tsapi.csta1.CSTASysStatReq;
import com.avaya.jtapi.tsapi.csta1.CSTASysStatReqConfEvent;
import com.avaya.jtapi.tsapi.csta1.CSTASysStatStart;
import com.avaya.jtapi.tsapi.csta1.CSTASysStatStartConfEvent;
import com.avaya.jtapi.tsapi.csta1.CSTASysStatStop;
import com.avaya.jtapi.tsapi.csta1.CSTASysStatStopConfEvent;
import com.avaya.jtapi.tsapi.csta1.CSTATransferCallConfEvent;
import com.avaya.jtapi.tsapi.csta1.CSTATransferredEvent;
import com.avaya.jtapi.tsapi.csta1.CSTAUniversalFailureConfEvent;
import com.avaya.jtapi.tsapi.csta1.CSTAUnsolicited;
import com.avaya.jtapi.tsapi.csta1.CSTAWorkNotReadyEvent;
import com.avaya.jtapi.tsapi.csta1.CSTAWorkReadyEvent;
import com.avaya.jtapi.tsapi.csta1.LucentPrivateData;
import com.avaya.jtapi.tsapi.tsapiInterface.streams.IntelByteArrayInputStream;
import com.avaya.jtapi.tsapi.tsapiInterface.streams.IntelByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import java.util.Vector;
import org.apache.log4j.Logger;

public class TsapiSession implements TsapiChannelReadHandler {
	private static Logger log = Logger.getLogger(TsapiSession.class);
	private TsapiInvokeIDTable invokeTable;
	private TsapiChannel channel;
	private TsapiEventHandler eventHandler;
	private TSProvider provider;
	private TsapiUnsolicitedHandler unsolicitedHandler;
	private TsapiHeartbeatStatus heartbeatStatus;
	private IntelByteArrayOutputStream out;
	private boolean inService = true;
	private String debugID;
	static final int AC_BLOCK_VER = 1;
	static final int AC_BLOCK_SIZE = 18;
	static final int DEFAULT_TIMEOUT = 60000;
	private static int timeout = 60000;
	private String theVendor;
	private byte[] vendorVersion;
	private String apiVersion;
	private String switchName = "";
	private String serverID = "";

	private boolean readFromServerFailed = false;

	private boolean requestingTrustedApplicationStatus = false;

	private String privateDataVersions = "4-10";

	public TsapiSession(TsapiChannel _channel, boolean _asynchThread,
			String _debugID) {
		this.debugID = _debugID;
		this.channel = _channel;
		this.channel.setReadHandler(this);

		this.invokeTable = new TsapiInvokeIDTable(this.debugID);
		this.out = new IntelByteArrayOutputStream();
		this.eventHandler = new TsapiEventDistributor(this.invokeTable,
				this.debugID);
		this.heartbeatStatus = new TsapiHeartbeatStatus();

		if (_asynchThread)
			this.eventHandler = new TsapiEventQueue(this.eventHandler,
					this.debugID);
	}

	public void startSession(String tlink, String login, String passwd,
			Vector<TsapiVendor> vendors, int timeout) {
		try {
			TsapiRequest req = new ACSKeyRequest(login);

			byte[] kPriv = { -128, 1, 1, 1, 3, 1, 1 };

			this.theVendor = "NT_TCP";
			CSTAPrivate keyPriv = new CSTAPrivate("NT_TCP", kPriv, 0);

			CSTAEvent event = send(req, keyPriv, timeout);

			byte[] cryptPass = null;

			if ((event.getEvent() instanceof ACSKeyReply)) {
				ACSKeyReply reply = (ACSKeyReply) event.getEvent();

				cryptPass = Crypt.scramblePassword(passwd, reply.getObjectID(),
						reply.getKey());
			} else if ((event.getEvent() instanceof ACSAuthReply)) {
				ACSAuthReply reply = (ACSAuthReply) event.getEvent();

				cryptPass = Crypt.scramblePassword(passwd, reply.getObjectID(),
						reply.getKey());
			} else if ((event.getEvent() instanceof ACSAuthReplyTwo)) {
				ACSAuthReplyTwo reply = (ACSAuthReplyTwo) event.getEvent();

				cryptPass = Crypt.encode(passwd, reply.getKey());
			} else {
				throw new TsapiPlatformException(4, 0,
						"unexpected reply on key request to <"
								+ this.channel.getInetSocketAddress() + ">");
			}

			req = new ACSOpenStream(
					(short) (isRequestingTrustedApplicationStatus() ? 5 : 1),
					tlink, login, cryptPass, "Jtapi Client", (short) 1,
					"TS1:2", "AES6.2.0.54", "");

			CSTAPrivate openPriv = null;

			StringBuffer vendStr = new StringBuffer("#ECS#"
					+ this.privateDataVersions + "#" + "AT&T Definity G3" + "#"
					+ this.privateDataVersions);

			if (vendors != null) {
				Enumeration<TsapiVendor> vendEnum = vendors.elements();

				while (vendEnum.hasMoreElements()) {
					TsapiVendor vendor;
					try {
						vendor = (TsapiVendor) vendEnum.nextElement();
						if (!LucentPrivateData.isAvayaVendor(vendor.name))
							vendStr.append("#" + vendor.name + "#"
									+ vendor.versions);
					} catch (NoSuchElementException e) {
						log.error(e.getMessage(), e);
					}
					continue;

				}
			}
			vendStr.append("#");
			byte[] buf = vendStr.toString().getBytes();
			this.theVendor = "VERSION";
			openPriv = new CSTAPrivate("VERSION", buf, 0);
			openPriv.data[0] = 0;
			openPriv.data[(openPriv.data.length - 1)] = 0;

			event = send(req, openPriv, timeout);

			if ((event.getEvent() instanceof ACSUniversalFailureConfEvent)) {
				TSErrorMap
						.throwACSException(((ACSUniversalFailureConfEvent) event
								.getEvent()).getError());
			} else if (!(event.getEvent() instanceof ACSOpenStreamConfEvent)) {
				throw new TsapiPlatformException(4, 0,
						"unexpected reply on open stream");
			}

			this.apiVersion = ((ACSOpenStreamConfEvent) event.getEvent())
					.getApiVer();

			if (event.getPrivData() != null) {
				this.theVendor = ((CSTAPrivate) event.getPrivData()).vendor;
				this.vendorVersion = ((CSTAPrivate) event.getPrivData()).data;
			}
		} catch (TsapiPlatformException e) {
			log.error("Tsapi<init>: " + e);
			throw e;
		} catch (Exception e) {
			log.error("Tsapi<init>: " + e);
			throw new TsapiPlatformException(4, 0, "initialization failed");
		}

		storeServerID(tlink);

		storeSwitchName(tlink);

		this.inService = true;
	}

	public synchronized void close() {
		if (!this.inService) {
			this.channel.close();
			return;
		}

		try {
			if (!this.readFromServerFailed) {
				TsapiRequest req = new ACSAbortStream();
				sendAsync(req, null);
			}
		} catch (Exception e) {
			log.error("shutdown(): " + e);
		} finally {
			this.inService = false;
			this.invokeTable.shutdown();
			this.eventHandler.close();
			this.channel.close();
		}
	}

	public CSTAEvent send(TsapiRequest req, CSTAPrivate priv)
			throws TsapiInvalidStateException, TsapiInvalidArgumentException,
			TsapiProviderUnavailableException, TsapiInvalidPartyException,
			TsapiPrivilegeViolationException, TsapiResourceUnavailableException {
		return send(req, priv, true, null, timeout);
	}

	public CSTAEvent send(TsapiRequest req, CSTAPrivate priv, int timeout)
			throws TsapiInvalidStateException, TsapiInvalidArgumentException,
			TsapiProviderUnavailableException, TsapiInvalidPartyException,
			TsapiPrivilegeViolationException, TsapiResourceUnavailableException {
		return send(req, priv, true, null, timeout);
	}

	public void send(TsapiRequest req, CSTAPrivate priv, ConfHandler handler)
			throws TsapiInvalidStateException, TsapiInvalidArgumentException,
			TsapiProviderUnavailableException, TsapiInvalidPartyException,
			TsapiPrivilegeViolationException, TsapiResourceUnavailableException {
		send(req, priv, true, handler, timeout);
	}

	public void sendAsync(TsapiRequest req, CSTAPrivate priv,
			ConfHandler handler) {
		try {
			send(req, priv, false, handler, timeout);
		} catch (TsapiPlatformException e) {
			throw e;
		} catch (Exception e) {
			log.error("sendAsync: " + e);
		}
	}

	public void sendAsync(TsapiRequest req, CSTAPrivate priv) {
		sendAsync(req, priv, null);
	}

	private CSTAEvent send(TsapiRequest req, CSTAPrivate priv, boolean sync,
			ConfHandler handler, int timeout)
			throws TsapiInvalidStateException, TsapiInvalidArgumentException,
			TsapiProviderUnavailableException, TsapiInvalidPartyException,
			TsapiPrivilegeViolationException, TsapiResourceUnavailableException {
		if (!this.inService) {
			throw new TsapiUnableToSendException(4, 2, "client not in service");
		}

		TSInvokeID invokeID = this.invokeTable.allocTSInvokeID(handler);
		req.setInvokeID(invokeID.getValue());

		if (priv != null) {
			priv.vendor = this.theVendor;
		}
		invokeID.setServiceRequestTurnaroundTime(System.currentTimeMillis());
		try {
			sendMsg(req, priv);
		} catch (Exception e) {
			log.error("send: " + e);
			log.error(e.getMessage(), e);
			throw new TsapiSocketException(4, 0, "send request failed");
		}

		if (sync) {
			CSTAEvent conf = invokeID.waitForConf(timeout);

			if (conf == null) {
				throw new TsapiPlatformException(4, 0, "no conf event");
			}

			if ((conf.getEvent() instanceof CSTAUniversalFailureConfEvent)) {
				TSErrorMap
						.throwCSTAException(((CSTAUniversalFailureConfEvent) conf
								.getEvent()).getError());
			}
			if ((conf.getEvent() instanceof ACSUniversalFailureConfEvent)) {
				TSErrorMap
						.throwACSException(((ACSUniversalFailureConfEvent) conf
								.getEvent()).getError());
			}
			CSTAPrivate.translatePrivateData(conf, this.debugID);

			return conf;
		}
		if (handler == null) {
			this.invokeTable.deallocTSInvokeID(invokeID);
		}

		return null;
	}

	TSProvider getProvider() {
		return this.provider;
	}

	public void setHandler(TsapiUnsolicitedHandler _handler) {
		this.unsolicitedHandler = _handler;
		this.eventHandler.setUnsolicitedHandler(this.unsolicitedHandler);
	}

	public TsapiUnsolicitedHandler getHandler() {
		return this.unsolicitedHandler;
	}

	private void sendMsg(TsapiRequest req, CSTAPrivate priv) throws IOException {
		synchronized (this.out) {
			IntelByteArrayOutputStream acBlock = new IntelByteArrayOutputStream(
					18);
			IntelByteArrayOutputStream encodeStream = new IntelByteArrayOutputStream();
			IntelByteArrayOutputStream privateData = new IntelByteArrayOutputStream(
					priv != null ? 34 + priv.data.length : 0);

			try {
				log.info("Sent InvokeID " + req.getInvokeID() + " for "
						+ this.debugID);
				if (log.isDebugEnabled()) {
					Collection<String> lines = req.print();
					for (String line : lines)
						log.debug(line);
				}
				try {
					req.encode(encodeStream);
				} catch (Exception e) {
					log.error("encode: " + e);
				}

				if (priv != null) {
					if (log.isDebugEnabled()) {
						for (String str : priv.print()) {
							log.debug(str);
						}
					}
					int length = priv.vendor.length();
					byte[] vendor = priv.vendor.getBytes();
					for (int i = 0; i < 32; i++) {
						privateData.write(i < length ? vendor[i] : 0);
					}
					privateData.writeShort(priv.data.length);
					privateData.write(priv.data, 0, priv.data.length);
				}

				acBlock.writeShort(1);
				acBlock.writeInt(req.getInvokeID());
				acBlock.writeInt(0);
				acBlock.writeShort(req.getPDUClass());
				acBlock.writeShort(req.getPDU());
				acBlock.writeShort(encodeStream.size());
				acBlock.writeShort(privateData.size());

				this.out.writeInt(acBlock.size() + encodeStream.size()
						+ privateData.size());
				acBlock.writeTo(this.out);
				encodeStream.writeTo(this.out);
				privateData.writeTo(this.out);

				this.channel.write(this.out);
				this.out.reset();
			} finally {
				privateData.close();
				encodeStream.close();
				acBlock.close();
			}

		}
	}

	public void handleRead(IntelByteArrayInputStream msg) {
		try {
			if (msg.readShort() != 1) {
				throw new TsapiPlatformException(4, 0,
						"message has wrong acBlock version");
			}

			int invokeID = msg.readInt();
			int monitorCrossRefID = msg.readInt();
			int eventClass = msg.readShort();
			int eventType = msg.readShort();
			msg.readShort();
			int privLength = msg.readShort();

			ACSEventHeader eventHeader = new ACSEventHeader(eventClass,
					eventType);

			TsapiPDU pdu = decodePDU(msg, eventClass, eventType);

			if (pdu == null) {
				return;
			}

			if ((pdu instanceof CSTAUnsolicited)) {
				((CSTAUnsolicited) pdu).setMonitorCrossRefID(monitorCrossRefID);
				log.info("Received monitorCrossRefID " + monitorCrossRefID
						+ " for " + this.debugID);
			} else if ((pdu instanceof CSTAConfirmation)) {
				((CSTAConfirmation) pdu).setInvokeID(invokeID);
				log.info("Received invokeID " + invokeID + " for "
						+ this.debugID);
			} else if ((pdu instanceof ACSConfirmation)) {
				((ACSConfirmation) pdu).setInvokeID(invokeID);
				log.info("Received invokeID " + invokeID + " for "
						+ this.debugID);
			}

			if (log.isDebugEnabled()) {
				Collection<String> lines = pdu.print();
				for (String line : lines)
					log.debug(line);
			}
			CSTAPrivate priv;
			if (privLength > 0) {
				priv = getPrivate(msg, eventType);

				if (log.isDebugEnabled()) {
					for (String str : priv.print()) {
						log.debug(str);
					}
				}
			} else {
				priv = null;
			}

			if (this.eventHandler == null) {
				log.error("TsapiSession: no eventHandler for session, discarding message.");

				return;
			}
			processEvent(new CSTAEvent(eventHeader, pdu, priv));
		} catch (Exception e) {
			if (this.unsolicitedHandler == null) {
				log.error("TsapiSession: no handler when Exception received, closing session. "
						+ e);

				log.error(e.getMessage(), e);
				close();
				return;
			}
			this.unsolicitedHandler.eventDistributorException(e);
		}
	}

	private void processEvent(CSTAEvent event) {
		if (heartbeatIsEnabled()) {
			this.heartbeatStatus.receivedEvent();
		}

		switch (event.getEventHeader().getEventClass()) {
		case 2:
			ACSConfirmation acsConf = (ACSConfirmation) event.getEvent();
			TSInvokeID invokeID = this.invokeTable.getTSInvokeID(acsConf
					.getInvokeID());
			if (invokeID != null) {
				if ((invokeID.getConfHandler() != null)
						&& (!(invokeID.getConfHandler() instanceof HandleConfOnCurrentThread))) {
					this.eventHandler.handleEvent(event);
				} else {
					this.invokeTable.deallocTSInvokeID(invokeID);
					invokeID.setConf(event);
				}
			}
			break;
		case 5:
			switch (event.getEventHeader().getEventType()) {
			case 38:
			case 53:
			case 90:
			case 99:
			case 101:
			case 103:
			case 114:
			case 118:
			case 125:
			case 127:
			case 129:
				CSTAConfirmation cstaConf = (CSTAConfirmation) event.getEvent();
				invokeID = this.invokeTable.getTSInvokeID(cstaConf
						.getInvokeID());
				if (invokeID != null) {
					if ((invokeID.getConfHandler() != null)
							&& (!(invokeID.getConfHandler() instanceof HandleConfOnCurrentThread))) {
						this.eventHandler.handleEvent(event);
					} else {
						switch (event.getEventHeader().getEventType()) {
						case 38:
						case 90:
							CSTAPrivate.translatePrivateData(event,
									this.debugID);
							break;
						}

						this.invokeTable.deallocTSInvokeID(invokeID);
						invokeID.setConf(event);
					}
				}
				break;
			default:
				this.eventHandler.handleEvent(event);
			}
			break;
		case 1:
		case 3:
		case 4:
		case 6:
			this.eventHandler.handleEvent(event);
			break;
		default:
			log.info("WARNING: event class "
					+ event.getEventHeader().getEventClass()
					+ " not implemented");
		}
	}

	public void handleException(Exception e) {
		if (this.unsolicitedHandler == null) {
			log.error("Call Control: no handler for session");
			log.error("Exception received: " + e);
			log.error(e.getMessage(), e);
			try {
				close();
			} catch (Exception e2) {
			}
			return;
		}
		if (this.inService) {
			try {
				if ((e instanceof EOFException)) {
					this.readFromServerFailed = true;
				}
				TsapiSocketException tse = new TsapiSocketException(4, 0,
						"read request failed");
				tse.initCause(e);
				this.unsolicitedHandler.eventDistributorException(tse);
			} catch (Exception e1) {
				try {
					close();
				} catch (Exception e2) {
				}
			}
		} else
			try {
				close();
			} catch (Exception e2) {
			}
	}

	private static CSTAPrivate getPrivate(IntelByteArrayInputStream msg,
			int eventType) throws IOException {
		byte[] vendBuf = new byte[32];
		int count = msg.read(vendBuf);
		if (count != 32) {
			log.error("expected 32bytes but read " + count + "bytes");
		}

		int vendLen = 0;
		while (vendBuf[vendLen] != 0) {
			vendLen++;
		}
		int length = msg.readShort();
		byte[] data = new byte[length];
		count = msg.read(data);
		if (count != length) {
			log.error("expected " + length + "bytes but read " + count
					+ "bytes");
		}
		return new CSTAPrivate(new String(vendBuf, 0, vendLen), data, eventType);
	}

	private TsapiPDU decodePDU(InputStream msg, int eventClass, int eventType) {
		switch (eventClass) {
		case 1:
		case 2:
			switch (eventType) {
			case 2:
				return ACSOpenStreamConfEvent.decode(msg);
			case 4:
				return ACSCloseStreamConfEvent.decode(msg);
			case 6:
				return ACSUniversalFailureConfEvent.decode(msg);
			case 7:
				return ACSUniversalFailureEvent.decode(msg);
			case 9:
				return ACSKeyReply.decode(msg);
			case 11:
				return ACSNameSrvReply.decode(msg);
			case 12:
				return ACSAuthReply.decode(msg);
			case 13:
				return ACSAuthReplyTwo.decode(msg);
			case 15:
				return ACSSetHeartbeatIntervalConfEvent.decode(msg);
			case 16:
				return ACSClientHeartbeatEvent.decode(msg);
			case 18:
				return ACSRequestPrivilegesConfEvent.decode(msg);
			case 20:
				return ACSSetPrivilegesConfEvent.decode(msg);
			case 3:
			case 5:
			case 8:
			case 10:
			case 14:
			case 17:
			case 19:
			}
			break;
		case 3:
		case 4:
		case 5:
		case 6:
			switch (eventType) {
			case 2:
				return CSTAAlternateCallConfEvent.decode(msg);
			case 4:
				return CSTAAnswerCallConfEvent.decode(msg);
			case 6:
				return CSTACallCompletionConfEvent.decode(msg);
			case 8:
				return CSTAClearCallConfEvent.decode(msg);
			case 10:
				return CSTAClearConnectionConfEvent.decode(msg);
			case 12:
				return CSTAConferenceCallConfEvent.decode(msg);
			case 14:
				return CSTAConsultationCallConfEvent.decode(msg);
			case 16:
				return CSTADeflectCallConfEvent.decode(msg);
			case 18:
				return CSTAPickupCallConfEvent.decode(msg);
			case 20:
				return CSTAGroupPickupCallConfEvent.decode(msg);
			case 22:
				return CSTAHoldCallConfEvent.decode(msg);
			case 24:
				return CSTAMakeCallConfEvent.decode(msg);
			case 26:
				return CSTAMakePredictiveCallConfEvent.decode(msg);
			case 28:
				return CSTAQueryMwiConfEvent.decode(msg);
			case 30:
				return CSTAQueryDndConfEvent.decode(msg);
			case 32:
				return CSTAQueryFwdConfEvent.decode(msg);
			case 34:
				return CSTAQueryAgentStateConfEvent.decode(msg);
			case 38:
				return CSTAQueryDeviceInfoConfEvent.decode(msg);
			case 40:
				return CSTAReconnectCallConfEvent.decode(msg);
			case 42:
				return CSTARetrieveCallConfEvent.decode(msg);
			case 44:
				return CSTASetMwiConfEvent.decode(msg);
			case 46:
				return CSTASetDndConfEvent.decode(msg);
			case 48:
				return CSTASetFwdConfEvent.decode(msg);
			case 50:
				return CSTASetAgentStateConfEvent.decode(msg);
			case 52:
				return CSTATransferCallConfEvent.decode(msg);
			case 53:
				return CSTAUniversalFailureConfEvent.decode(msg);
			case 54:
				return CSTACallClearedEvent.decode(msg);
			case 55:
				return CSTAConferencedEvent.decode(msg);
			case 56:
				return CSTAConnectionClearedEvent.decode(msg);
			case 57:
				return CSTADeliveredEvent.decode(msg);
			case 58:
				return CSTADivertedEvent.decode(msg);
			case 59:
				return CSTAEstablishedEvent.decode(msg);
			case 60:
				return CSTAFailedEvent.decode(msg);
			case 61:
				return CSTAHeldEvent.decode(msg);
			case 62:
				return CSTANetworkReachedEvent.decode(msg);
			case 63:
				return CSTAOriginatedEvent.decode(msg);
			case 64:
				return CSTAQueuedEvent.decode(msg);
			case 65:
				return CSTARetrievedEvent.decode(msg);
			case 66:
				return CSTAServiceInitiatedEvent.decode(msg);
			case 67:
				return CSTATransferredEvent.decode(msg);
			case 69:
				return CSTADoNotDisturbEvent.decode(msg);
			case 70:
				return CSTAForwardingEvent.decode(msg);
			case 71:
				return CSTAMessageWaitingEvent.decode(msg);
			case 72:
				return CSTALoggedOnEvent.decode(msg);
			case 73:
				return CSTALoggedOffEvent.decode(msg);
			case 74:
				return CSTANotReadyEvent.decode(msg);
			case 75:
				return CSTAReadyEvent.decode(msg);
			case 76:
				return CSTAWorkNotReadyEvent.decode(msg);
			case 77:
				return CSTAWorkReadyEvent.decode(msg);
			case 79:
				return CSTARouteRegisterReqConfEvent.decode(msg);
			case 81:
				return CSTARouteRegisterCancelConfEvent.decode(msg);
			case 82:
				return CSTARouteRegisterAbortEventReport.decode(msg);
			case 85:
				return CSTAReRouteRequest.decode(msg);
			case 87:
				return CSTARouteEndEventReport.decode(msg);
			case 90:
				return CSTAEscapeSvcConfEvent.decode(msg);
			case 93:
				return CSTAPrivateEventReport.decode(msg);
			case 94:
				return CSTAPrivateStatusEvent.decode(msg);
			case 114:
				return CSTAMonitorConfEvent.decode(msg);
			case 118:
				return CSTAMonitorStopConfEvent.decode(msg);
			case 119:
				return CSTAMonitorEndedEvent.decode(msg);
			case 121:
				return CSTASnapshotCallConfEvent.decode(msg);
			case 123:
				return CSTASnapshotDeviceConfEvent.decode(msg);
			case 125:
				return CSTAGetAPICapsConfEvent.decode(msg);
			case 127:
				return CSTAGetDeviceListConfEvent.decode(msg);
			case 129:
				return CSTAQueryCallMonitorConfEvent.decode(msg);
			case 83:
				return CSTARouteRequestEv.decode(msg);
			case 86:
				return CSTARouteUsedEventReport.decode(msg);
			case 130:
				return CSTARouteRequestExtEv.decode(msg);
			case 131:
				return CSTARouteUsedExtEventReport.decode(msg);
			case 100:
				return CSTASysStatStart.decode(msg);
			case 106:
				return CSTASysStatEventReport.decode(msg);
			case 101:
				return CSTASysStatStartConfEvent.decode(msg);
			case 102:
				return CSTASysStatStop.decode(msg);
			case 103:
				return CSTASysStatStopConfEvent.decode(msg);
			case 98:
				return CSTASysStatReq.decode(msg);
			case 99:
				return CSTASysStatReqConfEvent.decode(msg);
			case 3:
			case 5:
			case 7:
			case 9:
			case 11:
			case 13:
			case 15:
			case 17:
			case 19:
			case 21:
			case 23:
			case 25:
			case 27:
			case 29:
			case 31:
			case 33:
			case 35:
			case 36:
			case 37:
			case 39:
			case 41:
			case 43:
			case 45:
			case 47:
			case 49:
			case 51:
			case 68:
			case 78:
			case 80:
			case 84:
			case 88:
			case 89:
			case 91:
			case 92:
			case 95:
			case 96:
			case 97:
			case 104:
			case 105:
			case 107:
			case 108:
			case 109:
			case 110:
			case 111:
			case 112:
			case 113:
			case 115:
			case 116:
			case 117:
			case 120:
			case 122:
			case 124:
			case 126:
			case 128:
			}
			break;
		}

		log.info("got unknown event class " + eventClass + ", event type "
				+ eventType + " for " + this.debugID);
		return null;
	}

	private void storeServerID(String tLink) {
		this.serverID = tLink.toUpperCase();
	}

	private void storeSwitchName(String tLink) {
		String[] tokens = tLink.split("#");
		try {
			this.switchName = tokens[1];
		} catch (ArrayIndexOutOfBoundsException e) {
		}
	}

	public void requestTimeOut(ConfHandler handler) {
		this.invokeTable.requestTimeOut(handler);
	}

	public boolean isInService() {
		return this.inService;
	}

	public String getTheVendor() {
		return this.theVendor;
	}

	public String getApiVersion() {
		return this.apiVersion;
	}

	public byte[] getVendorVersion() {
		return this.vendorVersion;
	}

	public synchronized String getServerID() {
		return this.serverID;
	}

	public synchronized String getSwitchName() {
		return this.switchName;
	}

	public boolean heartbeatIsEnabled() {
		return this.heartbeatStatus.heartbeatIsEnabled();
	}

	public void enableHeartbeat() {
		this.heartbeatStatus.enableHeartbeat();
	}

	public void disableHeartbeat() {
		this.heartbeatStatus.disableHeartbeat();
	}

	public void setClientHeartbeatInterval(short heartbeatInterval) {
		this.heartbeatStatus.setHeartbeatInterval(heartbeatInterval);
	}

	void setHeartbeatTimeoutListener(ITsapiHeartbeatTimeoutListener listener) {
		this.heartbeatStatus.setHeartbeatTimeoutListener(listener);
	}

	public boolean isRequestingTrustedApplicationStatus() {
		return this.requestingTrustedApplicationStatus;
	}

	public void setRequestedPrivateDataVersions(String privateDataVersions) {
		this.privateDataVersions = privateDataVersions;
	}

	public void setRequestingTrustedApplicationStatus(
			boolean requestingTrustedApplicationStatus) {
		this.requestingTrustedApplicationStatus = requestingTrustedApplicationStatus;
	}

	public static void setTimeout(int _timeout) {
		timeout = _timeout;
	}
}