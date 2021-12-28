frequency = 0
seen = set()
foundDuplicate = False
while not foundDuplicate:
    with open("input.txt") as file:
        for line in file:
            if line[0] == "+":
                line = line[0:]
            frequency += int(line)
            if frequency in seen:
                # Part B
                print(frequency)
                foundDuplicate = True
                break
            else:
                seen.add(frequency)
# Part A
# print (frequency)