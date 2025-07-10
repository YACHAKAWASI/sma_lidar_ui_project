# ════════════════════════════════════════
# 🔽 DESCARGAR JADE
# ════════════════════════════════════════
cd jade_lidar_agent/lib
wget https://jade.tilab.com/dl/jadeBin.jar -O jade.jar

# ════════════════════════════════════════
# 🛠️ COMPILAR EL CÓDIGO JAVA
# ════════════════════════════════════════
cd ..
mkdir -p bin
javac -cp lib/jade.jar -d bin src/agents/*.java

# ════════════════════════════════════════
# 🚀 LANZAR EL AGENTE JADE
# ════════════════════════════════════════
java -cp lib/jade.jar:bin jade.Boot -gui -agents Lidar:agents.LidarAgent

# ════════════════════════════════════════
# 🐍 LANZAR EL NODO PYTHON (BRIDGE ROS 2 → SOCKET)
# ════════════════════════════════════════
cd ../../ros2_socket_bridge
source /opt/ros/humble/setup.bash
python3 scan_processor.py

# ════════════════════════════════════════
# 📡 LANZAR EL SENSOR LIDAR A1M8 CON ROS 2
# ════════════════════════════════════════
ros2 run rplidar_ros rplidar_composition --ros-args -p serial_port:=/dev/ttyUSB0 -p serial_baudrate:=115200

# ════════════════════════════════════════
# 🧾 O CON ARCHIVO LAUNCH PERSONALIZADO
# ════════════════════════════════════════
ros2 launch rplidar_ros view_rplidar_a1_launch.py
