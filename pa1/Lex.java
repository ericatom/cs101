//---------------------------------------
// Lex.java
// Erica Tom edtom
// pa1
// ---------------------------------------

import java.io.*;
import java.util.Scanner;

class Lex{
   public static void main(String[] args) throws IOException{
      Scanner in = null;
      PrintWriter out = null;
      String line = null;
      int n = 0, lineNumber = 0; //n = number of lines

      //check that there are two command line arguments
      //quit with usage message to stder if > or < than 2 stings
      if(args.length != 2){
         System.err.println("Error: Needs two command line arguments only");
         System.exit(1);
      }

      in = new Scanner(new File(args[0]));

      //count the number of lines n in the file named by args[0]
      while(in.hasNextLine()){
         n++;
         in.nextLine();
      }

      in.close();

      //create a string array of length n and read in the lines of the file
      //as Strings, placing them into the array

      String[] arrayofStrings = new String[n];

      in = new Scanner(new File(args[0]));
      out = new PrintWriter(new FileWriter(args[1]));

      while(in.hasNextLine()){
         arrayofStrings[lineNumber] = in.nextLine();
         lineNumber++;
      }

      //create a list of whose elements are the indices of the above string array
      //indices should be arranged in order that sorts the array
      List listOfIndices = new List();

      listOfIndices.prepend(0); //add first element

      //Use the List constructed in (3) to print the array in alphabetical order
      //to the file named in args[1]
      for (int j = 1; j < arrayofStrings.length; j++) {
         listOfIndices.moveBack();
         String temp = arrayofStrings[j];
         int i = j - 1;
         while (i >= 0 && temp.compareTo(arrayofStrings[listOfIndices.get()]) <= 0) {
            listOfIndices.movePrev();
            i--;
         }

         if(listOfIndices.index() >= 0)
            listOfIndices.insertAfter(j);
         else
            listOfIndices.prepend(j);
      }

      //array is never sorted. indirectly sort the indices
      listOfIndices.moveFront();
         while (listOfIndices.index() != -1) {
            out.print(arrayofStrings[listOfIndices.get()]);
            listOfIndices.moveNext();
            if (listOfIndices.index() != -1)
               out.println();
      }
      in.close();
      out.close();
   }
}
