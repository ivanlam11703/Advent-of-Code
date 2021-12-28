with open("input.txt") as file:
    content = file.readline().strip()
content = content.split(', ')

curDirection = 0
north = 0
east = 0
pastLocations = [[0, 0]]

for item in content:
    if item[0] == 'R':
        curDirection = (curDirection + 1) % 4
    else:
        curDirection = curDirection - 1
        if curDirection < 0:
            curDirection = 3
    blocks = int(item[1:])
    if curDirection == 0:
        north = north + blocks
    elif curDirection == 1:
        east = east + blocks
    elif curDirection == 2:
        north = north - blocks
    else:
        east = east - blocks
    
# Part A
# print(abs(north)+abs(east))

    # Part B

    lastLocation = pastLocations[len(pastLocations)-1]

    foundPastLocation = False
    for i in range(1, blocks+1):
        if curDirection == 0:
            testLocation = [lastLocation[0]+i, lastLocation[1]]
            if testLocation in pastLocations:
                print(abs(testLocation[0]+abs(testLocation[1])))
                foundPastLocation = True
                break
            pastLocations.append(testLocation)
        if curDirection == 1:
            testLocation = [lastLocation[0], lastLocation[1]+i]
            if testLocation in pastLocations:
                print(abs(testLocation[0]+abs(testLocation[1])))
                foundPastLocation = True
                break
            pastLocations.append(testLocation)
        if curDirection == 2:
            testLocation = [lastLocation[0]-i, lastLocation[1]]
            if testLocation in pastLocations:
                print(abs(testLocation[0]+abs(testLocation[1])))
                foundPastLocation = True
                break
            pastLocations.append(testLocation)
        if curDirection == 3:
            testLocation = [lastLocation[0], lastLocation[1]-i]
            if testLocation in pastLocations:
                print(abs(testLocation[0]+abs(testLocation[1])))
                foundPastLocation = True
                break
            pastLocations.append(testLocation)
    if foundPastLocation:
        break