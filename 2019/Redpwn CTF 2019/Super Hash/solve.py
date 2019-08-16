import hashlib
import string
import sys

HASH = "CD04302CBBD2E0EB259F53FAC7C57EE2"

ALPHABET_SET = string.ascii_letters + string.digits + '_!@#$%^&*()-=+/?.,'
# ALPHABET_SET = string.printable

def check(s):
    a = s
    # a = hashlib.md5(s.encode()).hexdigest()
    # a = hashlib.md5(a.encode()).hexdigest()
    # a = hashlib.md5(a.encode()).hexdigest()
    # a = hashlib.md5(a.encode()).hexdigest()
    # a = hashlib.md5(a.encode()).hexdigest()
    # a = hashlib.md5(a.encode()).hexdigest()
    # a = hashlib.md5(a.encode()).hexdigest()
    # a = hashlib.md5(a.encode()).hexdigest()
    # a = hashlib.md5(a.encode()).hexdigest()
    # a = hashlib.md5(a.encode()).hexdigest()
    for _ in range(10):
        h = hashlib.new('md5')
        h.update(a.encode())
        a = h.hexdigest().upper()

    # print(a)
    if a == HASH:
        return True
    return False

for i in ALPHABET_SET:
    for j in ALPHABET_SET:
        for k in ALPHABET_SET:
            for l in ALPHABET_SET:
                for m in ALPHABET_SET:
                    for n in ALPHABET_SET:
                        for o in ALPHABET_SET:
                            print("Trying: ", i+j+k+l+m+n+o)
                            if check(o):
                                print(o)
                                sys.exit(0)
                            if check(n+o):
                                print(n+o)
                                sys.exit(0)
                            if check(m+n+o):
                                print(m+n+o)
                                sys.exit(0)
                            if check(l+m+n+o):
                                print(l+m+n+o)
                                sys.exit(0)
                            if check(k+l+m+n+o):
                                print(k+l+m+n+o)
                                sys.exit(0)
                            if check(j+k+l+m+n+o):
                                print(j+k+l+m+n+o)
                                sys.exit(0)
                            if check(i+j+k+l+m+n+o):
                                print(i+j+k+l+m+n+o)
                                sys.exit(0)
                            