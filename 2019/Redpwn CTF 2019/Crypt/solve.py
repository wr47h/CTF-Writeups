# After decoding the JSFuck and reversing the logic of the resulting function

import base64

enc = 'vdDby72W15O2qrnJtqep0cSnsd3HqZzbx7io27C7tZi7lanYx6jPyb2nsczHuMec'

dec_1 = base64.b64decode(enc)

dec_1_enc = ""
for i in dec_1:
    dec_1_enc += chr(i - 99)

print(base64.b64decode(dec_1_enc))
