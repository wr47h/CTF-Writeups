xor_env = "\x1c\x07\x10\x0c\x00\x03\x00\x1d"
env_var = ""
for i in xor_env:
    env_var += chr(ord(i) ^ 0x4f)

print(env_var)

xor_data1 = "`-&!`<'"
data1 = ""
for i in xor_data1:
    data1 += chr(ord(i) ^ 0x4f)

print(data1)

xor_data2 = "\"$)&) "
data2 = ""
for i in xor_data2:
    data2 += chr(ord(i) ^ 0x4f)

print(data2)

xor_data2 = "?.? ,"
data2 = ""
for i in xor_data2:
    data2 += chr(ord(i) ^ 0x4f)

print(data2)

checksum = "39F27EEC7558D1CA14DE3C5839E88BABCF26D51573AE16D021895F98220515EC" # SHA256
# Decrypted to 000000149100020803781papoc
# So, pass 000000149100020803781 in SH_COLOR
