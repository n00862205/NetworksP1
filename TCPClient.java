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
			int num = 2;
			
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
				System.out.println(Thread.activeCount());
				
				//Option Menu
				Runnable rArray[] = new Runnable[75];
				System.out.println("1. Host current Date and Time");
				System.out.println("2. Host uptime");
				System.out.println("3. Host memory use");
				System.out.println("4. Host Netstat");
				System.out.println("5. Host current users");
				System.out.println("6. Host running processes");
				System.out.println("7. Quit");
				System.out.println("8. Light Load");
				System.out.println("9. Heavy Load");
				
				//Get user Input
				Option = inFromUser.readLine();
				if(Option.equals("1") || Option.equals("2") || Option.equals("3") || Option.equals("4")
						|| Option.equals("5") || Option.equals("6") || Option.equals("7") || Option.equals("8") || Option.equals("9"))
				{
					
					if(Option.equals("8") || Option.equals("9"))
					{
						if(Option.equals("8")){
							Option = "1";
						}
						else
							Option = "4";
						
						Thread tArray[] = new Thread[num];
						long timeArr = 0;
						
						for(int i = 0; i < num; i++)
						{
							rArray[i] = new MyThread(outToServer, inFromServer, Option + "\n", i);
							tArray[i] = new Thread(rArray[i]);
						}
						
						for(int i = 0; i < num; i++)
						{
							tArray[i].start();
						}
						
						for(int i = 0; i < num; i++)
						{
							System.out.println(i);
							tArray[i].join();
						}
					}
					else if(!Option.equals("7"))
					{
						String Results = null;
						outToServer.writeBytes(Option + '\n');
						while((Results = inFromServer.readLine()) != null && Results.length() != 0 )
						{
							System.out.println(Results);
						}
					}
					else
					{
						outToServer.writeBytes(Option + '\n');
						break;
					}

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
	private final int a;
	private long totalTime;

	   public MyThread(DataOutputStream ots, BufferedReader ifs, String o, int x){
	      outToServer = ots;
	      inFromServer = ifs;
	      Option =  o;
	      a = x;
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
					Results = null;
				}
				long endTime = System.currentTimeMillis();
				totalTime = endTime - startTime;
				System.out.println("I am Thread " + a + " and my time " + totalTime);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return;
	   }
	   
	   public long getTotalTime()
	   {
		   return totalTime;
	   }
	}
