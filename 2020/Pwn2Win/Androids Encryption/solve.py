# from pwn import *
import base64
from Crypto.Cipher import AES

# p = remote("encryption.pwn2.win", 1337)
# context.log_level = 'debug'

BUFF = 256
BLOCK_SIZE = 16
key2 = None
iv2 = None

def to_blocks(txt):
    return [txt[i*BLOCK_SIZE:(i+1)*BLOCK_SIZE] for i in range(len(txt)//BLOCK_SIZE)]

def xor(b1, b2=None):
    if isinstance(b1, list) and b2 is None:
        assert len(set([len(b) for b in b1])) == 1, 'xor() - Invalid input size'
        assert all([isinstance(b, bytes) for b in b1]), 'xor() - Invalid input type'
        x = [len(b) for b in b1][0]*b'\x00'
        for b in b1:
            x = xor(x, b)
        return x
    assert isinstance(b1, bytes) and isinstance(b2, bytes), 'xor() - Invalid input type'
    return bytes([a ^ b for a, b in zip(b1, b2)])

def decrypt(txt, key, iv):
    assert len(key) == BLOCK_SIZE, f'Invalid key size'
    assert len(iv) == BLOCK_SIZE, 'Invalid IV size'
    assert len(txt) % BLOCK_SIZE == 0, 'Invalid plaintext size'
    # global key2, iv2
    bs = len(key)
    blocks = to_blocks(txt)
    ctxt = b''
    aes = AES.new(key, AES.MODE_ECB)
    curr = iv
    for block in blocks:
        ctxt += xor(curr, aes.decrypt(block)) # Inverse of the encrypt function
        curr = xor(ctxt[-bs:], block)
    return ctxt

def encrypt_flag(p):
	p.recvuntil(b"Choice: ")
	p.sendline(b"2")
	x = p.recvline().strip()
	y = base64.b64decode(x)
	return y[:16], y[16:]

def enc_plaintext(p, plaintext):
	p.recvuntil(b"Choice: ")
	p.sendline(b"1")
	p.recvuntil(b"Plaintext: ")
	p.sendline(plaintext)
	x = p.recvline().strip()
	y = base64.b64decode(x)
	return y[:16], y[16:]

def getDecoding(s):
	y = base64.b64decode(s)
	return y[:16], y[16:]

# iv2_orig, flag_enc_orig = encrypt_flag(p)
# print(iv2_orig)
# print(flag_enc_orig)

# key2_new = xor(to_blocks(flag_enc_orig))
# print(b"Key2 :" + key2_new)
# iv2_new, flag_enc_cipher_new = encrypt_flag(p)
# print(b"iv2 :", iv2_new)
# print(b"flag_cipher_new :", flag_enc_cipher_new)

iv2_orig, flag_enc_orig = getDecoding(b"uwuCOw81+wNnyzEyC47vqL3FE8Ce1wUeOdseMF8dnkcW7hspFtlHpltm0s9ZSpkeSNKorfI+w0xGhX3cLIXH8g==")
print(iv2_orig)
print(flag_enc_orig)

key2_new = xor(to_blocks(flag_enc_orig))
print(b"Key2 :" + key2_new)
iv2_new, flag_enc_cipher_new = getDecoding(b"cBhC0oRewxL+H3jyg+ta1qQNGTgq+ubjY2xtpJrL8Imt/KO+w2R+KkkD+hn4zmQ/GzqOirS6tBIq7fVd5piIIg==")
print(b"iv2 :", iv2_new)
print(b"flag_cipher_new :", flag_enc_cipher_new)
print(decrypt(flag_enc_cipher_new, key2_new, iv2_new).decode())