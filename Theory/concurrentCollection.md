## ConcurrentHashMap Internal Process (Java 8 and later)

Internally stores data in an array of Node<K,V> (buckets).
Each bucket is a linked list or tree (TreeBin) when collisions are high.
Instead of locking the whole map, it uses fine-grained locking at the bucket (bin) level.

* Key Operations
   * Put (Insertion) Calculate hash of the key. Find the bucket index using (n - 1) & hash.
   If the bucket is empty, use CAS (Compare-And-Swap) to insert without locking.
   If bucket has entries:Acquire lock only on that bucket. Insert a new node or update existing.
   If bucket becomes too big(beyond a threshold) → convert to tree.

   * Get (Read)Calculate hash using hashCode and bucket index. Read from the bucket without locking (volatile reads ensure visibility).
    Traverse linked list or tree to find the key using equals method.

* Resize
    * When the map is too full (load factor threshold exceeded) and fill ratio 0.7%, it resizes by creating a bigger table.
    * Multiple threads help with resizing in parallel.

* Concurrency Mechanisms
   CAS (Compare-And-Swap) for lock-free insert when possible.
   Synchronized block only on a single bucket during updates.
   Volatile variables for visibility across threads.