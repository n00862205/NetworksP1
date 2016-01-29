import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;

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

         while(true)
         {
        	clientSentence = null;
        	 
            
           
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            clientSentence = inFromClient.readLine();
            
            switch(Integer.parseInt(clientSentence)){
            
            case 1:
            	
            	java.util.Date date = new java.util.Date();
            	String dateString = null;
            	SimpleDateFormat sdfr = new SimpleDateFormat("dd/MMM/yyyy");
            	   
            	   try{
            		dateString = sdfr.format(date);
            	   }catch (Exception ex ){
            		System.out.println(ex);
            	   }
            	   
                
            	
            	//System.out.println("Received: " + clientSentence);
                capitalizedSentence = dateString +'\n';
                
                
            	
            	break;
            	
            case 2:
            	
            	//System.out.println("Received: " + clientSentence);
                capitalizedSentence = "you entered 2" + '\n';
                
            	
            	break;
            	
            case 3:
	
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
            	
            	           
            }
            
            
            System.out.println("Received: " + clientSentence);
            //capitalizedSentence = clientSentence.toUpperCase() + '\n';
            outToClient.writeBytes(capitalizedSentence);
         }
      }
}
