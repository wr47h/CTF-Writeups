from pwn import *
import re

re.compile("")
p = remote('34.216.132.109', 9093)
x = p.recvline()
p.recvuntil(":D")

digits = [int(s) for s in x.split() if s.isdigit()]
letters = [s[1] for s in x.split() if s.startswith("'")]
# print digits
# print letters

st = letters[0]*digits[0] + letters[1]*digits[1] + str(ord(letters[0]) + ord(letters[1]))
print(st)
p.sendline(st)
p.interactive()