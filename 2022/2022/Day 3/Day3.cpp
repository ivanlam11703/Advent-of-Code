#include <iostream>
#include <stdlib.h>
#include <fstream>
#include <string.h>
#include <sstream>
#include <vector>
#include <map>
using namespace std;

int main() {
    ifstream file;
    int answer = 0;
    string line,line2,line3;
    map<char, int> found;
    file.open("input.txt");
    // Part B
    if (file.is_open()) {
        while(!file.eof()) {
            getline(file,line);
            getline(file,line2);
            getline(file,line3);
            int min1 = min(line.length(),line2.length());
            int line3Len = line3.length();
            int min2 = min(min1,line3Len);
            string minLine = line;
            if(min2 == line.length()) {
                minLine = line;
            } else if (min2 == line2.length()) {
                minLine = line2;
            } else {
                minLine = line3;
            }
            for (int i = 0; i < min2; i++) {
                if(line.find(minLine[i]) != string::npos && line2.find(minLine[i]) != string::npos && line3.find(minLine[i]) != string::npos) {
                    found[minLine[i]]++;
                    break;
                }
            }
        }
        for (auto x : found) {
            if (isupper(x.first)) {
                answer += (x.first-38) * x.second;
            } else {
                answer += (x.first-96) * x.second;
            }
        }
        cout << answer << endl;
    }
    // Part A
    // if (file.is_open()) {
    //     while(!file.eof()) {
    //         getline(file,line);
    //         string first = line.substr(0,line.length()/2);
    //         string second = line.substr(line.length()/2);
    //         for (int i = 0; i < first.length(); i++) {
    //             if (second.find(first[i]) != string::npos) {
    //                 found[first[i]]++;
    //                 break;
    //             }
    //         }
    //     }
    // }

    // for (auto x : found) {
    //     if (isupper(x.first)) {
    //         answer += (x.first-38) * x.second;
    //     } else {
    //         answer += (x.first-96) * x.second;
    //     }
    // }
    // cout << answer << endl;
}