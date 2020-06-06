README.flag
-----------

1. Use shuf
2. Use iconv

ORME.flag
---------

1. upx /tmp/chmod /bin/busybox
2. /tmp/chmod +r ORME.flag
3. shuf or iconv


** Good method **
------------------

Use linux's loader

1. ls /lib
2. /lib/ld-musl-x86_64.so.1 /bin/busybox cat README.flag
3. /lib/ld-musl-x86_64.so.1 /bin/busybox chmod +r ORME.flag
4. /lib/ld-musl-x86_64.so.1 /bin/busybox cat ORME.flag
