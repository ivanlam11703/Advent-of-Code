// find the exterior surface area of 1x1x1 connected cubes on a 3D grid given its x,y,z position.
// given the input:
// 2,2,2
// 1,2,2
// 3,2,2
// 2,1,2
// 2,3,2
// 2,2,1
// 2,2,3
// 2,2,4
// 2,2,6
// 1,2,5
// 3,2,5
// 2,1,5
// 2,3,5
// there are 64 sides that aren't connected to another cube. However, the expected output is 58 because there is one cube of air trapped within the formation at (2,2,5). This makes the exterior surface area of the formation 58 after subtracting those sides. code in java to find the exterior surface area without the surface area of any cube faces within the formation and parse this input from a txt file called "testinput.txt