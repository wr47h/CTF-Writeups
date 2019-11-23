from itertools import chain

from sympy import isprime

A002385 = sorted((n for n in chain((int(str(x)+str(x)[::-1]) for x in range(1, 10**3)), (int(str(x)+str(x)[-2::-1]) for x in range(1, 10**5))) if isprime(n)))
print(A002385)
