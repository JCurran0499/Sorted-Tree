#include "sortedtree.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

static void err(char *e);
static SortedTree *findDivision(SortedTree *s, char *d);
static int listSize(LinkedList *list);

/* this establishes the sorted tree as brand new.
This must be done to every Sorted Tree before
actions are taken on it, otherwise results are
undefined. This function will not free any
previously used memory. The clearTree() function
is used for that */
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

    LinkedList newValues = {NULL, NULL};
    char *divisionName = malloc(sizeof(d) + 1);
    strcpy(divisionName, d);

    SortedTree addition = {divisionName, &newValues, NULL, NULL};

    if (s -> division = NULL) { //if this is the first division
        *s = addition;
    } else {
        if (strcmp(d, s -> division) < 0) {

            if (s -> left = NULL) {
                s -> left = &addition;
            } else {
                addDivision(s -> left, d);
            }

        } else if (strcmp(d, s -> division) > 0) {

            if (s -> right = NULL) {
                s -> right = &addition;
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




static void err(char *e) {
    perror(e);
    exit(1);
}

static SortedTree *findDivision(SortedTree *s, char *d) {
    if (s == NULL)
        return NULL;

    if (strcmp(s -> division, d) == 0)
        return s;
    if (strcmp(s -> division, d) < 0)
        return findDivision(s -> right, d);
    if (strcmp(s -> division, d) > 0)
        return findDivision(s -> left, d);
}

static int listSize(LinkedList *list) {
    if (list == NULL || list -> value == NULL)
        return 0;

    return 1 + listSize(list -> next);
}

int main(void) {
    printf("%d", strcmp("a", "b"));

    return 0;
}
