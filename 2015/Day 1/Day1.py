# Part A
# answer = 0
# with open("input.txt") as f:
#     for char in f.readline():
#         if char == '(':
#             answer += 1
#         else:
#             answer -= 1
# print(answer)

# Part B
answer = 0
count = 0
with open("input.txt") as f:
    for char in f.readline():
        count += 1
        if char == '(':
            answer += 1
        else:
            answer -= 1
        if answer == -1:
            print(count)
            break