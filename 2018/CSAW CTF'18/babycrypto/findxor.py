import base64
from subprocess import call

bcode = "s5qQkd+WjN+e34+NkJiNnpKSmo3fiJeQ356Mj5aNmozfi5DfnI2anoua34+NkJiNnpKM34uXnovfl5qTj9+PmpCPk5rfm5Dfk5qMjNHft5rfiJ6Ri4zfi5Dfj4qL356Ki5CSnouWkJHfmZaNjIvT356Rm9+MnJ6Tnp2Wk5aLht+ek5CRmIyWm5rR37ea35uNmp6SjN+Qmd+e34iQjZOb34iXmo2a34uXmt+akZuTmoyM356Rm9+Ll5rflpGZlpGWi5rfnZqckJKa342anpOWi5aajN+LkN+SnpGUlpGb09+ekZvfiJeajZrfi5ea34uNiprfiZ6TiprfkJnfk5aZmt+WjN+PjZqMmo2JmpvRmZOemISblpmZlprSl5qTk5KekdKYz4+XzI2FjZ6wps61npPLnLeeuabGrKithr6uyZ63gg=="

temp = base64.b64decode(bcode)
f = open("key.txt", "w")

#call("xortool -b key.txt", shell=True)
txt = [ord(c) for c in bcode.decode('base64')]

# lg = len(txt)
# print(lg)
# key = "yeet"
# mul = lg/4 
# key_f = key*mul
# left = lg%4
# key_f += key[:left]
# print(key_f)
# assert(len(key_f) == lg)
# ans = ''
for i in range(1,256):
	ans = ''
	for j in range(len(txt)):
		ans += chr(txt[j] ^ i)
	f.write(ans)
	f.write('\n')

# print(ans)
