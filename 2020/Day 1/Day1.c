#include <stdio.h>
#include <stdlib.h>

int main()
{
	FILE *file;
	int count = 1;

	file = fopen("input.txt", "r");
	if (file == NULL)
	{
		printf("File not found.");
		exit(0);
	}
	else 
	{
		char c;
		for (c = getc(file); c != EOF; c = getc(file))
		{
			if (c == '\n')
			{
				count++;
			}
		}
	}
	int numbers[count];
	int i = 0;
	int j;
	rewind(file);

	fscanf (file, "%d", &numbers[i]);    
  	while (!feof (file))
    {  
    	i++;
    	fscanf (file, "%d", &numbers[i]);      
    }
  	fclose (file);

  	//part A

	// for (i = 0; i < count; i++)				
	// {
	// 	for (j = i + 1; j < count; j++)
	// 	{
	// 		if (numbers[i] + numbers[j] == 2020)
	// 		{
	// 			printf("%d", numbers[i] * numbers[j]);
	// 		}
	// 	}
	// }

  	//part B
  	
  	int k;
	for (i = 0; i < count; i++)
	{
		for (j = i + 1; j < count; j++)
		{
			for (k = j + 1; k < count; k++)
			{
				if (numbers[i] + numbers[j] + numbers[k] == 2020)
				{
					printf("%d", numbers[i] * numbers[j] * numbers[k]);
				}
			}
		}
	}
}