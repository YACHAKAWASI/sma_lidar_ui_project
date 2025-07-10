# 🤖 SMA Lidar UI Project

Este proyecto integra **ROS 2**, **JADE** (Java Agent DEvelopment Framework) y **Sockets TCP** para crear un sistema multiagente que procesa datos del sensor LiDAR. Permite que un agente autónomo en JADE reciba la distancia mínima y máxima capturada por ROS 2 en tiempo real.

---

## 📁 Estructura del proyecto

```
sma_lidar_ui_project/
├── ros2_ws/ # Workspace ROS 2
├── ros2_socket_bridge/ # Nodo Python que recibe /scan y envía por socket
└── jade_lidar_agent/ # Agente JADE que recibe datos desde ROS 2    
```
  

## 🧠 Arquitectura del sistema
```

ROS 2 (/scan) --> [scan_processor.py] --> Socket TCP --> [LidarAgent (JADE)]

```



ROS 2 publica datos del sensor LiDAR en el topic /scan.

Un nodo Python (scan_processor.py) se suscribe a /scan, calcula el mínimo y máximo de distancia, y lo envía por socket.

El agente LidarAgent en JADE recibe los datos y los muestra o los puede usar para tomar decisiones autónomas.



🚀 Requisitos
Ubuntu 22.04

ROS 2 Humble

Python 3.10+

Java JDK 8 o superior

JADE (descargado como jade.jar)



🔧 Configuración del entorno
1. Descargar JADE
```
cd jade_lidar_agent/lib
wget https://jade.tilab.com/dl/jadeBin.jar -O jade.jar
```
2. Compilar el código Java

```
cd jade_lidar_agent
mkdir -p bin
javac -cp lib/jade.jar -d bin src/agents/*.java
```



▶️ Ejecución del sistema

Paso 1: Lanzar el agente JADE
```
cd jade_lidar_agent
java -cp lib/jade.jar:bin jade.Boot -gui -agents Lidar:agents.LidarAgent
```

-gui: muestra la interfaz visual de JADE.

Lidar: nombre del agente.

agents.LidarAgent: clase Java que ejecuta el comportamiento.

Paso 2: Ejecutar el script Python que envía datos desde ROS 2
```
cd ros2_socket_bridge
source /opt/ros/humble/setup.bash
python3 scan_processor.py
```

Paso 3: Lanzar ROS 2 y el sensor LiDAR
```
ros2 run rplidar_ros rplidar_composition --ros-args -p serial_port:=/dev/ttyUSB0 -p serial_baudrate:=115200
```