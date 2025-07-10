package agents;

import jade.core.behaviours.CyclicBehaviour;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class LidarReceiverBehaviour extends CyclicBehaviour {
    @Override
    public void action() {
        try (ServerSocket serverSocket = new ServerSocket(8000)) {
            System.out.println("📡 Esperando conexión desde ROS 2...");

            Socket socket = serverSocket.accept();
            System.out.println("🔗 Conexión establecida con ROS 2");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String data;

            while ((data = in.readLine()) != null) {
                String[] partes = data.split(",");
                float min = Float.parseFloat(partes[0]);
                float max = Float.parseFloat(partes[1]);

                System.out.println("🔍 Distancia mínima: " + min + " m");
                System.out.println("📏 Distancia máxima: " + max + " m");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        block();  // Evita uso excesivo de CPU
    }
}
