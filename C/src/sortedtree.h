/* linked list structure */
typedef struct linkedlist {
    void *value;
    struct linkedlist *next;
} LinkedList;

/* binary search tree structure */
typedef struct sortedtree {
    char *division;
    LinkedList *values;
    struct sortedtree *left;
    struct sortedtree *right;
} SortedTree;

void initialize(SortedTree *s); //all trees must be initialized first
void addDivision(SortedTree *s, char *d);
int numDivisions(SortedTree *s);
int numValues(SortedTree *s, char *d);
int totalValues(SortedTree *s);
void addValue(SortedTree *s, char *d, void *v);
void addValueAtIndex(SortedTree *s, char *d, void *v);
void *getValue(SortedTree *s, char *d, int index);
int divisionExists(SortedTree *s, char *d);
int valueExists(SortedTree *s, char *d, void *v);
void removeDivision(SortedTree *s, char *d);
void removeValue(SortedTree *s, char *d, int index);
void clearTree(SortedTree *s);
