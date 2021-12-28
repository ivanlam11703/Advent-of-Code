# Part A

# idDic = {}
# alreadyFound = set()
# with open("input.txt") as f:
#     for line in f:
#         alreadyFound.clear()
#         charDic = {}
#         for char in line:
#             if char in charDic:
#                 charDic[char] += 1
#             else:
#                 charDic[char] = 1
#         for key in charDic:
#             if charDic[key] not in alreadyFound and charDic[key] != 1:
#                 alreadyFound.add(charDic[key])
#                 if charDic[key] not in idDic:
#                     idDic[charDic[key]] = 1
#                 else:
#                     idDic[charDic[key]] += 1
# answer = 1
# for key in idDic:
#     answer *= idDic[key]
# print(answer)

#Part B

with open("input.txt") as f:
    content = f.read().splitlines()
for i in range(len(content) - 1):
    for j in range(i + 1, len(content)):
        id1 = content[i]
        id2 = content[j]
        count = 0
        for index in range(len(id1)):
            if id1[index] != id2[index]:
                count += 1
        if count == 1:
            answer = ""
            for char in id1:
                if char in id2:
                    answer += char
            print(answer)