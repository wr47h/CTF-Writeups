from z3 import *

pairs = [
    [0x29abc13947b5373b86a1dc1d423807a,  0xb36b6b62a7e685bd1158744662c5d04a],
    [0xeeb83b72d3336a80a853bf9c61d6f254, 0x614d86b5b6653cdc8f33368c41e99254],
    [0x7a0e5ffc7208f978b81475201fbeb3a0, 0x292a7ff7f12b4e21db00e593246be5a0],
    [0xc464714f5cdce458f32608f8b5e2002e, 0x64f930da37d494c634fa22a609342ffe],
    [0xf944aaccf6779a65e8ba74795da3c41d, 0xaa3825e62d053fb0eb8e7e2621dabfe7],
    [0x552682756304d662fa18e624b09b2ac5, 0xf2ffdf4beb933681844c70190ecf60bf],
]

s = Solver()

encs = [[BitVec("x[%d,%d]" % (i,j), 128) for j in range(765 + 1)] for i in range(6)]
key = BitVec("key", 128)

for i in range(6):
    print("Adding constraints")
    s.add(encs[i][0] == pairs[i][0])

    for j in range(765):
        s.add(encs[i][j+1] == (encs[i][j] + key) ^ key)

    s.add(encs[i][765] == pairs[i][1])

print("Trying to solve")
r = s.check()

if r == sat:
    m = s.model()
else:
    print(r)
    exit(0)

print(m[key].as_long())