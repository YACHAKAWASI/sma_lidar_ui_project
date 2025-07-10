# 🤖 SMA Lidar UI Project

Este proyecto integra **ROS 2**, **JADE** (Java Agent DEvelopment Framework) y **Sockets TCP** para crear un sistema multiagente que procesa datos del sensor LiDAR. Permite que un agente autónomo en JADE reciba la distancia mínima y máxima capturada por ROS 2 en tiempo real.

---

## 📁 Estructura del proyecto

```bash
sma_lidar_ui_project/
├── ros2_ws/               # Workspace ROS 2
├── ros2_socket_bridge/    # Nodo Python que recibe /scan y envía por socket
└── jade_lidar_agent/      # Agente JADE que recibe datos desde ROS 2
🧠 Arquitectura del sistema
plaintext
Copiar
Editar
ROS 2 (/scan) → scan_processor.py → Socket TCP → LidarAgent (JADE)
ROS 2 publica datos del sensor LiDAR en el topic /scan.

Un nodo Python (scan_processor.py) se suscribe a /scan, calcula el valor mínimo y máximo de distancia, y los envía mediante socket TCP.

El agente LidarAgent en JADE recibe esos datos y los muestra en tiempo real, pudiendo integrarlos en decisiones autónomas o multiagente.

🚀 Requisitos del sistema
Ubuntu 22.04

ROS 2 Humble

Python 3.10+

Java JDK 8 o superior

JADE (descargado como jade.jar)

🔧 Configuración del entorno
1. Descargar JADE
bash
Copiar
Editar
cd jade_lidar_agent/lib
wget https://jade.tilab.com/dl/jadeBin.jar -O jade.jar
2. Compilar el código Java
bash
Copiar
Editar
cd jade_lidar_agent
mkdir -p bin
javac -cp lib/jade.jar -d bin src/agents/*.java
▶️ Ejecución del sistema
🟢 Paso 1: Lanzar el agente JADE
bash
Copiar
Editar
cd jade_lidar_agent
java -cp lib/jade.jar:bin jade.Boot -gui -agents Lidar:agents.LidarAgent
Explicación:

-gui: Muestra la interfaz visual de JADE.

Lidar: Nombre del agente.

agents.LidarAgent: Clase Java principal del agente.

🟢 Paso 2: Ejecutar el nodo Python de ROS 2
bash
Copiar
Editar
cd ros2_socket_bridge
source /opt/ros/humble/setup.bash
python3 scan_processor.py
Este nodo escucha el topic /scan, calcula mínimo y máximo, y envía los datos por socket.

🟢 Paso 3: Lanzar el sensor LiDAR (A1M8, por ejemplo)
bash
Copiar
Editar
ros2 run rplidar_ros rplidar_composition --ros-args -p serial_port:=/dev/ttyUSB0 -p serial_baudrate:=115200
O si tienes un archivo launch personalizado:

bash
Copiar
Editar
ros2 launch rplidar_ros view_rplidar_a1_launch.py
📦 Créditos
Este sistema fue desarrollado como parte de un proyecto de inteligencia artificial distribuida y robótica autónoma. Utiliza tecnologías de código abierto como:

ROS 2 Humble

JADE

Sockets TCP

Python 3

Java JDK

