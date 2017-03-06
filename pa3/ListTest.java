//-----------------------------------------------------------------------------------------
// ListTest.java
// Erica Tom edtom 
// pa3
//-----------------------------------------------------------------------------------------
public class ListTest{
   public static void main(String[] args){
      List A = new List();
      List B = new List();

      for(int i=1; i<=20; i++){
         A.append(i);
         B.prepend(i);
      }
      System.out.println("A: " + A);
      System.out.println("B: " + B);

      System.out.print("A Index: ");
      for(A.moveFront(); A.index()>=0; A.moveNext()){
         System.out.print(A.get()+" ");
      }
      System.out.println();
      System.out.print("B Index: ");
      for(B.moveBack(); B.index()>=0; B.movePrev()){
         System.out.print(B.get()+" ");
      }
      System.out.println();

      System.out.println("A == B: " + A.equals(B));
      System.out.println("A: " + A);
      System.out.println("B: " + B);

      System.out.println("A length: " + A.length());
      System.out.println("B length: " + B.length());

      System.out.println("A front: " + A.front() + " A back: " + A.back());
      System.out.println("B front: " + B.front() + " B back: " + B.back());

      System.out.println("A: " + A);
      System.out.println("B: " + B);

      System.out.println("Move to: 4");
      A.moveTo(4); B.moveTo(4);
      System.out.println("A cursor: " + A.index());
      System.out.println("B cursor: " + B.index());
   }
}
