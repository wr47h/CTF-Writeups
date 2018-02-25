import itertools

def subsets(s):
    for cardinality in range(len(s) + 1):
        yield from itertools.combinations(s, cardinality)

a,b=[int(x) for x in input().split()]
perm_list=[int(x) for x in input().split()]
cnt=0
pp=subsets(perm_list)
for p in pp:
    if len(p)==0:
        continue
    if sum(p)==b:
        cnt+=1
print(cnt)