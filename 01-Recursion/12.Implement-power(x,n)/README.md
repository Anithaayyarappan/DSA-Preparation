# Implement Pow(x, n) | X Raised to the Power N

## Problem

Given a number `x` and an integer `n`, calculate:

```text
xⁿ
```

Examples:

```text
2⁵ = 2 × 2 × 2 × 2 × 2 = 32
```

```text
2¹⁰ = 1024
```

The exponent can also be negative.

```text
2⁻² = 1 / 2² = 1 / 4 = 0.25
```

---

# Examples

## Example 1

```text
Input:
x = 2
n = 10

Output:
1024
```

Because:

```text
2¹⁰ = 1024
```

---

## Example 2

```text
Input:
x = 2
n = -2

Output:
0.25
```

Because:

```text
2⁻²

= 1 / 2²

= 1 / 4

= 0.25
```

---

## Example 3

```text
Input:
x = 3
n = 0

Output:
1
```

Because:

```text
Any number⁰ = 1
```

---

# How Do We Start Thinking?

When we first see:

```text
xⁿ
```

The first question that comes to our mind is:

> How do I normally calculate power?

Example:

```text
2⁵
```

Normally:

```text
2 × 2 × 2 × 2 × 2
```

So the first approach is:

> Multiply `x` by itself `n` times.

This gives us the Brute Force approach.

---

# Brute Force Approach

## Idea

Start with:

```text
ans = 1
```

Then multiply `x`, `n` times.

Example:

```text
x = 2
n = 5
```

Initially:

```text
ans = 1
```

### Iteration 1

```text
ans = 1 × 2

ans = 2
```

### Iteration 2

```text
ans = 2 × 2

ans = 4
```

### Iteration 3

```text
ans = 4 × 2

ans = 8
```

### Iteration 4

```text
ans = 8 × 2

ans = 16
```

### Iteration 5

```text
ans = 16 × 2

ans = 32
```

Answer:

```text
32
```

---

## Brute Force Code

```java
public static double myPow(double x, int n) {

    long power = n;

    if (power < 0) {
        x = 1 / x;
        power = -power;
    }

    double ans = 1;

    for (long i = 0; i < power; i++) {
        ans = ans * x;
    }

    return ans;
}
```

---

## Brute Force Complexity

### Time Complexity

```text
O(N)
```

Because we multiply `x`, `n` times.

### Space Complexity

```text
O(1)
```

Because we only use a few variables.

---

# Why Do We Need to Optimize?

Suppose:

```text
x = 2

n = 1,000,000
```

Brute force will do:

```text
1,000,000 multiplications
```

That is slow.

So the next question is:

> Can we reduce the number of multiplications?

This is where the important observation comes.

---

# Main Observation

Take:

```text
2¹⁰
```

Instead of multiplying `2` ten times:

```text
2 × 2 × 2 × 2 × 2 × 2 × 2 × 2 × 2 × 2
```

We can write:

```text
2¹⁰

= (2²)⁵

= 4⁵
```

Now the exponent changed:

```text
10 → 5
```

We reduced the problem size by half.

This is the main idea behind the optimal solution.

---

# Important Question

## What if N is Even?

Suppose:

```text
2¹⁰
```

Since `10` is even:

```text
2¹⁰

= (2 × 2)⁵

= 4⁵
```

In general:

```text
xⁿ = (x × x)ⁿ/²
```

So:

```java
power(x, n)
```

becomes:

```java
power(x * x, n / 2)
```

---

# Example for Even Exponent

```text
power(2, 10)
```

Since `10` is even:

```text
power(2 × 2, 10 / 2)

power(4, 5)
```

So:

```text
Exponent:

10 → 5
```

Problem size is reduced.

---

# Doubt: What If N Is Odd?

Suppose:

```text
2⁵
```

We cannot directly split `5` into equal pairs.

So what can we do?

Take one `x` outside.

```text
2⁵

= 2 × 2⁴
```

Now `4` is even.

So we can continue.

General formula:

```text
xⁿ = x × xⁿ⁻¹
```

So code:

```java
return x * calculatePower(x, n - 1);
```

---

# Example for Odd Exponent

```text
2⁵
```

Since `5` is odd:

```text
2⁵

= 2 × 2⁴
```

So:

```text
calculatePower(2, 5)

=

2 × calculatePower(2, 4)
```

Now:

```text
4 is even
```

So we can halve it.

---

# The Thinking Pattern

Whenever we see a power problem:

```text
xⁿ
```

Ask:

```text
Is n == 0?
```

If yes:

```text
Return 1
```

Otherwise:

```text
Is n Even?
```

If yes:

```text
Square x

Divide n by 2
```

If no:

```text
Take one x outside

Reduce n by 1
```

---

# Recursive Formulas

## Base Case

```text
x⁰ = 1
```

Code:

```java
if (n == 0) {
    return 1;
}
```

---

## Even Exponent

```text
xⁿ = (x²)ⁿ/²
```

Code:

```java
return calculatePower(x * x, n / 2);
```

---

## Odd Exponent

```text
xⁿ = x × xⁿ⁻¹
```

Code:

```java
return x * calculatePower(x, n - 1);
```

---

# Doubt: Why Do We Need Negative Exponent Handling?

Suppose:

```text
x = 2
n = -2
```

We need:

```text
2⁻²
```

From mathematics:

```text
x⁻ⁿ = 1 / xⁿ
```

Therefore:

```text
2⁻²

= 1 / 2²

= 1 / 4

= 0.25
```

So when `n` is negative:

```java
return 1 / calculatePower(x, -power);
```

---

# Important Doubt

## Why Not Just Call?

```java
calculatePower(x, -power)
```

Example:

```text
x = 2
n = -2
```

This gives:

```text
calculatePower(2, 2)

= 4
```

But the correct answer is:

```text
0.25
```

Because:

```text
2⁻² ≠ 2²
```

Instead:

```text
2⁻²

= 1 / 2²
```

So:

```java
return 1 / calculatePower(x, -power);
```

---

# Doubt: Why Convert int to long?

We write:

```java
long power = n;
```

Instead of directly doing everything with `int`.

Consider:

```text
n = Integer.MIN_VALUE

n = -2147483648
```

If we do:

```java
-n
```

We want:

```text
2147483648
```

But `int` maximum is:

```text
2147483647
```

So it overflows.

Therefore:

```java
long power = n;
```

Now:

```java
-power
```

is safe.

---

# Optimal Approach

## Step 1

Convert `n` to `long`.

```java
long power = n;
```

---

## Step 2

Check if the exponent is negative.

If negative:

```text
x⁻ⁿ = 1 / xⁿ
```

So:

```java
if (power < 0) {
    return 1 / calculatePower(x, -power);
}
```

---

## Step 3

For positive exponent, call the recursive function.

```java
return calculatePower(x, power);
```

---

## Step 4

Inside the recursive function:

```text
If n == 0

Return 1
```

---

## Step 5

If `n` is even:

```text
xⁿ

↓

(x × x)ⁿ/²
```

So:

```java
return calculatePower(x * x, n / 2);
```

---

## Step 6

If `n` is odd:

```text
xⁿ

↓

x × xⁿ⁻¹
```

So:

```java
return x * calculatePower(x, n - 1);
```

---

# Complete Code

```java
class Solution {

    public double myPow(double x, int n) {

        long power = n;

        // Handle negative exponent
        if (power < 0) {
            return 1 / calculatePower(x, -power);
        }

        // Handle positive exponent
        return calculatePower(x, power);
    }

    public static double calculatePower(double x, long n) {

        // Base Case
        if (n == 0) {
            return 1;
        }

        // Base Case
        if (n == 1) {
            return x;
        }

        // Even exponent
        if (n % 2 == 0) {
            return calculatePower(x * x, n / 2);
        }

        // Odd exponent
        return x * calculatePower(x, n - 1);
    }
}
```

---

# Dry Run 1

## Input

```text
x = 2

n = 10
```

Initial call:

```text
calculatePower(2, 10)
```

---

## Call 1

```text
calculatePower(2, 10)
```

`10` is even.

So:

```text
calculatePower(2 × 2, 10 / 2)

=

calculatePower(4, 5)
```

---

## Call 2

```text
calculatePower(4, 5)
```

`5` is odd.

So:

```text
4 × calculatePower(4, 4)
```

---

## Call 3

```text
calculatePower(4, 4)
```

`4` is even.

So:

```text
calculatePower(4 × 4, 4 / 2)

=

calculatePower(16, 2)
```

---

## Call 4

```text
calculatePower(16, 2)
```

`2` is even.

So:

```text
calculatePower(16 × 16, 2 / 2)

=

calculatePower(256, 1)
```

---

## Call 5

```text
calculatePower(256, 1)
```

Base Case:

```text
return 256
```

---

# Returning Back

Now:

```text
calculatePower(256, 1)

↓

256
```

Then:

```text
calculatePower(16, 2)

↓

256
```

Then:

```text
calculatePower(4, 4)

↓

256
```

Now remember:

```text
calculatePower(4, 5)

=

4 × calculatePower(4, 4)

=

4 × 256

=

1024
```

Final Answer:

```text
1024
```

---

# Full Recursion Flow

```text
calculatePower(2, 10)
        |
        | 10 is Even
        ↓
calculatePower(4, 5)
        |
        | 5 is Odd
        ↓
4 × calculatePower(4, 4)
        |
        | 4 is Even
        ↓
calculatePower(16, 2)
        |
        | 2 is Even
        ↓
calculatePower(256, 1)
        |
        | Base Case
        ↓
return 256
        |
        ↓
return 256
        |
        ↓
return 256
        |
        ↓
4 × 256
        |
        ↓
1024
```

---

# Dry Run 2 - Negative Exponent

## Input

```text
x = 2

n = -2
```

Convert:

```text
power = -2
```

Check:

```text
power < 0
```

Yes.

So:

```text
return 1 / calculatePower(2, 2)
```

Now calculate:

```text
calculatePower(2, 2)
```

`2` is even:

```text
calculatePower(2 × 2, 2 / 2)

=

calculatePower(4, 1)
```

Base case:

```text
return 4
```

Now:

```text
1 / 4

=

0.25
```

Final Answer:

```text
0.25
```

---

# Why Is This O(log N)?

This is the most important part.

Brute force:

```text
n = 16

16 multiplications
```

Optimal approach:

```text
16
↓
8
↓
4
↓
2
↓
1
```

Every even step:

```text
n = n / 2
```

So the exponent reduces approximately by half every time.

---

## Example

```text
N = 16

log₂(16) = 4
```

Only a few recursive levels are needed.

For:

```text
N = 1,000,000
```

Approximately:

```text
log₂(1,000,000)

≈ 20
```

Instead of:

```text
1,000,000 operations
```

🔥 That is the optimization.

---

# Important Doubt: But Odd Number Uses N - 1. Isn't That O(N)?

Good question.

For an odd number:

```text
N
```

We do:

```text
N - 1
```

But after that, it becomes even.

Then immediately:

```text
(N - 1) / 2
```

Example:

```text
15
↓ Odd

14
↓ Even

7
↓ Odd

6
↓ Even

3
↓ Odd

2
↓ Even

1
```

So after at most two operations, the number is approximately halved.

Therefore overall complexity is still:

```text
O(log N)
```

---

# Time Complexity

```text
O(log N)
```

Because the exponent is repeatedly reduced by half.

---

# Space Complexity

```text
O(log N)
```

Because recursive calls are stored in the recursion stack.

---

# Brute Force vs Optimal

| Approach          | Time Complexity | Space Complexity |
| ----------------- | --------------- | ---------------- |
| Brute Force       | O(N)            | O(1)             |
| Optimal Recursion | O(log N)        | O(log N)         |

---

# How to Think From Brute Force to Optimal

```text
Problem:
Calculate xⁿ
        ↓
First Thought:
Multiply x, n times
        ↓
Brute Force:
O(N)
        ↓
Question:
Can we reduce multiplications?
        ↓
Observation:
For even n

xⁿ = (x²)ⁿ/²
        ↓
Exponent becomes half
        ↓
For odd n

xⁿ = x × xⁿ⁻¹
        ↓
Make it even
        ↓
Then divide by 2
        ↓
Optimal:
O(log N)
```

---

# Key Formulas to Remember

```text
x⁰ = 1
```

```text
x⁻ⁿ = 1 / xⁿ
```

```text
If n is Even:

xⁿ = (x²)ⁿ/²
```

```text
If n is Odd:

xⁿ = x × xⁿ⁻¹
```

---

# Pattern

```text
Divide and Conquer
```

The main pattern is:

```text
Solve the same problem

with a smaller input

Instead of:

N → N - 1

We try to do:

N → N / 2
```

This is why:

```text
O(N)

becomes

O(log N)
```

---

# Final Takeaway

Whenever you see a problem where the input can be:

```text
Repeatedly divided by 2
```

Think about:

```text
Binary Search
```

or:

```text
Divide and Conquer
```

or:

```text
Binary Exponentiation
```

For this problem, the key idea is:

```text
Even n
→ Square the base
→ Divide exponent by 2

Odd n
→ Take one base outside
→ Reduce n by 1

Negative n
→ Take reciprocal

Base case
→ n = 0
→ Return 1
```
