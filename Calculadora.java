import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Calculadora{
    public static void main(String args[]){ 
		
    	DatagramSocket aSocket = null;

    	System.out.println("Calculadora  'pre-ordem'\n");
    	System.out.println("Ex. + 5 5 -> = 10");
    	System.out.println("Ex. * 5 5 -> = 25");
    	System.out.println("Ex. * - 5 3 3 -> = 6");
		
		Scanner sc = new Scanner(System.in);
		String msg= "";
		
		try {
			while(!msg.equals("\\out")){
				System.out.println("digite");
				
				msg = sc.nextLine();
				
				byte [] m = msg.getBytes();
				aSocket = new DatagramSocket();    
				InetAddress aHost = InetAddress.getByName("localhost");
				int serverPort = 6666;		                                                 
				
				DatagramPacket request = new DatagramPacket(m,  msg.length(), aHost, serverPort);
				aSocket.send(request);			                        
				
				
				byte[] buffer = new byte[1000];
				DatagramPacket reply = new DatagramPacket(buffer, buffer.length);	
				aSocket.receive(reply);
				System.out.println("Resultado: " + new String(reply.getData()));
			}
		}catch (SocketException e){System.out.println("Socket: " + e.getMessage());
		}catch (IOException e){System.out.println("IO: " + e.getMessage());
		}finally {if(aSocket != null) aSocket.close();}
	}		      	
}
