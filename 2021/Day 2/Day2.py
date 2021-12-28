depth = 0
distance = 0
aim = 0

#Part A

# with open("input.txt", "r") as f:
# 	for line in f:
# 		line = line.split()
# 		if line[0] == "forward":
# 			distance += int(line[1])
# 		elif line[0] == "down":
# 			depth += int(line[1])
# 		else:
# 			depth -= int(line[1])

#Part B

with open("input.txt", "r") as f:
	for line in f:
		line = line.split()
		if line[0] == "forward":
			depth += int(line[1]) * aim
			distance += int(line[1])
		elif line[0] == "down":
			aim += int(line[1])
		else:
			aim -= int(line[1])

print(distance*depth)