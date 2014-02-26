#include<vector>
using std::vector;
void in2post(const char * s, char * t) {
vector<char> v; //empty stack
int count=0;
while (*s!='\0') {
if (*s=='|') {
while (!v.empty() &&
v[v.size()-1]!='(') {
t[count]=v[v.size()-1];
v.pop_back();
count++;
}
v.push_back(*s);
}
else
if (*s=='.') {
while(!v.empty() &&
v[v.size()-1]!='|' &&
v[v.size()-1]!='(') {
t[count]=v[v.size()-1];
v.pop_back();
count++;
}
v.push_back(*s);
}
else
if (*s=='*' ||
*s=='+' ||
*s=='?')
v.push_back(*s);
else
if (*s=='(')
v.push_back(*s);
else
if (*s==')') {
while (v[v.size()-1]!='(') {
t[count]=v[v.size()-1];
v.pop_back();
count++;
}
v.pop_back();
}
else {
t[count]=*s;
count++;
}
s++;
}
while (!v.empty()) {
t[count]=v[v.size()-1];
v.pop_back();
count++;
}
t[count]='\0';
}