flag = ''

f1 = open("a.txt").read()
f2 = open("b.txt").read()

i = 0
j = 0

while i < len(f2) and j < len(f1):
	if f1[j] == f2[i]:
		i += 1
		j += 1
		continue
	else:
		flag += f1[j]
		j += 1

print(flag)