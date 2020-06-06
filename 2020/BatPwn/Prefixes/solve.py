import binascii
import string

target = "eae4a5b1aad7964ec9f1f0bff0229cf1a11b22b11bfefecc9922aaf4bff0dd3c88"
st = string.printable

def encrypt(st):
	initialize = 0
	ot = ""
	for c in st:
		val = ord(c)
		initialize ^= (val << 2) ^ val
		ot += chr(initialize & 0xff)
		initialize >>= 8
	a = binascii.hexlify(ot)
	a = a.replace("00", "")
	return a

flagval = "batpwn{"

while 1:
	for c in st:
		x = flagval + c
		a = len(x)-1
		flag = 0
		if encrypt(x) == target[:a*2+2]:
			print(c)	
			flagval += c
			flag = 1
			break
		if flag == 0:
			for c1 in st:
				x = flagval + c1
				a = len(x)-1
				if encrypt(x) == "00":
					print(c1)
					flagval += c1
	if flagval[-1] == "U":
		break

print(flagval[:-1])