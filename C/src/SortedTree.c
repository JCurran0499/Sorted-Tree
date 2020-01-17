#include "sortedtree.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>


//implicit declarations of private helper methods
static void err(char *e);
static SortedTree *findDivision(SortedTree *s, char *d);

/* Linked List functions */

void listAddValue(LinkedList *list, void *v) {
    LinkedList *addition = malloc(sizeof(LinkedList));
    addition -> value = v;
    addition -> next = NULL;

    if (list -> value == NULL) {
        *list = *addition;
    } else {
        LinkedList *temp = list;
        while (temp -> next != NULL)
            temp = temp -> next;

        temp -> next = addition;
    }
}

LinkedList *listAddValueAtIndex(LinkedList *list, void *v, int index) {
    LinkedList *addition = malloc(sizeof(LinkedList));
    addition -> value = v;
    addition -> next = NULL;

    if (index == 0) {
        addition -> next = list;
        return addition;
    } else {
        LinkedList *temp = list;
        for (int i = 0; i < index - 1; i++)
            temp = temp -> next;

        LinkedList *temp2 = temp -> next;
        temp -> next = addition;
        temp -> next -> next = temp2;

        return list;
    }
}

void *listGetValue(LinkedList *list, int index) {
    LinkedList *temp = list;
    for (int i = 0; i < index; i++)
        temp = temp -> next;

    return temp -> value;
}

LinkedList *listRemoveValue(LinkedList *list, int index) {
    if (index == 0) {
        LinkedList *newHead = list -> next;
        free(list -> value);
        free(list);
        return newHead;
    }


    LinkedList *temp = list;
    for (int i = 0; i < index - 1; i++)
        temp = temp -> next;

    LinkedList *temp2 = temp -> next -> next;

    free(temp -> next -> value);
    free(temp -> next);

    temp -> next = temp2;

    return list;
}

int listSize(LinkedList *list) {
    if (list == NULL || list -> value == NULL)
        return 0;

    return 1 + listSize(list -> next);
}

void listClear(LinkedList *list) {
    if (list != NULL) {
        free(list -> value);
        listClear(list -> next);
        free(list -> next);

        list -> value = NULL;
        list -> next = NULL;
    }
}



/* Sorted Tree functions */

void initialize(SortedTree *s) {
    if (s != NULL) {
        //set everything to NULL
        s -> division = NULL;
        s -> values = NULL;
        s -> left = NULL;
        s -> right = NULL;
    }
}

void addDivision(SortedTree *s, char *d) {
    if (s == NULL || d == NULL)
        err("Null Pointers");

    LinkedList *newValues = malloc(sizeof(LinkedList));
    newValues -> value = NULL;
    newValues -> next = NULL;

    char *divisionName = malloc(strlen(d) + 1);
    strcpy(divisionName, d);

    SortedTree *addition = malloc(sizeof(SortedTree));
    addition -> division = divisionName;
    addition -> values = newValues;
    addition -> left = NULL;
    addition -> right = NULL;

    if (s -> division == NULL) { //if this is the first division
        *s = *addition;
    } else {
        if (strcmp(d, s -> division) < 0) {

            if (s -> left == NULL) {
                s -> left = addition;
            } else {
                addDivision(s -> left, d);
            }

        } else if (strcmp(d, s -> division) > 0) {

            if (s -> right == NULL) {
                s -> right = addition;
            } else {
                addDivision(s -> right, d);
            }
        } else {
            err("Repeat Division Names"); //cannot have divisions of the same name
        }
    }
}

int numDivisions(SortedTree *s) {
    if (s == NULL)
        return 0;

    return 1 + numDivisions(s -> left) + numDivisions(s -> right);
}

int numValues(SortedTree *s, char *d) {
    SortedTree *tree = findDivision(s, d);

    if (tree == NULL)
        return -1;

    return listSize(s -> values);
}

int totalValues(SortedTree *s) {
    if (s == NULL)
        return 0;

    return listSize(s -> values) + totalValues(s -> left) + totalValues(s -> right);
}

void addValue(SortedTree *s, char *d, void *v) {
    SortedTree *division = findDivision(s, d);
    if (division == NULL)
        err("Division Does Not Exist");

    listAddValue(division -> values, v);
}

void addValueAtIndex(SortedTree *s, char *d, void *v, int index) {
    SortedTree *division = findDivision(s, d);
    if (division == NULL)
        err("Division Does Not Exist");
    if (index < 0 || index >= listSize(division -> values))
        err("Index Out of Bounds");

    division -> values = listAddValueAtIndex(division -> values, v, index);
}

void *getValue(SortedTree *s, char *d, int index) {
    SortedTree *division = findDivision(s, d);
    if (division == NULL)
        err("Division Does Not Exist");
    if (index < 0 || index >= listSize(division -> values))
        err("Index Out of Bounds");

    return listGetValue(division -> values, index);
}

int divisionExists(SortedTree *s, char *d) {
    return (findDivision(s, d) != NULL);
}

/* incomplete */
void removeDivision(SortedTree *s, char *d) {

}

void removeValue(SortedTree *s, char *d, int index) {
    SortedTree *division = findDivision(s, d);
    division -> values = listRemoveValue(division -> values, index);
}

void clearTree(SortedTree *s) {
    if (s != NULL) {
        free(s -> division);
        listClear(s -> values);
        clearTree(s -> left);
        clearTree(s -> right);
        free(s -> left);
        free(s -> right);

        s -> division = NULL;
        s -> values = NULL;
        s -> left = NULL;
        s -> right = NULL;
    }
}



/* private helper methods */

static void err(char *e) {
    printf("Error: %s", e);
    exit(1);
}

static SortedTree *findDivision(SortedTree *s, char *d) {
    if (s == NULL)
        return NULL;

    if (strcmp(s -> division, d) == 0)
        return s;
    else if (strcmp(s -> division, d) < 0)
        return findDivision(s -> right, d);
    else
        return findDivision(s -> left, d);
}



int main(void) {
    SortedTree tree;
    initialize(&tree);

    addDivision(&tree, "Numbers");

    int x = 10;
    addValue(&tree, "Numbers", &x);

    printf("Success!");
    return 0;
}
