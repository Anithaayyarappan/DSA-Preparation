# Recursive Implementation of atoi()

## Problem

Implement the `atoi()` function to convert a given string into a 32-bit signed integer.

The function should:

* Ignore leading whitespace characters.
* Check for a positive (`+`) or negative (`-`) sign.
* Convert consecutive digit characters into an integer.
* Stop when a non-digit character is found.
* Handle integer overflow using `Integer.MIN_VALUE` and `Integer.MAX_VALUE`.

---

## Example

### Input

```text
"   -1234"
```

### Output

```text
-1234
```

---

# Approach

## Step 1: Skip Leading Whitespaces

Start from index `0` and skip all spaces.

```text
String = "   -1234"

Index:

0 → ' '
1 → ' '
2 → ' '
3 → '-'
```

After skipping spaces:

```text
i = 3
```

---

## Step 2: Handle the Sign

Check whether the current character is:

```text
'+'
```

or:

```text
'-'
```

For the input:

```text
"   -1234"
```

Current character:

```text
'-'
```

So:

```text
sign = -1
```

Then move to the next character:

```text
i++
```

Now:

```text
i = 4
```

Current character:

```text
'1'
```

---

## Step 3: Start Recursive Processing

Call the helper function:

```java
helper(s, i, 0, sign);
```

Parameters:

```text
s    → Original String

i    → Current Index

num  → Number Calculated So Far

sign → Positive or Negative
```

Initial call:

```text
helper(s, 4, 0, -1)
```

---

## Step 4: Convert Character to Integer

To convert a character into an integer:

```java
s.charAt(i) - '0'
```

Example:

```text
'1' - '0'

= 1
```

Similarly:

```text
'2' - '0' = 2

'3' - '0' = 3

'4' - '0' = 4
```

---

## Step 5: Build the Number

Use the formula:

```java
num = num * 10 + digit;
```

Example:

```text
num = 0

Digit = 1

num = 0 * 10 + 1

num = 1
```

Next digit:

```text
num = 1

Digit = 2

num = 1 * 10 + 2

num = 12
```

Next digit:

```text
num = 12

Digit = 3

num = 12 * 10 + 3

num = 123
```

Next digit:

```text
num = 123

Digit = 4

num = 123 * 10 + 4

num = 1234
```

---

# Recursive Logic

For every recursive call:

1. Check whether the current character is a digit.
2. Convert the character into an integer.
3. Update the number.
4. Check for overflow.
5. Move to the next index.
6. Recursively process the remaining characters.

Recursive call:

```java
return helper(s, i + 1, num, sign);
```

---

# Base Case

Stop recursion when:

* The end of the string is reached.

OR

* The current character is not a digit.

```java
if (i >= s.length() || !Character.isDigit(s.charAt(i))) {
    return (int)(num * sign);
}
```

Finally, apply the sign:

```text
num = 1234

sign = -1

1234 * -1

= -1234
```

---

# Dry Run

## Input

```text
"   -1234"
```

---

### Step 1: Skip Spaces

```text
i = 0 → ' ' → Skip

i = 1 → ' ' → Skip

i = 2 → ' ' → Skip

i = 3 → '-'
```

Now:

```text
i = 3
```

---

### Step 2: Find Sign

```text
s.charAt(3) = '-'
```

So:

```text
sign = -1
```

Move index:

```text
i++

i = 4
```

---

### Step 3: First Recursive Call

```text
helper(s, 4, 0, -1)
```

Current character:

```text
'1'
```

Convert:

```text
'1' - '0' = 1
```

Calculate:

```text
num = 0 * 10 + 1

num = 1
```

Next call:

```text
helper(s, 5, 1, -1)
```

---

### Step 4: Second Recursive Call

Current character:

```text
'2'
```

Convert:

```text
'2' - '0' = 2
```

Calculate:

```text
num = 1 * 10 + 2

num = 12
```

Next call:

```text
helper(s, 6, 12, -1)
```

---

### Step 5: Third Recursive Call

Current character:

```text
'3'
```

Convert:

```text
'3' - '0' = 3
```

Calculate:

```text
num = 12 * 10 + 3

num = 123
```

Next call:

```text
helper(s, 7, 123, -1)
```

---

### Step 6: Fourth Recursive Call

Current character:

```text
'4'
```

Convert:

```text
'4' - '0' = 4
```

Calculate:

```text
num = 123 * 10 + 4

num = 1234
```

Next call:

```text
helper(s, 8, 1234, -1)
```

---

### Step 7: Base Case

Now:

```text
i >= s.length()
```

So return:

```text
num * sign

1234 * -1

= -1234
```

---

# Complete Recursion Flow

```text
helper(s, 4, 0, -1)
        |
        | '1'
        ↓
num = 1
        |
        ↓
helper(s, 5, 1, -1)
        |
        | '2'
        ↓
num = 12
        |
        ↓
helper(s, 6, 12, -1)
        |
        | '3'
        ↓
num = 123
        |
        ↓
helper(s, 7, 123, -1)
        |
        | '4'
        ↓
num = 1234
        |
        ↓
helper(s, 8, 1234, -1)
        |
        ↓
Base Case
        |
        ↓
1234 * -1
        |
        ↓
-1234
```

---

# Overflow Handling

Java `int` has a fixed range.

Instead of remembering the values manually, Java provides:

```java
Integer.MIN_VALUE
```

and:

```java
Integer.MAX_VALUE
```

For positive overflow:

```java
if (num * sign > Integer.MAX_VALUE) {
    return Integer.MAX_VALUE;
}
```

For negative overflow:

```java
if (num * sign < Integer.MIN_VALUE) {
    return Integer.MIN_VALUE;
}
```

We use `long num` because the number may overflow before checking if we use an `int`.

```java
public static int helper(String s, int i, long num, int sign)
```

---

# Code

```java
import java.util.*;

public class Solution {

    public static void main(String[] args) {

        String s = "   -1234";

        int ans = atoi(s);

        System.out.println(ans);
    }

    public static int atoi(String s) {

        int i = 0;

        // Skip leading spaces
        while (i < s.length() && s.charAt(i) == ' ') {
            i++;
        }

        // Handle sign
        int sign = 1;

        if (i < s.length() &&
                (s.charAt(i) == '+' || s.charAt(i) == '-')) {

            if (s.charAt(i) == '+') {
                sign = 1;
            } else {
                sign = -1;
            }

            // Move to the first digit
            i++;
        }

        return helper(s, i, 0, sign);
    }

    public static int helper(String s, int i, long num, int sign) {

        // Base Case
        if (i >= s.length() || !Character.isDigit(s.charAt(i))) {
            return (int)(num * sign);
        }

        // Convert character to digit and build the number
        num = num * 10 + (s.charAt(i) - '0');

        // Positive overflow
        if (num * sign > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }

        // Negative overflow
        if (num * sign < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }

        // Recursive Call
        return helper(s, i + 1, num, sign);
    }
}
```

---

# Time Complexity

```text
O(N)
```

Where `N` is the length of the string.

Each character is processed at most once.

---

# Space Complexity

```text
O(N)
```

The recursive calls are stored in the recursion stack.

In the worst case, all digits in the string are processed recursively.

---

# Key Takeaway

```text
Skip Spaces
     ↓
Find Sign
     ↓
Move to First Digit
     ↓
Process Current Digit
     ↓
num = num * 10 + digit
     ↓
Check Overflow
     ↓
Recursive Call for Next Digit
     ↓
Base Case
     ↓
Apply Sign
     ↓
Return Answer
```

## Pattern

```text
String Processing + Parameterized Recursion
```

The recursive function carries the current state using parameters:

```text
i    → Current Index

num  → Number Built So Far

sign → Positive or Negative
```
