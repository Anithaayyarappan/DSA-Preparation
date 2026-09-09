# Fibonacci Number using Recursion

## Problem

Find the Nth Fibonacci number using recursion.

The Fibonacci series is:

0, 1, 1, 2, 3, 5, 8, ...

Each number is the sum of the previous two numbers.

## Approach

- Take N as input.
- If N is 0, return 0 as the base case.
- If N is 1, return 1 as the base case.
- Find the previous Fibonacci number using recursion with N - 1.
- Find the previous previous Fibonacci number using recursion with N - 2.
- Add both returned values to get the Nth Fibonacci number.

## Time Complexity

O(2^N)

## Space Complexity

O(N)