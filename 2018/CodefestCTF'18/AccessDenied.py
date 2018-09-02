#!/usr/bin/env python
# -*- coding: utf-8 -*-
import random
from pwn import *

user = 'shreyansh'
context.log_level = 'DEBUG'
p = remote('34.216.132.109', 9094)
print(p.recv())
p.sendline(user)
for j in range(1, 1000):
    count_ = j
    generator = "xorshift"
    random.seed(generator)
    count = 0
    
    for ch in user:
        ra = random.randint(1, ord(ch))
        rb = (ord(ch) * random.randint(1, len(user))) ^ random.randint(1, ord(ch))
        count += (ra + rb)/2
    code = 1
    for i in range(1,count+count_):
        code = (code + random.randint(1, i) ) % 1000000
    final = random.randint(1,9) * 1000000 + code
    data = p.recvuntil('code: ')
    if 'flag' in data:
        print(data)
        break
    else:
        print(count_, 'failed', data)
    p.sendline(str(final))
