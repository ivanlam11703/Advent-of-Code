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
    string line;
    map<char,char> wins;
    map<char,char> draws;
    map<char,char> losses;
    map<char,int> values;
    values['X'] = 1;
    values['Y'] = 2;
    values['Z'] = 3;
    wins['A'] = 'Y';
    wins['B'] = 'Z';
    wins['C'] = 'X';
    draws['A'] = 'X';
    draws['B'] = 'Y';
    draws['C'] = 'Z';
    losses['A'] = 'Z';
    losses['B'] = 'X';
    losses['C'] = 'Y';
    int sum = 0;
    file.open("input.txt");

    //Part A
    // if (file.is_open()) {
    //     while(!file.eof()) {
    //         getline(file,line);
    //         if(wins[line[0]] == line[2]) {
    //             sum += 6 + values[line[2]];
    //         } else if (draws[line[0]] == line[2]) {
    //             sum += 3 + values[line[2]];
    //         } else {
    //             sum += values[line[2]];
    //         }
    //     }
    // }
    // cout << sum << endl;

    //Part B
    if (file.is_open()) {
        while(!file.eof()) {
            getline(file,line);
            if (line[2] == 'X') {
                sum += values[losses[line[0]]];
            } else if (line[2] == 'Y') {
                sum += 3 + values[draws[line[0]]];
            } else {
                sum += 6 + values[wins[line[0]]];
            }
        }
    }
    cout << sum << endl;
}