package com.fcn.spdy;

import java.util.ArrayList;
import java.util.List;

import org.jnetpcap.Pcap;
import org.jnetpcap.packet.JFlow;
import org.jnetpcap.packet.JFlowMap;
import org.jnetpcap.packet.JPacket;
import org.jnetpcap.packet.format.FormatUtils;
import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.protocol.tcpip.Http;
import org.jnetpcap.protocol.tcpip.Tcp;
import org.jnetpcap.protocol.tcpip.Udp;

import com.google.gson.Gson;

public class MainPCAPAnalyser {

	final static Tcp tcp = new Tcp(); 
	final static Ip4 ip = new Ip4();
	final static Http http = new Http();
	final static Udp udp = new Udp();
	static StringBuffer finalJsonString = new StringBuffer();
	static Gson gson = new Gson();
	
	public static List<FlowObject> main(String file, String filePath) {
        final StringBuilder errbuf = new StringBuilder(); 
      //  final String file = "F:\\sem2\\cs534\\HW\\2\\http_first_sample.pcap";
        System.out.printf("Opening file for reading: %s%n", file);  


        Pcap pcap = Pcap.openOffline(file, errbuf);  
  
        if (pcap == null) {  
            System.err.printf("Error while opening device for capture: "  
                + errbuf.toString());  
            return null;  
        }  

        List<FlowObject> listOfFlow = new ArrayList<FlowObject>();
        try{
        	
	        JFlowMap superFlowMap = new JFlowMap();  
	
	        pcap.loop(1000, superFlowMap, null);  
	  
	//        System.out.printf("superFlowMap::%s%n", superFlowMap);  
	        for (JFlow flow : superFlowMap.values()) {  
	       // 	System.out.println(flow);
	        	FlowObject fo = new FlowObject();
	        	long first =0;
	        	long second = 0 ;
	        	int size =0;
	        	int httpHeaderSize = 0;
	        	int temp = 0;
	        	int n=1;

	        	if(flow.getAll().size()>0){

	        		if (flow.isReversable()) {  

		                 List<JPacket> reverse = flow.getReverse();  
		    
		                 for (JPacket p : reverse) {  
		                	 if(fo.src==null&&p.hasHeader(ip)){
		                		 String dst = FormatUtils.ip(ip.destination());
		                         String src = FormatUtils.ip(ip.source());
		                         fo.setDest(dst);
		                         fo.setSrc(src);
		                	 }
		                	 if(first==0){
		                		 first=p.getCaptureHeader().timestampInMillis();
		                	 }
		                	 second = p.getCaptureHeader().timestampInMillis();

		                	 if(p.hasHeader(udp)){
		                		 p.getHeader(udp);
		                		 size +=(udp.getPayloadOffset()+udp.getPayloadLength());
		                	 }
		                	 else if(p.hasHeader(tcp)){
		                		 p.getHeader(tcp);
		                		 size +=(tcp.getPayloadLength()+tcp.getPayloadOffset());
		                	 }
		                	 if(p.hasHeader(http)){
		                		 p.getHeader(http);
		                		 httpHeaderSize += http.getHeaderLength()+http.getHeaderOffset();
		                	 }
		                	 fo.addRdir(TCPUtil.processPcapPacket(p));
		                	 if(((float)(second-first))/100>n){
			                	 fo.setrTP(size-temp);
			                	 temp =size;
			                	 n=n+1;
			                 }
		                 }
		                 if(second-first!=0){
		                	 System.out.println(size);
		                	 fo.setFthroughput((int) ((long)size*1000/((second-first))));
		                	 fo.setHeaderSize((int) ((long)httpHeaderSize*1000000/((second-first)*1000)));
		                 }
		                 
		                 first=0;
		                 size=0;
		                 temp=0;
		                 List<JPacket> forward = flow.getForward();  
		                 
		                 for (JPacket p : forward) {
		                	 if(fo.src==null&&p.hasHeader(ip)){
		                		 String dst = FormatUtils.ip(ip.destination());
		                		 String src = FormatUtils.ip(ip.source());
		                		 fo.setDest(dst);
		                		 fo.setSrc(src);
		                	 }
		                	 if(first==0){
		                		 first=p.getCaptureHeader().timestampInMillis();
		                	 }
		                	 second = p.getCaptureHeader().timestampInMillis();
		                	 if(p.hasHeader(udp)){
		                		 p.getHeader(udp);
		                		 size +=(udp.getPayloadOffset()+udp.getPayloadLength());
		                	 }
		                	 else if(p.hasHeader(tcp)){
		                		 p.getHeader(tcp);
		                		 size +=(tcp.getPayloadLength()+tcp.getPayloadOffset());
		                	 }
		                	 
		                	 fo.addFdir(TCPUtil.processPcapPacket(p));

		                	 if((float)(second-first)/10>n){
		                		 fo.setfTP(size-temp);
		                		 temp =size;
		                		 n=n+1;
		                	 }
		                 }
		                 if(second-first!=0){
	                		 fo.setRthroughput((int) ((long)size*1000/((second-first))));
	                		 fo.setPlt(""+((second-first)));
	                	 }
		             }
	        	}
	        	listOfFlow.add(fo);
	        }
	//        SuperJFlowUtil.processSJF(superFlowMap);
	    } finally {  
	    	pcap.close();  
	    }  
//        return JsonGenerator.convert(listOfFlow, filePath);
        return listOfFlow;
	}
	
	public static String generateJson(String file, String filePath){
		
		List<FlowObject> listOfFlow = main(file, filePath);
		
		
		return JsonGenerator.convert(listOfFlow, filePath);
	}
}
