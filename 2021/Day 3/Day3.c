#include <stdio.h>
#include <stdlib.h>
#include <string.h>


int convert(int arr[])
{
	int multiplier = 1;
	int i;
	int decimalNumber = 0;
	for (i = 11; i >= 0; i-- )
	{
	    decimalNumber += (multiplier * arr[i]);
	    multiplier *= 2;
	}
	return decimalNumber;
}

int main()
{
	FILE * file;
    char *line = NULL;
    size_t len;
    ssize_t read;

    file = fopen("input.txt", "r");
    if (file == NULL)
        exit(EXIT_FAILURE);

    int input[12] = {0};
    int gamma[12] = {0};
    int epsilon[12] = {0};
    int index;
    while ((read = getline(&line, &len, file)) != -1) 
    {
    	for (index = 0; index < 12; index++)
    	{
	        if (line[index] == '0')
	        {
	        	input[index]-= 1;
	        }
	        else if (line[index] == '1')
	        {
	        	input[index]+= 1;
	        }
        }
    }
    for (index = 0; index < strlen(line); index++)
    {
    	if (input[index] > 0)
    	{
    		gamma[index] = 1;
    		epsilon[index] = 0;
    	}
    	else
    	{
    		gamma[index] = 0;
    		epsilon[index] = 1;
    	}
    }
    int epsilonDecimal = convert(epsilon);
    int gammaDecimal = convert(gamma);
    printf("%d", epsilonDecimal * gammaDecimal);
}