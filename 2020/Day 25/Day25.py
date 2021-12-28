# Part A
# This day doesn't have a Part B

with open("input.txt") as f:
    content = f.read().splitlines()
value = 1
loopSize = 0
cardKey = int(content[0])
doorKey = int(content[1])

while value != cardKey:
    value = value * 7 % 20201227
    loopSize += 1

print(pow(doorKey, loopSize, 20201227))