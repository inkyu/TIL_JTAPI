package com.avaya.jtapi.tsapi;

public class UserToUserInfo {
	protected short type;
	protected byte[] data;

	public UserToUserInfo(String _data) {
		this.type = 4;
		this.data = _data.getBytes();
	}

	public UserToUserInfo(byte[] _data) {
		this.type = 0;
		this.data = _data;
	}

	public String getString() {
		return new String(this.data);
	}

	public byte[] getBytes() {
		return this.data;
	}

	public boolean isAscii() {
		return this.type == 4;
	}

	public UserToUserInfo(byte[] _data, short _type) {
		this.type = _type;
		this.data = _data;
	}

	public short getType() {
		return this.type;
	}
}