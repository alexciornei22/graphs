## Problem 1 - Cycle

The `Graph` and `Vertex` files contain the implementation for the
directed non-weighted graph used for this problem. The graph's edges
are stored as a Map with Vertex keys and lists of adjacent vertices as
values. A slightly modified version of the **DFS** algorithm has been
used to find if the graph contains a cycle or not. If the program detects
a *back edge* (and edge that points to an ancestor in the DFS tree, which
is represented by a GRAY colored node, meaning that we are not done
exploring that node) it means the graph contains a cycle.

---

For problems 2 and 3 the `WeightedGraph` implementation has been used,
which represents vertices by their Integer labels and uses the `Edge`
class to store adjacent vertices and the weights.

## Problem 2 - Shortest path in a DAG

To find the shortest path from the source to all other nodes in a DAG,
a **Topological Sort** is performed, and the nodes are traversed in that
order, updating the distances accordingly until the end.

## Problem 3 - Shortest path in a small range weight graph

Because we know that the weights of the edges can be at most **30**, we
can use *Dial's algorithm* which is a modified version of Dijkstra that
uses buckets instead of a priority queue or min-heap. A bucket *i*
is used to store the nodes with distance *i* from the source. The buckets
are traversed starting from 0 to the maximum distance value (the number
of nodes * the maximum weight) and the distances and buckets are updated
along the way similarly to dijkstra's algorithm.
