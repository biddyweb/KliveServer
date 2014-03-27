 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tcp;

import java.io.*;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import javax.swing.Timer;

/**
 *
 * @author home
 */
public class Receiver {
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        //Timer timer=new Timer(1000);
        
       
        MulticastSocket sendSocket = new MulticastSocket(9999);
        sendSocket.joinGroup(InetAddress.getByName("224.1.1.2"));
        sendSocket.setTimeToLive(0);
        
        
        try
        {
            Socket clientSocket = new Socket("localhost", 1234);

         
    int i=0;
    byte[] fileData = new byte[8196];
    String response;
    DatagramPacket sendPacket=new DatagramPacket(fileData, 0,InetAddress.getByName("224.1.1.2"),9999);
    sendSocket.setSendBufferSize(5000000);
    ByteBuffer wrapped = ByteBuffer.wrap(fileData);
    short prevTimeStamp = -1;
        while(true)
    {
        
        DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
        
        int toRead = dis.readInt();
        short timeStamp = dis.readShort();
        
       dis.read(fileData,0,toRead);
        
        
        
        System.out.println(toRead+":"+i++);
        //dis.read(fileData, 0, toRead);
       sendPacket.setData(fileData,0,toRead);
        
        
        sendSocket.send(sendPacket);
        if(timeStamp!=prevTimeStamp)
        {
            prevTimeStamp = timeStamp;
            Thread.sleep(700);
        }
    }
    
    //dis.close();
        
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    }
    void nextSecond()
    {
        
    }
    
}
