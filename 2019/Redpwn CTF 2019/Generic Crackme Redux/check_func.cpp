#include <iostream>

using namespace std;

long calc(int x) {
    return (long)(x * 10 & 0xffffff00U | (int)(x * 10 == 0xac292));
}

int main() {
    cout << "Enter x: ";
    int x;
    cin >> x;
    cout << calc(x) << endl;
    return 0;
}