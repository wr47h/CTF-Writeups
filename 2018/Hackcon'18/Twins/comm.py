a = list(open('file1', 'r').read())

b = list(open('file2', 'r').read())

print len(a), len(b)

c = ''
for i in range(len(a)):
	if a[i] == b[i]:
		c += a[i]

print(c)
