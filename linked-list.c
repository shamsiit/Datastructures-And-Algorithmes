#include<stdio.h>
#include<stdlib.h>
#include<assert.h>

struct node
{
	int data;
	struct node* next;
};

void printList(struct node *n){

	while(n != NULL){
		printf("%d    ",n->data);
		n = n->next;
	}
	printf("\n");

}

void push(struct node** head_ref,int new_data){
	struct node* new_node = (struct node*) malloc(sizeof(struct node));

	new_node->data = new_data;
	new_node->next = (*head_ref);
	(*head_ref) = new_node;

}

void insertAfter(struct node* prev_node,int new_data){

	if(prev_node == NULL){
		printf("the given previous node cannot be NULL");       
        return;
	}

	struct node* new_node = (struct node*)malloc(sizeof(struct node));

	new_node->data = new_data;
	new_node->next = prev_node->next;
	prev_node->next = new_node;

}
//insert in last
void append(struct node** head_ref,int new_data){

	struct node* new_node = (struct node*)malloc(sizeof(struct node));

	struct node *last = *head_ref;
	new_node->data = new_data;
	new_node->next = NULL;

	if(*head_ref == NULL){
		*head_ref = new_node;
		return;
	}

	while(last->next != NULL){
		last = last->next;
	}

	last->next = new_node;
	return;

}

void deleteNode(struct node **head_ref,int  key){

	struct node* temp = *head_ref,*prev;
	if(temp!= NULL && temp->data == key){
		*head_ref = temp->next;
		free(temp);
		return;
	}

	while(temp!=NULL && temp->data != key){
		prev = temp;
		temp = temp->next;
	}

	if(temp == NULL) return;

	prev->next = temp->next;
	free(temp);

}

int getNth(struct node* head , int index){
	struct node* current = head;
	int count = 0;
	while(current != NULL){
		if(count == index){
			return (current->data);

		}
		count++;
		current = current->next;
	}

}

int main(){
	struct node* head = NULL;
 
  // Insert 6.  So linked list becomes 6->NULL
  append(&head, 6);
 
  // Insert 7 at the beginning. So linked list becomes 7->6->NULL
  push(&head, 7);
 
  // Insert 1 at the beginning. So linked list becomes 1->7->6->NULL
  push(&head, 1);
 
  // Insert 4 at the end. So linked list becomes 1->7->6->4->NULL
  append(&head, 4);
 
  // Insert 8, after 7. So linked list becomes 1->7->8->6->4->NULL
  insertAfter(head->next, 8);
 
  printf("\n Created Linked list is: ");
  printList(head);
  deleteNode(&head, 1);
  puts("\nLinked List after Deletion of 1: ");
  printList(head);
 
  return 0;
}
