
import java.net.*;
import java.nio.ByteBuffer;
import java.util.Scanner;

import java.io.*;
public class Servidor{
    int pos = 0;
	
    public static void main(String args[]){ 
    	DatagramSocket aSocket = null;
    	Scanner sc = new Scanner(System.in);
    	try{
	    	aSocket = new DatagramSocket(6666);
			while(true){
				byte[] buffer = new byte[10000];
				
 				DatagramPacket request = new DatagramPacket(buffer, buffer.length);
  				aSocket.receive(request);
  				
  				DatagramPacket reply = new DatagramPacket(request.getData(), request.getLength(), 
  						request.getAddress(), request.getPort());
    			
  				String teste = new String(reply.getData()) ;
    			String array[] = teste.split(" ");
    			
    			Calculate cal = new Calculate();
    			String a = cal.calculate(array);
    			System.err.println(a);
    			
    			byte [] m = a.getBytes();
    			
    			DatagramPacket response =
    				 	new DatagramPacket(m, a.length(),request.getAddress(), request.getPort());
    			
    			aSocket.send(response);
    		}
		}catch (SocketException e){System.out.println("Socket: " + e.getMessage());
		}catch (IOException e) {System.out.println("IO: " + e.getMessage());
		}finally {if(aSocket != null) aSocket.close();}
    }
    
}
