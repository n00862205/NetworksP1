import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Scanner;

class TCPServer
{
   public static void main(String argv[]) throws Exception
      {
         String clientSentence;
         String capitalizedSentence = null;
         ServerSocket welcomeSocket = new ServerSocket(2541);
         
         Socket connectionSocket = welcomeSocket.accept();
         
         BufferedReader inFromClient =
                 new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
         boolean closed = true;
         while(true)
         {
        	clientSentence = null;

            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            clientSentence = inFromClient.readLine();
            
            switch(Integer.parseInt(clientSentence)){
            
            case 1:
            	
                capitalizedSentence = LocalDateTime.now().toString() + "\n";
                outToClient.writeBytes(capitalizedSentence);
                System.out.println("Input: " + clientSentence);
                break;
            	
            case 2:

            	capitalizedSentence = new Scanner(new FileInputStream("/proc/uptime")).next() + "\n";
            	outToClient.writeBytes(capitalizedSentence);
            	System.out.println("Input: " + clientSentence);
            	break;
            	
            case 3:
            	capitalizedSentence = new Scanner(new FileInputStream("/proc/meminfo")).next() + "\n";
            	outToClient.writeBytes(capitalizedSentence);
            	System.out.println("Input: " + clientSentence);
            	
            	break;
	
            case 4:
	
            	break;
	
            case 5:
	
            	break;
	
            case 6:
	
            	break;
            	
            case 7:
            	
            	break;
            	
            default:
            	
            	break;           
            }

         }
      }
}
