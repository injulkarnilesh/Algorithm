Algorithms
===================
Implemention of **Coursera Algorithms** course.

----------

Dynamic Connectivity / Union-Find
-------------
Given set of N objects,
 - *Union command*: connects two objects.
 - *Find Query*: is there path connecting the two objects.
 > **While programming**
	 - Name these objects 0 to (N -1) and use these as array indices.
	 -	*Find* : check if objects are in same component
	 - *Union* : replace components containing two objects with their union.


####  Quick Find
 - Integer array [N]
 - Two elements (array indexes) are connected iff they have the same id.
 - Find : i and j are connected is array[i] == array[j].
 - Union : merge components (set of array elements) having i and j so that all elements in components containing i should have value array[j].
 - N<sup>2</sup> array accesses to process sequence of N union commands.

#### Quick Union
 - Integer array [N]
 - array[i] is parent of i
 - root of i is parent of parent of and so on until a[p] == p.
 - Find : i and j are connected if root(i) == root(j)
 - Union : set id of root(i) to root(j) so root(i) becomes child of root(j)

#### Weighted Quick Union
 - Quick union with effort to avoid tall trees.
 - Keep track of size of each tree (weight)
 - Connect root to smaller tree to root of larger tree.
 - Find: i and j are connected if root(i) == root(j)
 - Union : connect root(i) to root(j) such that size(i)<=size(j) and update size array of larger tree increase by size of smaller tree attached.

#### Weighted Quick Union with Path Compression
 - While finding root(i) set all elements visited to point to root(i) as parent.
 - Keeps tree almost flat
 - One Pass :   make every element on path to root to point to grandparent array[array[i]]
 - Two Pass: Keep track of visited elements and then on finding root set all their parents to root(i)
 - Practically almost linear.

| Algorithm            | Initialisation  | Union     |  Fin     |
| -------------        |:-------------:  | -----:    | -----:   |
| QuickFind            | N               |  N        |  1       |
| Quick Union          | N               |  N        |  N       |
| Weighted Quick Union | N               |  log N    |  log N  |


Analysis of algorithms
-------------
![Common Order-of-growth classification](https://raw.githubusercontent.com/injulkarnilesh/java_tryout/master/Algorithm/src/main/resources/OrderOfGrowth.png)

#### Running Time
 - Best Case : Big Omega
 - Worst Case : Big Oh
 - Average Case : Big Theta

#### Memory
 - 64 Bit machine —> 8 byte pointer.

| Type           | Bytes                         |
| -------------  |:-------------:                |
| boolean        |  1                            |
| byte           |  1                            |
| char           |  2                            |
| int            |  4                            |
| float          |  4                            |
| long           |  8                            |
| double         |  8                            |
| Array[N]       |  N (Cost of type) + 24  <br/> Eg long[n] = 8N + 24 |
| Object         |  Object Overhead : 16 bytes <br/> Reference field: 8 bytes each <br/> Padding : extra bytes used to make it total multiple of 8|


Stack and Queue
-------------
 - Stack - LIFO
 - Queue - FIFO

> Stack or queue implemented with array can be made resizable.
When get’s full, use underlying array of twice the size.
When on removing item, no of items become quarter of array size, use underlying array of half the size.

Sorting
-------------
#### Selection Sort
 - In i<sup>th</sup> iteration, i in [0, N-2], find index min of smallest of remaining elements (>=i).
 - Swap array[i] and array[min]
 - N<sup>2</sup>/2 comparisons and N exchanges.
 - O(N<sup>2</sup>)
 
#### Insertion Sort
 - In i<sup>th</sup> iteration, i in [0, N-1],
    - j in [i, j>0] with j--, swap array[j] with array[j-1] as long as array[j] < array[j-1].
 - 1/4 N<sup>2</sup> exchanges on average.
 - O(N<sup>2</sup>)

> An inversion is pair of values that are out of order. If in an array[N] with number of inversions < N, 
then Insertion sort takes linear time.

#### Shell Sort
 - Insertion sort moves array elements one position at a time.
 - h-sorting array means, in insertion sort move elements h positions at a time.
 - Shell sort is multiple iteration of h-sorting the array with decreasing value of h till 1.
 - h sequence 
     - Powers of two 1, 2, 4, 8, 16, ... :x:
     - Powers of two minus one 1, 3, 7, 15, 31, 63, ...  :x:
     - (3x+1) 1, 4, 13, 40, 121, 364, ...   :heavy_check_mark:
    
 - O(N<sup>3/2</sup>)

#### Merge Sort
 - Divide and conquer
 - Divide array into two halves, recursively sort each half, merge two halves in a sorted manner.
 - Merge two halves
     - Two halves to be merged are in an auxiliary array.
     - Starting from beginning points of both arrays compare pointed elements, copy smaller element into main array and increment that pointer
     - Do until some half or both are exhausted. If first some half exhausted, copy remaining of other half into main array.
 - O(N log N)
 - Requires extra space of auxiliary array, it better be created once and used rather than copying original array every time.
 
> Stability 
<br> Sort algorithm is stable if an array of objects sorted with one criteria (Comparator) is sorted again with another criteria
then for same values of second criteria array remains sorted with first criteria.
<br> Eg. If array of students is sorted by name and then by class, then in final array all students in same class remain sorted by name.
Insertion sort and Merge sort are stable, Selection and Shell sort are not.

#### Quick Sort
 - Steps
     - Shuffle the array to avoid worst case.
     - Partition array with pivot element (first generally) and place it at index i
     - Such that no larger elements on left side and no smaller element on right side of array[i]
     - Sort each piece the same way.
 - Partition with low, high
     - i from low + 1 towards right (i++) as long as a[i] < a[low]
     - j from high towards left (j--) as long as a[j] > a[low]
     - continue until i and j cross, and are within array bounds, then swap a[low] with a[j].
 - Worst case O(N<sup>2</sup>), already sorted.
 - Average case O(N Log N)  ~ 1.39 N Log N
 - Not stable but in place sorting, no extra array needed.
 - With many duplicate values quick sort becomes quadratic.
 - Dijkstra's 3 way partitioning
     - a[low] be pivot
     - v = i = low and j = high
     - a[v] > a[i] then swap(v, i)  i++ and v++
     - a[v] < a[i] then swap(i, j)  j--
     - a[v] == a[i] then i++
     - until i and j have crossed
     - then array between v and j is sorted and elements between v and i are same 
     - then sort pieces, low to v-1 and j+1 to high
     
#### Heap Sort
 - Uses binary heap data structure.
 - Steps
     - Create max heap within the same array with all N elements.
     - Repeatedly (N times) remove max element from the max heap. Do not null out the deleted element in the heap.
 - In place sorting with O (N log N). 
 
  
| Sort Algorithm      | In place          | Stable            | Worst             | Average           | Best              |  Remarks        |
| -------------       |:------------:     |:------------:     |:------------:     |:------------:     |:------------:     |:------------:   |
| Selection           |:heavy_check_mark: | :x:               | N<sup>2</sup>/2   |N<sup>2</sup>/2    |N<sup>2</sup>/2    | N exchanges     |
| Insertion           |:heavy_check_mark: |:heavy_check_mark: | N<sup>2</sup>/2   |N<sup>2</sup>/4    |N                  | Used for small N or partially sorted  |
| Shell               |:heavy_check_mark: |:x:                |  :question:       | :question:        |N                  | Tight code and sub quadratic |
| Quick               |:heavy_check_mark: |:x:                | N<sup>2</sup>/2   | 2 N log N         | N log N           | Fastest in practice |
| 3 Way Quick         |:heavy_check_mark: |:x:                | N<sup>2</sup>/2   | 2 N log N         | N                 | Improved quick sort for duplicate keys |
| Merge               |:x:                |:heavy_check_mark: | N log N           | N log N           | N log N           | Guaranteed N log N, stable |
| Heap                |:heavy_check_mark: |:x:                | 2 N log N         | 2 N log N         | N log N           | Guaranteed N log N, in place |
 
 
Priority Queue
------------- 
 - Dequeue operation always removes smallest/largest element in the queue.
 - Ordered Implementation maintains order of elements so while inserting it needs to iterate current structure to find 
   appropriate place for new element. Dequeue operation then is very easy, remove first element from head.
 - UnOrdered Implementation does not maintain order, so new element is simply inserted at head. But while dequeue it finds 
   smallest or largest element, removes and returns it.

> Application Example :
<br> To find out largest N elements from very large set of elements without having those all in the memory at a time. 
<br> It can be implemented with MinPriorityQueue where dequeue operation will always remove the smallest in the queue. 
<br> Iterate over all these elements one at a time, insert it into the priority queue, if queue's size is above N, dequeue one element so that smallest will be removed.

| Implementation           | Insert         | DelMax         | Max         |
| -------------            |:-------------: |:-------------: |:----------: |
| UnOrdered List of Array  |  1             | N              | N           |
| Ordered List of Array    |  N             | 1              | 1           |
| Binary Heap              |  log N         | log N          | 1           |


Binary Heap
-------------
 - Complete binary tree : Leaf nodes are at level height or height-1. height = log N where N is no of nodes.
 - Heap Ordered binary tree : Parent node value is no smaller than children node values.
 - Array implementation :
     - Indies start with 1. Also possible with 0 but, with 1 calculations are simplified.
     - Nodes in order from top to bottom, left to right. 
     - parentOf(n) = n/2, leftChildOf(n) = n*2, rightChildOf(n) = (n*2 + 1) for 1 indexed array representation.
 - Promotion of node if its value larger than parent.
     - Swap it with parent then check and continue the parent with grand-parent and so on until root.
     - Used in inserting new node. Insert new node at last index and check if promotion needed.
 - Demotion of node if its value is smaller than any of its children.
     - Swap it with child with larger value, then check that child with its children and so on until no more children.
     - Used in deleting max valued node in heap, that is at root. 
     - Exchange root node with last node, exclude this root node at last by reducing N index. Demote the new root node.
 - Applications : Binary Heap Priority Queue, Heap Sort.
     

Symbol Table
-------------
 - Key value pairs.
 - One value per key, pu will add new key value pair or update value to new one if key exist.
 - Map in java

#### UnOrdered Symbol Table
 - Linked list of Key-Value pairs.
 - Search : Scan through all keys until match if no match return null.
 - Insert : Scan through all keys until match. If match found update the value, if not insert new pair to front.

#### Ordered Symbol Table
 - Pairs are maintained in order of keys.
 - Two arrays, of for keys and other for values.
 - Insert new value need to shift all greater keys and values forward.
 - Delete needs to shift all greater keys and values backward.

| Symbol Table Implementation               | Sequential Search | Binary Search   |BST                |  
| -------------                             |:-------------:    |:-------------:  |:--------:         |
| Search                                    |  N                | log N           | h (Height ~log N) |
| Insert                                    |  N                | N               | h                 |
| Delete                                    |  N/2              | N/2             | SquareRoot(N)     |
| Min / Max                                 |  N                | 1               | h                 | 
| Floor / Ceiling                           |  N                | log N           | h                 |
| Rank                                      |  N                | log N           | h                 |
| Select                                    |  N                | 1               | h                 |
| Ordered Iteration                         |  N log N          | N               | N                 |



| Symbol Table Implementation               | Worst Case <br/> Search | Worst Case <br/> Insert | Average Case <br/> Search Hit | Average Case <br/> Insert |
| -------------                             |:-------------:          |:-------------:          |:----------:                   |:-------------:            |
| Sequential Search  <br/> (UnOrdered List) |  N                      | N                       | N/2                           | N                         |
| Binary Search <br/> (Ordered  Array)      |  log N                  | N                       | log N                         | N/2                       |
| Using BST                                 |  N                      | N                       | log N                         | log N                     |

Binary Search Tree
-------------
- Binary Tree where each node has a key whose value is larger than all keys in left subtree and smaller than all keys in right subtree.
- Each node has value, count of number of nodes in the subtree rooted at it, pointers to left and right subtrees.
- Search 
    - If lesser go left, if larger go right, if equal search hit, if null miss.
- Insert 
    - If lesser go left, if larger go right, if null insert, if equal update value.
- Number of compares for search/insert is equal to 1 + depth of node. Average ~ 2 log N.
- Delete 
    - Case : Node to delete has no children
        - Set parent's pointer to this node as null, update counts.
    - Case : Node to delete has one child
        - Set parent's pointer to this node to the child node of this node, update counts.
    - Case : Node to delete has two children (Hibbard Deletion)
        - Find smallest node in right subtree of the node to delete
        - Put the value of that minimum node in place of node to delete
        - Delete that minimum in that right subtree
        - Update the counts
        - But leaves the tree un-symmetric.
        

Balanced Search Tree
-------------

#### 2-3 Tree
- Allow one or two keys per node
    - 2 Node : One Key, two children
    - 3 Node : Two keys, three children
- Every path from root to null link has same path length.
- Nodes maintain values in ordered fashion.
- Search
    - For two node, if item to search smaller than key then go left, if larger then go right, if match hit.
    - For three node, if smaller than left key go left, if larger than right key go right, if in between left and right keys go to middle link, if matches with any of the keys hit.
- Insert
    - Into two node at bottom
        - Search for matching node, insert into the node to make it a three node
    - Into three node at bottom
        - Insert into matching three node to make it a temporary 4 Node.
        - Split 4 Node by moving middle key into parent, continue to split all 4 nodes till root.
- Worst case log N, Best case log<sub>3</sub> N. Guaranteed logarithmic performance for search and insert.

#### Red Black Tree
- Left leaning red black tree.
- Represent 2-3 tree as binary search tree by using internal left leaning red links to represent a 3 node.
- A Binary search tree such that :
    - No node has two red links connected to it.
    - Every path from root to null has same number of black links.
    - Red links lean left
- Search is same as elementary binary search tree ignoring the link colors.
- Elementary Operations
    - Left Rotation : Orient (temporarily) right leaning red link to lean left
    - Right Rotation : Orient left leaning red link to lean (temporarily) to right.
    - Color Flip : Recolor left and right red links to black and link to current node as red, represents splitting a 4 node.
- Insert
    - Normal insert with new link as red. 
    - If current node
        - Has right child red and left child black, then rotate left.
        - Left child and grand-left child are red, then rotate right.
        - Both children red, then flip colors.
    - On insert, go up from that node till root while checking above logic for every node on a way.
- Height of tree <= 2 log N in worst case.

#### B tree
- Generalized 2-3 trees by allowing up to M-1 key-link pairs per node.
- At least 2 key-link pairs at root.
- At least M/2 key-link pairs in other nodes.
- External nodes contain actual keys and internal nodes contain copies of keys to guide the search.
- Search
    - Starting from root, find interval for search key and take corespondent link till external node.
- Insert
    - Search for new key, insert at bottom.
    - Split nodes with M key-link pairs on a way up the tree.
- Search and insert for M order and N nodes takes between log<sub>M-1</sub>N and log<sub>M/2</sub>N probes.

![Common Order-of-growth classification](https://raw.githubusercontent.com/injulkarnilesh/java_tryout/master/Algorithm/src/main/resources/B-Tree.png)

 

Symbol Table Collision Resolution Techniques
-------------
- Collision happens when multiples distinct keys are hashed to same index. 

#### Separate Chaining
- Use array of M linked lists.
- Hash : Map key hashcode to index i of the array [0 to (M-1)]
- Insert : Put key value node at front of the i<sup>th</sup> linked list if key not in the list, if present update the value.
- Search : Search for match of key in i<sup>th</sup> linked list.

#### Linear Probing
- Use array of size M > N, where N is number of keys to be inserted.
- Hash : Map key hashcode to index i of the array [0 to (M-1)]
- Insert : Put key-value at index i if free, if not try i+1, i+2 etc till there is no free spot. If match found update the value.
- Search : Search at index i, if not matched try i+1, i+2 etc till free spot found or array is all full.
