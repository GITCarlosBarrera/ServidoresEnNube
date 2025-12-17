package ejercicio2;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;

public class ServidorUDP {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(12345);

            byte[] receiveData = new byte[1024];

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);

                int num = Integer.parseInt(new String(receivePacket.getData(), 0, receivePacket.getLength()));
                StringBuilder message = new StringBuilder();

                if (num > 1) {
                    for (int n = 2; n <= num; n++) {
                        boolean esPrimo = true;

                        for (int i = 2; i * i <= n; i++) {
                            if (n % i == 0) {
                                esPrimo = false;
                                break;
                            }
                        }

                        if (esPrimo) {
                            if (message.length() > 0) {
                                message.append(", ");
                            }
                            message.append(n);
                        }
                    }
                }

                byte[] sendData = message.toString()
                        .getBytes(StandardCharsets.UTF_8);

                DatagramPacket sendPacket = new DatagramPacket(
                        sendData,
                        sendData.length,
                        receivePacket.getAddress(),
                        receivePacket.getPort()
                );

                socket.send(sendPacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
