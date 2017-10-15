SIMPLY BLACK, Steg, 100pts
==========================

Since it is a steganography problem, my first approach was to use **stegsolve** to solve it. On seeing the image under Blue plane 0, the word **LETHAL** was clearly visible which is our flag.

MALICIOUS, Misc, 100pts
=======================

The zip file contains a text file - flag.txt. The file contains a hex string which on decoding gives - `flag{k33p_up_y0ur_zipp3r5}`.

CRACK THIS, Steg, 100pts
=======================================

On running **file cr4ck_7hi5_7ce7aa193db6fd41fc3857602e72fc1d** in the terminal, we find it is a ELF 64-bit LSB executable. On running the executable, we get the message "Nothing to see here, except maybe some near 4195840.", which doesn't help. The memory of the executable also doesn't reveal much. So we run **binwalk** on the file and see that it contains a png image as well. We extract the png file with `binwalk -D 'png image:png' cr4ck_7hi5_7ce7aa193db6fd41fc3857602e72fc1d`. The png file contains the flag `flag{didn'tknowflagscouldbeinimages}`.

UNLOCK ME, Reverse Engineering
==============================

This is also a ELF 64-bit LSB executable. Running this gives the message -  
```
|  >  What you are looking for is in my memory...
|
|
|  >  The only thing you need is to find it! :)  
```
On checking the memory with `cat un10ck_m3_a04acef13380d5a9bbc20fddc7dd426c`, we find the flag `flag{410h0m0r4_10ck}`

ANONYMOUS LOGIN, Web, 100pts
============================

We are first presentd with a webpage with a button to enter. The source code of the page doesn't reveal much. So we inspect the cookies of the page. We find a cookie with the name **flag** and its value set to **True**. We convert it to **False** and then on pressing Enter, we are taken to a login page which asks for a username. We enter the username as **root** since it was given in the question to access the page as root. Then we are asked for a password. On inspecting the cookies again, we find a cookie with name **pass**. It is a 128 bit string, so we try to run a md5 hash decryter on it which gives the decoded passsword as **aunty**. We enter the password to login and we get the message that the password is our flag. Hence the flag is `flag{aunty}`.

GRAB THE FWAG, Web, 100pts
==========================

The page presents with a simple login page. We check the source code to find a function **validate_login** being used. The function is found in the file *kernel.js* associated with the webpage. Its definition is at line 200 of the JS code. There we find that the entered value is being converted to its md5 hash and compared with a precomputed hash. We run a md5 hash decryter on it, which gives the username as **14075064**. On entering this in the login, we get the flag `flag{17_w45_hidd3n_in_p14in_5igh7}`.
