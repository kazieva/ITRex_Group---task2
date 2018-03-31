# task2

## Description of the test task
#### Implement an algorithm. Please see description below:

Opening his eyes, the Prince of Persia, found himself at the top of the underground Jaffar’s labyrinth. The labyrinth contains H levels, located strictly one above the other. Each level is a rectangular area, divided into m × n areas. In some areas there are pillars. In such areas the Prince can not go.

Prince can move from one area to another adjacent free area on the same level. He can also break through the floor to be at a lower level (at the lowest level he can not break through the floor). Any movement of the Prince takes 5 seconds.
There is a Princess waiting for Prince at one of the areas on the lowest level. Your task is help Prince to find Princess as soon as possible.

#### Input format:
The first line of INPUT.TXT file contain digits H, M, N - count of levels and rectangle size of each level. Then other lines contain H blocks, each of them describes one level from top to bottom. Each block contains M rows and N columns. Every cell contain: “.”, “o”, “1”, 2”.

    “.” means that it’s free space.
    “o” means columns there.
    “1” Prince position.
    “2” Princes position.

Symbol “1” is always in the first block. Symbol “2” is always in the last block. Nearby blocks are separated by one blank line.
Output format:
The minimum time the Prince have to find Princess in seconds. As fairy tales always have happy ends it is guaranteed that the Prince can do it.


### Examples:
Input
###### 3 3 3

###### 1..
###### oo.
###### ...

###### ooo
###### ..o
###### .oo

###### ooo
###### o..
###### o.2

Output
###### 60

#### As a plus will be:

  Implement test use JUnit or TestNG

