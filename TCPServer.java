import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.util.*;

class TCPServer
{


public static void main(String argv[]) throws Exception
      {
	   
        String clientSentence;
        String capitalizedSentence = null;
        ServerSocket welcomeSocket = new ServerSocket(2541);
        boolean exitFlag = false;
        Socket connectionSocket = welcomeSocket.accept();
         
        Scanner inFromClient =
                new Scanner(new InputStreamReader(connectionSocket.getInputStream()));
        
        
        DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
        
        String s;
 		Process p;
 		String localFinal = null;
		BufferedReader br;
        while(!exitFlag)
        {
            clientSentence = inFromClient.next();
   
            switch(Integer.parseInt(clientSentence)){
            
            case 1:
            	
                capitalizedSentence = LocalDateTime.now().toString() + "\n" + "\n";
                outToClient.writeBytes(capitalizedSentence);
                System.out.println("Input: " + clientSentence);
                break;
            	
            case 2:
            	capitalizedSentence = new Scanner(new FileInputStream("/proc/uptime")).next() + "\n" + "\n";
            	outToClient.writeBytes(capitalizedSentence);
            	System.out.println("Input: " + clientSentence);
            	break;
            	
            case 3:
            	Scanner scanner = new Scanner(new FileInputStream("/proc/meminfo"));
            	String finalString = null;
            	
            	while(scanner.hasNext())
            	{
            		finalString += scanner.nextLine() + "\n";
            	}
            	
            	capitalizedSentence = finalString + "\n";
            	
            	System.out.println("Input: " + clientSentence);
            	outToClient.writeBytes(capitalizedSentence);
            	
            	
            	break;
	
            case 4:
            	

        		p = Runtime.getRuntime().exec("netstat");
        		br = new BufferedReader(new InputStreamReader(p.getInputStream()));
        		
        		while((s = br.readLine()) != null)
        		localFinal += s + "\n";
        		p.waitFor();
        		capitalizedSentence = localFinal + "\n";
        		p.destroy();
        		System.out.println("Input: " + clientSentence);
            	outToClient.writeBytes(capitalizedSentence);
            	break;
	
            case 5:
        		p = Runtime.getRuntime().exec("w");
        		br = new BufferedReader(new InputStreamReader(p.getInputStream()));
        		
        		while((s = br.readLine()) != null)
        		localFinal += s + "\n";
        		p.waitFor();
        		capitalizedSentence = localFinal + "\n";
        		p.destroy();
        		System.out.println("Input: " + clientSentence);
            	outToClient.writeBytes(capitalizedSentence);
            	break;
	
            case 6:
            	p = Runtime.getRuntime().exec("ps aux");
        		br = new BufferedReader(new InputStreamReader(p.getInputStream()));
        		
        		while((s = br.readLine()) != null)
        		localFinal += s + "\n";
        		p.waitFor();
        		capitalizedSentence = localFinal + "\n";
        		p.destroy();
        		System.out.println("Input: " + clientSentence);
            	outToClient.writeBytes(capitalizedSentence);
            	break;
            	
            case 7:
            	System.out.println("Input: " + clientSentence);
            	exitFlag = true;
            	break;
            	
            default:
            	break;           
            }
         }
         
         try
         {
        	 connectionSocket.close();
        	 welcomeSocket.close();
        	 
         }
         catch(Exception x)
         {
        	 
         }
      }
}
