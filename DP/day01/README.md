# Dynamic Programming - Day01

## Resources

The MIT lecture series covers both the text-justification and matrix-parenthesization problems we covered today in class: [MIT DP lecture 2](https://youtu.be/ENyox7kNKeY). We recommend at some point watching through the entire 4-video series, but the text-justification starts at [17:04](https://youtu.be/ENyox7kNKeY?t=17m4s) The corresponding lecture notes are in the course google drive.

## Testing

You have been provided with one test case for each problem to get you started.  Write at least one more test case for each problem.  Your tests should show that your solution can handle an edge or difficult case, and you should have enough tests to say with confidence that your solution is correct.

## Solutions

Choose one of the problems below to do in the top-down method, one in the bottom-up method, and the last however you choose.

## Text Justification

Given a body of text as an array of strings in order (`w`) and the maximum length (`m`) of a line, return a `List` of the indices of the first word of each line of justified text.  For example: 

```
w = {"This", "is", "an", "example", "of", "text", "justification"}
m = 16

This   is    an 
example of text
justification
```

should return the array `{0, 3, 6}`.

**Reminders from class:**

The cost of line l is c(l):
* c(l) = INF, if total_length(l) > m
* c(l) = (m - total_length(l))^3, otherwise

where:
* total_length = words + single space between each word (i.e. the number of words in the line - 1)
* m = the maximum length of a line

## Edit Distance

There are many cases in which it can be helpful to determine how different one string is from another.  Spellcheck programs use this concept to determine the most likely word attempted, biologists use it to determine how well one strand of DNA matches another, and machine translation programs use it to predict the accuracy of a generated sentence. 

Given two strings `a` and `b`, return the minimum number of operations required to convert string `a` to string `b`. You can apply three different operations to the first string:

    * Insert a new character
    * Delete an existing character
    * Replace one character with another

For example: 

```
a = "sunday"
b = "saturday"

editDistance = 3

Steps: replace n with r, insert t, insert a 
```

## Dungeon Game

Monsters have captured Professor Sidd, `S`, and imprisoned him in the bottom-right corner of the Academic Center.  The monsters also did some remodelling, so the AC now consists of M x N rooms laid out in a 2D grid.  The rest of our valiant teaching team, `T`, will need to fight through the rooms of monsters in order to save Sidd.  

The teaching team has a health level indicated by the integer `h`.  If h drops to `0` or below at any point, everyone died, and Sidd is stuck with the monsters.

Each room has either a monster or a health power up (e.g. coffee, monster fighting weapons, etc.) and will increase or decrease the health of the teaching team by the integer amount given for that room.

Given the 2D array of integers representing the health values of each room in the AC, assuming the teaching team starts in the top-left corner and Sidd in the bottom-right corner, and assuming that the teaching team only moves right or down, return the **minimum initial health** required to save Professor Sidd.  We need to know how much coffee to drink before attempting the rescue.

Example:

```
-------------------------
| -2 (T) |  -3	|   3   |
-------------------------
|   -5   | -10  |   1   |
-------------------------
|   10   |  30  | -5 (S)|
-------------------------

map = {{-2, -3, 3}, {-5, -10, 1}, {10, 30, -5}}

minInitialHealth = 7, following the optimal path R -> R -> D -> D
```
