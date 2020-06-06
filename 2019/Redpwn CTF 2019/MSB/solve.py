# We check out the blue channel
from PIL import Image

img = Image.open('lol.png')
im = img.load()
width, height = img.size

info = ""

for i in range(width):
    for j in range(height):
        val = im[i,j][2]
        info += (bin(val)[2:].zfill(8))[0]

flag = ""

while(len(info) != 0):
    flag += chr(int(info[:8], 2))
    info = info[8:]

print(flag)