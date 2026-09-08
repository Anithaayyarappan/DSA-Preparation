# Factorial of N using Parameterized Recursion

## Problem

Find the factorial of N using parameterized recursion.

## Approach

- Take N as input and initialize `total` as 1 (because multiplication starts with 1).

- If N becomes 0, `total` contains the final factorial value, so print it and stop the recursion.

- Multiply the current value of N with `total`.

- Call the function again with N - 1 and the updated total.

- The factorial value is carried as a parameter while going down through the recursive calls.

## Time Complexity

O(N)

## Space Complexity

O(N)