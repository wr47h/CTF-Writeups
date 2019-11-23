#!/usr/bin/env python

import re
h = open('secret.c')
lines = [ x[:-1] for x in h.readlines() ]  # remove newline char
h.close()

flag = []
for line in lines:
    num =''.join(re.findall(r'\s+', line)).replace('\t','1').replace(' ','0')
    if num:
        try:
            flag.append(chr(int(num,2)))
        except:
            pass

print ''.join(flag)[::-1]
print re.findall(r'CodefestCTF{.*}', ''.join(flag)[::-1])[0]
