#!/usr/bin/python3
import hashlib
import string
import itertools
import sys
 
hash = "CD04302CBBD2E0EB259F53FAC7C57EE2"
 
alphabet = string.printable
 
for length in range(1, 8): # the string is REALLY short
	print("Len: {}".format(length))
	for i in itertools.product(alphabet, repeat=length):
		s = ''.join(i)
		for cnt in range(10):
			h = hashlib.new('md5')
			h.update(s.encode())
			s = h.hexdigest().upper()
		if s == hash:
			print("Found string: {}".format(''.join(i)))
			sys.exit(0)