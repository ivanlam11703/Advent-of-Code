#include <string>
#include <iostream>

int detectFirstStartOfPacketMarker(const std::string& line)
{
    std::string window;
    for (int i = 0; i < line.length(); i++) {
        window += line[i];
        if (window.length() > 4) {
            window.erase(0, 1);
        }
        if (window.length() == 4 && window[0] != window[1] && window[1] != window[2] && window[2] != window[3]) {
            return i+1;
        }
    }
    return -1;
}

int main()
{
    std::cin >> line;
    int pos = detectFirstStartOfPacketMarker(line);
    std::cout << pos << std::endl;
    return 0;
}