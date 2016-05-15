package com.fcn.spdy.pojo;

import org.jnetpcap.protocol.tcpip.Udp;

public class CUDP {

	public int src;
	public int dest;
	public int length;
	public int checkSum;
	public int payLoadLength ;
	
	
	
	public CUDP(Udp udp){
		
		this.src = udp.source();
		this.dest = udp.destination();
		this.length= udp.length();
		this.checkSum = udp.checksum();
		this.payLoadLength= udp.getPayloadLength()+udp.getPayloadOffset();
		
	}
	public String toString(){
		
		return "UDP :: ";
	}
	
}
