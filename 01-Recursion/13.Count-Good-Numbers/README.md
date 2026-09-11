# Count Good Numbers

## Problem

A digit string is called a **Good Number** if:

* At every **even index**, we can place only even digits:
  `0, 2, 4, 6, 8`
* At every **odd index**, we can place only prime digits:
  `2, 3, 5, 7`

We need to find the total number of good digit strings of length `n`.

Since the answer can become very large, return the answer modulo:

`10^9 + 7`

---

# What is a Good Number?

Let's take:

```text
n = 3
```

The indexes are:

```text
Index:  0   1   2
        E   O   E
```

At index `0` (Even):

```text
0, 2, 4, 6, 8
```

We have `5` choices.

At index `1` (Odd):

```text
2, 3, 5, 7
```

We have `4` choices.

At index `2` (Even):

```text
0, 2, 4, 6, 8
```

We have `5` choices.

So the total number of good strings is:

```text
5 × 4 × 5 = 100
```

---

## Example

### Input

```text
n = 2
```

Indexes:

```text
Index:  0   1
        E   O
```

Index `0` has:

```text
0, 2, 4, 6, 8
```

`5` choices.

Index `1` has:

```text
2, 3, 5, 7
```

`4` choices.

Total:

```text
5 × 4 = 20
```

### Output

```text
20
```

---

# Brute Force Approach

In the brute force approach, we try every possible valid digit.

For an even index:

```text
0
2
4
6
8
```

For an odd index:

```text
2
3
5
7
```

We recursively try all possible combinations.

For example:

```text
n = 2
```

Possible strings include:

```text
02
03
05
07

22
23
25
27

42
43
45
47

...
```

We generate every possible valid string and count them.

---

## Brute Force Idea

```text
Start from index 0
        ↓
Check if index is Even or Odd
        ↓
Try all valid digits
        ↓
Move to the next index
        ↓
When index == n
        ↓
One valid string is completed
        ↓
Return 1
```

---

## Brute Force Time Complexity

At even indexes, we have `5` choices.

At odd indexes, we have `4` choices.

So:

```text
O(5^(ceil(n/2)) × 4^(floor(n/2)))
```

This is exponential time complexity.

### Space Complexity

The recursion goes from:

```text
index = 0
index = 1
index = 2
...
index = n
```

So:

```text
O(n)
```

---

# How to Think from Brute Force to Optimal?

In brute force:

```text
Generate every possible string
```

But the problem does not ask us to print all strings.

It only asks:

```text
How many good strings are possible?
```

So instead of generating every string, we can directly count the choices.

---

# Important Observation

At every even index:

```text
5 choices
```

At every odd index:

```text
4 choices
```

---

## Example: n = 6

Indexes:

```text
Index:  0   1   2   3   4   5
        E   O   E   O   E   O
```

Even indexes:

```text
0, 2, 4
```

Number of even indexes:

```text
3
```

Each even index has `5` choices:

```text
5 × 5 × 5 = 5³
```

Odd indexes:

```text
1, 3, 5
```

Number of odd indexes:

```text
3
```

Each odd index has `4` choices:

```text
4 × 4 × 4 = 4³
```

Final answer:

```text
5³ × 4³
```

---

# Finding Even and Odd Index Count

We can find the number of even indexes using:

```java
long evenCount = (n + 1) / 2;
```

We can find the number of odd indexes using:

```java
long oddCount = n / 2;
```

---

## Example: n = 3

Indexes:

```text
Index:  0   1   2
        E   O   E
```

Even indexes:

```text
0, 2
```

Count:

```text
2
```

Odd indexes:

```text
1
```

Count:

```text
1
```

Therefore:

```text
5² × 4¹
```

```text
25 × 4
```

```text
100
```

---

# Optimal Approach

We know:

```text
Answer = 5^evenCount × 4^oddCount
```

So we need to calculate powers efficiently.

For large `n`, normal multiplication is slow.

Example:

```text
5^100000
```

We should not multiply `5` one hundred thousand times.

Instead, we use **Binary Exponentiation**.

---

# Binary Exponentiation

The main idea is to reduce the exponent by half.

---

## If n is Even

Example:

```text
2^4
```

We can write:

```text
2^4 = 2^2 × 2^2
```

So:

```text
half = 2^2
```

Then:

```text
2^4 = half × half
```

---

## If n is Odd

Example:

```text
2^5
```

We can write:

```text
2^5 = 2 × 2^2 × 2^2
```

So:

```text
half = 2^2
```

Then:

```text
2^5 = x × half × half
```

The extra `x` is needed because the exponent is odd.

---

# Power Function

```java
public static long power(long x, long n) {

    // Base Case
    if (n == 0) {
        return 1;
    }

    // Calculate power for half exponent
    long half = power(x, n / 2);

    // If exponent is even
    if (n % 2 == 0) {
        return (half * half) % MOD;
    }

    // If exponent is odd
    return (x * half * half) % MOD;
}
```

---

# Why do we use MOD?

The answer can become very large.

The problem asks us to return:

```text
answer % 1,000,000,007
```

So:

```java
static final long MOD = 1_000_000_007;
```

After multiplication, we use:

```java
% MOD
```

This keeps the number within the required range.

---

# Dry Run

## Input

```text
n = 3
```

---

## Step 1: Find Even Count

```java
long evenCount = (n + 1) / 2;
```

Calculation:

```text
(3 + 1) / 2

= 4 / 2

= 2
```

So:

```text
evenCount = 2
```

---

## Step 2: Find Odd Count

```java
long oddCount = n / 2;
```

Calculation:

```text
3 / 2 = 1
```

So:

```text
oddCount = 1
```

---

## Step 3: Calculate 5²

Call:

```text
power(5, 2)
```

### First Call

```text
power(5, 2)
```

Calculate:

```text
half = power(5, 2 / 2)

half = power(5, 1)
```

---

### Second Call

```text
power(5, 1)
```

Calculate:

```text
half = power(5, 1 / 2)

half = power(5, 0)
```

---

### Base Case

```text
power(5, 0)
```

Returns:

```text
1
```

---

### Back to power(5, 1)

```text
half = 1
```

`1` is odd.

So:

```text
x × half × half

5 × 1 × 1

= 5
```

Returns:

```text
5
```

---

### Back to power(5, 2)

```text
half = 5
```

`2` is even.

So:

```text
half × half

5 × 5

= 25
```

Returns:

```text
25
```

Therefore:

```text
5² = 25
```

---

# Step 4: Calculate 4¹

Call:

```text
power(4, 1)
```

Calculate:

```text
half = power(4, 0)
```

Base case:

```text
return 1
```

Now:

```text
half = 1
```

`1` is odd.

So:

```text
x × half × half

4 × 1 × 1

= 4
```

Returns:

```text
4
```

Therefore:

```text
4¹ = 4
```

---

# Step 5: Final Answer

We have:

```text
evenResult = 25

oddResult = 4
```

Final calculation:

```text
25 × 4
```

```text
= 100
```

---

# Output

```text
100
```

---

# Full Flow

```text
n = 3
        ↓

Even Count = 2
Odd Count = 1
        ↓

5² × 4¹
        ↓

25 × 4
        ↓

100
```

---

# Code

```java
import java.util.Scanner;

public class Solution {

    static final long MOD = 1_000_000_007;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        long n = sc.nextLong();

        int answer = countGoodNumbers(n);

        System.out.println(answer);

        sc.close();
    }

    public static int countGoodNumbers(long n) {

        // Number of even indexes
        long evenCount = (n + 1) / 2;

        // Number of odd indexes
        long oddCount = n / 2;

        // Calculate 5^evenCount
        long evenResult = power(5, evenCount);

        // Calculate 4^oddCount
        long oddResult = power(4, oddCount);

        // Final answer
        return (int) ((evenResult * oddResult) % MOD);
    }

    public static long power(long x, long n) {

        // Base Case
        if (n == 0) {
            return 1;
        }

        // Calculate power for half exponent
        long half = power(x, n / 2);

        // If exponent is even
        if (n % 2 == 0) {
            return (half * half) % MOD;
        }

        // If exponent is odd
        return (x * half * half) % MOD;
    }
}
```

---

# Time Complexity

We call the power function two times:

```text
power(5, evenCount)

power(4, oddCount)
```

In every recursive call, the exponent is divided by `2`.

Example:

```text
n

n / 2

n / 4

n / 8

...
```

So:

```text
Time Complexity = O(log N)
```

---

# Space Complexity

The recursion stack also goes:

```text
n

n / 2

n / 4

n / 8

...
```

Therefore:

```text
Space Complexity = O(log N)
```

---

# Key Points to Remember

```text
Even Index → 5 choices

0, 2, 4, 6, 8
```

```text
Odd Index → 4 choices

2, 3, 5, 7
```

```text
Number of Even Indexes = (n + 1) / 2
```

```text
Number of Odd Indexes = n / 2
```

```text
Answer = 5^evenCount × 4^oddCount
```

```text
Use Binary Exponentiation to calculate power efficiently.
```

---

# Complexity

```text
Time Complexity: O(log N)

Space Complexity: O(log N)
```
---

# simple words for mod

MOD use panrom because actual answer romba perusa aagi
overflow aagalam.

MOD use pannina number manageable-ah irukkum,
and problem ketta required answer-um correct-ah kidaikkum.