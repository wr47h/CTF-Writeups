from pwn import *
from subprocess import check_output

p = remote("chall.2019.redpwn.net", 6001)

p.recvuntil("!!!")

while True:
    try:
        print(p.recv())
        s = p.recvuntil('?')
    except EOFError:
        s = p.recv()
        print(s)
    print(s)
    s = s[:-1]
    s = s.split('capital of ')[1]
    # print(s)
    ans = check_output(['python3', 'get_capital.py', s])
    print(ans)
    p.sendline(ans)

p.interactive()