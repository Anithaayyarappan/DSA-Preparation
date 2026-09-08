# Reverse an Array using Recursion

## Problem

Reverse a given array using recursion.

## Approach

- Use two pointers: `low` starting from the beginning and `high` starting from the end.
- If `low` becomes greater than or equal to `high`, stop the recursion because all elements have been reversed.
- Swap the elements at `low` and `high`.
- Call the function again with `low + 1` and `high - 1` to reverse the remaining inner part of the array.
- The array is reversed in-place without using an extra array.

## Time Complexity

O(N)

## Space Complexity

O(N)