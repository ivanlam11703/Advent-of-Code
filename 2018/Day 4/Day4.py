records = []
with open("input.txt") as f:
    records = f.read().splitlines()
records.sort()
guards = {}
number = 0
for record in records:
    if record.split()[2] == "Guard":
        number = record.split()[3][1:]
        if number not in guards:
            guards[number] = list()
            continue
    else:
        guards[number].append(record)
sleep = {}
for key in guards:
    if key not in sleep:
        sleep[key] = [0] * 60
    for i in range(0, len(guards[key]) - 1, 2):
        for j in range(int(guards[key][i].split()[1][3:5]), int(guards[key][i+1].split()[1][3:5])):
            sleep[key][j] += 1

# Part A

# sleepyGuard = -1
# mostSleep = 0
# for guard in sleep:
#     totalSleep = sum(sleep[guard])
#     if totalSleep > mostSleep:
#         sleepyGuard = guard
#         mostSleep = totalSleep
# index = -1
# biggest = -1
# for i in range(len(sleep[sleepyGuard])):
#     if sleep[sleepyGuard][i] > biggest:
#         biggest = sleep[sleepyGuard][i]
#         index = i
# print(int(sleepyGuard) * index)

# Part B

longestMinute = -1
timeSpent = -1
guardNumber = -1
for guard in sleep:
    for i in range(len(sleep[guard])):
        if sleep[guard][i] > timeSpent:
            timeSpent = sleep[guard][i]
            longestMinute = i
            guardNumber = int(guard)
print(guardNumber * longestMinute)