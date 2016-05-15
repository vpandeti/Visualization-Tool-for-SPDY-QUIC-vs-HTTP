package com.fcn.spdy;

import java.math.BigInteger;

import org.jnetpcap.packet.JPacket;
import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.protocol.tcpip.Http;
import org.jnetpcap.protocol.tcpip.Tcp;
import org.jnetpcap.protocol.tcpip.Udp;

import com.fcn.spdy.pojo.CHttp;
import com.fcn.spdy.pojo.CIP;
import com.fcn.spdy.pojo.CTCP;
import com.fcn.spdy.pojo.CUDP;
import com.fcn.spdy.pojo.CustomPacket;

public class TCPUtil {

	static Udp udp = new Udp();
	static Tcp tcp= new Tcp();
	static Http http = new Http();
	static Ip4 ip = new Ip4();
	
	public static CustomPacket processPcapPacket(JPacket p){
		CustomPacket cp = new CustomPacket();
		
		if(p.hasHeader(Tcp.ID)){
			p.getHeader(tcp);
			CTCP ctcp = new CTCP(tcp);
			cp.setTcp(ctcp);
		}
		 if(p.hasHeader(Udp.ID)){
			p.getHeader(udp);
			CUDP cudp = new CUDP(udp);
			cp.setUdp(cudp);
			if(cudp.src==443||cudp.src==80){
				cp.setPacketType(1);
			}
		}
		 if(p.hasHeader(Http.ID)){
			p.getHeader(http);
			CHttp chttp = new CHttp(http);
			cp.setHttp(chttp);
			//System.out.println(http.getAnnotatedHeader());
		}
		 if(p.hasHeader(Ip4.ID)){
			p.getHeader(ip);
			CIP cip = new CIP(ip);
			cp.setIp(cip);
			cp.setSrcIP(cip.getSrcIP());
			cp.setDestIP(cip.getDestIP());
		}
		return cp;
	}
	
	
	public static CustomTCP processUDP(Udp udp){

		CustomTCP tu = new CustomTCP();

		tu.setSourcePort(Integer.toString(udp.source()));
		tu.setDestinationPort(Integer.toString(udp.destination()));
		/*tu.setSequenceNumber(udp.);
		tu.setAcknowledgementNumber(convertHexString(tcp1.substring(16,24)));
		tu.processFlags(tcp1.substring(24,28));
		tu.setWindow(convertHexInt(tcp1.substring(28,32)));
		tu.setDataOffSet(convertHexInt(tcp1.substring(24,25)));
		//System.out.println(tu.dataOffSet);
		int off = (int)tu.dataOffSet-5;
		off = off*8;
		int pntr = 8;
		while(off>0){
			int a = 0;
			long first = convertHexInt(tcp2.substring(pntr, pntr+2));
			if(first ==0){
				off=0;
				a=0;
			}else if(first ==1){
				a=a+2;
				pntr=pntr+2;
			}else if(first ==2){
				tu.setMss(convertHexInt(tcp2.substring(pntr+4,pntr+8)));
				pntr=pntr+8;
				a=a+8;
			}else if(first==3){
				pntr=pntr+6;
				a=a+6;
			}else if(first ==4){
				pntr = pntr+4;
				a=a+4;
			}else if(first ==5){
				pntr = pntr+(int)convertHexInt(tcp2.substring(pntr+2, pntr+4));
			}else if(first ==8){
				long timeStamp1 = convertHexInt(tcp2.substring(pntr+4, pntr+12));
				long timeStamp2 = convertHexInt(tcp2.substring(pntr+12, pntr+20));
				System.out.println(timeStamp1);
				System.out.println(timeStamp2);
				tu.setForwardTimeStamp(timeStamp1);
				tu.setBackwardTimeStamp(timeStamp2);
				pntr = pntr+20;
				a=a+20;
			}
			off=off-(a)*4;
		}
		tu.setiCW(getIntialCongestionWindow(tu.getMss()));
*/		//tu.setDataSize(dataSize);
		return tu;
	}
	
	public static long getIntialCongestionWindow(long mss){
		
		if (mss > 2190)
			return 2 * mss;
		else if ((mss > 1095) && (mss <= 2190))
			return 3 * mss;
		else if (mss <= 1095)
			return 4 * mss;

		return mss;
	}
	public static BigInteger hexStringToByteArray(String s) {
	     return new BigInteger(s, 16);
	}
	
	public static String convertHexString(String s){
		return String.valueOf(convertHexInt(s));
	}
	
	public  static long convertHexInt(String s){
		return Long.parseLong(s,16);
	}

}


