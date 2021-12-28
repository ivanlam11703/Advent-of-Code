numbers = [0] * 12
with open("input.txt") as file:
	for line in file:
		for i in range(0, 12):
			if line[i] == "0":
				numbers[i] += 1
			else:
				numbers[i] -= 1
gamma = [0] * 12
epsilon = [0] * 12
index = 0

for number in numbers:
	if number > 0:
		gamma[index] = 0
		epsilon[index] = 1
	else:
		gamma[index] = 1
		epsilon[index] = 0
	index = index + 1

print(int("".join(str(x) for x in gamma), 2) * int("".join(str(x) for x in epsilon), 2))