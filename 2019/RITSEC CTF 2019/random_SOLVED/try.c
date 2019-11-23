#include <stdio.h>
#include <stdlib.h>

int main() {
	int start = 1573776000;  // 15th Nov 2019, 00:00 UTC
	int end = 1573948800;  // // 17th Nov 2019, 00:00 UTC

	for(int i=start; i<end; i++) {
		srand(i);
		int a = rand();
		int b = rand();
		int c = rand();
		int d = rand();
		int e = rand();
		int f = rand();
		if(a==1068399227 && b==161933545 && c==741438783 && d==1951874661 && e==1076387813) {
			printf("Seed: %d\n", i);
			printf("Next: %d\n", f);
			break;
		}
	}
	return 0;
}