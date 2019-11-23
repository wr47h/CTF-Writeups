import r2pipe
import binascii
import sys

for i in range(1, 1000):
   # Load nth binary
   print('elfs/{0:03}'.format(i))
   b = r2pipe.open('elfs/{0:03}'.format(i) + '.c.out')

   # Get decompiled code
   disass = b.cmd('aaa; s main; pdd')

   # Identify the correct field
   field = disass.split("eax = *(obj.")[1][0]

   # Get the byte
   byte = disass.split(f'*(obj.{field}) = ')[-1][2:4]

   # Print the decoded hex byte
   print(binascii.unhexlify(byte).decode('ascii'))