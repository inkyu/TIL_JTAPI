package com.avaya.jtapi.tsapi.impl.core;

import java.io.EOFException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.avaya.jtapi.tsapi.TsapiPlatformException;
import com.avaya.jtapi.tsapi.csta1.CSTACallClearedEvent;
import com.avaya.jtapi.tsapi.csta1.CSTAConferencedEvent;
import com.avaya.jtapi.tsapi.csta1.CSTAConnection;
import com.avaya.jtapi.tsapi.csta1.CSTAConnectionClearedEvent;
import com.avaya.jtapi.tsapi.csta1.CSTAConnectionID;
import com.avaya.jtapi.tsapi.csta1.CSTADeliveredEvent;
import com.avaya.jtapi.tsapi.csta1.CSTADivertedEvent;
import com.avaya.jtapi.tsapi.csta1.CSTADoNotDisturbEvent;
import com.avaya.jtapi.tsapi.csta1.CSTAEstablishedEvent;
import com.avaya.jtapi.tsapi.csta1.CSTAEvent;
import com.avaya.jtapi.tsapi.csta1.CSTAEventReport;
import com.avaya.jtapi.tsapi.csta1.CSTAExtendedDeviceID;
import com.avaya.jtapi.tsapi.csta1.CSTAFailedEvent;
import com.avaya.jtapi.tsapi.csta1.CSTAForwardingEvent;
import com.avaya.jtapi.tsapi.csta1.CSTAForwardingInfo;
import com.avaya.jtapi.tsapi.csta1.CSTAHeldEvent;
import com.avaya.jtapi.tsapi.csta1.CSTALoggedOffEvent;
import com.avaya.jtapi.tsapi.csta1.CSTALoggedOnEvent;
import com.avaya.jtapi.tsapi.csta1.CSTAMessageWaitingEvent;
import com.avaya.jtapi.tsapi.csta1.CSTAMonitorEndedEvent;
import com.avaya.jtapi.tsapi.csta1.CSTANetworkReachedEvent;
import com.avaya.jtapi.tsapi.csta1.CSTANotReadyEvent;
import com.avaya.jtapi.tsapi.csta1.CSTAOriginatedEvent;
import com.avaya.jtapi.tsapi.csta1.CSTAQueuedEvent;
import com.avaya.jtapi.tsapi.csta1.CSTAReRouteRequest;
import com.avaya.jtapi.tsapi.csta1.CSTAReadyEvent;
import com.avaya.jtapi.tsapi.csta1.CSTARequest;
import com.avaya.jtapi.tsapi.csta1.CSTARetrievedEvent;
import com.avaya.jtapi.tsapi.csta1.CSTARouteEndEventReport;
import com.avaya.jtapi.tsapi.csta1.CSTARouteRegisterAbortEventReport;
import com.avaya.jtapi.tsapi.csta1.CSTARouteRequestEv;
import com.avaya.jtapi.tsapi.csta1.CSTARouteRequestExtEv;
import com.avaya.jtapi.tsapi.csta1.CSTARouteUsedEventReport;
import com.avaya.jtapi.tsapi.csta1.CSTARouteUsedExtEventReport;
import com.avaya.jtapi.tsapi.csta1.CSTAServiceInitiatedEvent;
import com.avaya.jtapi.tsapi.csta1.CSTASysStatEventReport;
import com.avaya.jtapi.tsapi.csta1.CSTATransferredEvent;
import com.avaya.jtapi.tsapi.csta1.CSTATrunkInfo;
import com.avaya.jtapi.tsapi.csta1.CSTAUnsolicited;
import com.avaya.jtapi.tsapi.csta1.CSTAWorkNotReadyEvent;
import com.avaya.jtapi.tsapi.csta1.CSTAWorkReadyEvent;
import com.avaya.jtapi.tsapi.csta1.LucentAgentModeChangeEvent;
import com.avaya.jtapi.tsapi.csta1.LucentCallClearedEvent;
import com.avaya.jtapi.tsapi.csta1.LucentChargeAdvice;
import com.avaya.jtapi.tsapi.csta1.LucentConferencedEvent;
import com.avaya.jtapi.tsapi.csta1.LucentConnectionClearedEvent;
import com.avaya.jtapi.tsapi.csta1.LucentDeliveredEvent;
import com.avaya.jtapi.tsapi.csta1.LucentDivertedEvent;
import com.avaya.jtapi.tsapi.csta1.LucentEnteredDigitsEvent;
import com.avaya.jtapi.tsapi.csta1.LucentEstablishedEvent;
import com.avaya.jtapi.tsapi.csta1.LucentFailedEvent;
import com.avaya.jtapi.tsapi.csta1.LucentLoggedOffEvent;
import com.avaya.jtapi.tsapi.csta1.LucentLoggedOnEvent;
import com.avaya.jtapi.tsapi.csta1.LucentOriginatedEvent;
import com.avaya.jtapi.tsapi.csta1.LucentQueryAgentLoginResp;
import com.avaya.jtapi.tsapi.csta1.LucentQueryMwiConfEvent;
import com.avaya.jtapi.tsapi.csta1.LucentQueuedEvent;
import com.avaya.jtapi.tsapi.csta1.LucentRouteRequestEvent;
import com.avaya.jtapi.tsapi.csta1.LucentServiceInitiatedEvent;
import com.avaya.jtapi.tsapi.csta1.LucentTransferredEvent;
import com.avaya.jtapi.tsapi.csta1.LucentTrunkConnectionMapping;
import com.avaya.jtapi.tsapi.csta1.LucentV5ConferencedEvent;
import com.avaya.jtapi.tsapi.csta1.LucentV5DeliveredEvent;
import com.avaya.jtapi.tsapi.csta1.LucentV5EstablishedEvent;
import com.avaya.jtapi.tsapi.csta1.LucentV5NetworkProgressInfo;
import com.avaya.jtapi.tsapi.csta1.LucentV5RouteRequestEvent;
import com.avaya.jtapi.tsapi.csta1.LucentV5TransferredEvent;
import com.avaya.jtapi.tsapi.csta1.LucentV7ConferencedEvent;
import com.avaya.jtapi.tsapi.csta1.LucentV7ConnectionClearedEvent;
import com.avaya.jtapi.tsapi.csta1.LucentV7DeliveredEvent;
import com.avaya.jtapi.tsapi.csta1.LucentV7EstablishedEvent;
import com.avaya.jtapi.tsapi.csta1.LucentV7NetworkProgressInfo;
import com.avaya.jtapi.tsapi.csta1.LucentV7RouteRequestEvent;
import com.avaya.jtapi.tsapi.csta1.LucentV7TransferredEvent;
import com.avaya.jtapi.tsapi.csta1.LucentV8FailedEvent;
import com.avaya.jtapi.tsapi.csta1.LucentV9HeldEvent;
import com.avaya.jtapi.tsapi.csta1.LucentV9OriginatedEvent;
import com.avaya.jtapi.tsapi.csta1.LucentV9ServiceInitiatedEvent;
import com.avaya.jtapi.tsapi.impl.LucentACDAddressImpl;
import com.avaya.jtapi.tsapi.impl.LucentCallImpl;
import com.avaya.jtapi.tsapi.impl.LucentTerminalImpl;
import com.avaya.jtapi.tsapi.impl.LucentTrunkInfoMapItem;
import com.avaya.jtapi.tsapi.impl.TsapiAddress;
import com.avaya.jtapi.tsapi.impl.TsapiTrunkImpl;
import com.avaya.jtapi.tsapi.impl.monitor.TsapiAddressMonitor;
import com.avaya.jtapi.tsapi.impl.monitor.TsapiCallMonitor;
import com.avaya.jtapi.tsapi.impl.monitor.TsapiProviderMonitor;
import com.avaya.jtapi.tsapi.impl.monitor.TsapiRouteMonitor;
import com.avaya.jtapi.tsapi.impl.monitor.TsapiTerminalMonitor;
import com.avaya.jtapi.tsapi.tsapiInterface.Tsapi;
import com.avaya.jtapi.tsapi.tsapiInterface.TsapiUnsolicitedHandler;
import com.avaya.jtapi.tsapi.util.JTAPILoggingAdapter;
import com.avaya.jtapi.tsapi.util.PerfStatisticsCollector;

final class TSEventHandler implements TsapiUnsolicitedHandler {
	private static Logger log = Logger.getLogger(TSEventHandler.class);
	TSProviderImpl provider;
	final short EVAL_UNLIKELY = 1;
	final short EVAL_POSSIBLE = 2;
	final short EVAL_LIKELY = 3;

	public void acsUnsolicited(CSTAEvent event) {
		Object privateData = event.getPrivData();

		switch (event.getEventHeader().getEventType()) {
		case 7:
			log.info("Handling ACS_UNIVERSAL_FAILURE event " + event + " for "
					+ this.provider);

			this.provider.shutdown(privateData);

			log.info("DONE handling ACS_UNIVERSAL_FAILURE for " + this.provider);
			break;
		case 16:
			log.info("Handling ACS_CLIENT_HEARTBEAT event for " + this.provider);

			if (!this.provider.heartbeatIsEnabled()) {
				this.provider.enableHeartbeat();
			}

			log.info("DONE handling ACS_CLIENT_HEARTBEAT event for "
					+ this.provider);
			break;
		default:
			log.info("WARNING: event " + event.getEventHeader().getEventType()
					+ " not implemented");
		}
	}

	public void cstaUnsolicited(CSTAEvent event) {
		Runtime rt = Runtime.getRuntime();

		log.info("CSTA Unsolicited Event: " + event);
		log.info("Free memory: " + rt.freeMemory());
		log.info("Total memory: " + rt.totalMemory());

		CSTAUnsolicited cstaEvent = (CSTAUnsolicited) event.getEvent();

		Object privateData = event.getPrivData();

		Object monitored = this.provider.getMonitoredObject(cstaEvent
				.getMonitorCrossRefID());
		if (monitored == null) {
			if (event.getEventHeader().getEventType() != 54) {
				log.info("CSTA event " + event + "("
						+ event.getEventHeader().getEventType()
						+ ") ignored for " + this.provider);
				return;
			}

			CSTACallClearedEvent callCleared = (CSTACallClearedEvent) cstaEvent;
			TSCall call = this.provider.createCall(callCleared.getClearedCall()
					.getCallID());
			if (call != null) {
				call.getTSProviderImpl().addMonitor(
						cstaEvent.getMonitorCrossRefID(), call);
			}

			monitored = call;
		}

		switch (event.getEventHeader().getEventType()) {
		case 66:
			log.info("Handling CSTA_SERVICE_INITIATED for " + this.provider);
			CSTAServiceInitiatedEvent initiated = (CSTAServiceInitiatedEvent) cstaEvent;
			doConnEvents(66, monitored, initiated.getCause(), null,
					initiated.getInitiatedConnection(), 84, 98, privateData,
					null, null, null, true, 0);

			log.info("DONE handling CSTA_SERVICE_INITIATED for "
					+ this.provider);
			break;
		case 63:
			log.info("Handling CSTA_ORIGINATED for " + this.provider);
			CSTAOriginatedEvent originated = (CSTAOriginatedEvent) cstaEvent;
			doConnEvents(63, monitored, originated.getCause(),
					originated.getCallingDevice(),
					originated.getOriginatedConnection(), 88, 98, privateData,
					originated.getCallingDevice(),
					originated.getCalledDevice(), null, true, 0);

			log.info("DONE handling CSTA_ORIGINATED for " + this.provider);
			break;
		case 57:
			log.info("Handling CSTA_DELIVERED for " + this.provider);
			CSTADeliveredEvent delivered = (CSTADeliveredEvent) cstaEvent;
			doConnEvents(57, monitored, delivered.getCause(),
					delivered.getAlertingDevice(), delivered.getConnection(),
					83, 97, privateData, delivered.getCallingDevice(),
					delivered.getCalledDevice(),
					delivered.getLastRedirectionDevice(), false, 0);

			log.info("DONE handling CSTA_DELIVERED for " + this.provider);
			break;
		case 59:
			log.info("Handling CSTA_ESTABLISHED for " + this.provider);
			CSTAEstablishedEvent established = (CSTAEstablishedEvent) cstaEvent;
			doConnEvents(59, monitored, established.getCause(),
					established.getAnsweringDevice(),
					established.getEstablishedConnection(), 88, 98,
					privateData, established.getCallingDevice(),
					established.getCalledDevice(),
					established.getLastRedirectionDevice(), false, 0);

			log.info("DONE handling CSTA_ESTABLISHED for " + this.provider);
			break;
		case 62:
			log.info("Handling CSTA_NETWORK_REACHED for " + this.provider);
			CSTANetworkReachedEvent networkReached = (CSTANetworkReachedEvent) cstaEvent;
			doConnEvents(62, monitored, networkReached.getCause(),
					networkReached.getTrunkUsed(),
					networkReached.getConnection(), 86, 98, privateData, null,
					networkReached.getCalledDevice(), null, false, 0);

			log.info("DONE handling CSTA_NETWORK_REACHED for " + this.provider);
			break;
		case 65:
			log.info("Handling CSTA_RETRIEVED for " + this.provider);
			CSTARetrievedEvent retrieved = (CSTARetrievedEvent) cstaEvent;
			doConnEvents(65, monitored, retrieved.getCause(),
					retrieved.getRetrievingDevice(),
					retrieved.getRetrievedConnection(), 88, 98, privateData,
					null, null, null, false, 0);

			log.info("DONE handling CSTA_RETRIEVED for " + this.provider);
			break;
		case 61:
			log.info("Handling CSTA_HELD for " + this.provider);
			CSTAHeldEvent held = (CSTAHeldEvent) cstaEvent;
			doConnEvents(61, monitored, held.getCause(),
					held.getHoldingDevice(), held.getHeldConnection(), 88, 99,
					privateData, null, null, null, false, 0);

			log.info("DONE handling CSTA_HELD for " + this.provider);
			break;
		case 56:
			log.info("Handling CSTA_CONNECTION_CLEARED for " + this.provider);
			CSTAConnectionClearedEvent connCleared = (CSTAConnectionClearedEvent) cstaEvent;
			doConnEvents(56, monitored, connCleared.getCause(),
					connCleared.getReleasingDevice(),
					connCleared.getDroppedConnection(), 89, 102, privateData,
					null, null, null, false, 0);

			log.info("DONE handling CSTA_CONNECTION_CLEARED for "
					+ this.provider);
			break;
		case 60:
			log.info("Handling CSTA_FAILED for " + this.provider);
			CSTAFailedEvent failed = (CSTAFailedEvent) cstaEvent;
			doConnEvents(60, monitored, failed.getCause(),
					failed.getFailingDevice(), failed.getFailedConnection(),
					90, 102, privateData, null, failed.getCalledDevice(), null,
					false, 0);

			log.info("DONE handling CSTA_FAILED for " + this.provider);
			break;
		case 119:
			log.info("Handling CSTA_MONITOR_ENDED for " + this.provider);
			CSTAMonitorEndedEvent monitorEnded = (CSTAMonitorEndedEvent) cstaEvent;
			doMonitorEnded(monitorEnded.getCause(),
					cstaEvent.getMonitorCrossRefID(), monitored, privateData);
			log.info("DONE handling CSTA_MONITOR_ENDED for " + this.provider);
			break;
		case 64:
			log.info("Handling CSTA_QUEUED for " + this.provider);
			CSTAQueuedEvent queued = (CSTAQueuedEvent) cstaEvent;
			doConnEvents(64, monitored, queued.getCause(),
					queued.getQueueingDevice(), queued.getQueuedConnection(),
					82, -1, privateData, queued.getCallingDevice(),
					queued.getCalledDevice(),
					queued.getLastRedirectionDevice(), false,
					queued.getNumberQueued());

			log.info("DONE handling CSTA_QUEUED for " + this.provider);
			break;
		case 58:
			log.info("Handling CSTA_DIVERTED for " + this.provider);
			CSTADivertedEvent diverted = (CSTADivertedEvent) cstaEvent;

			if ((Tsapi.isPatch_enable_PreserveRedirectedVDNAsUNKNOWN())
					&& (this.provider.getDeviceExt(diverted
							.getDivertingDevice().getDeviceID()) == 1)) {
				log.info("Preserving redirecting VDN party of CSTA_DIVERTED for "
						+ this.provider);

				doConnEvents(58, monitored, diverted.getCause(),
						diverted.getDivertingDevice(),
						diverted.getConnection(), 91, 103, privateData, null,
						diverted.getNewDestination(), null, false, 0);
			} else {
				log.info("Dropping party due to handling of CSTA_DIVERTED for "
						+ this.provider);

				doConnEvents(58, monitored, diverted.getCause(),
						diverted.getDivertingDevice(),
						diverted.getConnection(), 89, 102, privateData, null,
						diverted.getNewDestination(), null, false, 0);
			}

			log.info("DONE handling CSTA_DIVERTED for " + this.provider);
			break;
		case 55:
			log.info("Handling CSTA_CONFERENCED for " + this.provider);
			CSTAConferencedEvent conferenced = (CSTAConferencedEvent) cstaEvent;
			doConfXfer(207, conferenced.getPrimaryOldCall(),
					conferenced.getSecondaryOldCall(),
					conferenced.getConferenceConnections(), privateData,
					conferenced.getCause());

			log.info("DONE handling CSTA_CONFERENCED for " + this.provider);
			break;
		case 67:
			log.info("Handling CSTA_TRANSFERRED for " + this.provider);
			CSTATransferredEvent transferred = (CSTATransferredEvent) cstaEvent;
			doConfXfer(212, transferred.getPrimaryOldCall(),
					transferred.getSecondaryOldCall(),
					transferred.getTransferredConnections(), privateData,
					transferred.getCause());

			log.info("DONE handling CSTA_TRANSFERRED for " + this.provider);
			break;
		case 54:
			log.info("Handling CSTA_CALL_CLEARED for " + this.provider);
			CSTACallClearedEvent callCleared = (CSTACallClearedEvent) cstaEvent;
			doCallEvents(54, monitored, callCleared.getCause(),
					callCleared.getClearedCall(), 34, privateData);

			log.info("DONE handling CSTA_CALL_CLEARED for " + this.provider);
			break;
		case 72:
			log.info("Handling CSTA_LOGGED_ON for " + this.provider);
			CSTALoggedOnEvent loggedOn = (CSTALoggedOnEvent) cstaEvent;
			doAgentEvents(72, monitored, loggedOn.getAgentDevice(),
					loggedOn.getAgentID(), loggedOn.getAgentGroup(),
					loggedOn.getPassword(), 1, privateData);

			log.info("DONE handling CSTA_LOGGED_ON for " + this.provider);
			break;
		case 73:
			log.info("Handling CSTA_LOGGED_OFF for " + this.provider);
			CSTALoggedOffEvent loggedOff = (CSTALoggedOffEvent) cstaEvent;
			doAgentEvents(73, monitored, loggedOff.getAgentDevice(),
					loggedOff.getAgentID(), loggedOff.getAgentGroup(), null, 2,
					privateData);

			log.info("DONE handling CSTA_LOGGED_OFF for " + this.provider);
			break;
		case 74:
			log.info("Handling CSTA_NOT_READY for " + this.provider);
			CSTANotReadyEvent notReady = (CSTANotReadyEvent) cstaEvent;
			doAgentEvents(74, monitored, notReady.getAgentDevice(),
					notReady.getAgentID(), null, null, 3, privateData);

			log.info("DONE handling CSTA_NOT_READY for " + this.provider);
			break;
		case 75:
			log.info("Handling CSTA_READY for " + this.provider);
			CSTAReadyEvent ready = (CSTAReadyEvent) cstaEvent;
			doAgentEvents(75, monitored, ready.getAgentDevice(),
					ready.getAgentID(), null, null, 4, privateData);

			log.info("DONE handling CSTA_READY for " + this.provider);
			break;
		case 76:
			log.info("Handling CSTA_WORK_NOT_READY for " + this.provider);
			CSTAWorkNotReadyEvent workNotReady = (CSTAWorkNotReadyEvent) cstaEvent;
			doAgentEvents(76, monitored, workNotReady.getAgentDevice(),
					workNotReady.getAgentID(), null, null, 5, privateData);

			log.info("DONE handling CSTA_WORK_NOT_READY for " + this.provider);
			break;
		case 77:
			log.info("Handling CSTA_WORK_READY for " + this.provider);
			CSTAWorkReadyEvent workReady = (CSTAWorkReadyEvent) cstaEvent;
			doAgentEvents(77, monitored, workReady.getAgentDevice(),
					workReady.getAgentID(), null, null, 6, privateData);

			log.info("DONE handling CSTA_WORK_READY for " + this.provider);
			break;
		case 69:
			log.info("Handling CSTA_DO_NOT_DISTURB for " + this.provider);
			CSTADoNotDisturbEvent doNotDisturb = (CSTADoNotDisturbEvent) cstaEvent;

			doDeviceEvents(69, monitored, doNotDisturb.getDevice(),
					doNotDisturb.isDoNotDisturbOn(), null, privateData);

			log.info("DONE handling CSTA_DO_NOT_DISTURB for " + this.provider);
			break;
		case 71:
			log.info("Handling CSTA_MESSAGE_WAITING for " + this.provider);
			CSTAMessageWaitingEvent messageWaiting = (CSTAMessageWaitingEvent) cstaEvent;

			doDeviceEvents(71, monitored, messageWaiting.getDeviceForMessage(),
					messageWaiting.isMessageWaitingOn(), null, privateData);

			log.info("DONE handling CSTA_MESSAGE_WAITING for " + this.provider);
			break;
		case 70:
			log.info("Handling CSTA_FORWARDING for " + this.provider);
			CSTAForwardingEvent forwarding = (CSTAForwardingEvent) cstaEvent;

			doDeviceEvents(70, monitored, forwarding.getDevice(), false,
					forwarding.getForwardingInformation(), privateData);

			log.info("DONE handling CSTA_FORWARDING for " + this.provider);
			break;
		case 94:
			log.info("Handling CSTA_PRIVATE_STATUS for " + this.provider);
			if (((monitored instanceof TSCall))
					|| ((privateData instanceof LucentEnteredDigitsEvent))
					|| ((privateData instanceof LucentChargeAdvice))) {
				CSTAConnectionID connID = null;

				if ((privateData instanceof LucentEnteredDigitsEvent)) {
					connID = ((LucentEnteredDigitsEvent) privateData)
							.getConnection_asn();
				} else if ((privateData instanceof LucentChargeAdvice)) {
					connID = ((LucentChargeAdvice) privateData)
							.getConnection_asn();
				}
				doCallEvents(94, monitored, (short) 0, connID, 0, privateData);
			} else {
				doDeviceEvents(94, monitored, null, false, null, privateData);
			}

			log.info("DONE handling CSTA_PRIVATE_STATUS for " + this.provider);
			break;
		case 68:
		case 78:
		case 79:
		case 80:
		case 81:
		case 82:
		case 83:
		case 84:
		case 85:
		case 86:
		case 87:
		case 88:
		case 89:
		case 90:
		case 91:
		case 92:
		case 93:
		case 95:
		case 96:
		case 97:
		case 98:
		case 99:
		case 100:
		case 101:
		case 102:
		case 103:
		case 104:
		case 105:
		case 106:
		case 107:
		case 108:
		case 109:
		case 110:
		case 111:
		case 112:
		case 113:
		case 114:
		case 115:
		case 116:
		case 117:
		case 118:
		default:
			log.info("WARNING: event " + event.getEventHeader().getEventType()
					+ " not implemented");
		}

		if (JTAPILoggingAdapter.isPerformanceLoggingEnabled()) {
			log.debug("updating statistics collector with unsolicited handling time for this iteration");
			PerfStatisticsCollector.updateUnsolicitedHandlingTime(System
					.currentTimeMillis() - event.getQueuedTimeStamp());
		}
	}

	public void cstaRequest(CSTAEvent event) {
		TSDevice tsDevice = null;
		TsapiRouteMonitor tsRouteCallback = null;
		TSRouteSession tsRouteSession = null;
		CSTARequest cstaEvent = (CSTARequest) event.getEvent();
		Object privateData = event.getPrivData();

		switch (event.getEventHeader().getEventType()) {
		case 130:
			log.info("Handling CSTA_ROUTE_REQUEST for " + this.provider);
			CSTARouteRequestExtEv routeRequestExt = (CSTARouteRequestExtEv) cstaEvent;

			tsDevice = (TSDevice) this.provider.routeRegHash.get(new Integer(
					routeRequestExt.getRouteRegisterReqID()));
			if (tsDevice != null) {
				tsRouteCallback = tsDevice.getTSRouteCallback();
				if (tsRouteCallback != null) {
					TSDevice currentRouteDevice = null;
					TSDevice callingDevice = null;
					TSCall call = null;
					currentRouteDevice = this.provider
							.createDevice(routeRequestExt.getCurrentRoute());
					callingDevice = this.provider.createDevice(routeRequestExt
							.getCallingDevice());
					call = this.provider.createCall(routeRequestExt
							.getRoutedCall().getCallID(), privateData);

					tsRouteSession = new TSRouteSession(this.provider,
							tsDevice, routeRequestExt.getRoutingCrossRefID(),
							currentRouteDevice, callingDevice, call,
							routeRequestExt.getRoutedSelAlgorithm(), null);

					if ((privateData instanceof LucentRouteRequestEvent)) {
						LucentRouteRequestEvent luPrivData = (LucentRouteRequestEvent) privateData;
						tsRouteSession.setUUI(luPrivData.getUserInfo());
						tsRouteSession.setLAI(luPrivData.getLookaheadInfo());
						tsRouteSession.setUEC(luPrivData.getUserEnteredCode());
						TsapiTrunkImpl tsapiTrunk = TsapiPromoter
								.promoteTrunk(this.provider,
										luPrivData.getTrunkGroup(), null);

						if (tsapiTrunk != null) {
							TSTrunk trk = tsapiTrunk.getTSTrunk();
							tsRouteSession.setTrunk(trk);
						}
						if ((privateData instanceof LucentV5RouteRequestEvent)) {
							LucentV5RouteRequestEvent luV5PrivData = (LucentV5RouteRequestEvent) privateData;
							tsRouteSession.setUCID(luV5PrivData.getUcid());
							tsRouteSession.setCallOriginatorInfo(luV5PrivData
									.getCallOriginatorInfo());
							tsRouteSession.setFlexibleBilling(luV5PrivData
									.isFlexibleBilling());

							if ((privateData instanceof LucentV7RouteRequestEvent)) {
								LucentV7RouteRequestEvent luV7PrivData = (LucentV7RouteRequestEvent) privateData;
								tsRouteSession.setDeviceHistory(TsapiPromoter
										.promoteDeviceHistory(luV7PrivData
												.getDeviceHistory()));
							}
						}
					}

					TSEvent tsapiEvent = tsRouteSession.setState(1);
					if (tsapiEvent != null) {
						log.info("ROUTEEVENT for " + tsDevice + " for "
								+ this.provider);
						tsRouteCallback.deliverEvent(tsapiEvent);
					}
				}
			}
			log.info("DONE handling CSTA_ROUTE_REQUEST for " + this.provider);
			break;
		case 83:
			log.info("Handling CSTA_ROUTE_REQUEST for " + this.provider);
			CSTARouteRequestEv routeRequest = (CSTARouteRequestEv) cstaEvent;

			tsDevice = (TSDevice) this.provider.routeRegHash.get(new Integer(
					routeRequest.getRouteRegisterReqID()));
			if (tsDevice != null) {
				tsRouteCallback = tsDevice.getTSRouteCallback();
				if (tsRouteCallback != null) {
					TSDevice currentRouteDevice = null;
					TSDevice callingDevice = null;
					TSCall call = null;
					currentRouteDevice = this.provider
							.createDevice(routeRequest.getCurrentRoute());
					callingDevice = this.provider.createDevice(routeRequest
							.getCallingDevice());
					call = this.provider.createCall(routeRequest
							.getRoutedCall().getCallID(), privateData);

					tsRouteSession = new TSRouteSession(this.provider,
							tsDevice, routeRequest.getRoutingCrossRefID(),
							currentRouteDevice, callingDevice, call,
							routeRequest.getRoutedSelAlgorithm(), null);

					if ((privateData instanceof LucentRouteRequestEvent)) {
						LucentRouteRequestEvent luPrivData = (LucentRouteRequestEvent) privateData;
						tsRouteSession.setUUI(luPrivData.getUserInfo());
						tsRouteSession.setLAI(luPrivData.getLookaheadInfo());
						tsRouteSession.setUEC(luPrivData.getUserEnteredCode());
						TsapiTrunkImpl tsapiTrunk = TsapiPromoter
								.promoteTrunk(this.provider,
										luPrivData.getTrunkGroup(), null);

						if (tsapiTrunk != null) {
							TSTrunk trk = tsapiTrunk.getTSTrunk();
							tsRouteSession.setTrunk(trk);
						}
					}
					TSEvent tsapiEvent = tsRouteSession.setState(1);
					if (tsapiEvent != null) {
						log.info("ROUTEEVENT for " + tsDevice + " for "
								+ this.provider);
						tsRouteCallback.deliverEvent(tsapiEvent);
					}
				}
			}
			log.info("DONE handling CSTA_ROUTE_REQUEST for " + this.provider);
			break;
		case 85:
			log.info("Handling CSTA_RE_ROUTE_REQUEST for " + this.provider);
			CSTAReRouteRequest reRouteRequest = (CSTAReRouteRequest) cstaEvent;

			tsDevice = (TSDevice) this.provider.routeRegHash.get(new Integer(
					reRouteRequest.getRouteRegisterReqID()));
			if (tsDevice != null) {
				tsRouteCallback = tsDevice.getTSRouteCallback();
				if ((tsRouteCallback != null) && (tsDevice.sessionHash != null)) {
					tsRouteSession = (TSRouteSession) tsDevice.sessionHash
							.get(new Integer(reRouteRequest
									.getRoutingCrossRefID()));
					if (tsRouteSession != null) {
						TSEvent tsapiEvent = tsRouteSession.setState(4);
						if (tsapiEvent != null) {
							log.info("REROUTEEVENT for " + tsDevice + " for "
									+ this.provider);
							tsRouteCallback.deliverEvent(tsapiEvent);
						}
					}
				}
			}
			log.info("DONE handling CSTA_RE_ROUTE_REQUEST for " + this.provider);
			break;
		default:
			log.info("WARNING: event " + event.getEventHeader().getEventType()
					+ " not implemented");
		}
	}

	public void cstaEventReport(CSTAEvent event) {
		TSDevice tsDevice = null;
		TsapiRouteMonitor tsRouteCallback = null;
		TSRouteSession tsRouteSession = null;
		CSTAEventReport cstaEvent = (CSTAEventReport) event.getEvent();
		TSDevice routeUsedDevice = null;
		TSDevice callingDevice = null;

		switch (event.getEventHeader().getEventType()) {
		case 131:
			log.info("Handling CSTA_ROUTE_USED for " + this.provider);
			CSTARouteUsedExtEventReport routeUsedEventExt = (CSTARouteUsedExtEventReport) cstaEvent;
			tsDevice = (TSDevice) this.provider.routeRegHash.get(new Integer(
					routeUsedEventExt.getRouteRegisterReqID()));
			if (tsDevice != null) {
				tsRouteCallback = tsDevice.getTSRouteCallback();
				if ((tsRouteCallback != null) && (tsDevice.sessionHash != null)) {
					tsRouteSession = (TSRouteSession) tsDevice.sessionHash
							.get(new Integer(routeUsedEventExt
									.getRoutingCrossRefID()));
					if (tsRouteSession != null) {
						routeUsedDevice = this.provider
								.createDevice(routeUsedEventExt.getRouteUsed());
						callingDevice = this.provider
								.createDevice(routeUsedEventExt
										.getCallingDevice());
						tsRouteSession.setRouteUsedDevice(routeUsedDevice);
						tsRouteSession.setCallingDevice(callingDevice);
						tsRouteSession.setDomain(routeUsedEventExt.isDomain());
						TSEvent tsapiEvent = tsRouteSession.setState(2);
						if (tsapiEvent != null) {
							log.info("ROUTEUSEDEVENT for " + tsDevice + " for "
									+ this.provider);
							tsRouteCallback.deliverEvent(tsapiEvent);
						}
					}
				}
			}
			log.info("DONE handling CSTA_ROUTE_USED for " + this.provider);
			break;
		case 86:
			log.info("Handling CSTA_ROUTE_USED for " + this.provider);
			CSTARouteUsedEventReport routeUsedEvent = (CSTARouteUsedEventReport) cstaEvent;
			tsDevice = (TSDevice) this.provider.routeRegHash.get(new Integer(
					routeUsedEvent.getRouteRegisterReqID()));
			if (tsDevice != null) {
				tsRouteCallback = tsDevice.getTSRouteCallback();
				if ((tsRouteCallback != null) && (tsDevice.sessionHash != null)) {
					tsRouteSession = (TSRouteSession) tsDevice.sessionHash
							.get(new Integer(routeUsedEvent
									.getRoutingCrossRefID()));
					if (tsRouteSession != null) {
						routeUsedDevice = this.provider
								.createDevice(routeUsedEvent.getRouteUsed());
						callingDevice = this.provider
								.createDevice(routeUsedEvent.getCallingDevice());
						tsRouteSession.setRouteUsedDevice(routeUsedDevice);
						tsRouteSession.setCallingDevice(callingDevice);
						tsRouteSession.setDomain(routeUsedEvent.isDomain());
						TSEvent tsapiEvent = tsRouteSession.setState(2);
						if (tsapiEvent != null) {
							log.info("ROUTEUSEDEVENT for " + tsDevice + " for "
									+ this.provider);
							tsRouteCallback.deliverEvent(tsapiEvent);
						}
					}
				}
			}
			log.info("DONE handling CSTA_ROUTE_USED for " + this.provider);
			break;
		case 87:
			log.info("Handling CSTA_ROUTE_END for " + this.provider);
			CSTARouteEndEventReport routeEndEvent = (CSTARouteEndEventReport) cstaEvent;
			tsDevice = (TSDevice) this.provider.routeRegHash.get(new Integer(
					routeEndEvent.getRouteRegisterReqID()));
			if (tsDevice != null) {
				tsRouteCallback = tsDevice.getTSRouteCallback();
				if ((tsRouteCallback != null) && (tsDevice.sessionHash != null)) {
					tsRouteSession = (TSRouteSession) tsDevice.sessionHash
							.get(new Integer(routeEndEvent
									.getRoutingCrossRefID()));
					if (tsRouteSession != null) {
						tsRouteSession.setCause(routeEndEvent.getCause());
						TSEvent tsapiEvent = tsRouteSession.setState(3);
						if (tsapiEvent != null) {
							log.info("ROUTEENDEVENT for " + tsDevice + " for "
									+ this.provider);
							tsRouteCallback.deliverEvent(tsapiEvent);
						}
					}
				}
			}
			log.info("DONE handling CSTA_ROUTE_END for " + this.provider);
			break;
		case 82:
			log.info("Handling CSTA_ROUTE_REGISTER_ABORT for " + this.provider);
			CSTARouteRegisterAbortEventReport registerAbortEvent = (CSTARouteRegisterAbortEventReport) cstaEvent;

			tsDevice = (TSDevice) this.provider.routeRegHash.get(new Integer(
					registerAbortEvent.getRouteRegisterReqID()));
			if (tsDevice != null) {
				tsRouteCallback = tsDevice.getTSRouteCallback();
				if (tsRouteCallback != null)
					tsRouteCallback.deleteReference(tsDevice);
			}
			log.info("DONE handling CSTA_ROUTE_REGISTER_ABORT for "
					+ this.provider);
			break;
		case 93:
			log.info("Handling CSTA_PRIVATE for " + this.provider);
			Object replyPriv = event.getPrivData();
			if ((replyPriv instanceof LucentQueryAgentLoginResp)) {
				LucentQueryAgentLoginResp respEvent = (LucentQueryAgentLoginResp) replyPriv;
				tsDevice = this.provider.findACDDevice(respEvent
						.getPrivEventCrossRefID());
				if (tsDevice != null) {
					tsDevice.handleAgentLoginResponse(respEvent);
				}
			}
			log.info("DONE handling CSTA_PRIVATE for " + this.provider);
			break;
		case 106:
			CSTASysStatEventReport sysStat = (CSTASysStatEventReport) cstaEvent;
			Vector<TSEvent> eventList = new Vector<TSEvent>();

			if ((sysStat.getState() == 6) || (sysStat.getState() == 5)
					|| (sysStat.getState() == 4)) {
				this.provider.setState(0, eventList);
			} else if ((sysStat.getState() == 2) || (sysStat.getState() == 1)) {
				this.provider.setState(2, eventList);
			}
			if (eventList.size() > 0) {
				Vector<?> monitors = this.provider.getMonitors();
				for (int j = 0; j < monitors.size(); j++) {
					TsapiProviderMonitor callback = (TsapiProviderMonitor) monitors
							.elementAt(j);

					callback.deliverEvents(eventList, false);
				}
			}
			break;
		default:
			log.info("WARNING: event " + event.getEventHeader().getEventType()
					+ " not implemented");
		}
	}

	TSEventHandler(TSProviderImpl _provider) {
		this.provider = _provider;
	}

	void doConnEvents(int eventType, Object monitored, short cause,
			CSTAExtendedDeviceID subjectDeviceID, CSTAConnectionID connID,
			int connState, int termConnState, Object privateData,
			CSTAExtendedDeviceID callingDeviceID,
			CSTAExtendedDeviceID calledDeviceID,
			CSTAExtendedDeviceID lastRedirectionDeviceID,
			boolean dontNeedSnapshot, int numQueued) {
		int jtapiCause = getJtapiCause(cause);
		TSDevice subjectDevice = this.provider.createDevice(subjectDeviceID,
				connID);
		if (subjectDevice == null) {
			if (((subjectDeviceID == null) || (subjectDeviceID
					.getDeviceIDStatus() == 2))
					&& ((monitored instanceof TSDevice))) {
				if (this.provider.isLucent()) {
					subjectDevice = this.provider.createDevice(
							connID.getDeviceID(), connID);
				} else {
					subjectDevice = (TSDevice) monitored;
				}
			} else {
				if (connID == null) {
					return;
				}

				subjectDevice = this.provider.createDevice(
						connID.getDeviceID(), connID);
			}
			if (subjectDevice == null) {
				return;
			}

		}

		if ((this.provider.isLucent()) && (connID.getDevIDType() == 1)) {
			CSTAConnectionID realConnID = subjectDevice.matchConn(connID);
			if (realConnID != null) {
				connID = realConnID;
			}
		}

		if (eventType == 64) {
			subjectDevice.setNumberQueued(numQueued);
		}

		TSDevice connectionDevice = subjectDevice;
		if (cause == 17) {
			TSDevice confDevice = null;
			switch (eventType) {
			case 57:
			case 59:
				confDevice = this.provider.createDevice(calledDeviceID);
				break;
			case 63:
				if ((privateData instanceof LucentOriginatedEvent)) {
					LucentOriginatedEvent luPrivData = (LucentOriginatedEvent) privateData;
					LucentTerminalImpl physicalTerminal = null;
					try {
						physicalTerminal = TsapiPromoter.promoteTerminal(
								this.provider,
								luPrivData.getPhysicalTerminal_asn());
					} catch (TsapiPlatformException e) {
						log.error(e.getMessage(), e);
					}
					if (physicalTerminal != null) {
						subjectDevice = physicalTerminal.getTSDevice();
					}
				} else {
					confDevice = this.provider.createDevice(callingDeviceID);
				}
				break;
			}

			if (confDevice != null) {
				connectionDevice = confDevice;
			}
		} else if ((privateData instanceof LucentOriginatedEvent)) {
			LucentOriginatedEvent luPrivData = (LucentOriginatedEvent) privateData;
			LucentTerminalImpl physicalTerminal = null;
			try {
				physicalTerminal = TsapiPromoter.promoteTerminal(this.provider,
						luPrivData.getPhysicalTerminal_asn());
			} catch (TsapiPlatformException e) {
				log.error(e.getMessage(), e);
			}
			if (physicalTerminal != null) {
				subjectDevice = physicalTerminal.getTSDevice();
			}

		}

		TSCall call = this.provider.createCall(connID.getCallID(), privateData);

		call = this.provider
				.validateCall(privateData, call, connID.getCallID());

		call.setCSTACause(cause);

		extractAndProcessConsultMode(cause, privateData, call);

		if (eventType == 63) {
			log.debug("Setting receivedCSTAOriginatedEvent to true for call: "
					+ call.getCallID());
			call.setReceivedCSTAOriginatedEvent(true);
		}

		if ((monitored != null) && ((monitored instanceof TSDevice))
				&& (((TSDevice) monitored).getDeviceType() == 1)) {
			this.provider.addCallToDomain((TSDevice) monitored, call);
		}

		call.considerAddingVDNMonitorCallObservers(monitored);

		if ((eventType == 58) || (eventType == 56)) {
			if (call.getSnapshotCallConfPending()) {
				log.info("set redo snapshot call to true");
				call.setNeedRedoSnapshotCall(true);
			}

		}

		if (call.getTSState() == 34) {
			switch (eventType) {
			case 56:
			case 58:
			case 60:
				break;
			case 57:
			case 59:
			default:
				this.provider.dumpCall(connID.getCallID());
				call = this.provider.createCall(connID.getCallID());
			}

		}

		switch (eventType) {
		case 61:
		case 65:
			subjectDevice.setIsATerminal(true);
		}

		Vector<TSEvent> eventList = new Vector<TSEvent>();

		switch (eventType) {
		case 57:
			if (this.provider.isConnInAnyHash(connID) == true) {
				TSConnection dumpingConn = this.provider
						.createTerminalConnection(connID, subjectDevice,
								eventList, connectionDevice);
				int oldCState = dumpingConn.getCallControlConnState();
				int oldTCState = dumpingConn.getCallControlTermConnState();

				if ((oldCState == 89) || (oldTCState == 102)) {
					if (((!dumpingConn.isTerminalConnection()) && (connState != 89))
							|| ((dumpingConn.isTerminalConnection() == true) && (termConnState != 102))) {
						eventList = new Vector<TSEvent>();

						dumpingConn.delete();
						this.provider.dumpConn(connID);
					}
				}

			}

			break;
		}

		if (!dontNeedSnapshot) {
			if (call.needsSnapshot()) {
				SnapshotCallExtraConfHandler handler = new UnsolicitedSnapshotCallConfHandler(
						this, eventType, cause, call.getCSTA3Cause(),
						subjectDeviceID, subjectDevice, connID, call,
						privateData, callingDeviceID, calledDeviceID,
						lastRedirectionDeviceID, connState, termConnState,
						connectionDevice);

				call.doSnapshot(connID, handler, false);
				call.staleObsCleanup(100);
			}

		} else {
			call.setNeedSnapshot(false);
		}

		TSConnection connection = this.provider.createTerminalConnection(
				connID, subjectDevice, eventList, connectionDevice);
		int oldConnState = connection.getCallControlConnState();
		int oldTermConnState = connection.getCallControlTermConnState();

		if ((oldConnState == 89) || (oldTermConnState == 102)) {
			switch (eventType) {
			case 63:
			case 66:
				eventList = new Vector<TSEvent>();

				call.setState(34, eventList);
				doCallMonitors(call, eventList, 102, null);
				this.provider.dumpCall(connID.getCallID());

				eventList = new Vector<TSEvent>();
				call = this.provider.createCall(connID.getCallID());
				call.setNeedSnapshot(false);
				connection = this.provider.createTerminalConnection(connID,
						subjectDevice, eventList, connectionDevice);

				oldConnState = connection.getCallControlConnState();
				oldTermConnState = connection.getCallControlTermConnState();
				break;
			default:
				if (((!connection.isTerminalConnection()) && (connState != 89))
						|| ((connection.isTerminalConnection() == true) && (termConnState != 102))) {
					eventList = new Vector<TSEvent>();

					connection.delete();
					this.provider.dumpConn(connID);

					connection = this.provider.createTerminalConnection(connID,
							subjectDevice, eventList, connectionDevice);

					oldConnState = connection.getCallControlConnState();
					oldTermConnState = connection.getCallControlTermConnState();
				}
				break;
			}
		}

		if (eventType == 56) {
			Vector<?> allConnections = call.getConnections();
			if (allConnections.size() <= 2) {
				for (int i = 0; i < allConnections.size(); i++) {
					TSConnection tmpconn = (TSConnection) allConnections
							.elementAt(i);
					if (tmpconn != connection) {
						if ((tmpconn.getTSConnState() == 53)
								|| (tmpconn.getTSConnState() == 52)) {
							call.setState(34, eventList);
						}

					}

				}

			}

		}

		if ((eventType == 57) && (!call.hasReceivedCSTAOriginatedEvent())
				&& (!Tsapi.isDisableSimulationForCSTAOriginatedEvent())) {
			log.debug("Trying to simulate CSTAOriginatedEvent for call: "
					+ call.getCallID());
			Vector<?> connections = call.getConnections();
			Enumeration<?> e = connections.elements();
			TSConnection callingConnection = null;
			while (e.hasMoreElements()) {
				TSConnection tempConnection = (TSConnection) e.nextElement();
				if (tempConnection.getTSDevice().getName()
						.equals(callingDeviceID.getDeviceID())) {
					callingConnection = tempConnection;
					break;
				}
			}

			if (callingConnection != null) {
				log.debug("Moving callingConnection state to CallControlConnection.ESTABLISHED for device: "
						+ callingConnection.getConnID().getDeviceID());

				callingConnection.setConnectionState(88, eventList);
				call.setReceivedCSTAOriginatedEvent(true);
			} else {
				log.debug("Could not find connection for callingDevice:"
						+ callingDeviceID.getDeviceID() + " in call:"
						+ call.getCallID());
			}
		}

		connection.setConnectionState(connState, eventList);

		if ((eventType == 60)
				&& (Tsapi.isEnableDelayInTermConnDropOnCstaFailed())) {
			try {
				call.waitForConnectProcess();
			} catch (InterruptedException interruptedException) {
			}

		}

		if ((eventType != 60) || (!Tsapi.isDisableTermConnDropOnCstaFailed())) {
			connection.setTermConnState(termConnState, eventList);
		}

		if (eventType == 56) {
			Vector<?> allConnections = call.getConnections();
			if (allConnections.size() >= 1) {
				Vector<TSConnection> listOfConnsBelongToDiffDevIDType = new Vector<TSConnection>();

				for (int i = 0; i < allConnections.size(); i++) {
					TSConnection tmpconn = (TSConnection) allConnections
							.elementAt(i);
					if (tmpconn.isDoNotExpectConnectionClearedEvent()) {
						listOfConnsBelongToDiffDevIDType.addElement(tmpconn);
					}
					tmpconn = null;
				}

				if (allConnections.equals(listOfConnsBelongToDiffDevIDType)) {
					for (int i = 0; i < allConnections.size(); i++) {
						TSConnection tmpconn = (TSConnection) allConnections
								.elementAt(i);
						if (tmpconn.isDoNotExpectConnectionClearedEvent()) {
							log.info("Conn "
									+ tmpconn
									+ ", has 'connBelongToDifferentDeviceIDType' flag set. Clearing connection.");
							tmpconn.setConnectionState(connState, eventList);
							tmpconn.setTermConnState(termConnState, eventList);
						}
						tmpconn = null;
					}
				}
				listOfConnsBelongToDiffDevIDType = null;
			}

		}

		if (dontNeedSnapshot) {
			call.setNeedSnapshot(false);
		}

		finishConnEvents(monitored, eventType, cause, jtapiCause,
				subjectDeviceID, subjectDevice, connection, call, privateData,
				callingDeviceID, calledDeviceID, lastRedirectionDeviceID,
				connState, oldConnState, oldTermConnState, eventList);

		if (!call.checkForMonitors()) {
			call.setNeedSnapshot(true);
		}

		if (eventType == 56) {
			if (!call.isCallMonitorSet()) {
				if ((((TSDevice) monitored).equals(subjectDevice))
						&& (((TSDevice) monitored).getDeviceType() == 0)) {
					if (call.getConnections().size() == 0) {
						eventList.removeAllElements();
						call.setState(34, eventList);
					}
				}
			}
		}
	}

	private void extractAndProcessConsultMode(short cause, Object privateData,
			TSCall call) {
		if (this.provider.isLucentV9()) {
			short consultMode = 0;

			if ((privateData instanceof LucentV9OriginatedEvent)) {
				consultMode = ((LucentV9OriginatedEvent) privateData)
						.getConsultMode();
			} else if ((privateData instanceof LucentV9ServiceInitiatedEvent)) {
				consultMode = ((LucentV9ServiceInitiatedEvent) privateData)
						.getConsultMode();
			} else if ((privateData instanceof LucentV9HeldEvent)) {
				consultMode = ((LucentV9HeldEvent) privateData)
						.getConsultMode();
			}
			call.setCSTA3Cause(checkIfCSTA3CauseIsApplicable(consultMode, cause));
		} else {
			call.setCSTA3Cause(cause);
		}
	}

	private short checkIfCSTA3CauseIsApplicable(short consultMode,
			short cstaCause) {
		short retValue = cstaCause;

		switch (consultMode) {
		case 1:
			retValue = 37;
			break;
		case 3:
			retValue = 63;
			break;
		case 2:
			retValue = 32;
		}

		return retValue;
	}

	short endpointEvaluation(short deviceIDType) {
		switch (deviceIDType) {
		case 0:
			return 3;
		case 70:
			return 1;
		case 55:
			return 3;
		case 40:
			return 2;
		}

		return 1;
	}

	boolean isCalledBetterEndpoint(CSTAExtendedDeviceID calling,
			CSTAExtendedDeviceID called) {
		if ((calling != null) && (called != null)
				&& (calling.getDeviceIDStatus() == 0)
				&& (called.getDeviceIDStatus() == 0)) {
			short callingEval = endpointEvaluation(calling.getDeviceIDType());
			short calledEval = endpointEvaluation(called.getDeviceIDType());

			return calledEval >= callingEval;
		}

		return true;
	}

	void finishConnEvents(Object monitored, int eventType, short cause,
			int jtapiCause, CSTAExtendedDeviceID subjectDeviceID,
			TSDevice subjectDevice, TSConnection connection, TSCall call,
			Object privateData, CSTAExtendedDeviceID callingDeviceID,
			CSTAExtendedDeviceID calledDeviceID,
			CSTAExtendedDeviceID lastRedirectionDeviceID, int connState,
			int oldConnState, int oldTermConnState, Vector<TSEvent> eventList) {
		TSDevice callingDevice = this.provider.createDevice(callingDeviceID);
		TSDevice calledDevice = this.provider.createDevice(calledDeviceID);
		TSDevice lastRedirectionDevice = this.provider
				.createDevice(lastRedirectionDeviceID);

		call.setCallingDevices(callingDevice);
		call.setCalledDevice(calledDevice);
		call.setLastRedirectionDevice(lastRedirectionDevice);

		if ((eventType == 62) && (subjectDeviceID != null)) {
			TSTrunk trk = null;

			if ((privateData instanceof LucentV5NetworkProgressInfo)) {
				LucentV5NetworkProgressInfo luPrivData = (LucentV5NetworkProgressInfo) privateData;
				if (luPrivData != null) {
					TsapiTrunkImpl tsapiTrunk = TsapiPromoter.promoteTrunk(
							this.provider, luPrivData.getTrunkGroup(),
							luPrivData.getTrunkMember());

					if (tsapiTrunk != null) {
						trk = tsapiTrunk.getTSTrunk();
						if (trk != null) {
							connection.setTrunk(trk);
							trk.setTSConnection(connection);
						}
					}
				}

			} else {
				trk = this.provider.createTrunk(subjectDevice.getName(), 2);
				if (trk != null) {
					if ((privateData instanceof LucentV5NetworkProgressInfo)) {
						LucentV5NetworkProgressInfo luPrivData = (LucentV5NetworkProgressInfo) privateData;
						if (luPrivData != null) {
							trk.setGroupName(luPrivData.getTrunkGroup());
							trk.setMemberName(luPrivData.getTrunkMember());
						}
					}
				}
			}

			if (trk != null) {
				call.addTrunk(trk, eventList);
			}

		}

		TSDevice deliveringACDDevice = null;
		TSDevice distributingDevice = null;
		TSDevice distributingVDN = null;
		if ((privateData instanceof LucentDeliveredEvent)) {
			LucentDeliveredEvent luPrivData = (LucentDeliveredEvent) privateData;
			LucentACDAddressImpl acdAddr = TsapiPromoter.promoteACDAddress(
					this.provider,
					((LucentDeliveredEvent) privateData).getSplit_asn());

			if (acdAddr != null) {
				deliveringACDDevice = acdAddr.getTSDevice();
				call.setDeliveringACDDevice(deliveringACDDevice);
			}
			TsapiAddress address = TsapiPromoter.promoteDeviceIDToAddress(
					this.provider, luPrivData.getDistributingDevice_asn());

			if (address != null) {
				distributingDevice = address.getTSDevice();
				call.setDistributingDevice(distributingDevice);
			}

			call.setUUI(luPrivData.getUserInfo());
			call.setLAI(luPrivData.getLookaheadInfo());
			call.setUEC(luPrivData.getUserEnteredCode());
			call.setOCI(luPrivData.getOriginalCallInfo());
			call.setReason(luPrivData.getReason());
			TsapiTrunkImpl tsapiTrunk = TsapiPromoter.promoteTrunk(
					this.provider, luPrivData.getTrunkGroup(),
					luPrivData.getTrunkMember());

			if (tsapiTrunk != null) {
				TSTrunk trk = tsapiTrunk.getTSTrunk();

				if (!this.provider.isDeviceMonitorable(subjectDeviceID
						.getDeviceID())) {
					trk.setType(2);
				}
				TSConnection trkConn = call.findOtherConnection(connection);
				if (trkConn != null) {
					trkConn.setTrunk(trk);
					trk.setTSConnection(trkConn);
				}
				call.addTrunk(trk, eventList);
			}
			if ((privateData instanceof LucentV5DeliveredEvent)) {
				LucentV5DeliveredEvent luV5PrivData = (LucentV5DeliveredEvent) privateData;
				call.setUCID(luV5PrivData.getUcid());
				call.setCallOriginatorInfo(luV5PrivData.getCallOriginatorInfo());
				call.setFlexibleBilling(luV5PrivData.isFlexibleBilling());

				if ((privateData instanceof LucentV7DeliveredEvent)) {
					LucentV7DeliveredEvent luV7PrivData = (LucentV7DeliveredEvent) privateData;
					call.setDeviceHistory(TsapiPromoter
							.promoteDeviceHistory(luV7PrivData
									.getDeviceHistory()));

					if (luV7PrivData.getDistributingVDN_asn() != null) {
						distributingVDN = TsapiPromoter
								.promoteExtendedDeviceIDToTSDevice(
										this.provider,
										luV7PrivData.getDistributingVDN_asn());

						call.setDistributingVDN(distributingVDN);
					}
				}
			}
		} else if ((privateData instanceof LucentEstablishedEvent)) {
			LucentEstablishedEvent luPrivData = (LucentEstablishedEvent) privateData;
			LucentACDAddressImpl acdAddr = TsapiPromoter.promoteACDAddress(
					this.provider, luPrivData.getSplit_asn());

			if (acdAddr != null) {
				deliveringACDDevice = acdAddr.getTSDevice();
				call.setDeliveringACDDevice(deliveringACDDevice);
			}
			TsapiAddress address = TsapiPromoter.promoteDeviceIDToAddress(
					this.provider, luPrivData.getDistributingDevice_asn());

			if (address != null) {
				distributingDevice = address.getTSDevice();
				call.setDistributingDevice(distributingDevice);
			}
			call.setUUI(luPrivData.getUserInfo());
			call.setLAI(luPrivData.getLookaheadInfo());
			call.setUEC(luPrivData.getUserEnteredCode());
			call.setOCI(luPrivData.getOriginalCallInfo());
			call.setReason(luPrivData.getReason());
			TsapiTrunkImpl tsapiTrunk = TsapiPromoter.promoteTrunk(
					this.provider, luPrivData.getTrunkGroup(),
					luPrivData.getTrunkMember());

			if (tsapiTrunk != null) {
				TSTrunk trk = tsapiTrunk.getTSTrunk();

				if (!this.provider.isDeviceMonitorable(subjectDeviceID
						.getDeviceID())) {
					trk.setType(2);
				}
				TSConnection otherConn = call.findOtherConnection(connection);

				TSConnection trkConn = isCalledBetterEndpoint(callingDeviceID,
						calledDeviceID) ? otherConn : connection;

				if (trkConn != null) {
					trkConn.setTrunk(trk);
					trk.setTSConnection(trkConn);
				}
				call.addTrunk(trk, eventList);
			}
			if ((privateData instanceof LucentV5EstablishedEvent)) {
				LucentV5EstablishedEvent luV5PrivData = (LucentV5EstablishedEvent) privateData;
				call.setUCID(luV5PrivData.getUcid());
				call.setCallOriginatorInfo(luV5PrivData.getCallOriginatorInfo());
				call.setFlexibleBilling(luV5PrivData.isFlexibleBilling());

				if ((privateData instanceof LucentV7EstablishedEvent)) {
					LucentV7EstablishedEvent luV7PrivData = (LucentV7EstablishedEvent) privateData;
					call.setDeviceHistory(TsapiPromoter
							.promoteDeviceHistory(luV7PrivData
									.getDeviceHistory()));

					if (luV7PrivData.getDistributingVDN_asn() != null) {
						distributingVDN = TsapiPromoter
								.promoteExtendedDeviceIDToTSDevice(
										this.provider,
										luV7PrivData.getDistributingVDN_asn());

						call.setDistributingVDN(distributingVDN);
					}
				}
			}
		} else if ((privateData instanceof LucentConnectionClearedEvent)) {
			LucentConnectionClearedEvent luPrivData = (LucentConnectionClearedEvent) privateData;
			call.setUUI(luPrivData.getUserInfo());

			if ((privateData instanceof LucentV7ConnectionClearedEvent)) {
				LucentV7ConnectionClearedEvent luV7PrivData = (LucentV7ConnectionClearedEvent) privateData;
				call.setDeviceHistory(TsapiPromoter
						.promoteDeviceHistory(luV7PrivData.getDeviceHistory()));
			}

		} else if ((privateData instanceof LucentOriginatedEvent)) {
			LucentOriginatedEvent luPrivData = (LucentOriginatedEvent) privateData;
			call.setUUI(luPrivData.getUserInfo());
		} else if ((privateData instanceof LucentServiceInitiatedEvent)) {
			LucentServiceInitiatedEvent luPrivData = (LucentServiceInitiatedEvent) privateData;
			call.setUCID(luPrivData.getUcid());
		} else if ((privateData instanceof LucentQueuedEvent)) {
			LucentQueuedEvent luV7PrivData = (LucentQueuedEvent) privateData;
			call.setDeviceHistory(TsapiPromoter
					.promoteDeviceHistory(luV7PrivData.getDeviceHistory()));
		} else if ((privateData instanceof LucentV7NetworkProgressInfo)) {
			LucentV7NetworkProgressInfo luV7PrivData = (LucentV7NetworkProgressInfo) privateData;
			call.setDeviceHistory(TsapiPromoter
					.promoteDeviceHistory(luV7PrivData.getDeviceHistory()));
		} else if ((privateData instanceof LucentDivertedEvent)) {
			LucentDivertedEvent luV7PrivData = (LucentDivertedEvent) privateData;
			call.setDeviceHistory(TsapiPromoter
					.promoteDeviceHistory(luV7PrivData.getDeviceHistory()));
		} else if ((privateData instanceof LucentFailedEvent)) {
			LucentFailedEvent luV7PrivData = (LucentFailedEvent) privateData;
			call.setDeviceHistory(TsapiPromoter
					.promoteDeviceHistory(luV7PrivData.getDeviceHistory()));

			if ((privateData instanceof LucentV8FailedEvent)) {
				LucentV8FailedEvent luV8PrivData = (LucentV8FailedEvent) privateData;

				if (luV8PrivData.getCallingDevice() != null) {
					call.setCallingDevices(TsapiPromoter
							.promoteExtendedDeviceIDToTSDevice(this.provider,
									luV8PrivData.getCallingDevice()));
				}

			}

		}

		if ((eventType == 57) && (getJtapiCause(cause) == 210)) {
			TSDevice[] dropDevices = new TSDevice[3];
			dropDevices[0] = lastRedirectionDevice;
			dropDevices[1] = deliveringACDDevice;

			dropDevices[2] = (distributingVDN == null ? distributingDevice
					: distributingVDN);
			for (int j = 0; j < 3; j++) {
				if (dropDevices[j] != null) {
					if ((dropDevices[j].getDeviceType() == 2)
							|| (dropDevices[j].getDeviceType() == 1)) {
						TSConnection dropConn = call
								.getConnAtDevice(dropDevices[j]);

						if ((dropConn != null)
								&& (!dropConn.getTSDevice().getName()
										.equals(subjectDeviceID.getDeviceID()))) {
							dropConn.setConnectionState(89, eventList);
						}
					}
				}
			}
		}

		if ((eventType == 64) && (cause == 28)
				&& (lastRedirectionDevice != null)) {
			call.setDeliveringACDDevice(subjectDevice);

			if (oldConnState != 83) {
				TSConnection lastRedirConn = call
						.getConnAtDevice(lastRedirectionDevice);
				if (lastRedirConn != null) {
					if (lastRedirectionDevice.getDeviceType() == 1) {
						lastRedirConn.addACDConns(connection);

						connection.setACDManagerConn(lastRedirConn);
					} else {
						TSConnection acdManagerConn = lastRedirConn
								.getACDManagerConn();
						if (acdManagerConn != null) {
							acdManagerConn.addACDConns(connection);

							connection.setACDManagerConn(acdManagerConn);
						}
					}
				}
			}

		}

		if ((monitored != null) && (eventType == 58)
				&& ((monitored instanceof TSDevice))
				&& (((TSDevice) monitored).getDeviceType() == 1)) {
			this.provider.removeCallFromDomain(call);
		}

		doCallMonitors(call, eventList, jtapiCause, privateData);
	}

	void doCallEvents(int eventType, Object monitored, short cause,
			CSTAConnectionID connID, int callState, Object privateData) {
		Vector<TSEvent> eventList = new Vector<TSEvent>();

		int jtapiCause = getJtapiCause(cause);
		TSCall call = null;
		if (connID == null) {
			if (!(monitored instanceof TSCall)) {
				return;
			}
			call = (TSCall) monitored;
		} else {
			call = this.provider.createCall(connID.getCallID(), privateData);

			call = this.provider.validateCall(privateData, call,
					connID.getCallID());

			if (call.getTSState() == 34) {
				switch (eventType) {
				case 54:
					break;
				case 94:
					if ((privateData instanceof LucentChargeAdvice)) {
						break;
					}

				default:
					this.provider.dumpCall(connID.getCallID());
					call = this.provider.createCall(connID.getCallID());
				}

			}

		}

		call.setCSTACause(cause);
		if ((privateData instanceof LucentCallClearedEvent)) {
			LucentCallClearedEvent luPrivData = (LucentCallClearedEvent) privateData;
			call.setReason(luPrivData.getReason());
		}

		if ((eventType == 54) && (cause == 32)) {
			call.setReceivedCallClearedTransfer(true);
			return;
		}

		if (eventType == 94) {
			if ((privateData instanceof LucentEnteredDigitsEvent)) {
				LucentEnteredDigitsEvent luPrivData = (LucentEnteredDigitsEvent) privateData;
				LucentCallImpl connection = TsapiPromoter.promoteConnection(
						this.provider, luPrivData.getConnection_asn());

				call = connection.getTSCall();
				call.setDigits(luPrivData.getDigits());
				jtapiCause = getJtapiCause(luPrivData.getCause());
				eventList.addElement(new TSEvent(58, call));

				privateData = null;
			}
		} else {
			call.setState(callState, eventList);
		}

		doCallMonitors(call, eventList, jtapiCause, privateData);
	}

	void doCallMonitors(TSCall call, Vector<TSEvent> eventList, int jtapiCause,
			Object privateData) {
		if (call == null) {
			return;
		}

		if (privateData != null) {
			for (int i = 0; i < eventList.size(); i++) {
				TSEvent ev = (TSEvent) eventList.elementAt(i);
				if (ev.getPrivateData() == null) {
					ev.setPrivateData(privateData);
				}
			}
			if (!this.provider.isLucent()) {
				eventList.addElement(new TSEvent(9999, call, privateData,
						this.provider));
			} else if ((privateData instanceof LucentChargeAdvice)) {
				eventList.addElement(new TSEvent(9999, call, privateData,
						this.provider));
			}
		}

		Vector<?> observers = null;

		if (eventList.size() > 0) {
			observers = call.getObservers();

			for (int j = 0; j < observers.size(); j++) {
				TsapiCallMonitor callback = (TsapiCallMonitor) observers
						.elementAt(j);
				callback.deliverEvents(eventList, jtapiCause, false);
			}
		}

		call.processCallbackSnapshots(jtapiCause);

		if (call.getTSState() == 34) {
			call.endCVDObservers(jtapiCause, null);
			call.endNonCVDObservers(jtapiCause);
		}

		call.staleObsCleanup(jtapiCause);
	}

	void doAgentEvents(int eventType, Object monitored,
			CSTAExtendedDeviceID subjectDeviceID, String agentID,
			String agentGroup, String agentPassword, int agentState,
			Object privateData) {
		Vector<TSEvent> eventList = new Vector<TSEvent>();

		TSDevice subjectDevice = this.provider.createDevice(subjectDeviceID);

		if (subjectDevice == null) {
			if ((monitored instanceof TSDevice)) {
				subjectDevice = (TSDevice) monitored;
			} else {
				return;
			}
		}

		if ((agentID == null) && (agentState != 2))
			agentID = subjectDevice.getAgentID();
		TSAgentKey agentKey = new TSAgentKey(subjectDevice.getName(),
				agentGroup, agentID);
		TSAgent agent = this.provider.createAgent(agentKey, agentGroup,
				agentPassword, TSProviderImpl.CREATEAGENT_REFUSE_DELETED);

		if (agent == null) {
			return;
		}

		int workMode = 0;
		int reasonCode = 0;

		if ((privateData instanceof LucentLoggedOnEvent)) {
			switch (((LucentLoggedOnEvent) privateData).getWorkMode()) {
			case 3:
				workMode = 1;
				break;
			case 4:
				workMode = 2;
				break;
			default:
				workMode = 0;
				break;
			}
		} else if ((privateData instanceof LucentLoggedOffEvent)) {
			reasonCode = ((LucentLoggedOffEvent) privateData).getReasonCode();
		} else if ((privateData instanceof LucentAgentModeChangeEvent)) {
			reasonCode = ((LucentAgentModeChangeEvent) privateData)
					.getReasonCode();
		}

		agent.updateState(agentState, workMode, reasonCode, eventList);

		if ((privateData instanceof LucentLoggedOnEvent)) {
			switch (((LucentLoggedOnEvent) privateData).getWorkMode()) {
			case 3:
			case 4:
				agentState = 4;
				break;
			default:
				agentState = 3;
			}

			agent.updateState(agentState, workMode, reasonCode, eventList);
		}

		doAgentMonitors(agent, eventList, privateData);
	}

	void doAgentMonitors(TSAgent agent, Vector<TSEvent> eventList,
			Object privateData) {
		if (eventList.size() == 0) {
			if (privateData == null) {
				return;
			}

		}

		if (privateData != null) {
			for (int i = 0; i < eventList.size(); i++) {
				TSEvent ev = (TSEvent) eventList.elementAt(i);
				if (ev.getPrivateData() == null) {
					ev.setPrivateData(privateData);
				}
			}
			if (!this.provider.isLucent()) {
				eventList.addElement(new TSEvent(9999, agent, privateData,
						this.provider));
			}
		}
		Vector<?> observers = null;

		TSDevice acdDevice = agent.getTSACDDevice();
		if (acdDevice != null) {
			observers = acdDevice.getAddressObservers();
			for (int j = 0; j < observers.size(); j++) {
				TsapiAddressMonitor callback = (TsapiAddressMonitor) observers
						.elementAt(j);
				callback.deliverEvents(eventList, false);
			}

		} else {
			Vector<?> skillsVector = agent.getSkillsVector();
			for (int i = 0; i < skillsVector.size(); i++) {
				TSDevice skillDevice = (TSDevice) skillsVector.elementAt(i);
				observers = skillDevice.getAddressObservers();
				for (int j = 0; j < eventList.size(); j++) {
					TSEvent ev = (TSEvent) eventList.elementAt(j);
					Object tsTarget = ev.getEventTarget();
					if ((tsTarget instanceof TSAgent)) {
						ev.setSkillDevice(skillDevice);
					}
				}
				for (int j = 0; j < observers.size(); j++) {
					TsapiAddressMonitor callback = (TsapiAddressMonitor) observers
							.elementAt(j);
					callback.deliverEvents(eventList, false);
				}
			}
		}

		Vector<?> terminalObservers = agent.getTerminalObservers();

		for (int j = 0; j < terminalObservers.size(); j++) {
			TsapiTerminalMonitor callback = (TsapiTerminalMonitor) terminalObservers
					.elementAt(j);
			callback.deliverEvents(eventList, false);
		}

		agent.getParentAgent().deliverEventsToOtherSkills(agent, eventList,
				agent.getTSACDDevice());
	}

	void doDeviceEvents(int eventType, Object monitored,
			CSTAExtendedDeviceID subjectDeviceID, boolean state,
			CSTAForwardingInfo fwdInfo, Object privateData) {
		Vector<TSEvent> eventList = new Vector<TSEvent>();
		TSDevice subjectDevice = this.provider.createDevice(subjectDeviceID);

		if (subjectDevice == null) {
			if ((monitored instanceof TSDevice)) {
				subjectDevice = (TSDevice) monitored;
			} else {
				return;
			}
		}

		switch (eventType) {
		case 69:
			subjectDevice.updateDNDState(state, eventList);
			break;
		case 71:
			int newBits = 0;
			if ((privateData instanceof LucentQueryMwiConfEvent)) {
				LucentQueryMwiConfEvent luPrivData = (LucentQueryMwiConfEvent) privateData;
				newBits = luPrivData.getApplicationType();
			} else if (state) {
				newBits = -1;
			}
			subjectDevice.updateMessageWaitingBits(newBits, eventList);
			break;
		case 70:
			CSTAForwardingInfo[] fwdArray = new CSTAForwardingInfo[1];
			fwdArray[0] = fwdInfo;
			subjectDevice.updateForwarding(fwdArray, eventList);
		}

		doDeviceMonitors(subjectDevice, eventList, privateData);
	}

	void doDeviceMonitors(TSDevice device, Vector<TSEvent> eventList,
			Object privateData) {
		if (eventList.size() == 0) {
			if (privateData == null) {
				return;
			}

		}

		if (privateData != null) {
			for (int i = 0; i < eventList.size(); i++) {
				TSEvent ev = (TSEvent) eventList.elementAt(i);
				if (ev.getPrivateData() == null) {
					ev.setPrivateData(privateData);
				}
			}
			if (!this.provider.isLucent()) {
				eventList.addElement(new TSEvent(9999, device, privateData,
						this.provider));
			}
		}

		Vector<?> observers = device.getAddressObservers();

		for (int j = 0; j < observers.size(); j++) {
			TsapiAddressMonitor callback = (TsapiAddressMonitor) observers
					.elementAt(j);
			callback.deliverEvents(eventList, false);
		}

		Vector<?> terminalObservers = device.getTerminalObservers();

		for (int j = 0; j < terminalObservers.size(); j++) {
			TsapiTerminalMonitor callback = (TsapiTerminalMonitor) terminalObservers
					.elementAt(j);
			callback.deliverEvents(eventList, false);
		}
	}

	void doMonitorEnded(int cause, int xrefID, Object monitored,
			Object privateData) {
		int jtapiCause = getJtapiCause(cause);

		log.info("Monitor Ended, jtapiCause = " + jtapiCause);

		if ((monitored instanceof TSDevice)) {
			((TSDevice) monitored).removeObservers(jtapiCause, privateData,
					xrefID);
		} else if ((monitored instanceof TSCall)) {
			((TSCall) monitored).removeObservers(jtapiCause, privateData,
					xrefID);
		}
	}

	void doConfXfer(int jtapiCause, CSTAConnectionID primaryConnID,
			CSTAConnectionID secondaryConnID, CSTAConnection[] connList,
			Object privateData, short cause) {
		if ((connList == null) || (connList.length == 0)) {
			return;
		}

		TSCall call = null;
		TSCall secCall = null;

		if ((this.provider.isLucentV5()) && (primaryConnID != null)
				&& (primaryConnID.getCallID() == 0)) {
			primaryConnID = null;
		}
		if ((this.provider.isLucentV5()) && (secondaryConnID != null)
				&& (secondaryConnID.getCallID() == 0)) {
			secondaryConnID = null;
		}

		if (connList.length > 0) {
			call = this.provider.createCall(connList[0].getParty().getCallID(),
					privateData);

			call = this.provider.validateCall(privateData, call, connList[0]
					.getParty().getCallID());

			if (primaryConnID == null) {
				if ((secondaryConnID != null)
						&& (connList[0].getParty().getCallID() != secondaryConnID
								.getCallID())) {
					if (call.getTSState() != 33)
						;
				}

			} else if (secondaryConnID == null) {
				if ((primaryConnID != null)
						&& (connList[0].getParty().getCallID() != primaryConnID
								.getCallID())) {
					if (call.getTSState() != 33)
						;
				}

			} else if ((connList[0].getParty().getCallID() != primaryConnID
					.getCallID())
					&& (connList[0].getParty().getCallID() != secondaryConnID
							.getCallID())) {
				if (call.getTSState() == 33) {
					return;
				}
			}

		}

		if (primaryConnID != null) {
			call = this.provider.createCall(primaryConnID.getCallID(),
					privateData);

			call = this.provider.validateCall(privateData, call,
					primaryConnID.getCallID());

			extractAndProcessConsultMode(cause, privateData, call);
		}

		if (secondaryConnID != null) {
			secCall = this.provider.createCall(secondaryConnID.getCallID(),
					privateData);

			secCall = this.provider.validateCall(privateData, secCall,
					secondaryConnID.getCallID());

			extractAndProcessConsultMode(cause, privateData, secCall);
		}

		if (call == secCall) {
			secCall = null;
		}

		if ((call != null) && (call.getTSState() == 34) && (secCall != null)
				&& (secCall.getTSState() == 34)) {
			return;
		}

		boolean swapCalls = false;
		if ((secCall != null) && (secCall.getSnapshotCallConfPending())) {
			log.info("a snapshot call is pending for call " + secCall
					+ " call id =" + secCall.callID);
			swapCalls = true;
		} else if ((call != null) && (call.getCallObservers().size() == 0)
				&& (secCall != null) && (secCall.getCallObservers().size() > 0)) {
			swapCalls = true;
		} else if ((call != null) && (call.getCallObservers().size() > 0)
				&& (secCall != null)
				&& (secCall.getCallObservers().size() == 0)) {
			call.setReceivedCallClearedTransfer(false);
		} else if ((call == null) || (secCall != null)) {
			if ((call != null) && (call.doHeldTalkingMatch(secCall))) {
				swapCalls = true;
			} else if ((secCall == null) || (secCall.getTSState() != 34)) {
				if ((call != null) && (call.getTSState() == 34)) {
					swapCalls = true;
				} else if ((call == null) && (secCall != null)) {
					swapCalls = true;
				} else {
					if (call == null) {
						return;
					}
					if ((secCall != null)
							&& (secCall.getCallID() == connList[0].getParty()
									.getCallID())) {
						swapCalls = true;
					}
				}

			}

		}

		if (swapCalls) {
			TSCall tempCall = call;
			call = secCall;
			secCall = tempCall;
		}

		call.setCSTACause(cause);

		Vector<?> oldConns = null;
		Vector<?> oldSecConns = null;

		oldConns = call.getConnections();
		if (connList.length > 0) {
			call.setCallID(connList[0].getParty().getCallID());
			call = call.getHandOff();
		}
		call.setNeedSnapshot(false);
		if (secCall != null) {
			oldSecConns = secCall.getConnections();
		}

		Vector<TSEvent> priEventList = new Vector<TSEvent>();
		Vector<TSEvent> secEventList = new Vector<TSEvent>();

		TSConnection conn = null;
		TSConnection secConn = null;
		TSConnection tc = null;

		Vector<TSConnection> newConnections = new Vector<TSConnection>();
		TSDevice device = null;

		Vector<TSConnection> snapConnections = new Vector<TSConnection>();

		for (int i = 0; i < connList.length; i++) {
			boolean found = false;
			device = this.provider.createDevice(connList[i].getStaticDevice(),
					connList[i].getParty());
			if (device != null) {
				if (oldConns != null) {
					Vector<?> oldConnections = new Vector<Object>(oldConns);
					for (int j = 0; j < oldConnections.size(); j++) {
						conn = (TSConnection) oldConnections.elementAt(j);
						Vector<?> cv = conn.getTermConns();
						if ((cv != null) && (cv.size() > 0)) {
							Vector<?> termConns = new Vector<Object>(cv);
							for (int k = 0; k < termConns.size(); k++) {
								tc = (TSConnection) termConns.elementAt(k);
								if (tc.getTSDevice() == device) {
									tc.setConnID(connList[i].getParty());
									newConnections.addElement(tc);
									found = true;
									int tcs = tc.getCallControlTermConnState();
									if (tcs != 96) {
										break;
									}
									if (this.provider.getCapabilities()
											.getSnapshotCallReq() != 0) {
										snapConnections.addElement(tc
												.getTSConn());
									}

									tc.setConnectionState(91, null);
									tc.setTermConnState(103, null);
									break;
								}

							}

						} else if (conn.getTSDevice() == device) {
							try {
								conn.setConnID(connList[i].getParty());
							} catch (TsapiPlatformException e) {
								log.error("TSEventHandler.doConfXfer() caught TsapiPlatformException from setConnID() while processing connList, i="
										+ i
										+ ", j="
										+ j
										+ ", party="
										+ connList[i].getParty());
								log.error(e.getMessage(), e);
								log.trace("Dumping call (" + call + "):");
								call.dump("   ");
								log.trace("Dumping conn (" + conn + "):");
								conn.dump("   ");
								log.trace("Dumping provider (" + this.provider
										+ "):");
								this.provider.dump("   ");

								throw e;
							}

							newConnections.addElement(conn);
							found = true;
							int cs = conn.getCallControlConnState();
							if (cs != 80) {
								break;
							}
							if (this.provider.getCapabilities()
									.getSnapshotCallReq() != 0) {
								snapConnections.addElement(conn.getTSConn());
							}

							conn.setConnectionState(91, null);
							conn.setTermConnState(103, null);
							break;
						}

						if (found) {
							break;
						}
					}
				}
				if (!found) {
					Vector<TSEvent> tempEventList = new Vector<TSEvent>();

					conn = this.provider.createTerminalConnection(
							connList[i].getParty(), device, tempEventList,
							device);

					int oldConnState = conn.getCallControlConnState();
					int oldTermConnState = conn.getCallControlTermConnState();

					if ((oldConnState == 89) || (oldTermConnState == 102)) {
						tempEventList = new Vector<TSEvent>();

						conn.delete();
						this.provider.dumpConn(connList[i].getParty());

						conn = this.provider.createTerminalConnection(
								connList[i].getParty(), device, tempEventList,
								device);
					}

					if (oldSecConns != null) {
						Vector<?> oldSecConnections = new Vector<Object>(oldSecConns);
						for (int j = 0; j < oldSecConnections.size(); j++) {
							secConn = (TSConnection) oldSecConnections
									.elementAt(j);
							Vector<?> cv = secConn.getTermConns();
							if ((cv != null) && (cv.size() > 0)) {
								Vector<?> termConns = new Vector<Object>(cv);
								for (int k = 0; k < termConns.size(); k++) {
									tc = (TSConnection) termConns.elementAt(k);
									if (conn.getTSDevice() == tc.getTSDevice()) {
										for (int m = 0; m < tempEventList
												.size(); m++) {
											priEventList
													.addElement(tempEventList
															.elementAt(m));
										}
										conn.setConnectionState(
												tc.getCallControlConnState(),
												priEventList);
										conn.setTermConnState(tc
												.getCallControlTermConnState(),
												priEventList);
										found = true;
										break;
									}

								}

							} else if (conn.getTSDevice() == secConn
									.getTSDevice()) {
								for (int m = 0; m < tempEventList.size(); m++) {
									priEventList.addElement(tempEventList
											.elementAt(m));
								}
								conn.setConnectionState(
										secConn.getCallControlConnState(),
										priEventList);
								conn.setTermConnState(
										secConn.getCallControlTermConnState(),
										priEventList);
								found = true;
								break;
							}

							if (found) {
								break;
							}
						}
					}
					if (!found) {
						if (this.provider.getCapabilities()
								.getSnapshotCallReq() != 0) {
							snapConnections.addElement(conn.getTSConn());
						}

						for (int m = 0; m < tempEventList.size(); m++) {
							priEventList.addElement(tempEventList.elementAt(m));
						}
						conn.setConnectionState(91, priEventList);
						conn.setTermConnState(103, priEventList);
					}

					newConnections.addElement(conn);
				}
			}
		}
		Vector<TSEvent> eventList = new Vector<TSEvent>();

		call.replaceConnections(newConnections, eventList);
		for (int m = 0; m < priEventList.size(); m++) {
			eventList.addElement(priEventList.elementAt(m));
		}

		if (secCall != null) {
			if (swapCalls) {
				secCall.delayVDNremoveCallFromDomain = true;
				secCall.setState(34, secEventList);
				secCall.delayVDNremoveCallFromDomain = false;
				call.copyStuff(secCall);
			} else {
				secCall.setState(34, secEventList);
			}

		}

		doCallMonitors(secCall, secEventList, jtapiCause, privateData);

		TSDevice distributingDevice = null;
		TSDevice distributingVDN = null;
		if ((privateData instanceof LucentConferencedEvent)) {
			LucentConferencedEvent luPrivData = (LucentConferencedEvent) privateData;
			TsapiAddress address = TsapiPromoter.promoteDeviceIDToAddress(
					this.provider, luPrivData.getDistributingDevice_asn());

			if (address != null) {
				distributingDevice = address.getTSDevice();
				call.setDistributingDevice(distributingDevice);
			}
			call.setOCI(luPrivData.getOriginalCallInfo());
			if ((privateData instanceof LucentV5ConferencedEvent)) {
				LucentV5ConferencedEvent luV5PrivData = (LucentV5ConferencedEvent) privateData;
				call.setUCID(luV5PrivData.getUcid());

				if ((privateData instanceof LucentV7ConferencedEvent)) {
					LucentV7ConferencedEvent luV7PrivData = (LucentV7ConferencedEvent) privateData;
					call.setDeviceHistory(TsapiPromoter
							.promoteDeviceHistory(luV7PrivData
									.getDeviceHistory()));

					if (luV7PrivData.getDistributingVDN_asn() != null) {
						distributingVDN = TsapiPromoter
								.promoteExtendedDeviceIDToTSDevice(
										this.provider,
										luV7PrivData.getDistributingVDN_asn());

						call.setDistributingVDN(distributingVDN);
					}
				}
			}

		} else if ((privateData instanceof LucentTransferredEvent)) {
			LucentTransferredEvent luPrivData = (LucentTransferredEvent) privateData;
			TsapiAddress address = TsapiPromoter.promoteDeviceIDToAddress(
					this.provider, luPrivData.getDistributingDevice_asn());

			if (address != null) {
				distributingDevice = address.getTSDevice();
				call.setDistributingDevice(distributingDevice);
			}
			call.setOCI(luPrivData.getOriginalCallInfo());
			if ((privateData instanceof LucentV5TransferredEvent)) {
				LucentV5TransferredEvent luV5PrivData = (LucentV5TransferredEvent) privateData;
				call.setUCID(luV5PrivData.getUcid());

				if ((privateData instanceof LucentV7TransferredEvent)) {
					LucentV7TransferredEvent luV7PrivData = (LucentV7TransferredEvent) privateData;
					call.setDeviceHistory(TsapiPromoter
							.promoteDeviceHistory(luV7PrivData
									.getDeviceHistory()));

					if (luV7PrivData.getDistributingVDN_asn() != null) {
						distributingVDN = TsapiPromoter
								.promoteExtendedDeviceIDToTSDevice(
										this.provider,
										luV7PrivData.getDistributingVDN_asn());

						call.setDistributingVDN(distributingVDN);
					}
				}
			}

		}

		if ((privateData instanceof LucentTrunkConnectionMapping)) {
			CSTATrunkInfo[] trunkList = ((LucentTrunkConnectionMapping) privateData)
					.getLucentTrunkInfo();

			LucentTrunkInfoMapItem[] trunkMapItems = LucentTrunkInfoMapItem
					.createLucentTrunkInfoMapItemArray(trunkList, this.provider);

			if (trunkMapItems != null) {
				for (int i = 0; i < trunkMapItems.length; i++) {
					LucentTrunkInfoMapItem item = trunkMapItems[i];
					if (item != null) {
						item.interLinkConnectionCallAndTrunk(eventList);
					}
				}

			}

		}

		call.moveStuff(secCall);

		if (secCall != null) {
			secCall.setStateForVDN();
		}
		if (snapConnections.size() > 0) {
			call.setNeedSnapshot(true);

			SnapshotCallExtraConfHandler handler = new XferConfSnapshotCallConfHandler(
					this, call, jtapiCause, privateData, snapConnections);

			call.doSnapshot(
					((TSConnection) snapConnections.elementAt(0)).getConnID(),
					handler, false);
		}
		TransferredEventParams transferredEventParams;
		if ((jtapiCause == 212) || (jtapiCause == 207)) {
			TSCall call1 = null;
			TSCall call2 = null;
			if (primaryConnID != null)
				call1 = this.provider.findCall(primaryConnID.getCallID());
			if (secondaryConnID != null)
				call2 = this.provider.findCall(secondaryConnID.getCallID());
			ArrayList<TSCall> callList = new ArrayList<TSCall>();
			if (call1 != null)
				callList.add(call1);
			if (call2 != null)
				callList.add(call2);
			transferredEventParams = new TransferredEventParams(callList);
			for (TSEvent ev : eventList) {
				ev.setTransferredEventParams(transferredEventParams);
			}
		}
		doCallMonitors(call, eventList, jtapiCause, privateData);

		if (!call.checkForMonitors()) {
			call.setNeedSnapshot(true);
		}
	}

	int getJtapiCause(int cstaCause) {
		switch (cstaCause) {
		case -1:
		case 12:
		case 17:
		case 22:
		case 24:
		case 27:
		case 31:
		case 34:
			return 100;
		case 1:
			return 207;
		case 2:
			return 202;
		case 3:
			return 203;
		case 4:
			return 204;
		case 5:
			return 102;
		case 6:
		case 7:
		case 8:
		case 9:
		case 28:
			return 210;
		case 10:
			return 205;
		case 11:
			return 206;
		case 13:
		case 16:
		case 19:
		case 46:
		case 60:
		case 65:
		case 80:
		case 81:
		case 87:
			return 103;
		case 14:
			return 208;
		case 15:
			return 104;
		case 18:
			return 105;
		case 20:
			return 108;
		case 21:
			return 109;
		case 23:
			return 302;
		case 25:
			return 209;
		case 26:
		case 30:
			return 107;
		case 29:
			return 211;
		case 32:
		case 52:
			return 212;
		case 33:
			return 213;
		case 0:
		case 35:
		case 36:
		case 37:
		case 38:
		case 39:
		case 40:
		case 41:
		case 42:
		case 43:
		case 44:
		case 45:
		case 47:
		case 48:
		case 49:
		case 50:
		case 51:
		case 53:
		case 54:
		case 55:
		case 56:
		case 57:
		case 58:
		case 59:
		case 61:
		case 62:
		case 63:
		case 64:
		case 66:
		case 67:
		case 68:
		case 69:
		case 70:
		case 71:
		case 72:
		case 73:
		case 74:
		case 75:
		case 76:
		case 77:
		case 78:
		case 79:
		case 82:
		case 83:
		case 84:
		case 85:
		case 86:
		}
		return 101;
	}

	public void eventDistributorException(Exception e) {
		if (log != null) {
			log.error("Event Distributor Exception - shutting down provider "
					+ this.provider);
			log.error(e.getMessage(), e);
		} else {
			System.out
					.println("Event Distributor Exception - shutting down provider "
							+ this.provider);
			e.printStackTrace();
		}

		if ((e.getCause() instanceof EOFException)) {
			this.provider.setServerStreamClosed(true);
		}
		this.provider.shutdown();
	}

	public String toString() {
		return this.provider.toString();
	}
}