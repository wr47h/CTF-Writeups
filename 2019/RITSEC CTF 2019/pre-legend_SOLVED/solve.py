cipher = '9EEADi^^8:E9F3]4@>^4=2J32==^D@>6E9:?8\FD67F=\C:ED64'
text = ''

for i in range(1,51):
	for char in cipher:
		text += chr(ord(char)+i)
	print(i, text)
	text = ''