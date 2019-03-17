Admin Panel (Web)
=================

This problem was based on PHP type juggling. This can easily be judged by the use of `!=` operator. Also from the given PHP file we know a few things t find the flag -  
1. There should be a cookie of the name **otadmin**.
2. The value of the cookie should be of the form - `{"hash": [0-9A-Z\"]+}`.
3. Each character of the MD5 of the *cfg_pass* variable when '&' with `0xC0`, gives the following string **0006464640640064000646464640006400640640646400**.


Now, from this string we know that a `0` is produced when a number is '&' with `0xC0`. And `64` is produced when we have a uppercase character. So, we know that the first 3 characters of the hash are numbers. Also due to the type juggling in PHP, `"123abc124"` becomes `123`, wgen compared with a number. So, we basically bruteforce the first three characters of the hash value. The script is in [get_flag.py](get_flag.py).

The flag is - **p4{wtf_php_comparisons_how_do_they_work...}**
