# Factorial of N using Functional Recursion

## Problem

Find the factorial of N using recursion.

## Approach

- Take N as input.
- If N becomes 0, return 1 as the base case (because 0! is 1).
- Find the factorial of N - 1 using recursion.
- Multiply the current value of N with the returned factorial (because N! = N × (N - 1)!).
- Return the final factorial value.

## Time Complexity

O(N)

## Space Complexity

O(N)