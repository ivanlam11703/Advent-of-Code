import numpy as np

def reduceMap():
    global scanner
    for i in range(len(scanner)):
        if 'B' in scanner[i] or 'S' in scanner[i]:
            scanner = scanner[i:,:,:]
            break

    for i in range(len(scanner)-1, -1, -1):
        if 'B' in scanner[i] or 'S' in scanner[i]:
            scanner = scanner[:i+1,:,:]
            break
    
    for i in range(len(scanner[0])):
        if 'B' in scanner[:,i] or 'S' in scanner[:,i]:
            scanner = scanner[:,i:,:]
            break

    for i in range(len(scanner[0])-1, -1, -1):
        if 'B' in scanner[:,i] or 'S' in scanner[:,i]:
            scanner = scanner[:, :i+1,:]
            #printMap()
            break

    for i in range(len(scanner[0,0])):
        if 'B' in scanner[:,:,i] or 'S' in scanner[:,:,i]:
            scanner = scanner[:,:,i:]
            break

    for i in range(len(scanner[0,0])-1, -1, -1):
        if 'B' in scanner[:,:,i] or 'S' in scanner[:,:,i]:
            scanner = scanner[:, :,:i+1]
            #printMap()
            break

maps = []
coords = []
scannerData = []
with open('testinput.txt') as f:
    lines = f.read().splitlines()

for line in lines:
    if line == '':
        # X = [item[0] for item in coords]
        # Y = [item[1] for item in coords]
        # Z = [item[2] for item in coords]
        # spanX = max(X)-min(X)
        # spanY = max(Y)-min(Y)
        # spanZ = max(Z)-min(Z)
        # maxX = max([abs(max(X)),abs(min(X))])
        # if spanX < maxX:
        #     spanX = maxX
        # maxY = max([abs(max(Y)),abs(min(Y))])
        # if spanY < maxY:
        #     spanY = maxY
        # maxZ = max([abs(max(Z)),abs(min(Z))])
        # if spanZ < maxZ:
        #     spanZ = maxZ
        # #scanner = []
        # scanner = np.full((2000, 2000, 2000), '.')
        # scanner = np.full((spanY*2+1, spanX*2+1, spanZ*2+1), '.')
        # #for i in range(10):
        # #    temp = ['.'] * 10
        # #    t = ['.'] * 10
        # #    temp.append(t)
        # #    scanner.append(temp)
        # #print(scanner)
        # scanner[spanY,spanX,spanZ] = 'S'

        # for coord in coords:
        #     scanner[spanY-coord[1],spanX+coord[0],spanZ+coord[2]] = 'B'
        # reduceMap()
        # maps.append(scanner)
        
        scannerData.append(coords)

        coords = []
    elif not 'scanner' in line:
        location = line.split(',')
        coords.append([int(location[0]), int(location[1]), int(location[2])])
#print(scannerData)
relScanner = []
for scanner in scannerData:
    relBeacon = []
    for i in range(len(scanner)):
        
        result = []
        for j in range(len(scanner)):
            if scanner[i] != scanner[j]:
                result.append(abs(scanner[i][0] - scanner[j][0]) + abs(scanner[i][1] - scanner[j][1]) + abs(scanner[i][2] - scanner[j][2]))
        relBeacon.append(result)
    relScanner.append(relBeacon)

# for scanner in scannerData:
#     relBeacon = []
#     for initBeacon in scanner:   
#         result = []
#         for targetBeacon in scanner:
#             if initBeacon != targetBeacon:
#                 result.append(abs(initBeacon[0] - targetBeacon[0]) + abs(initBeacon[1] - targetBeacon[1]) + abs(initBeacon[2] - targetBeacon[2]))
#         #result.sort()
#         relBeacon.append(result)
#     relScanner.append(relBeacon)

# print(relScanner[0][0])
pairs = []
for i in range(len(scannerData)):
    for j in range(len(scannerData)):
        scanner1 = relScanner[i]
        scanner2 = relScanner[j]
        if scanner1 == scanner2:
            continue
        else:
            for beacon1 in scanner1:
                for beacon2 in scanner2:
                    counter = 0
                    for result in beacon1:
                        
                        if result in beacon2:
                            counter += 1
                    if counter >= 12:
                        pair = [i,j]
                        pair.sort()
                        if not pair in pairs:                   
                            pairs.append([i,j])


print(pairs)