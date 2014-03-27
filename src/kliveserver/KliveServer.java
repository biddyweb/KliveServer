/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kliveserver;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author home
 */
public class KliveServer {
    
   
   private static ServerSocket serversoc = null;
   
   private static Socket clientsoc= null;
  
  
    public static void main(String args[])
    {
        int port=9999;
        
        try{
            serversoc=new ServerSocket(port);
          
            
           }
        catch(IOException e)
                {
                    System.out.println(e);
                }
   
   
        try{
            
        clientsoc=serversoc.accept();
        
        Request r1 = new Request(clientsoc);
        r1.start();
        
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    
    
    }
    
    
}

 class Request extends Thread
{
  private Socket cs;
  public static  int i=0;
  private Socket[] soclist= new Socket[100];
  
  private DataInputStream is = null;
  private PrintStream os = null;
  
 
  
  public Request(Socket clientsoc)
  {
      this.cs=clientsoc;
      
     
  }
  
  public void run()
  {
      
    
      try
      {
          
      is = new DataInputStream(cs.getInputStream());
      
      os = new PrintStream(cs.getOutputStream());
  
      while (true) {
        String line = is.readLine();
        if(!line.isEmpty())
          System.out.println("request is : "+line);
        if(line=="CONNECT")
        {
            soclist[i]=cs;
            i++;
            os.print("CONNECTED OK");
            //createchannel class should be called
        }
        else if(line=="CREATECHANNEL")
        {
            
        }
        //remaining cases
        
        //os.println(line);
      
      }
      
      }
      catch(Exception e)
      {
          e.printStackTrace();
      }
      
  }
  
     
}