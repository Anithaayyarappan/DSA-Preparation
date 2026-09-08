# Sum of First N Numbers

## Problem

Find the sum of the first N natural numbers using recursion.

## Approach

- Take N as input.
- If N becomes 0, return 0 as the base case.
- Find the sum of smaller numbers using the function with N - 1.
- Add the current value of N to the returned sum (because the sum of first N numbers is N + sum of first N - 1 numbers).  n+sum(n-1)
- Return the final sum.

## Time Complexity

O(N)

## Space Complexity

O(N)