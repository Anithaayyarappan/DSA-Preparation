# Reverse a Stack Using Recursion

## Problem Statement

Given a stack, reverse the stack using **recursion**.

### Example

**Input Stack:**

```text
Bottom → 1 2 3 4 ← Top
```

**Reversed Stack:**

```text
Bottom → 4 3 2 1 ← Top
```

---

## Approach

We use two recursive functions:

1. `reverseStack()` - Reverses the stack.
2. `insertAtBottom()` - Inserts an element at the bottom of the stack.

### Main Logic

1. Pop the top element from the stack.
2. Recursively reverse the remaining stack.
3. Insert the popped element at the bottom.

---

## Algorithm

### `reverseStack()`

```text
1. If stack is empty, return.
2. Pop the top element.
3. Recursively reverse the remaining stack.
4. Insert the popped element at the bottom.
```

### `insertAtBottom()`

```text
1. If stack is empty, push the element.
2. Pop the top element.
3. Recursively insert the target element at the bottom.
4. Push the popped element back.
```

---

## Dry Run

### Input

```text
Stack = [1, 2, 3]

Top = 3
```

### Recursive Calls

```text
reverseStack([1, 2, 3])

pop 3
    reverseStack([1, 2])

    pop 2
        reverseStack([1])

        pop 1
            reverseStack([])

            Stack is empty
            return

        insertAtBottom(1)

    insertAtBottom(2)

insertAtBottom(3)
```

---

## Backtracking

### Insert 1

```text
Stack = []

Push 1

Stack = [1]
```

### Insert 2 at Bottom

```text
Stack = [1]

Pop 1

Stack = []

Push 2

Stack = [2]

Push back 1

Stack = [2, 1]
```

### Insert 3 at Bottom

```text
Stack = [2, 1]

Pop 1
Pop 2

Stack = []

Push 3

Stack = [3]

Push back 2

Stack = [3, 2]

Push back 1

Stack = [3, 2, 1]
```

---

## Final Output

```text
Original Stack:

[1, 2, 3]

Reversed Stack:

[3, 2, 1]
```

---

## Code

```java
import java.util.*;

public class Solution {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int n = in.nextInt();

        Stack<Integer> s = new Stack<>();

        // Push elements into stack
        for (int i = 0; i < n; i++) {
            s.push(in.nextInt());
        }

        // Reverse the stack
        reverseStack(s);

        // Print reversed stack
        System.out.println(s);
    }

    public static void reverseStack(Stack<Integer> st) {

        // Base case
        if (st.isEmpty()) {
            return;
        }

        // Remove top element
        int temp = st.pop();

        // Reverse remaining stack
        reverseStack(st);

        // Insert element at bottom
        insertAtBottom(st, temp);
    }

    public static void insertAtBottom(Stack<Integer> st, int temp) {

        // Base case
        if (st.isEmpty()) {
            st.push(temp);
            return;
        }

        // Remove top element
        int ele = st.pop();

        // Insert temp at bottom
        insertAtBottom(st, temp);

        // Push removed element back
        st.push(ele);
    }
}
```

---

## Time Complexity

```text
O(N²)
```

Each element may require traversing the stack to insert it at the bottom.

---

## Space Complexity

```text
O(N)
```

The recursion call stack stores up to `N` function calls.

---

## Key Concept

```text
POP
 ↓
Reverse remaining stack using recursion
 ↓
Insert popped element at BOTTOM
```

### Easy Interview Explanation

> First, I pop every element recursively from the stack. After reaching the empty stack, while recursion backtracks, I insert each popped element at the bottom. This reverses the st
