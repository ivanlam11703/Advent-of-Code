claims = []
with open("input.txt") as f:
    for line in f:
        claims.append(line.strip().split("@")[1][1:])

fabric = []
for i in range(0, 1000):
    fabric.append([0] * 1000)
for claim in claims:
    starts = claim.split(":")[0].split(",")
    x_start = int(starts[0])
    y_start = int(starts[1])

    distances = claim.split(":")[1].split("x")
    x_distance = int(distances[0][1:])
    y_distance = int(distances[1])

    for i in range(x_start, x_start + x_distance):
        for j in range(y_start, y_start + y_distance):
            fabric[i][j] += 1

# Part A

# count = 0 
# for i in range(len(fabric)):
#     for j in range(len(fabric[i])):
#         if fabric[i][j] > 1:
#             count += 1
# print(count)

# for i in range(len(fabric)):
#     for j in range(len(fabric[i])):
#         print(fabric[i][j], end= "")
#     print()

# Part B
# Use ctrl + f in input file to find the claim number because I got rid of claim numbers while reading the file
for claim in claims:
    starts = claim.split(":")[0].split(",")
    x_start = int(starts[0])
    y_start = int(starts[1])

    distances = claim.split(":")[1].split("x")
    x_distance = int(distances[0][1:])
    y_distance = int(distances[1])

    found = False
    if not found:
        found = True
        for i in range(x_start, x_start + x_distance):
            for j in range(y_start, y_start + y_distance):
                if fabric[i][j] != 1:
                    found = False
        if found:
            print(claim)
            break