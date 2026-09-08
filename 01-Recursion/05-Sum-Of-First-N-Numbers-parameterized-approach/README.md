# Sum of First N Numbers using Parameterized Recursion

## Problem

Find the sum of the first N natural numbers using parameterized recursion.

## Approach

- Take N and an initial sum of 0 as input.
- If N becomes 0, the accumulated sum is the final answer, so print it and stop the recursion.
- Add the current value of N to the sum parameter.
- Call the function again with N - 1 and the updated sum.
- The answer is accumulated while going down through the recursive calls.


main difference of functional and parameterized

- functional:- return n+sum(n-1);
- parameterized:- sum(n-1,total+n)  it maintain the total in the parameter...n==0 that time return the total from base case

## Time Complexity

O(N)

## Space Complexity

O(N)