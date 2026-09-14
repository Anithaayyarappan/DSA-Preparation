# Sort a Stack Using Recursion

## Problem Statement

Given a stack of integers, sort the stack using **recursion only**.

The stack should be sorted such that the **greatest element is at the top**.

Allowed stack operations:

- `push()`
- `pop()`
- `peek()`
- `isEmpty()`

Do not use any loop-based sorting algorithms or built-in sorting methods.

---

## Example

### Input Stack

```text
Bottom [4, 1, 3, 2] TOP
```

### Sorted Stack

```text
Bottom [1, 2, 3, 4] TOP
```

### Pop Order

```text
4 3 2 1
```

The elements are printed in **descending order**.

---

# Approach

We use two recursive functions:

1. `sortStack()` - Removes all elements one by one and sorts the remaining stack.
2. `insertAtCorrectPosition()` - Inserts the removed element into its correct position.

---

## Main Pattern

```text
POP
 |
 v
RECURSE
 |
 v
INSERT
```

---

# Algorithm

## `sortStack()`

1. If the stack is empty, return.
2. Pop the top element and store it in `temp`.
3. Recursively sort the remaining stack.
4. Insert `temp` back into its correct position.

### Pattern

```text
POP -> RECURSE -> INSERT
```

---

## `insertAtCorrectPosition()`

1. If the stack is empty, push `temp`.
2. If the top element is smaller than or equal to `temp`, push `temp`.
3. Otherwise, pop the top element temporarily.
4. Recursively insert `temp`.
5. Push the removed element back.

### Pattern

```text
Correct Position?
       |
      YES
       |
     PUSH
       |
     RETURN

      NO
       |
      POP
       |
    RECURSE
       |
   PUSH BACK
```

---

# Java Solution

```java
import java.util.*;

class Solution {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int n = in.nextInt();

        Stack<Integer> s = new Stack<>();

        // Push elements into stack
        for (int i = 0; i < n; i++) {
            s.push(in.nextInt());
        }

        // Sort the stack
        sortStack(s);

        // Print elements
        for (int i = 0; i < n; i++) {
            System.out.print(s.pop() + " ");
        }
    }

    // Sort stack using recursion
    public static void sortStack(Stack<Integer> st) {

        // Base case
        if (st.isEmpty()) {
            return;
        }

        // POP
        int temp = st.pop();

        // RECURSE
        sortStack(st);

        // INSERT
        insertAtCorrectPosition(st, temp);
    }

    // Insert element at correct position
    public static void insertAtCorrectPosition(
            Stack<Integer> st, int temp) {

        // Correct position found
        if (st.isEmpty() || st.peek() <= temp) {
            st.push(temp);
            return;
        }

        // POP top element
        int ele = st.pop();

        // RECURSE
        insertAtCorrectPosition(st, temp);

        // PUSH BACK
        st.push(ele);
    }
}
```

---

# Dry Run

## Input

```text
4
4 1 3 2
```

Initial Stack:

```text
Bottom [4, 1, 3, 2] TOP
```

---

# Step 1: `sortStack()`

## Call 1

```text
Stack = [4, 1, 3, 2]

Pop 2

temp = 2

Stack = [4, 1, 3]
```

Now call:

```text
sortStack([4, 1, 3])
```

Call 1 is waiting.

---

## Call 2

```text
Stack = [4, 1, 3]

Pop 3

temp = 3

Stack = [4, 1]
```

Call 2 is waiting.

---

## Call 3

```text
Stack = [4, 1]

Pop 1

temp = 1

Stack = [4]
```

Call 3 is waiting.

---

## Call 4

```text
Stack = [4]

Pop 4

temp = 4

Stack = []
```

Call 4 is waiting.

---

## Base Case

```text
Stack = []
```

Condition:

```java
if (st.isEmpty()) {
    return;
}
```

Now recursion starts returning.

---

# Important Doubt 1: How Does Recursion Remember Values?

Each recursive function call has its own memory.

```text
TOP

+----------------+
| CALL 4         |
| temp = 4       |
| WAITING        |
+----------------+

| CALL 3         |
| temp = 1       |
| WAITING        |
+----------------+

| CALL 2         |
| temp = 3       |
| WAITING        |
+----------------+

| CALL 1         |
| temp = 2       |
| WAITING        |
+----------------+
```

This is called the **Call Stack**.

Each function call has its own `temp` variable.

That is why values are not lost.

---

# Going Down vs Coming Back

## Going Down

```text
Pop 2
  |
Pop 3
  |
Pop 1
  |
Pop 4
  |
Stack Empty
```

## Coming Back

```text
Insert 4
  |
Insert 1
  |
Insert 3
  |
Insert 2
```

Recursion follows:

```text
LIFO

Last In
First Out
```

---

# Insert 4

Stack is empty:

```text
[]
```

Call:

```text
insertAtCorrectPosition([], 4)
```

Stack is empty.

Push 4:

```text
[4]
```

Return.

---

# Insert 1

Current Stack:

```text
[4]
```

We need to insert:

```text
temp = 1
```

Check:

```text
4 <= 1
```

False.

Pop 4:

```text
ele = 4

Stack = []
```

Call recursively:

```text
insertAtCorrectPosition([], 1)
```

Stack is empty.

Push 1:

```text
[1]
```

Return.

Previous function call remembers:

```text
ele = 4
```

Push 4 back:

```text
[1, 4]
```

---

# Important Doubt 2: There Is `return`, Then How Does 4 Come Back?

Outer function:

```java
int ele = st.pop();

insertAtCorrectPosition(st, temp);

st.push(ele);
```

When:

```java
insertAtCorrectPosition(st, temp);
```

returns, the outer function continues from:

```java
st.push(ele);
```

Flow:

```text
Outer Call

ele = 4

      |
      v

Inner Call

Push 1

return

      |
      v

Back to Outer Call

ele = 4 is remembered

      |
      v

Push 4
```

Result:

```text
[1, 4]
```

Important:

> `return` only finishes the current function call.

It does not stop all recursive calls.

---

# Insert 3

Current Stack:

```text
[1, 4]
```

Need to insert:

```text
temp = 3
```

Check:

```text
4 <= 3
```

False.

Pop 4:

```text
ele = 4

Stack = [1]
```

Check:

```text
1 <= 3
```

True.

Push 3:

```text
[1, 3]
```

Return.

Push 4 back:

```text
[1, 3, 4]
```

---

# Insert 2

Current Stack:

```text
Bottom [1, 3, 4] TOP
```

Need to insert:

```text
temp = 2
```

---

## Call A

Check:

```text
4 <= 2
```

False.

Pop 4:

```text
ele = 4

Stack = [1, 3]
```

Call A remembers:

```text
temp = 2
ele = 4
```

---

## Call B

Check:

```text
3 <= 2
```

False.

Pop 3:

```text
ele = 3

Stack = [1]
```

Call B remembers:

```text
temp = 2
ele = 3
```

---

## Call C

Check:

```text
1 <= 2
```

True.

Push 2:

```text
[1, 2]
```

Return.

---

# Important Doubt 3: We Popped 4 and 3. How Are They Remembered?

Each recursive function call has its own variable.

```text
CALL A

ele = 4

WAITING
```

```text
CALL B

ele = 3

WAITING
```

After pushing 2:

```text
[1, 2]
```

Recursion starts returning.

---

## Back to Call B

Call B remembers:

```text
ele = 3
```

Push 3:

```text
[1, 2, 3]
```

---

## Back to Call A

Call A remembers:

```text
ele = 4
```

Push 4:

```text
[1, 2, 3, 4]
```

---

# Why Is 3 Pushed Before 4?

Pop order:

```text
4
3
```

Recursion returns in reverse order:

```text
3
4
```

Because recursion follows:

```text
LIFO

Last In -> First Out
```

So:

```text
POP:

4
3

PUSH BACK:

3
4
```

---

# Final Stack

```text
Bottom [1, 2, 3, 4] TOP
```

When popping:

```text
4 3 2 1
```

---

# Important Doubt 4: How Does `return` Work in Recursion?

Example:

```java
void fun(int n) {

    System.out.println("Start " + n);

    if (n == 0) {
        return;
    }

    fun(n - 1);

    System.out.println("End " + n);
}
```

Call:

```java
fun(3);
```

---

## Going Down

```text
Start 3
Start 2
Start 1
Start 0
```

At:

```text
n = 0
```

Function returns.

---

## Coming Back

Return to `fun(1)`:

```text
End 1
```

Return to `fun(2)`:

```text
End 2
```

Return to `fun(3)`:

```text
End 3
```

Final Output:

```text
Start 3
Start 2
Start 1
Start 0
End 1
End 2
End 3
```

---

# Descending vs Ascending

## Descending Pop Order

Condition:

```java
st.peek() <= temp
```

Stack:

```text
Bottom [1, 2, 3, 4] TOP
```

Pop:

```text
4 3 2 1
```

---

## Ascending Pop Order

Condition:

```java
st.peek() >= temp
```

Stack:

```text
Bottom [4, 3, 2, 1] TOP
```

Pop:

```text
1 2 3 4
```

Only the condition changes.

---

# Complexity Analysis

## Time Complexity

```text
O(N^2)
```

For every element, we may need to pop multiple elements to find the correct position.

Example:

```text
N + (N-1) + (N-2) + ... + 1
```

Therefore:

```text
O(N^2)
```

---

## Space Complexity

```text
O(N)
```

Recursion uses the Call Stack.

At most `N` recursive calls can exist at the same time.

---

# Final Revision Notes

## `sortStack()`

```text
POP
 |
RECURSE
 |
INSERT
```

Code:

```java
int temp = st.pop();

sortStack(st);

insertAtCorrectPosition(st, temp);
```

---

## `insertAtCorrectPosition()`

```text
Correct Position

YES -> PUSH -> RETURN

NO  -> POP -> RECURSE -> PUSH BACK
```

---

# Easy Memory Trick

> Remove one element, recursively sort the remaining stack, then insert the removed element back into the correct position.

## Main Pattern

```text
GOING DOWN

POP


COMING BACK

INSERT
```

---

# Summary

```text
sortStack()

POP
 |
RECURSE
 |
INSERT
```

```text
insertAtCorrectPosition()

Correct Position
    |
   PUSH
    |
  RETURN

Not Correct
    |
   POP
    |
 RECURSE
    |
PUSH BACK
```

The main concept used in this problem is:

```text
Recursion + Call Stack + Stack Operations
```