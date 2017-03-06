//---------------------------------------
// List.java
// Erica Tom edtom
// pa1
// ---------------------------------------

class List{

   private class Node{
      int data;
      Node previous;
      Node next;

      Node(int data, Node previous, Node next){
         this.data = data;
         this.previous = previous;
         this.next = next;
      }

      // returns the string form of data
      public String toString() {
         return String.valueOf(data);
      }

      public boolean equals(Object x){
         boolean eq = false;
         Node that;
         if(x instanceof Node){
            that = (Node) x;
            eq = (this.data == that.data);
         }
         return eq;
     }
   }

   private Node front;
   private Node back;
   private Node cursor;
   private int length;  //length of list
   private int index;   //index of cursor

   // Constructor
   // Creates a new empty list
   List(){
      front = null;
      back = null;
      cursor = null;
      length = 0;
      index = -1;
   }

   //Access functions

   //Returns the number of elements in this List
   int length(){
      return length;
   }

   // If cursor is defined, returns the index of the cursor element, otherwise return -1
   int index(){
      return index;
   }

   // Returns front element. pre: length()>0
   int front(){
      if (length < 1)
         throw new RuntimeException("Error: Empty List has no front element");
      return front.data;
   }

   // Returns back eleement. pre: length()>0
   int back(){
      if (length < 1)
         throw new RuntimeException("Error: Empty List has no back element");
      return back.data;
   }
   
   // Returns cursor element. pre: length()>0, index()>=0
   int get(){
      if (length <= 0)
         throw new RuntimeException("Error: Unable to call get() on empty list");
      if (index < 0)
         throw new RuntimeException("Error: Unable to call get() while index is undefined");
      return cursor.data;
   }

   // Returns true if this List and L are the same integer sequence
   // The cursor is ignored in both lists.
   boolean equals(List L){
      if(L.length != length){
         return false;
      }
      Node tempL = L.front;
      Node temp = front;
      while(tempL != null && temp != null){
         if(!tempL.equals(temp)){
            return false;
         }
        tempL = tempL.next;
        temp = temp.next;
      }
      return true;
   }

   // Manipulation procedures

   // Resets this List to its original empty state
   void clear(){
      front = null;
      back = null;
      cursor = null;
      length = 0;
      index = -1;
   }

   // If List is non-empty, places the cursor under the front element, otherwise do nothing
   void moveFront(){
      if (length > 0){
         cursor = front;
         index = 0;
      }
   }

   // If cursor is non-empty, places the cursor under the back element, otherwise do nothing
   void moveBack(){
      if (length > 0){
         cursor = back;
         index = length-1;
      }
   }

   // If cursor is defined and not at front, moves cursor one step toward
   // front of this List, if cursor is defined and at front, cursor becomes
   // undefined, if cursor is undefined does nothing.
   void movePrev(){
      if(cursor != null && index != 0){ //if cursor is defined and not at front
         cursor = cursor.previous;
         index--;
      }else if(cursor != null && index == 0){
         cursor = null;
         index = -1;
      }
   }

   // If cursor is defined and not at back, moves cursor one step toward
   // back of this List, if cursor is defined and at back, cursor becomes
   // undefined, if cursor is undefined does nothing.
   void moveNext(){
      if(cursor != null && index != length-1){ //if cursor is defined and not at back
         cursor = cursor.next;
         index++;
      }else if(cursor != null && index == length-1){ //if cursor is defined and at back
         cursor = null;
         index = -1;
      }
   }
   
   // Insert new element into this List. If List is non-empty,
   // insertion takes place after back element.
   void append(int data){
      Node newNode = new Node(data,null,null);
      if (length == 0){
         front = newNode;
         back = newNode;
      }else{
         back.next = newNode;
         newNode.previous = back;
         back = newNode;
      }
      length++;
   }

   // Insert new element before cursor.
   // Pre: length()>0, index()>=0
   void insertBefore(int data){
      if(length < 1){
         throw new RuntimeException("Error: Unable to insertBefore() on empty list");
      }
      if(index < 0){
         throw new RuntimeException("Error: Unable to insertBefore() on undefined index");
      }
      Node newNode = new Node(data,null,null);
      newNode.previous = cursor.previous;
      newNode.next = cursor;
      if(cursor.previous == null){
         front = newNode;
      }else{
         cursor.previous.next = newNode;
      }
      cursor.previous = newNode;
      length++;
   }
   
   // Inserts new element after cursor.
   // Pre: length()>0, index()>=0
   void insertAfter(int data){
      if(length < 1){
         throw new RuntimeException("Error: Unable to insertAfter() on empty list");
      }
      if(index < 0){
         throw new RuntimeException("Error: Unable to insertBefore() on undefined index");
      }
      Node newNode = new Node(data,null,null);
      newNode.previous = cursor;
      newNode.next = cursor.next;
      if(cursor.next == null){
         back = newNode;
      }else{
         cursor.next.previous = newNode;
      }
      cursor.next = newNode;
      length++;
   }

   // Deletes the front element. Pre: length()>0
   void deleteFront(){
      if(length <= 0){
         throw new RuntimeException("Erorr: Unable to deleteFront() of empty list");
      }
      if (cursor == front){
         cursor = null;
         index = -1;
      }
      front = front.next;
      front.previous = null;
      length--;
   }
   
   // Deletes the back element. Pre: length()>0
   void deleteBack(){
      if(length <= 0){
         throw new RuntimeException("Erorr: Unable to deleteBack() of empty list");
      }
      if(length == 1){
         clear();
      }
      if(length > 1){
         back = back.previous;
         back.next = null;
         length--;
      }
   }

   // Deletes cursor element, making cursor undefined.
   // Pre: length()>0, index()>=0
   void delete(){
      if(length <= 0){
         throw new RuntimeException("Error: Unable to delete empty list");
      }
      if(index < 0){
         throw new RuntimeException("Error: Unable to delete undefined index");
      }
      if (cursor == front){
         deleteFront();
      }
      if (cursor == back){
         deleteBack();
      }
      if (cursor != back && cursor != front){
         cursor.previous.next = cursor.next;
         cursor.next = cursor.previous;
         cursor = null;
         length--;
         index = -1;
      }
   }
   
   // Other methods

   // Overrides Object's toString method. Returns a String
   // representation of this List consisting of a space
   // separated sequence of integers, with front on left.
   public String toString(){
      String stringList = "";
      Node tempfront = front;
      while(tempfront != null){
         stringList = stringList + String.valueOf(tempfront.data) + " ";
         tempfront = tempfront.next;
      }
      return stringList;
   }

   // Returns a new List representing the same integer sequence as this
   // List. The cursor in the new list is undefined, regardless of the
   // state of the cursor in this List. This List is unchanged.
   List copy(){
      List newList = new List();
      Node tempfront = front;
      while(tempfront != null){
         newList.append(tempfront.data);
         tempfront = tempfront.next;
      }
      return newList;
   }

   // Returns a new List which is the concatenation of
   // this list followed by L. The cursor in the new List
   // is undefined, regardless of the states of the cursors
   // in this List and L. The states of this List and L are unchanged.
   List concat(List L){
      List cc = copy();
      Node tempC = L.front;
      while(tempC != null){
         cc.append(tempC.data);
         tempC = tempC.next;
      }
      return cc;
   }
}
   

