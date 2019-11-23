import requests

with requests.Session() as s:
	r = s.get("http://34.216.132.109:8083/fp")
	i = 0
	while True:
		print i
		i += 1
		c = r.content
		if "flag" in c:
			print c
			break
		x = c.find('action="')
		y = c[x+8:x+23]
		f = open(y[1:-1], "w")
		f.write(c)
		print("http://34.216.132.109:8083"+y)
		r = s.get("http://34.216.132.109:8083"+y)
