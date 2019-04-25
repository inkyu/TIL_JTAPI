package com.avaya.jtapi.tsapi.impl.core;

import java.net.InetSocketAddress;
import java.util.Collection;

final class ConnectStringData {
	public final String serverId;
	public final String loginId;
	public final String password;
	public final Collection<InetSocketAddress> telephonyServers;
	public final String url;

	ConnectStringData(String serverId, String loginId, String password,
			Collection<InetSocketAddress> telephonyServers, String url) {
		this.serverId = serverId;
		this.loginId = loginId;
		this.password = password;
		this.telephonyServers = telephonyServers;
		this.url = url;
	}

	public String toString() {
		return this.serverId + ";loginID=" + this.loginId + ";passwd="
				+ this.password + ";servers=" + this.telephonyServers;
	}
}