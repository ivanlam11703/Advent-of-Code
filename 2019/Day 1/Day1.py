# Part A

# fuelTotal = 0
# with open("input.txt") as f:
#     for line in f:
#         fuelTotal += int(line) // 3 - 2
# print(fuelTotal)

# Part B

fuelTotal = 0
with open("input.txt") as f:
    for line in f:
        num = int(line)
        temp = 0
        while (num > 0):
            num = num // 3 - 2
            if (num > 0):
                temp += num
        fuelTotal += temp
print(fuelTotal)