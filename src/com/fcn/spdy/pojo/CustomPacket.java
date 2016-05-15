package com.fcn.spdy.pojo;

public class CustomPacket {

	final int quicType =1;
	final int http2Type = 2;
	final int spdyType = 3;
	
	String srcIP ;
	String destIP;
	int packetType;
	
	public int getPacketType() {
		return packetType;
	}
	public void setPacketType(int packetType) {
		this.packetType = packetType;
	}

	CUDP udp;
	CTCP tcp;
	CHttp http;
	CIP ip;
	
	
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
	public CUDP getUdp() {
		return udp;
	}
	public void setUdp(CUDP udp) {
		this.udp = udp;
	}
	public CTCP getTcp() {
		return tcp;
	}
	public void setTcp(CTCP tcp) {
		this.tcp = tcp;
	}
	public CHttp getHttp() {
		return http;
	}
	public void setHttp(CHttp http) {
		this.http = http;
	}
	public CIP getIp() {
		return ip;
	}
	public void setIp(CIP ip) {
		this.ip = ip;
	}
	
	/*public String toString(){
		String res ="";
		if(tcp!=null){
			res =res+tcp;
		}
		if(udp!=null){
			res =res+udp;
		}
		if(ip!=null){
			res =res+ip;
		}
		if(http!=null){
			res =res+http;
		}
		res =res+"\n";
		return res;
	}*/
	
	public  String toString(){
		StringBuffer sb = new StringBuffer();

	//	sb.append("Src ");
		if(ip!=null){
			
			sb.append(ip.srcIP);
			sb.append('\t');
			
			//sb.append("Dest ");
			sb.append(ip.destIP);
			sb.append('\t');
		}
		
		if(tcp!=null){
			
			//sb.append("Src port ");
			sb.append(tcp.sourcePort);
			sb.append('\t');
			
			//sb.append("Dest Port");
			sb.append(tcp.destinationPort);
			sb.append('\t');
			
			//sb.append("Sequence #");
			sb.append(tcp.seq);
			sb.append('\t');
			
			//sb.append("Acknowle #");
			sb.append(tcp.ack);
			sb.append('\t');
			
		//	sb.append("IS SYN");
			sb.append(tcp.inSyn);
			sb.append('\t');
			
			//sb.append("IS ACK");
			sb.append(tcp.isAck);
			sb.append('\t');
			
			//sb.append("IS FYN");
			sb.append(tcp.isFyn);
			sb.append('\t');
			
			//sb.append("Window ");
			sb.append('\t');
		}
		else if(udp!=null){
			//sb.append("Src port ");
			sb.append(udp.src);
			sb.append('\t');

			//sb.append("Dest Port");
			sb.append(udp.dest);
			sb.append('\t');

		}
		sb.append("MSS ");
		sb.append('\t');

		sb.append("ICW ");
		sb.append('\t');

		sb.append("TimeStamp ");
		sb.append('\t');

		sb.append("TimeStamp ");
		sb.append('\t');

		return sb.toString();

	}

}
