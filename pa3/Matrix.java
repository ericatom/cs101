//-----------------------------------------------------------------------------------------
// Matrix.java
// Erica Tom edtom 
// pa3
//-----------------------------------------------------------------------------------------

class Matrix{

   private class Entry{
      int column;
      double value;

      Entry(int column, double value){
         this.column = column;
         this.value = value;
      }

      public String toString(){
         return String.valueOf("(" + column + ", " + value +") ");
      }

      public boolean equals(Object x){
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


   // Constructor

   // Makes a new n x n zero Matrix. pre: n>=1
   Matrix(int n){ // Makes a new n x n zero Matrix. pre: n>=1
      this.size = n;
      if(n < 1)
         throw new RuntimeException("Matrix Error: Matrix() called with no columns or rows");
      row = new List[n+1];
      for(int i = 1; i <= n; i++){
         row[i] = new List();
      }
   }

   // Returns n, the number of rows and columns of this Matrix
   int getSize(){ // Returns n, the number of rows and columns of this Matrix
      return this.size;
   }

   // Returns the number of non-zero entries in this Matrix
   int getNNZ(){ // Returns the number of non-zero entries in this Matrix
      int nnz = 0;
      for(int i = 1; i < this.size; i++){
         nnz += row[i].length();
      }
      return nnz;
   }

   // overrides Object's equals() method
   public boolean equals(Object x){ // overrides Object's equals() method
      Matrix M;
      if (x instanceof Matrix){
         M = (Matrix) x;
      if (this.getSize() != M.getSize()){
         return false;
      }
      for (int i = 1; i< this.size; i++){
         if(!(row[i].equals(M.row[i]))){
            return false;
         }
      }
      return true;
      }
   return false;
   }

   // Manipulation procedures

   // sets this Matrix to the zero state
   void makeZero(){ // sets this Matrix to the zero state
      for(int i = 1; i <= size; i++){
         row[i].clear();
      }
   }

   // returns a new Matrix having the same entries as this Matrix 
   Matrix copy(){ // returns a new Matrix having the same entries as this Matrix
      Matrix C = new Matrix(size);
      for(int i = 1; i <= this.size; i++){
         row[i].moveFront();
         while(row[i].index() >= 0){
            Entry temp = (Entry) row[i].get();
            C.changeEntry(i, temp.column, temp.value);
            row[i].moveNext();
         }
      }
      C.size = this.size;
      return C;
   }

   // changes ith row, jth column of this Matrix to x
   // pre: 1<=i<=getSize(), 1<=j<=getSize()
   void changeEntry(int i, int j, double x){
      if(i < 1 || i > getSize())
         throw new RuntimeException("Matrix Error: changeEntry() called with invalid row number");
      if(j < 1 || j > getSize())
         throw new RuntimeException("Matrix Error: changeEntry() called with invalid column number");
      Entry temp = new Entry(j,x);
      List tempR = row[i];
      tempR.moveFront();
      if(x == 0.0 && j == 0){
      }else if( x != 0.0 && tempR.length() == 0){
         tempR.append(temp);
      }else if(x != 0.0 && tempR.length() >= 1){
         Entry entry1 = (Entry)tempR.get();
         if(entry1.column == j){
             entry1.value = x;
         }else{
            while(entry1.column < j && tempR.index() != -1){
               tempR.moveNext();
               if(tempR.index() != -1){
                  entry1 = (Entry)tempR.get();
               }
            }
            if (tempR.index() == -1){
               tempR.append(temp);
            }else{
               tempR.insertBefore(temp);
            }
         }
      }else if(x == 0 && tempR.length() >= 1){
         Entry entry2 = (Entry)tempR.get();
         if(entry2.column == j){
            tempR.delete();
         }else{
            while(entry2.column < j && tempR.index() != -1){
               entry2 = (Entry)tempR.get();
               tempR.moveNext();
            }
            if(tempR.index() != -1){
               tempR.delete();
            }
         }
      }
   }
   
   // returns a new Matrix that is the scalar product of this Matrix with x
   Matrix scalarMult(double x){
      Matrix A = new Matrix(size);
      for(int i = 1; i <= this.size; i++){
         if(row[i].length() != 0){
            this.row[i].moveFront();
               while(this.row[i].index() >= 0){
                  Entry temp = (Entry) this.row[i].get();
                  temp = new Entry(temp.column, temp.value);
                  temp.value = temp.value * x;
                  A.row[i].append(temp);
                  this.row[i].moveNext();
            }
         }
      }
      return A;
   }
   
   // returns a new Matrix that is the sum of this Matrix with M
   // pre: getSize()==M.getSize()
   Matrix add(Matrix M){
      if (this.getSize() != M.getSize()) {
         throw new RuntimeException("Matrix Error: add() called on Matrices of different size");
      }
      Matrix A = new Matrix(this.getSize());
      for (int i = 1; i <= this.getSize(); i++) {
         List t = this.row[i];
         List m = M.row[i];
         t.moveFront();
         m.moveFront();
         if (this.equals(M)) {
            A = this.scalarMult(2);
         }else {
            while (t.index() != -1 || m.index() != -1) {
               if (t.index() != -1 && m.index() != -1) {
                  Entry a = (Entry)t.get();
                  Entry b = (Entry)m.get();
                  if (a.column > b.column) {
                     A.changeEntry(i, b.column, b.value);
                     m.moveNext();
                  } else if (a.column < b.column) {
                     A.changeEntry(i, a.column, a.value);
                     t.moveNext();
                  } else if (a.column == b.column) {
                     A.changeEntry(i, a.column, a.value + b.value);
                     t.moveNext();
                     m.moveNext();
                  }
               } else if (t.index() != -1 && m.index() == -1) {
                  Entry a = (Entry)t.get();
                  A.changeEntry(i, a.column, a.value);
                  t.moveNext();
               } else if (t.index() == -1 && m.index() != -1) {
                  Entry b = (Entry)m.get();
                  A.changeEntry(i, b.column, b.value);
                  m.moveNext();
               }
            }
         }
      }
      return A;
   }
   
   // returns a new Matrix that is the difference of this Matrix with M
   // pre: getSize()==M.getSize()
   Matrix sub(Matrix M){
      if (this.getSize() != M.getSize()) {
         throw new RuntimeException("Matrix Error: sub() called on Matrices of different size");
      }
      Matrix A = new Matrix(this.getSize());
      for (int i = 1; i <= this.getSize(); i++) {
         List t = this.row[i];
         List m = M.row[i];
         t.moveFront();
         m.moveFront();
         while (t.index() != -1 || m.index() != -1) {
            if (t.index() != -1 && m.index() != -1) {
               Entry a = (Entry)t.get();
               Entry b = (Entry)m.get();
               if (a.column > b.column) {
                  A.changeEntry(i, b.column, -(b.value));
                  m.moveNext();
               } else if (a.column < b.column) {
                  A.changeEntry(i, a.column, a.value);
                  t.moveNext();
               } else if (a.column == b.column) {
                  A.changeEntry(i, a.column, a.value - b.value);
                  t.moveNext();
                  m.moveNext();
               }
            }else if (t.index() != -1 && m.index() == -1) {
               Entry a = (Entry)t.get();
               A.changeEntry(i, a.column, a.value);
               t.moveNext();
            }else if (t.index() == -1 && m.index() != -1) {
               Entry b = (Entry)m.get();
               A.changeEntry(i, b.column, -(b.value));
               m.moveNext();
            }
         }
      }
      return A;

   }

    // returns a new Matrix that is the product of this Matrix with M
   // pre: getSize()==M.getSize()
   Matrix mult(Matrix M){
      if(this.getSize() != M.getSize())
         throw new RuntimeException("Matrix Error: unable to mult() two matrices of different sizes");
      Matrix A = new Matrix(size);
      Matrix B = M.transpose();

      for(int i = 1; i <= this.size; i++){
         if(this.row[i].length() != 0){
            for(int j = 1; j <= size; j++){
               A.changeEntry(i, j, dot(row[i], B.row[j]));
            }
         }
      }
      return A;
   }

   // overrides Object's toString() method
   public String toString(){
      String print = "";
      for(int i = 1; i <= this.size; i++){
         row[i].moveTo(0);
         if(row[i].length() > 0){
            print += i + ": " + row[i] + "\n";
         }
      }
      return print;
   }

   private static double dot(List P, List Q){
      double product = 0;
      P.moveFront();
      Q.moveFront();
      while(P.index() != -1 && Q.index() != -1){
         Entry tempP = (Entry) P.get();
         Entry tempQ = (Entry) Q.get();
         if(tempP.column < tempQ.column){
             P.moveNext();
         }else if(tempP.column > tempQ.column){
            Q.moveNext();
         }else{
            product += (tempP.value*tempQ.value);
            P.moveNext();
            Q.moveNext();
         }
       }
      return product;
    }

}
