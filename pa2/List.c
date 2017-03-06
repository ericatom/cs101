#include<stdio.h>
#include<stdlib.h>
#include "List.h"

// structs --------------------------------------------------------------------

// private NodeObj type
typedef struct NodeObj{
   int data;
   struct NodeObj* next;
   struct NodeObj* previous;
} NodeObj;

// private Node type
typedef NodeObj* Node;

// private ListObj type
typedef struct ListObj{
   Node front;
   Node back;
   Node cursor;
   int length;
   int index;
} ListObj;

// Constructors-Destructors ---------------------------------------------------

// newNode()
// Returns reference to new Node object. Initializes next and data fields.
// Private.
Node newNode(int data, Node previous, Node next){
   Node N = malloc(sizeof(NodeObj));
   N->data = data;
   N->next = NULL;
   N->previous = NULL;
   return(N);
}

// freeNode()
// Frees heap memory pointed to by *pN, sets *pN to NULL.
// Private.
void freeNode(Node* pN){
   if(pN != NULL && *pN != NULL){
      free(*pN);
      *pN = NULL;
   }
}

// newList()
// Returns reference to new empty List object.
List newList(void){
   List L;
   L = malloc(sizeof(ListObj));
   L->front = L->back = NULL;
   L->length = 0;
   L->index = -1;
   return(L);
}

// freeList()
// Frees all heap memory associated with List *pL, and sets *pL to NULL.S
void freeList(List* pL){
   if(pL!=NULL && *pL!=NULL){
      Node tempFront = (*pL)->front;
      while(tempFront != NULL){
         Node head = tempFront;
         tempFront = tempFront->next;
         free(head);
      }
      free(*pL);
      *pL = NULL;
   }
}

// Access functions -----------------------------------------------------------

int length(List L){
   if(L == NULL){
      printf("List Error: called length() on null list\n");
      exit(1);
   }
   return L->length;
}

int index(List L){
   if(L == NULL){
      printf("List Error: called index() on null list\n");
      exit(1);
   }
   return L->index;
}

int front(List L){
   if(L == NULL){
      printf("List Error: called front() on null list\n");
      exit(1);
   }
   if(L->length == 0){
      printf("List Error: called front() on empty list\n");
      exit(1);
   }
   return L->front->data;
}

int back(List L){
   if(L == NULL){
      printf("List Error: called back() on null list\n");
      exit(1);
   }
   if(L->length == 0){
      printf("List Error: called back() on empty list\n");
      exit(1);
   }
   return L->back->data;
}

int get(List L){
   if(L == NULL){
      printf("List Error: called get() on null list\n");
      exit(1);
   }
   if (L->length < 1){
      printf("List Error: called get() on empty list");
      exit(1);
   }
   if (L->index < 0){
      printf("List Error: called get() while index is undefined");
      exit(1);
   }
   return L->cursor->data;
}

int equals(List A, List B){
   if(A == NULL || B == NULL){
      printf("List Error: called equals() on null list\n");
      exit(1);
   }
   if((A->length) != (B->length)){
      return 0;
   }
   Node tempA = A->front;
   Node tempB = B->front;
   while(tempA != NULL || tempB!=NULL){
      if(tempA->data == tempB->data){
         tempA = tempA->next;
         tempB = tempB->next;
      }else{
       return 0;
      }
   }
   return 1;
}

// Manipulation procedures ----------------------------------------------------
void clear(List L){
   if(L == NULL){
      printf("List Error: called clear() on null list");
      exit(1);
   }
   L->front = NULL;
   L->back = NULL;
   L->cursor = NULL;
   L->length = 0;
   L->index = -1;
}

void moveFront(List L){
   if(L == NULL){
      printf("List Error: called moveFront() on null list");
      exit(1);
   }
   if(L->length > 0){
      L->cursor = L->front;
      L->index = 0;
   }
}

void moveBack(List L){
   if(L == NULL){
      printf("List Error: called moveBack() on null list");
      exit(1);
   }
   if(L->length > 0){
      L->cursor = L->back;
      L->index = L->length-1;
   }
}

void movePrev(List L){
   if(L == NULL){
      printf("List Error: called movePrev() on null list");
      exit(1);
   }
   if(L->cursor != NULL && L->index != 0){
      L->cursor = L->cursor->previous;
      L->index--;
   }else if(L->cursor != NULL && L->index == 0){
      L->cursor = NULL;
      L->index = -1;
   }
}

void moveNext(List L){
   if(L == NULL){
      printf("List Error: called moveNext() on null list");
      exit(1);
   }
   if(L->cursor != NULL && L->index != L->length-1){
      L->cursor = L->cursor->next;
      L->index++;
   }else if(L->cursor != NULL && L->index == L->length-1){
      L->cursor = NULL;
      L->index = -1;
   }
}

void prepend(List L, int data){
   if(L == NULL){
      printf("List Error: called prepend() on null list");
      exit(1);
   }
   Node nNode = newNode(data, NULL, NULL);
   if(L->length == 0){
      L->front = nNode;
      L->back = nNode;
   }else{
      L->front->previous = nNode;
      nNode->next = L->front;
      L->front = nNode;
   }
   L->length++;
}

void append(List L, int data){
   if(L == NULL){
      printf("List Error: called append() on null list");
      exit(1);
   }
   Node nNode = newNode(data, NULL, NULL);
   if(L->length == 0){
      L->front = nNode;
      L->back = nNode;
   }else{
      L->back->next = nNode;
      nNode->previous = L->back;
      L->back = nNode;
   }
   L->length++;
}

void insertBefore(List L, int data){
        if(L == NULL){
                printf("List Error: called insertBefore() on null list");
                exit(1);
        }
        if(L->length <= 0){
                printf("List Error: called insertBefore() on empty list");
                exit(1);
        }
        if(L->index < 0){
                printf("List Error: called insertBefore() on undefined index");
                exit(1);
        }
        if(L->index > 0){
           Node nNode = newNode(data, L->cursor->previous, L->cursor);
           nNode->previous = L->cursor->previous;
           nNode->next = L->cursor;
           if(L->cursor->previous == NULL){
              L->front = nNode;
           }else
            L->front = nNode;
        L->cursor->previous->next = nNode;
        L->length++;
    }
}

void insertAfter(List L, int data){
   if(L == NULL){
      printf("List Error: called insertBefore() on null list");
          exit(1);
   }
   if(L->length <= 0){
          printf("List Error: called insertBefore() on empty list");
          exit(1);
   }
   if(L->index < 0){
          printf("List Error: called insertBefore() on undefined index");
          exit(1);
   }
          Node nNode = newNode(data, L->cursor, L->cursor->next);
          nNode->previous = L->cursor;
          nNode->next = L->cursor->next;
          if(L->cursor->next == NULL)
             L->back = nNode;
          else
             L->cursor->next->previous = nNode;
   L->cursor->next = nNode;
   L->length++;
}

void deleteFront(List L){
   if(L == NULL){
      printf("List Error: called deleteFront() on null list");
      exit(1);
   }
   if(L->length <= 0){
      printf("List Error: called deleteFront() on empty list");
      exit(1);
   }
   if(L->cursor == L->front){
      L->cursor= NULL;
      L->index = -1;
   }
   Node temp = L->front;
   L->front = L->front->next;
   L->front->previous = NULL;
   L->length--;
   freeNode(&temp);
}

void deleteBack(List L){
   if(L == NULL){
      printf("List Error: called deleteBack() on null list");
      exit(1);
   }
   if(L->length <= 0){
      printf("List Error: called deleteBack() on empty list");
      exit(1);
   }
   if(L->cursor == L->back){
      L->cursor = NULL;
      L->index = -1;
   }
   Node temp = L->back;
   L->back = L->back->previous;
   L->back->next = NULL;
   L->length--;
   freeNode(&temp);
}

void delete(List L){
   if(L == NULL){
      printf("List Error: called delete() on null list");
      exit(1);
   }
   if(L->length <= 0){
      printf("List Error: called delete() on empty list");
      exit(1);
   }
   if(L->index < 0){
      printf("List Error: called delete() on undefined index");
      exit(1);
   }
   if(L->cursor == L->front){
      deleteFront(L);
   }
   else if(L->cursor == L->back){
      deleteBack(L);
   }
   else{
      Node temp = L->cursor;
      L->cursor->previous->next = L->cursor->next;
      L->cursor->next->previous = L->cursor->previous;
      L->cursor = NULL;
      L->length--;
      L->index = -1;
      freeNode(&temp);
   }
}

// Other operations -----------------------------------------------------------

void printList(FILE* out, List L){
   Node N = NULL;
   if( L == NULL){
      printf("List Error: called printList() on null list");
      exit(1);
   }
   for(N = L->front; N != NULL; N = N->next){
      printf("%d ", N->data);
   }
}

List copyList(List L){
   List nList = newList();
   Node tempfront = L->front;
   while(tempfront != NULL){
      append(nList,tempfront->data);
      tempfront = tempfront->next;
   }
   return nList;
}
