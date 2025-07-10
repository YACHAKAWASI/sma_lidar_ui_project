import rclpy
from rclpy.node import Node
from sensor_msgs.msg import LaserScan
import socket

class ScanProcessor(Node):
    def __init__(self):
        super().__init__('scan_processor')
        self.subscription = self.create_subscription(
            LaserScan,
            '/scan',
            self.listener_callback,
            10)
        self.socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.socket.connect(('192.168.0.7', 8000))  # ← IP donde corre JADE

    def listener_callback(self, msg):
        datos = [r for r in msg.ranges if r > 0.01 and r < msg.range_max]
        if datos:
            min_dist = min(datos)
            max_dist = max(datos)
            mensaje = f"{min_dist},{max_dist}\n"
            self.socket.sendall(mensaje.encode())

def main(args=None):
    rclpy.init(args=args)
    node = ScanProcessor()
    rclpy.spin(node)
    node.destroy_node()
    rclpy.shutdown()

if __name__ == '__main__':
    main()
