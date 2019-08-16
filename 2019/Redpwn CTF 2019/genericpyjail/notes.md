## Approach 1

**Use hex encoding**

`print(open('flag.txt').read()) => 7072696e74286f70656e2827666c61672e74787427292e72656164282929`

`$ "7072696e74286f70656e2827666c61672e74787427292e72656164282929".decode('hex')`

## Approach 2

**dictionary elements can be accessed by .get()**

`exit(getattr(locals().get(chr(95)*2+'built'+'ins'+chr(95)*2), 'op'+'en')('fl'+'ag.txt').read())`