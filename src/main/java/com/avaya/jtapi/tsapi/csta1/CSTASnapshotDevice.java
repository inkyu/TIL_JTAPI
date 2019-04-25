package com.avaya.jtapi.tsapi.csta1;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;

public final class CSTASnapshotDevice extends CSTARequest {
	String snapshotObject;
	public static final int PDU = 122;

	public CSTASnapshotDevice(String _snapshotObject) {
		this.snapshotObject = _snapshotObject;
	}

	public CSTASnapshotDevice() {
	}

	public void encodeMembers(OutputStream memberStream) {
		DeviceID.encode(this.snapshotObject, memberStream);
	}

	public static CSTASnapshotDevice decode(InputStream in) {
		CSTASnapshotDevice _this = new CSTASnapshotDevice();
		_this.doDecode(in);

		return _this;
	}

	public void decodeMembers(InputStream memberStream) {
		this.snapshotObject = DeviceID.decode(memberStream);
	}

	public Collection<String> print() {
		Collection<String> lines = new ArrayList<String>();

		lines.add("CSTASnapshotDevice ::=");
		lines.add("{");

		String indent = "  ";

		lines.addAll(DeviceID.print(this.snapshotObject, "snapshotObject",
				indent));

		lines.add("}");
		return lines;
	}

	public int getPDU() {
		return 122;
	}

	public String getSnapshotObject() {
		return this.snapshotObject;
	}
}