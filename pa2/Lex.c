#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include "List.h"

#define MAX_LEN 255

int main(int argc, char *argv[]){
   int count = 0;
   FILE *in, *out;
   char line[MAX_LEN];

if( argc != 3 ){
   printf("Usage: %s <input file> <output file>\n", argv[0]);
   exit(1);
}

in = fopen(argv[1], "r");
out = fopen(argv[2], "w");
if( in==NULL ){
   printf("Unable to open file %s for reading\n", argv[1]);
   exit(1);
}
if( out==NULL ){
   printf("Unable to open file %s for writing\n", argv[2]);
   exit(1);
}

while( fgets(line,MAX_LEN, in) != NULL){
        count++;
}

fclose(in);
in = fopen(argv[1], "r");

char lines[count -1][MAX_LEN];
int lineNumber = -1;

while (fgets(line, MAX_LEN, in) != NULL){
   strcpy(lines[++lineNumber], line);
}

List listofIndices = newList();

prepend(listofIndices,0); //add first element

for(int j = 1; j < count; j++){
   moveBack(listofIndices);
   char *temp = lines[j];
   int i = j-1;
   while(i >= 0 && strcmp(temp,lines[get(listofIndices)]) <= 0){
      movePrev(listofIndices);
      i--;
   }

   if(index(listofIndices) >= 0)
      insertAfter(listofIndices,j);
   else
          prepend(listofIndices,j);
}

moveFront(listofIndices);

while(index(listofIndices) >= 0){
   fprintf(out, "%s", lines[get(listofIndices)]);
   moveNext(listofIndices);
}

fclose(in);
fclose(out);

freeList(&listofIndices);

return(0);

}
