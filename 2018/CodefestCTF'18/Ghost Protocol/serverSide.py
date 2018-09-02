##########################################################
##########################################################
                ####### ######   # #####
                #       #        # #   #
                #       ###      # #####
                #       #        # #   #
                #####   #        # #####
##########################################################
##########################################################

from hidemsg import hidemsg
# [DELETED]

# [DELETED]
# [DELETED]
# [DELETED]

def generateRand(mLength):
    rand = ''.join([random.choice(string.ascii_letters
            + string.digits) for n in range(mLength)])
    return rand

def send_(client_s, msg):
    # [DELETED] # send "msg" to client

def receive_(client_s):
    # [DELETED] # wait for message from client and return the message once receicved.

def handle_client_connection(client_socket):
    global enc
    send_(client_socket, "Tell me your name and secret\n")
    received = receive_(client_socket)
    try:
        name, nounce = received.split(" ")
        if name == None or nounce == None:
            raise ValueError('Not allowed!')
    except:
        send_(client_socket, "Wrong format!")
        client_socket.close()
        return; 

    val = generateRand(random.randint(5,10))
    send_(client_socket, val +" " +str(enc.encrypt(nounce.rstrip())) + "\n")
    received = receive_(client_socket)

    msg = "You aint't authorized!"
    if received == str(enc.encrypt(val)):
        msg  = "The flag is [DELETED]" #:P 
    send_(client_socket,msg)

    client_socket.close()


enc = hidemsg()
while True:
    # [DELETED]
    # [DELETED]
    # [DELETED]
    # Somewhere here "handle_client_connection()" is called each time new client makes a connection.
    # [DELETED]

