package com.fcn.spdy;

import java.util.ArrayList;
import java.util.List;

import com.fcn.spdy.pojo.CustomPacket;

public class FlowObject {
	int fthroughput ;
	int rthroughput ;
	int headerSize ;

	int rtt;
	String src ;
	String dest;
	String plt;
	List<Integer> fthroughputEverySec = new ArrayList<Integer>();
	List<Integer> rthroughputEverySec = new ArrayList<Integer>();
	
	List<CustomPacket> quicPackets = new ArrayList<CustomPacket>();
	public void setfTP(int tp){
		fthroughputEverySec.add(tp);
	}
	public void setrTP(int tp){
		rthroughputEverySec.add(tp);
	}
	public String getPlt() {
		return plt;
	}
	public void setPlt(String plt) {
		this.plt = plt;
	}
	List<CustomPacket> fdir = new ArrayList<CustomPacket>();
	List<CustomPacket> rdir = new ArrayList<CustomPacket>();
	
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public String getDest() {
		return dest;
	}
	public void setDest(String dest) {
		this.dest = dest;
	}

	public int getFthroughput() {
		return fthroughput;
	}
	public void setFthroughput(int fthroughput) {
		this.fthroughput = fthroughput;
	}
	public int getRthroughput() {
		return rthroughput;
	}
	public void setRthroughput(int rthroughput) {
		this.rthroughput = rthroughput;
	}
	public void addFdir(CustomPacket packet){
		
		fdir.add(packet);
	}
	public void addRdir(CustomPacket packet){
		rdir.add(packet);
	}
	

	public int getRtt() {
		return rtt;
	}
	public void setRtt(int rtt) {
		this.rtt = rtt;
	}
	@Override
	public String toString(){
		
		return "src: "+src+" dest: "+dest+" throughput1: "+rthroughput+" throughput2: "+fthroughput+ " plt : "+plt+" seconds "+"\n Fdir ::"+fdir+"\n Rdir ::"+rdir+"\n ftp ::"+fthroughputEverySec+"\n rtp ::"+rthroughputEverySec;
		
	}
	
	public int getHeaderSize() {
		return headerSize;
	}
	public void setHeaderSize(int headerSize) {
		this.headerSize = headerSize;
	}
}

