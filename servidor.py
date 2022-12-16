import socket

class Server:
    def __init__(self, host, port):
        self.host = host
        self.port = port
        self.server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.server.bind((self.host, self.port))
        self.server.listen()

    def receive_data(self):
        client, address = self.server.accept()
        data = client.recv(1024).decode()
        print(f'Received data from {address}: {data}')


server = Server('127.0.0.1', 8000)

while True:
    server.receive_data()