from pwn import *
import string

FOLDER = './elfs/'
filenames = []

s = string.digits + string.letters + string.punctuation
# print(s)

for i in range(1,1000):
	filenames.append(str(i).zfill(3) + '.c.out')

flag = ''
f = open('flag.txt', 'w')
for file in filenames:
	for inp in s:
		p = process(FOLDER+file)
		p.recv()
		p.sendline(inp)
		a = p.recvline()
		if 'OK!' in a:
			flag += inp
			p.close()
			print("FLAG: " + flag)
			f.write("FLAG: " + flag)
			f.write('\n')
			break
		else:
			p.close()

f.write("FLAG: " + flag)
print(flag)
f.close()