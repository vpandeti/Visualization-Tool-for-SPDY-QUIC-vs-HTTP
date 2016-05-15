package com.fcn.spdy.pojo;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.jnetpcap.protocol.network.Ip4;

public class CIP {


	String srcIP;
	public String getSrcIP() {
		return srcIP;
	}
	public void setSrcIP(String srcIP) {
		this.srcIP = srcIP;
	}
	public String getDestIP() {
		return destIP;
	}
	public void setDestIP(String destIP) {
		this.destIP = destIP;
	}

	String destIP;
	int ttl;
	public CIP(Ip4 ip){
		this.ttl = ip.ttl();
		this.srcIP = getSIP(ip.source());
		this.destIP = getDIP(ip.destination());
	}
	public String getSIP(byte[] sourceIPByte){
		String sourceIP="";
		InetAddress address = null;
		try {
			 address = InetAddress.getByAddress(sourceIPByte);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sourceIP = address.toString();

		return sourceIP;
	}
	public String getDIP(byte[] destinationIPByte){

		String destinationIP="";


		InetAddress address2 = null;
		try {
			 address2 = InetAddress.getByAddress(destinationIPByte);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		destinationIP = address2.toString();

		return destinationIP;
	}

	public String toString(){

		return "IP4:: SourceIP :"+srcIP+" DestinationIP :" +destIP+ " ttl :"+ttl;
	}
}
