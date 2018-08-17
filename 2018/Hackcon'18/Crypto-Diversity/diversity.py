a = input()
b = a.split()

def hex(s):
	#return binascii.unhexlify(s)
	return chr(int(s, 16))

def binary(s):
	return chr(int(s, 2))

def decimal(s):
	return chr(int(s))

def octal(s):
	return chr(int(s, 8))

c = ''
# for i in b:
# 	if i.startswith('x'):
# 		c += hex(i[1:])
# 	elif i.startswith('d'):
# 		c += decimal(i[1:])
# 	elif i.startswith('b'):
# 		c += binary(i[1:])
# 	else:
# 		c += octal(i[1:])

for i in b:
	c += decimal(i)

print(c)