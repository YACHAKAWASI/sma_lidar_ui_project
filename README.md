# 🤖 SMA Lidar UI Project

Este proyecto integra **ROS 2**, **JADE** (Java Agent DEvelopment Framework) y **Sockets TCP** para crear un sistema multiagente que procesa datos del sensor LiDAR. Permite que un agente autónomo en JADE reciba la distancia mínima y máxima capturada por ROS 2 en tiempo real.

---

## 📁 Estructura del proyecto

```
sma_lidar_ui_project/
├── ros2_ws/ # Workspace ROS 2
├── ros2_socket_bridge/ # Nodo Python que recibe /scan y envía por socket
└── jade_lidar_agent/ # Agente JADE que recibe datos desde ROS 2    ```


## 🧠 Arquitectura del sistema

```plaintext
ROS 2 (/scan) --> [scan_processor.py] --> Socket TCP --> [LidarAgent (JADE)]
