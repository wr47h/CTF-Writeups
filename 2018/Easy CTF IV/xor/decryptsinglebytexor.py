import binascii

encoded = "080c1e140e190b16030200020919151a06070b0609060e001a151502140808041510"

nums = binascii.unhexlify(encoded)

strings = (''.join(chr(num ^ key) for num in nums) for key in range(256))

a = list(strings)
for i in a:
	print(i)