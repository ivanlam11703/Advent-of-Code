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
    string temp;
    vector<int> values;
    vector<string> tempVec;
    // file.open("testinput.txt");
    file.open("input.txt");

    if(file.is_open()) {
        while (!file.eof()) {
            getline(file,temp,',');
            cout << temp << endl;
            stringstream tempstream(temp);
            string tempInt;
            // getline(tempstream, tempInt, '-');
            // tempVec.push_back(tempInt);
            // getline(tempstream, tempInt);
            // tempVec.push_back(tempInt);
            while(getline(tempstream, tempInt, '-')) {
                tempVec.push_back(tempInt);
            }
        }
    }
    // for (int i : values) {
    //     cout << i << endl;
    // }
    for (int i = 0; i < tempVec.size(); i++) {
        cout << "index: " << i << " = " << tempVec.at(i) << endl;
    }
    // for (unsigned int i = 0; i < tempVec.size(); i++) {
    //     values.push_back(stoi(tempVec[i]));
    //     cout << values[i] << endl;
    // }
    
    // for (unsigned int i = 0; i < values.size() - 4; i+=4) {
    //     if(values[i] > values[i+1]) {
    //         int hold = values[i+1];
    //         values[i+1] = values[i];
    //         values[i] = hold;
    //     }
    //     if (values[i+2] > values[i+3]) {
    //         int hold = values[i+3];
    //         values[i+3] = values[i+2];
    //         values[i+2] = hold;
    //     }
    //     if (values[i] <= values[i+2] && values[i+1] >= values[i+3]) {
    //         //cout << values[i] << "-" << values[i+1] << "," << values[i+2] << "-" << values[i+3]<<endl;
    //         answer++;
    //     } else if (values[i] >= values[i+2] && values[i+1] <= values[i+3]) {
    //         // cout << values[i] << "-" << values[i+1] << "," << values[i+2] << "-" << values[i+3]<< endl;
    //         answer++;
    //     }
    // }
    // cout << answer << endl;
}