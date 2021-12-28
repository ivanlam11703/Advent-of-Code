#include <stdio.h>
#include <stdlib.h>
#include<string.h>

int main()
{
	FILE *file;
	char line[50];
	int depth, distance;
	int aim = 0;

	file = fopen("input.txt", "r");
	if (file == NULL)
	{
		printf("File not found.");
		return 0;
	}
	//Part A

	// while (fgets(line, 50, file) != NULL)
	// {
	// 	if (line[0] == 'f')
	// 	{
	// 		distance += line[strlen(line) - 3] - '0';
	// 	}
	// 	else if (line[0] == 'd')
	// 	{
	// 		depth += line[strlen(line) - 3] - '0';
	// 	}
	// 	else
	// 	{
	// 		depth -= line[strlen(line) - 3] - '0';
	// 	}
	// }

	//Part B

	while (fgets(line, 50, file) != NULL)
	{
		if (line[0] == 'f')
		{
			distance += line[strlen(line) - 3] - '0';
			depth += aim * (line[strlen(line) - 3] -'0');
		}
		else if (line[0] == 'd')
		{
			aim += line[strlen(line) - 3] - '0';
		}
		else
		{
			aim -= line[strlen(line) - 3] - '0';
		}
	}
	printf("%d\n", distance);
	printf("%d\n", depth);
	printf("%d\n", distance * depth);
}