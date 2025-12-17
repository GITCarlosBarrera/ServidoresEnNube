package ejercicio1;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClienteTCP {

    public static void main(String[] args) {
        final String IP = "";
        int num;
        String message;

        try {
            Socket socket = new Socket(IP, 12345);
            System.out.println("Conexión establecida con el servidor.");

            OutputStream outputStream = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream, true);
            Scanner scanner = new Scanner(System.in);

            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            while (true) {
                message = bufferedReader.readLine();
                System.out.println(message);

                num = scanner.nextInt();
                printWriter.println(num);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
