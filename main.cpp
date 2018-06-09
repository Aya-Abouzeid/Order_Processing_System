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
int random_isbn(){
    ostringstream os;
    int digit = (rand() % 1000) + 1;
    os << digit;

    cout<< stoi(os.str());
}
int random_author() {
    ostringstream os;

    int digit = rand() % 6;
    os << digit;
    return stoi(os.str());
}


int main(int argc, const char *argv[]) {

    string categories[] = {"Science", "Art", "Religion", "History", "Geography"};

    string authors [] = {"M.Ahmed","hassan hussein","ahmed hamdy","eman shawky","Hala Moustafa ","Youssef A.Darwish"};
    freopen("./author.txt", "w", stdout);

    for (int i = 1; i <= 100; ++i) {
        cout << "aaa"
             << to_string(i)
             << ","
             << authors[random_author()]
             //             << ","
             //             << "\"" << std::setfill('a') << std::setw(6) << to_string(i) << "\""
             << endl;

    }

    freopen("./BOOK.txt", "w", stdout);
    int j = 1980;

    for (int i = 1; i <= 200000; ++i, j++) {
        cout << "aaa"
             << to_string(i+800000)
             << ","
             << "BOOK" << to_string(i+800000)
             << ","
             << to_string((i % 6) +1)   // pid
             << ", "
             << to_string(j) <<"-01-01"        //year
             << ","
             << random_price()      //price
             << " ,"
             << categories[random_category()]   //category
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


    freopen("./Author.txt", "w", stdout);

    // start with : i=1,i+=2
    // start with : i=2,i+=2
    // then random to make random books have more than 1 author
    // i+=10
    //i+=100
    //i+=50



    for (int i = 2,j=1; i <= 1000000; i+=4,j++) {
        cout << "aaa"
             << to_string(i)
             << ","
             << "Author" << to_string(j)
             << endl;

        if(j>500000)
            j=1;
    }



    return 0;
}

