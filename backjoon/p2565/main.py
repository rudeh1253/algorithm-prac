n = int(input())

inp = []

for i in range(n):
    line = input().split(" ")
    inp.append((int(line[0]), int(line[1])))

inp.sort()

