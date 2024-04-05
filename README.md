make a tile-matching game (in the same genre as Tetris, Bejeweled, and
Candy Crush). To begin the game, ask the user for the numbers of rows, columns, and types of tiles.
During the game, your program must repeat the following steps:
1. Choose and display a tile type from among the first letters of the alphabet. For example, if there
are six tile types, each tile should be A, B, C, D, E, or F, selected independently at random with
an equal probability of each letter.
2. Display the current tile grid. The grid is initially empty. When displayed, each column is labeled
at the top (starting from 1) and separated from adjacent columns by vertical bars.
3. Ask the user which column to drop the current tile.
4. If the column is full, end the game with an appropriate message. Otherwise, place the tile in the
bottom-most open space in the selected column.
5. If there are any sets of at least three of the same tiles in a row or column, change all tiles in all
sets to asterisks. Sets may overlap. Prompt the user for input before continuing.
6. Remove all asterisks from the grid and slide any tiles above those into the empty slots.
7. Repeat steps 5-6 until no sets remain.
