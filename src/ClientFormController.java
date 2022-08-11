import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author : Gihan Madhusankha
 * 2022-08-11 5:26 PM
 **/

public class ClientFormController {
    Socket socket;
    String username;
    BufferedReader bufferedReader;
    BufferedWriter bufferedWriter;

    public ClientFormController(Socket socket) throws IOException {
        this.socket = socket;
        this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.username = bufferedReader.readLine();
    }

    public static void main(String[] args) {

        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your username > ");
            String username = scanner.nextLine();

            Socket socket = new Socket("localhost", 8000);
            ClientFormController clientFormController = new ClientFormController(socket);
            clientFormController.sendMessages(socket);
            clientFormController.listenMessages(socket);


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void sendMessages(Socket socket) throws IOException {
        bufferedWriter.write(username);
        bufferedWriter.newLine();
        bufferedWriter.flush();

        Scanner scanner = new Scanner(System.in);
        while (socket.isConnected()) {
            String sendMsg = scanner.nextLine();
            bufferedWriter.write(username+" : "+ sendMsg);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }

    }

}