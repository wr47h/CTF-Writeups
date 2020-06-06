#!/usr/bin/env python2
# I AM NOOB :)
import string
from hashlib import md5
from itertools import izip, cycle
import base64
import time

def xor(data, key): 
    return bytearray(a^b for a, b in zip(*map(bytearray, [data, key]))) 

flag=""
 
noobie = base64.decodestring("U1FEQEdeS1JDSEBEXlZDUEFYSG5ZQ29TVVBFRFlXRFxvUUJFTA==").strip()
print noobie

start = 1589414400
end = 1589500800

for i in range(start, end):
	key = md5(str(int(i))).hexdigest()
	my_hexdata = key
	scale = 16 
	num_of_bits = 8
	noobda = bin(int(my_hexdata, scale))[2:].zfill(num_of_bits)
	a = xor(noobie, noobda)
	if "batpwn{" in a and "}" in a:
		print(a)
