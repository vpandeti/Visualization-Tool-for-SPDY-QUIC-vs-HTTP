package com.fcn.spdy;

import java.util.List;

import org.jnetpcap.Pcap;
import org.jnetpcap.packet.JFlow;
import org.jnetpcap.packet.JFlowMap;
import org.jnetpcap.packet.JPacket;
import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.packet.PcapPacketHandler;
import org.jnetpcap.protocol.tcpip.Tcp;
import org.jnetpcap.protocol.tcpip.Udp;

import com.google.gson.Gson;

public class PCAPAnalyser {

	final static Tcp tcp = new Tcp();
	final static Udp udp = new Udp();
	static StringBuffer finalJsonString = new StringBuffer();
	static Gson gson = new Gson();

	public void main(String file) {

		final StringBuilder errbuf = new StringBuilder(); // For any error msgs
		// final String file = "F:\\sem2\\cs534\\HW\\2\\HTTP_SampleB.pcap";
		System.out.printf("Opening file for reading: %s%n", file);
		Pcap pcap = Pcap.openOffline(file, errbuf);

		if (pcap == null) {
			System.err.printf("Error while opening device for capture: "
					+ errbuf.toString());
			return;
		}

		PcapPacketHandler<String> jpacketHandler = new PcapPacketHandler<String>() {

			public void nextPacket(PcapPacket packet, String user) {
				if (packet.hasHeader(Tcp.ID)) {

					packet.getHeader(tcp);
					System.out.println(tcp);

				} else if (packet.hasHeader(Udp.ID)) {
					packet.getHeader(udp);
					CustomTCP customTCP = TCPUtil.processUDP(udp);
					System.out.println(packet);
					System.out.printf("udp.dst_port=%d%n", udp.destination());
					System.out.printf("udp.src_port=%d%n", udp.source());

					finalJsonString.append(gson.toJson(customTCP));

				}
				/*
				 * System.out.println(); System.out.printf("tcp.dst_port=%d%n",
				 * tcp.destination()); System.out.printf("tcp.src_port=%d%n",
				 * tcp.source());
				 */
				// System.out.print(tcp);

				/*
				 * System.out.printf("Received at %s caplen=%-4d len=%-4d %s\n",
				 * new Date(packet.getCaptureHeader().timestampInMillis()),
				 * packet.getCaptureHeader().caplen(), // Length actually
				 * captured packet.getCaptureHeader().wirelen(), // Original
				 * length user // User supplied object );
				 */
				// System.out.println(packet);
			}
		};

		try {
			/* pcap.loop(20, jpacketHandler, "jNetPcap rocks!"); */

			JFlowMap superFlowMap = new JFlowMap();

			pcap.loop(Pcap.LOOP_INFINITE, superFlowMap, null);

			System.out.printf("superFlowMap::%s%n", superFlowMap);

			for (JFlow flow : superFlowMap.values()) {
				System.out.println(flow);
				if (flow.isReversable()) {
					/*
					 * We can get directional flow packets, but only if the flow
					 * is reversable. Not all flows are reversable and this is
					 * determined by the header types. If a flow is not
					 * reversable, flow.getReverse will return empty list, which
					 * is something we don't want to have to process.
					 */

					List<JPacket> forward = flow.getForward();
					for (JPacket p : forward) {
						System.out.printf("%d, ", p.getFrameNumber());
					}
					System.out.println();

					List<JPacket> reverse = flow.getReverse();
					for (JPacket p : reverse) {
						System.out.println(p);
					}
				}
			}
		} finally {

			// System.out.println(finalJsonString.toString());
			// JsonGenerator.convert(finalJsonString.toString());
			pcap.close();
		}
	}
}
