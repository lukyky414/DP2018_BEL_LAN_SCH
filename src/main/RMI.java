package main;

import model.Coup;
import model.IA;
import model.Jeu;
import model.Terrain;

import java.io.IOException;
import java.net.*;

public class RMI {
	public final static int LOCAL=0, HOST=1, JOIN=2;
	public static int ETAT=LOCAL;

	private static InetAddress ip;

	public static void connect(){
		switch(ETAT){
			case LOCAL:
				break;
			case HOST:
				readBroadcast();
				break;
			case JOIN:
				writebroadcast();
				break;
		}
	}

	public static void setPlacement(Terrain terrain){
		switch(ETAT){
			case LOCAL:
				IA.jeu.setTerrainJ2(terrain);
				break;
			case HOST:
				break;
			case JOIN:
				break;
		}
	}

	public static Terrain getPlacement(){
		switch(ETAT){
			case LOCAL:
				IA.jeu.setSe(Jeu.getInstance().getEpoque());
				return IA.placerBateaux();
			case HOST:
				break;
			case JOIN:
				break;
		}
		return null;
	}

	public static void setTir(Coup c){
		switch(ETAT){
			case LOCAL:
				IA.jeu.getTerrainJ1().tirer(c);
				break;
			case HOST:
				break;
			case JOIN:
				break;
		}
	}

	public static Coup getTir(){
		switch(ETAT){
			case LOCAL:
				return IA.tirer();
			case HOST:
				break;
			case JOIN:
				break;
		}
		return null;
	}

	private static String message = "I need an Host for Bataille Navale";

	private static void writebroadcast() {
		try {
			//PARTIE SEND
			DatagramSocket socket = new DatagramSocket();
			socket.setBroadcast(true);

			byte[] buffer = message.getBytes();

			DatagramPacket packet
					= new DatagramPacket(buffer, buffer.length, InetAddress.getByName("255.255.255.255"), 4445);

			socket.send(packet);
			socket.close();
			//FIN PARTIE SEND
			/*On envoie qu'un seul message, si l'host ecoute deja c'est bon on se co.*/



			Thread.sleep(1000);
			//PARTIE READ
			boolean running = true;
			byte[] buf = new byte[256];
			socket = new DatagramSocket(4445);
			while (running) {
				packet
						= new DatagramPacket(buf, buf.length);
				socket.receive(packet);

				ip = packet.getAddress();
				int port = packet.getPort();

				packet = new DatagramPacket(buf, buf.length, ip, port);
				String received
						= new String(packet.getData(), 0, packet.getLength());

				if(received.equals(message)){
					running = false;
				}
			}
			socket.close();
			//FIN PARTIE READ
			/*On read plusieurs message dans le cas ou plusieurs broadcast sont recus. On veut etre sur que l'on recoit le broadcast de notre appli*/

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void readBroadcast(){
		try {
			boolean running = true;
			DatagramSocket socket = new DatagramSocket(4445);
			byte[] buf = new byte[256];

			while (running) {
				DatagramPacket packet
						= new DatagramPacket(buf, buf.length);
				socket.receive(packet);

				ip = packet.getAddress();
				int port = packet.getPort();

				packet = new DatagramPacket(buf, buf.length, ip, port);
				String received
						= new String(packet.getData(), 0, packet.getLength());

				if(received.equals(message)){
					running = false;
				}
			}
			socket.close();

			//Apres avoir recu la demande de co, on revoit que oui c'est moi
			socket = new DatagramSocket();
			socket.setBroadcast(false);

			byte[] buffer = message.getBytes();

			DatagramPacket packet
					= new DatagramPacket(buffer, buffer.length, ip, 4445);

			socket.send(packet);
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
