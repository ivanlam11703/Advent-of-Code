#include <stdio.h>
#include <stdlib.h>

int main()
{
	FILE *file;
	int count = 0;
	int first, second, third, sum1, sum2;

	file = fopen("input.txt", "r");
	if (file == NULL)
	{
		printf("File not found.");
		return 0;
	}

	//Part A

	// fscanf (file, "%d", &first);
	// while (!feof(file))
	// {
	// 	fscanf(file, "%d", &second);
	// 	if (second > first)
	// 	{
	// 		count++;
	// 	}
	// 	first = second;
	// }
	// fclose(file);
	// printf("%d", count);

	//Part B

	fscanf(file, "%d", &first);
	fscanf(file, "%d", &second);
	fscanf(file, "%d", &third);
	sum1 = first + second + third;

	while (!feof(file))
	{
		first = second;
		second = third;
		fscanf(file, "%d", &third);
		sum2 = first + second + third;
		if (sum1 < sum2)
		{
			count++;
		}
		sum1 = sum2;
	}
	fclose(file);
	printf("%d", count);
}