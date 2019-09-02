require 'securerandom'
require 'openssl'

ROUNDS = 765
BITS = 128
PAIRS = 6

def encrypt(msg, key)
    enc = msg
    mask = (1 << BITS) - 1
    ROUNDS.times do
        enc = (enc + key) & mask
        enc = enc ^ key
    end
    enc
end

def decrypt(msg, key)
    enc = msg
    mask = (1 << BITS) - 1
    ROUNDS.times do
        enc = enc ^ key
        enc = (enc - key) & mask
    end
    enc
end

fail unless BITS % 8 == 0

key = 62900030173734087782946667685685220617
enc = 0x43713622de24d04b9c05395bb753d437

STDOUT.puts("enc=%x plain=%x" % [enc, decrypt(enc, key)])