// Constructor
private class Matrix{
 
   private class Entry{
      int column;
      double value; 

      Entry(int column, double value){
         this.column = column;
         this.value = value;  
      }

      public String toString(){ 
         return String.valueOf(value);
      }

      public boolean equals(Entry x){
         boolean eq = false;
         Entry that;
         if(x instanceof Entry){
            that = (Entry) x;
            eq = ((this.column == that.column)&&(this.value == that.value));
         }
         return eq;
      }

 }
 private List[] row;
 private int size;
 private int length; //number of nnz 

Matrix(int n){ // Makes a new n x n zero Matrix. pre: n>=1
   size = n;
   if(n < 1)
      throw new RuntimeException("Matrix Error: Matrix() called with no columns or rows");
   row = new List[n];
   for(int i = 1; i <= n; i++){
      row[i] = new List();  
   }
   length = 0;
}

// Access functions

int getSize(){ // Returns n, the number of rows and columns of this Matrix
   return size;
}

int getNNZ(){ // Returns the number of non-zero entries in this Matrix
   return length;
   /*int counter = 0;
   for(int i = 1; i <= getSize(); i++){
      counter = counter + row[i].length;
   }
   return counter; */
}

public boolean equals(Object x){ // overrides Object's equals() method
   
}

// Manipulation procedures

void makeZero(){ // sets this Matrix to the zero state
   for(int i = 0; i <= size; i++){
      row[i].clear();
   }
   length = 0;
}

Matrix copy(){ // returns a new Matrix having the same entries as this Matrix
   Matrix C = new Matrix(size);
   for(int i = 0; i <= this.size; i++){
      row[i].moveFront();
      while(row[i].index() >= 0){
         Entry temp = row[i].get();
         C.changeEntry(i, temp.column, temp.value);
         row[i].moveNext();
      }
   }
   C.length = this.length;
   return C;
}

 // changes ith row, jth column of this Matrix to x
 // pre: 1<=i<=getSize(), 1<=j<=getSize()
void changeEntry(int i, int j, double x){
   if(i < 1 || i > getSize())
      throw new RuntimeException("Matrix Error: changeEntry() called with invalid row number");
   if(j < 1 || j > getSize())
      throw new RuntimeException("Matrix Error: changeEntry() called with invalid column number");
   if(x == 0.0 && j == 0){
      //do nothing
   }else if(x == 0.0 && j != 0){
      for(int k = 0; k < j; k++){
      row[i].moveNext();
      }
      delete();
      length--;
   }else{
      for(int k = 0; k < j; k++){
         row[i].moveNext();
      }
      delete();
      for(int k = 0; k < j; k++){
         row[i].moveNext();
      }
      insertBefore(x);
   }
}

Matrix scalarMult(double x)
 // returns a new Matrix that is the scalar product of this Matrix with x

// returns a new Matrix that is the sum of this Matrix with M
// pre: getSize()==M.getSize()
Matrix add(Matrix M){
   if(this.getSize() != M.getSize())
      throw new RuntimeException("Matrix Error: unable to add() two matrices of different sizes");
   if(this.equals(M))
      return scalarMult(2.0);
   Matrix addMatrix = new Matrix(size);
   

}
 
Matrix sub(Matrix M)
 // returns a new Matrix that is the difference of this Matrix with M
 // pre: getSize()==M.getSize()

Matrix transpose()
 // returns a new Matrix that is the transpose of this Matrix

Matrix mult(Matrix M)
 // returns a new Matrix that is the product of this Matrix with M
 // pre: getSize()==M.getSize()

// Other functions
public String toString() // overrides Object's toString() method

private static double dot(List P, List Q)

}
