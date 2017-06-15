import os
import socket
import time

port = int(os.environ["DB_PORT"]) # 5432

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
while True:
    try:
        s.connect(('myproject-db', port))
        s.close()
        break
    except socket.error as ex:
        time.sleep(0.1)