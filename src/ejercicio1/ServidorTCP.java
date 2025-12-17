package ejercicio1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ThreadLocalRandom;

public class ServidorTCP {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Servidor conectado...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Nuevo cliente conectado.");

                Thread clientThread = new Thread(new ClientHandler(clientSocket));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientHandler implements Runnable {
    private Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        int num = ThreadLocalRandom.current().nextInt(1, 101);
        boolean adivinado = false;
        int inputNum;

        try {
            InputStream inputStream = clientSocket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            OutputStream outputStream = clientSocket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream, true);

            printWriter.println("Adivina mi numero");
            while (true) {
                inputNum = Integer.parseInt(bufferedReader.readLine());

                if (inputNum == num) {
                    printWriter.println("Número correcto. Adivina mi nuevo número.");
                    num = ThreadLocalRandom.current().nextInt(1, 101);
                } else if (inputNum > num) {
                    printWriter.println("El número es menor");
                } else if (inputNum < num) {
                    printWriter.println("El número es mayor");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
