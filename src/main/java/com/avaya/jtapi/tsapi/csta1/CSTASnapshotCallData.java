package com.avaya.jtapi.tsapi.csta1;

import com.avaya.jtapi.tsapi.asn1.ASNSequenceOf;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;

public final class CSTASnapshotCallData extends ASNSequenceOf {
	CSTASnapshotCallResponseInfo[] array;

	public CSTASnapshotCallData() {
	}

	public CSTASnapshotCallData(CSTASnapshotCallResponseInfo[] _array) {
		this.array = _array;
	}

	public static void encode(CSTASnapshotCallResponseInfo[] array,
			OutputStream out) {
		CSTASnapshotCallData _this = new CSTASnapshotCallData(array);
		_this.doEncode(array.length, out);
	}

	public void encodeMember(int index, OutputStream memberStream) {
		CSTASnapshotCallResponseInfo.encode(this.array[index], memberStream);
	}

	public static CSTASnapshotCallResponseInfo[] decode(InputStream in) {
		CSTASnapshotCallData _this = new CSTASnapshotCallData();
		_this.doDecode(in);
		return _this.array;
	}

	public void doDecode(InputStream in) {
		super.doDecode(in);

		this.array = new CSTASnapshotCallResponseInfo[this.vec.size()];

		for (int i = 0; i < this.array.length; i++) {
			this.array[i] = ((CSTASnapshotCallResponseInfo) this.vec
					.elementAt(i));
		}
	}

	public Object decodeMember(InputStream memberStream) {
		return CSTASnapshotCallResponseInfo.decode(memberStream);
	}

	public static Collection<String> print(
			CSTASnapshotCallResponseInfo[] array, String name, String _indent) {
		Collection<String> lines = new ArrayList<String>();
		if (array == null) {
			lines.add(_indent + name + " <null>");
			return lines;
		}
		if (name != null) {
			lines.add(_indent + name);
		}
		lines.add(_indent + "{");

		String indent = _indent + "  ";

		for (int i = 0; i < array.length; i++) {
			lines.addAll(CSTASnapshotCallResponseInfo.print(array[i], null,
					indent));
		}
		lines.add(_indent + "}");
		return lines;
	}

	public CSTASnapshotCallResponseInfo[] getArray() {
		return this.array;
	}
}