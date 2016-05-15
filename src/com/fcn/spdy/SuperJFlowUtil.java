package com.fcn.spdy;

import java.util.List;

import org.jnetpcap.packet.JFlow;
import org.jnetpcap.packet.JFlowMap;
import org.jnetpcap.packet.JPacket;

import com.fcn.spdy.pojo.CustomPacket;

public class SuperJFlowUtil {

	
	public static void processSJF(JFlowMap map ){
		  for (JFlow flow : map.values()) {  
			  
			  List<JPacket> list = flow.getAll();
			  	
			  for(JPacket p : list){
				  CustomPacket cp = TCPUtil.processPcapPacket(p);
				  System.out.println(cp);
			  }
		  }
	}
}
