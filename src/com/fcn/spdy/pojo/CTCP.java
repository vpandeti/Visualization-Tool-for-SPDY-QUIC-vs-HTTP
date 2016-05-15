package com.fcn.spdy.pojo;

import org.jnetpcap.protocol.tcpip.Tcp;

public class CTCP {
	long ack;
	int sourcePort;
	int destinationPort;
	long seq;
	boolean inSyn;
	boolean isAck;
	boolean isFyn;
	int window;
	
	
	public CTCP(Tcp tcp){
		this.sourcePort = tcp.source();
		this.destinationPort = tcp.destination();
		this.ack = tcp.ack();
		this.seq = tcp.seq();
		
		this.isAck = tcp.flags_ACK();
		this.inSyn = tcp.flags_SYN();
		this.isFyn = tcp.flags_FIN();
		
		this.window = tcp.window();
	}

	
	public String toString(){
		
		return "TCP :: sourcePort ::"+sourcePort+" destinationPort :: "+destinationPort+"Ack ::"+ack+" isSyn ::"+inSyn+" WindowSize ::"+window+"\n";
	}
}
