#include <iostream>
#include <iomanip>
#include <unordered_map>
#include <chrono>
#include "string.h"

using namespace std;


const int MAX =  1000;
unordered_map<int, int> hashTable;

/*
    Recursive Fibonacci Sequence
*/


int FiboRecursive(int k) {

    if(k == 1 || k == 0) {
	return k;
    } else  {
      return  FiboRecursive(k - 1) + FiboRecursive(k - 2);
    }
}

int FiboNoRecursive(int k) {

    int Fibonacci[MAX];

    Fibonacci[0] = 0;
    Fibonacci[1] = 1;

    for(int i = 2; i <= k; ++i) {
      Fibonacci[i] = Fibonacci[i-1] + Fibonacci[i-2];
     }

    return Fibonacci[k];
}



int MODFiboRecursive(int k) {

    if(hashTable[k] == -1) {
	if(k <= 1) {
       	    hashTable[k] = k;
       } else {
            hashTable[k] = MODFiboRecursive(k - 1) + MODFiboRecursive(k - 2);
        }
    return hashTable[k];
    }

    return hashTable[k];
}

int main() {

     for(int i = 0; i < MAX; i++) {
        hashTable.insert({i, -1});
     }

     cout << setw(4) << setfill('-') << "" << " nTh term: "
     << setw(4) << setfill('-') << "" << " FiboRecursive "
     << setw(4) << setfill('-')  << "" << " No Recursion Fibo "
     << setw(4) << setfill('-') << "" << " Memoized Recursion "
     << setw(4) << setfill('-') << ""<< " Fibo Value Memoized " << endl;

    cout << endl << endl;

     for(int i = 0; i <= 60; i += 5 ) {
        cout << setw(4) << setfill(' ') << "" << i << " term ";
        if (i <= 50) {
        auto start = chrono::high_resolution_clock::now();
        FiboRecursive(i);
        auto stop = chrono::high_resolution_clock::now();
        auto duration = chrono::duration_cast<chrono::seconds>(stop - start);
        cout << setw(10) << "" << duration.count() << " seconds ";
        start = chrono::high_resolution_clock::now();
        FiboNoRecursive(i);
        stop = chrono::high_resolution_clock::now();
        duration = chrono::duration_cast<chrono::seconds>(stop - start);
        cout << setw(10) << "" << duration.count() << " seconds ";
        start = chrono::high_resolution_clock::now();
        long long value = MODFiboRecursive(i);
        stop = chrono::high_resolution_clock::now();
        duration = chrono::duration_cast<chrono::seconds>(stop - start);
        cout << setw(10) << "" << duration.count() << " seconds ";
        cout << setw(15) << "" << value << " value " << endl;
        } else {
            cpp_int value = MODFiboRecursive(i);
            cout << setw(75) << "" << value << " value " << endl;
        }
     }
}


