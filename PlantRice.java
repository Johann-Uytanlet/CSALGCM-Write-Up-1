#include <bits/stdc++.h>

#define EPS 1e-8

using namespace std;

void solve(double s, double d, double k, double a){
     // compute and print answer here

    double rate;// = 1 - pow((1/(1+exp(-k*(x-0.5)))), a) + d;
    bool sweetSpot = false;
    double x = 0.0;
    double increment = 0.1;
    double err;
    while(x <= 1.0 && x >= 0.0 && increment > 0.0000000000001){
        rate = 1 - pow((1/(1+exp(-k*(x-0.5)))), a) + d;
        err = abs(rate-s);
        if(err <= 0.0000000000001){
            sweetSpot = true;
            break;
        }
        else if (rate > s){
            x += increment;
        }
        else{
            increment /= 10;
            x -= 9*increment;
        }
    }

    if (!sweetSpot)
        printf("Sweet spot cannot be reached! Those cheeky developers!\n");
    else
        printf("%.12lf\n", x);
}

int main() {
    int n;
    scanf("%d",&n);
    
    vector<vector<double>> vals(n,vector<double>(4));
    for(int i = 0; i < n; i++){
      	double s,d,k,a;
      	scanf("%lf%lf%lf%lf",&s,&d,&k,&a);
      	solve(s,d,k,a);
    }
}
