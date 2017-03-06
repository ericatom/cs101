//-----------------------------------------------------------------------------------------
// Sparse.java
// Erica Tom edtom 
// pa3
//-----------------------------------------------------------------------------------------

import java.io.*;
import java.util.Scanner;

class Sparse{
   public static void main(String[] args) throws IOException{
      Scanner in = null;
      PrintWriter out = null;

      if(args.length != 2){
         System.err.println("Error: Needs two command line arguments only");
         System.exit(1);
      }

      in = new Scanner(new File(args[0]));
      out = new PrintWriter(new FileWriter(args[1]));

      int sizeM = in.nextInt();

      Matrix A = new Matrix(sizeM);
      Matrix B = new Matrix(sizeM);


      int nnzA = in.nextInt();
      int nnzB = in.nextInt();

      for (int i = 1; i <= nnzA; i++) {
         A.changeEntry(in.nextInt(), in.nextInt(), in.nextDouble());
      }

      for (int j = 1; j <= nnzB; j++) {
         B.changeEntry(in.nextInt(), in.nextInt(), in.nextDouble());
      }
      
      out.println("A has " + nnzA + " non-zero entries:\n" + A);
      out.println("B has " + nnzB + " non-zero entries:\n" + B);

      Matrix C = A.scalarMult(1.5);
      out.println("(1.5)*A =\n" + C);

      Matrix D = A.add(B);
      out.println("A+B =\n" + D);

      Matrix E = A.add(A);
      out.println("A+A =\n" + E);

      Matrix F = B.sub(A);
      out.println("B-A =\n" + F);

      Matrix G = A.sub(A);
      out.println("A-A =\n" + G);

      Matrix H = A.transpose();
      out.println("Transpose(A) =\n" + H);

      Matrix I = A.mult(B);
      out.println("A*B =\n" + I);

      Matrix J = B.mult(B);
      out.println("B*B =\n" + J);

      in.close();
      out.close();

    }
}
