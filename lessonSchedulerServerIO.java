import java.net.Socket;
import java.io.IOException;
import java.net.ServerSocket;
import java.io.ObjectInputStream;
import java.io.File;
import java.io.FileWriter;

public class lessonSchedulerServerIO {

	private Socket socket;
	private ServerSocket serverSocket;
	private ObjectInputStream inputStream;
 
	public lessonSchedulerServerIO() {
		System.out.println("Server is running");
		try {
			serverSocket = new ServerSocket(1098, 500);
			socket = serverSocket.accept();
			inputStream = new ObjectInputStream(socket.getInputStream());
			String message = (String) inputStream.readObject();
			File myFile = new File("Export.txt");
			FileWriter writer = new FileWriter(myFile);
			writer.write(message);
			writer.close();
			System.out.println(message);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		System.out.println("End of Connection");
	}

	public static void main(String[] args) {
  
		new lessonSchedulerServerIO();

	}

}
