package com.fcn.spdy.pojo;

import org.jnetpcap.protocol.tcpip.Http;

public class CHttp {

	String version;
	public CHttp(Http http){
		this.version = http.getName();

	}
	
	public String toString(){
		
		return " HTTP:: "+version;
	}
}
