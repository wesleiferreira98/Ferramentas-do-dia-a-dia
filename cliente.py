import sys
from PyQt5.QtWidgets import QApplication, QMainWindow, QLineEdit, QPushButton, QLabel
import socket

class Client:
    def __init__(self, host, port):
        self.host = host
        self.port = port
        self.client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.client.connect((self.host, self.port))

    def send_data(self, data):
        self.client.sendall(data.encode())

class MyApp(QMainWindow):
    def __init__(self):
        super().__init__()
        self.client = Client('127.0.0.1', 8000)
        self.initUI()
    
    def initUI(self):
        self.label = QLabel('Enter data to send:', self)
        self.label.move(10, 10)
        self.line_edit = QLineEdit(self)
        self.line_edit.move(10, 30)
        self.line_edit.resize(280, 20)
        self.button = QPushButton('Send', self)
        self.button.move(10, 60)
        self.button.clicked.connect(self.send_data)
        self.setGeometry(300, 300, 300, 150)
        self.setWindowTitle('Data sender')
        self.show()
    
    def send_data(self):
        data = self.line_edit.text()
        self.client.send_data(data)

if __name__ == '__main__':
    app = QApplication(sys.argv)
    ex = MyApp()
    sys.exit(app.exec_())
