from bs4 import BeautifulSoup as bs
import binascii
#from urllib.request import urlopen 
import urllib2

html = urllib2.urlopen('http://bitmap01.3dsctf.org:8010/')
soup = bs(html, 'lxml')
bytes = ''
tds = soup.findAll('td')

for td in tds:
	bytes += binascii.a2b_hex(td['bgcolor'].replace('#', ''))

file = open('bitmap', 'wb')
file.write(bytes)
file.close()