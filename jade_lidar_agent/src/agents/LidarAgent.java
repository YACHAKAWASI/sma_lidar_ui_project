package agents;

import jade.core.Agent;

public class LidarAgent extends Agent {
    @Override
    protected void setup() {
        System.out.println("✅ Agente Lidar iniciado correctamente.");
        addBehaviour(new LidarReceiverBehaviour());
    }
}
