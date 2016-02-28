import java.io.*;
import java.net.*;

//host name is 192.168.100.111
class TCPClient
{
	public static void main(String argv[]) throws Exception
	{
		if(argv[0] != null)
		{
			//Declare variables
			String hostname = argv[0];
			String Option = "0";
			int num = 60;
			
			//BufferedReader to get user input
			BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in));
			
			//Create Socket
			Socket clientSocket = new Socket(hostname, 2541);
			
			//Send user input to the Server
			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
			
			//Get Server output 
			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
			while(true)
			{
				//Option Menu
				//Option = "0";
				Runnable rArray[] = new Runnable[75];
				System.out.println("1. Host current Date and Time");
				System.out.println("2. Host uptime");
				System.out.println("3. Host memory use");
				System.out.println("4. Host Netstat");
				System.out.println("5. Host current users");
				System.out.println("6. Host running processes");
				System.out.println("7. Quit");
				
				//Get user Input
				Option = inFromUser.readLine();
				if(Option.equals("1") || Option.equals("2") || Option.equals("3") || Option.equals("4")
						|| Option.equals("5") || Option.equals("6") || Option.equals("7"))
				{
					if(!Option.equals("7"))
					{
						Thread tArray[] = new Thread[75];
						for(int i = 0; i < num; i++)
						{
							rArray[i] = new MyThread(outToServer, inFromServer, Option + "\n");
							tArray[i] = new Thread(rArray[i]);
						}
						
						for(int i = 0; i < num; i++)
						{
							tArray[i].start();
							//tArray[i].join();
						}

					}
					else
					{
						outToServer.writeBytes(Option + '\n');
						break;
					}
					
					//outToServer.writeBytes(Option + '\n');
//					if(!Option.equals("7"))
//					{
//						while((Results = inFromServer.readLine()) != null && Results.length() != 0 )
//						{
//							System.out.println(Results);
//						}
//					}
//					else
//						break;
				}
				else
					System.out.println("Invalid Input");
			}
			clientSocket.close();
		}
		else
		{
			System.out.println("No agruments given");
		}
	}	
}

class MyThread implements Runnable{
	private final DataOutputStream outToServer;
	private final BufferedReader inFromServer;
	private final String Option;
	private long totalTime;

	   public MyThread(DataOutputStream ots, BufferedReader ifs, String o){
	      outToServer = ots;
	      inFromServer = ifs;
	      Option =  o;
	   }

	   public void run(){
		   String Results;
		   String FullResults = null;
		   
			long startTime = System.currentTimeMillis();
			try {
				outToServer.writeBytes(Option);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			try {
				while((Results = inFromServer.readLine()) != null && Results.length() != 0 )
				{
					FullResults = FullResults + Results;
					//System.out.println(Results);
				}
				long endTime = System.currentTimeMillis();
				totalTime = endTime - startTime;
				System.out.println(totalTime);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
	   }
	}
