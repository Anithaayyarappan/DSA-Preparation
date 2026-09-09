# Check if a String is Palindrome using Recursion

## Problem

Check whether a given string is a palindrome using recursion.

## Approach

- Use two pointers: `left` starting from the beginning and `right` starting from the end.
- If `left` becomes greater than or equal to `right`, all characters have been checked successfully, so return `true`.
- Compare the characters at `left` and `right`.
- If both characters are different, return `false`.
- If both characters are the same, call the function again with `left + 1` and `right - 1`.
- Continue checking the characters recursively until the pointers meet or a mismatch is found.

## Time Complexity

O(N)

## Space Complexity

O(N)


# Connect the pattern
## Reverse an array
- Two Pointers
- Swap
- Move Inside

## Palindrome
- Two Pointers
- Compare
- Move Inside