#include <iostream>
#include <cstdlib>
#include <sstream>
#include <iomanip>

using namespace std;

string random_phone() {
    ostringstream os;
    for (int i = 0; i < 11; ++i) {
        int digit = rand() % 10;
        os << digit;
    }
    return os.str();
}

int random_category() {
    ostringstream os;

    int digit = rand() % 5;
    os << digit;
    return stoi(os.str());
}

int random_price() {
    ostringstream os;
    int digit = (rand() % 100) + 150;
    os << digit;
    return stoi(os.str());
}

int random_stock() {
    ostringstream os;
    int digit = (rand() % 10) + 10;
    os << digit;
    return stoi(os.str());
}

int random_threshold() {
    ostringstream os;
    int digit = (rand() % 5) + 5;
    os << digit;
    return stoi(os.str());
}

int main(int argc, const char *argv[]) {

    string categories[] = {"Science", "Art", "Religion", "History", "Geography"};



    freopen("./BOOK.txt", "w", stdout);
    int j = 1980;
    for (int i = 1; i <= 5000; ++i, j++) {
        cout << "aaa"
             << to_string(i)
             << ","
             << "\"" << "BOOK" << to_string(i) << "\""
             << ","
             << to_string((i % 10) +1)   // pid
             << ", "
              << to_string(j) <<"-01-01"        //year
             << ","
             << random_price()      //price
             << " ,"
             << "\"" << categories[random_category()] << "\""   //category
             << ","
             << random_stock()    //stock
             << ","
             << random_threshold()    //threshold
             //             << ","
             //             << "\"" << std::setfill('a') << std::setw(6) << to_string(i) << "\""
             << endl;
        if (j == 2018)
            j -= 39;
    }


    return 0;
}
