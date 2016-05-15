package com.fcn.spdy;

public class CustomTCP {


	String sourcePort ;
	String destinationPort;
	String sequenceNumber;
	String acknowledgementNumber;
	boolean isForward ;

	int offset;
	int reserved;
	long dataOffSet;
	long mss;
	long window;
	long iCW;
	long dataSize;
	
	long forwardTimeStamp;
	long backwardTimeStamp;

	boolean isURG=false;
	boolean isACK = false;
	boolean isPSH = false;
	
	boolean isRST = false;
	boolean isSYN = false;
	boolean isFIN = false;
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();

		sb.append(getSourcePort());
		sb.append('\t');

		sb.append(getDestinationPort());
		sb.append('\t');

		sb.append(getSequenceNumber());
		sb.append('\t');

		sb.append(getAcknowledgementNumber());
		sb.append('\t');

		sb.append(isSYN);
		sb.append('\t');

		sb.append(isACK);
		sb.append('\t');

		sb.append(isFIN);
		sb.append('\t');

		sb.append(getWindow());
		sb.append('\t');
		
		sb.append(getMss());
		sb.append('\t');

		sb.append(getiCW());
		sb.append('\t');
		
		sb.append(getForwardTimeStamp());
		sb.append('\t');
		
		sb.append(getBackwardTimeStamp());
		sb.append('\t');
		return sb.toString();
	}


	
/*	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Source Port ::");
		sb.append(getSourcePort());
		sb.append('\n');
		sb.append("Destination Port ::");
		sb.append(getDestinationPort());
		sb.append('\n');
		sb.append("Sequence Number ::");
		sb.append(getSequenceNumber());
		sb.append('\n');
		sb.append("Ack Number ::");
		sb.append(getAcknowledgementNumber());
		sb.append('\n');
		sb.append("SYN ::");
		sb.append(isSYN);
		sb.append('\n');
		sb.append("Ack ::");
		sb.append(isACK);
		sb.append('\n');
		sb.append("FIN ::");
		sb.append(isFIN);
		sb.append('\n');
		sb.append("Window Size ::");
		sb.append(getWindow());
		sb.append('\n');
		
		return sb.toString();
	}
*/	public  void processFlags(String s){
		int byt = Integer.parseInt(s,16);


		int one =1;
		while(one<64){
			if( (byt&one) == one){
				switch(one){
				case 1:
					isFIN = true;
					break;
				case 2:
					isSYN = true;
					break;
				case 4:
					isRST = true;
					break;
				case 8:
					isPSH = true;
					break;
					
				case 16:
					isACK = true;
					break;
				case 32:
					isURG = true;
					break;
				}
			}
			one= one<<1;
			
		}
	}

	public String getSourcePort() {
		return sourcePort;
	}


	public void setSourcePort(String sourcePort) {
		this.sourcePort = sourcePort;
	}


	public String getDestinationPort() {
		return destinationPort;
	}


	public void setDestinationPort(String destinationPort) {
		this.destinationPort = destinationPort;
	}


	public String getSequenceNumber() {
		return sequenceNumber;
	}


	public void setSequenceNumber(String sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}


	public String getAcknowledgementNumber() {
		return acknowledgementNumber;
	}


	public void setAcknowledgementNumber(String acknowledgementNumber) {
		this.acknowledgementNumber = acknowledgementNumber;
	}


	public int getOffset() {
		return offset;
	}


	public void setOffset(int offset) {
		this.offset = offset;
	}


	public int getReserved() {
		return reserved;
	}


	public void setReserved(int reserved) {
		this.reserved = reserved;
	}


	public long getWindow() {
		return window;
	}


	public void setWindow(long window) {
		this.window = window;
	}

	public long getDataOffSet() {
		return dataOffSet;
	}

	public void setDataOffSet(long dataOffSet) {
		this.dataOffSet = dataOffSet;
	}


	public long getMss() {
		return mss;
	}



	public void setMss(long mss) {
		this.mss = mss;
	}

	public long getiCW() {
		return iCW;
	}



	public void setiCW(long iCW) {
		this.iCW = iCW;
	}

	public long getForwardTimeStamp() {
		return forwardTimeStamp;
	}



	public void setForwardTimeStamp(long forwardTimeStamp) {
		this.forwardTimeStamp = forwardTimeStamp;
	}



	public long getBackwardTimeStamp() {
		return backwardTimeStamp;
	}



	public void setBackwardTimeStamp(long backwardTimeStamp) {
		this.backwardTimeStamp = backwardTimeStamp;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	public long getDataSize() {
		return dataSize;
	}



	public void setDataSize(long dataSize) {
		this.dataSize = dataSize;
	}

	public boolean isForward() {
		return isForward;
	}
	public void setForward(boolean isForward) {
		this.isForward = isForward;
	}

}
