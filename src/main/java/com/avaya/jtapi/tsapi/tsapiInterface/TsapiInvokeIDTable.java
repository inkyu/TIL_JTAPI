package com.avaya.jtapi.tsapi.tsapiInterface;

import java.util.Hashtable;
import java.util.Iterator;
import org.apache.log4j.Logger;

public class TsapiInvokeIDTable {
	private static Logger log = Logger.getLogger(TsapiInvokeIDTable.class);
	private Hashtable<Integer, TSInvokeID> invokeIDTbl;
	private int nextInvokeID = 1;
	private String debugID;

	public TsapiInvokeIDTable(String _debugID) {
		this.debugID = _debugID;
		this.invokeIDTbl = new Hashtable<Integer, TSInvokeID>();
	}

	public TSInvokeID allocTSInvokeID(ConfHandler handler) {
		synchronized (this.invokeIDTbl) {
			TSInvokeID invokeID = new TSInvokeID(this.nextInvokeID, handler,
					this.debugID);
			Object oldObj = this.invokeIDTbl.put(
					new Integer(this.nextInvokeID), invokeID);
			if (oldObj != null) {
				log.info("NOTICE: invokeIDTbl.put() replaced " + oldObj
						+ " for " + this.debugID);
			}
			this.nextInvokeID += 1;

			return invokeID;
		}
	}

	public void deallocTSInvokeID(TSInvokeID invokeID) {
		if (this.invokeIDTbl.remove(new Integer(invokeID.getValue())) == null) {
			log.info("couldn't dealloc invokeID " + invokeID.getValue()
					+ " for " + this.debugID);
		}
	}

	public TSInvokeID getTSInvokeID(int value) {
		TSInvokeID invokeID = (TSInvokeID) this.invokeIDTbl.get(new Integer(
				value));
		if ((invokeID == null) || (invokeID.getValue() != value)) {
			log.info("couldn't find invokeID " + value + " for " + this.debugID);
		}
		return invokeID;
	}

	public void requestTimeOut(ConfHandler handler) {
		Iterator<TSInvokeID> ids = this.invokeIDTbl.values().iterator();
		while (ids.hasNext()) {
			TSInvokeID id = (TSInvokeID) ids.next();
			if (id.getConfHandler().equals(handler)) {
				deallocTSInvokeID(id);
				break;
			}
		}
	}

	public void shutdown() {
		Iterator<TSInvokeID> ids = this.invokeIDTbl.values().iterator();
		while (ids.hasNext()) {
			TSInvokeID id = (TSInvokeID) ids.next();
			id.setConf(null);
		}
	}
}