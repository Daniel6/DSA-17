# Dynamic Programming - Day02

**This assignment is due Monday, April 24th**

## Resources

The MIT lecture series [covers](https://youtu.be/ocZMDMZwhCY?t=28m) the longest common subsequence problem, but does so by treating it like a more specific version of the edit-distance problem from last homework. It also covers the [Knapsack problem](https://youtu.be/ocZMDMZwhCY?t=43m12s).

## Assignment

You are free to write these solutions in whichever manner you prefer, top-down or bottom-up.

For this homework, we provide you with 5 potential problems. Two of them we covered in class (LCS and Knapsack), the other three are new. **You only have to write implementations for 2 of the problems, at least one of cannot be from class today.** If you are itching to do more DP, feel free to do more.

## In Class

### LCS

Given two strings, return their longest common subsequence.

### Knapsack

Given the size of your knapsack, an array of item sizes `S`, and an array representing how much you value each item `V`, return the most amount of value you can fit into your knapsack.

## New Problems

**Note: One of the following problems can be solved more efficiently without dynammic programming. Don't feel forced to use DP if you think you have a better solution.**

### Unique BSTs (not too bad)

Given n, how many structurally unique BST's (binary search trees) exist that store values 1...n? For example, given n=3, there are 5 unique BSTs:

```
   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
```

### Trampoline (harder)

After the teaching team's miraculous and heroic rescue of Professor Sidd from the monsters, Sidd has found himself in a pickle once again. To escape the monsters chasing him, Sidd must somehow cross a large river. Luckily, there are trampolines spread out evenly throughout the river. Each trampoline has a corresponding integer `n` which represents how bouncy the trampoline is. `n` is the maximal jump length at that position. If `n=3`, Sidd can use the trampoline the jump *up to* 3 trampolines forward.

Write a function than given an array of trampolines, returns the fewest number of jumps Sidd needs to reach the final trampoline.

For example: given `[2, 3, 0, 1, 4]`, return `2`. (Sidd starts at the index `0` trampoline. Jump forward `1`. Then jump forward `3` and reach the end.)

Assume you can always reach the end.

### Regex Matching (hardest)

Write a function that implements regular expression matching for `'.'` and `'*'`. `'.'` matches any single character, while `'*'` matches 0 or more of the preceding character. The matching should cover the entire input string. Your inputs will be `s`, the input string, and `p`, the regular expression.

For example:

```
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "a*") → true
isMatch("aa", ".*") → true
isMatch("ab", ".*") → true
isMatch("aab", "c*a*b") → true
```
