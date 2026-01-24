#include <windows.h>
#include <stdlib.h>

int main(int argc, char** argv) {
    int delay = atoi(argv[1]);
    if (argc < 2) {
        return 1;
    }
    while (1) {
        mouse_event(MOUSEEVENTF_LEFTDOWN, 0, 0, 0, 0);
        mouse_event(MOUSEEVENTF_LEFTUP, 0, 0, 0, 0);
        Sleep(delay);
    }
    return 0;
}