#include <iostream>
#include <stdlib.h>
#include <fstream>
#include <string.h>
#include <sstream>
#include <vector>
using namespace std;

int main() {
    ifstream file;
    string line;
    file.open("input.txt");
    int max1 = -1, max2 = -2, max3 = -3;
    if (file.is_open()) {
        while (!file.eof()) {
            int current = 0;
            getline(file,line);
            while (!line.empty() && !file.eof()) {
                current += stoi(line);
                getline(file,line);
            }
            if (max1 < 0) {
                max1 = current;
            } else if (max2 < 0) {
                if (current > max1) {
                    max2 = max1;
                    max1 = current;
                } else {
                    max2 = current;
                }
            } else if (max3 < 0) {
                if (current > max1) {
                    max3 = max2;
                    max2 = max1;
                    max1 = current;
                } else if (current > max2) {
                    max3 = max2;
                    max2 = current;
                } else {
                    max3 = current;
                }
            }
            if (max1 < current) {
                max3 = max2;
                max2 = max1;
                max1 = current;
            } else if (max2 < current) {
                max3 = max2;
                max2 = current;
            } else if (max3 < current) {
                max3 = current;
            }
        }
    }
    cout << max1+max2+max3 << endl;
}