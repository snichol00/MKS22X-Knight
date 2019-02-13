public class KnightBoard{
  private int[][] board;
  //@throws IllegalArgumentException when either parameter is negative.
  //Initialize the board to the correct size and make them all 0's
  public KnightBoard(int startingRows,int startingCols){
    board = new int[startingRows][startingCols];
    for (int y = 0; y < startingRows; y++){
      for (int x = 0; x < startingCols; x++){
        board[y][x] = 0;
      }
    }
  }

  public String toString(){
    String output = "";
    for (int y = 0; y < board.length; y++){
      for (int x = 0; x < board[0].length; x++){
        if (board[y][x] == 0){
          output += " _";
        }
        if (board[y][x] == -1){
          output += " K";
        }
      }
      output += "\n";
    }
    return output;
  }

  //@throws IllegalStateException when the board contains non-zero values.
  //@throws IllegalArgumentException when either parameter is negative or out of bounds.
  public boolean solve(int startingRow, int startingCol){
    for (int y = 0; y < board.length; y++){
      for (int x = 0; x < board[0].length; x++){
        //checks that board starts out empty
        if (board[y][x] != 0){
          throw new IllegalStateException();
        }
      }
    }
    if (startingRow < 0 || startingRow >= board.length){
      throw new IllegalArgumentexception();
    }
    if (startingCol < 0 || startingCol >= board[0].length){
      throw new IllegalArgumentexception();
    }
    //calls helper
    return solveH(startingRow, startingCol, 0);
  }

  //@throws IllegalStateException when the board contains non-zero values.
  //@throws IllegalArgumentException when either parameter is negative or out of bounds.
  public int countSolutions(int startingRow, int startingCol){

  }

  // level is the # of the knight
  private boolean solveH(int row ,int col, int level){
    // if it reaches last row, then return true because all queens have been placed
    if (level == board.length * board[0].length){
      return true;
    }
    if (row < board.length - 3){
      if (col < board[0].length - 1){
        if (board[row + 2][col + 1] == 0){
          board[row + 2][col + 1] = -1;
          return solveH(row + 2, col + 1, level + 1);
        }
      }
      if (col > 0){
        if (board[row + 2][col - 1] == 0){
          board[row + 2][col - 1] = -1;
          return solveH(row + 2, col - 1, level + 1);
        }
      }
    }
    if (row > 1){
      if (col < board[0].length - 1){
        if (board[row - 2][col + 1] == 0){
          board[row - 2][col + 1] = -1;
          return solveH(row + 2, col + 1, level + 1);
        }
      }
      if (col > 0){
        if (board[row - 2][col - 1] == 0){
          board[row - 2][col - 1] = -1;
          return solveH(row - 2, col - 1, level + 1);
        }
      }
    }
    if (col < board.length[0] - 3){
      if (row < board.length - 1){
        if (board[row + 1][col + 2] == 0){
          board[row + 1][col + 2] = -1;
          return solveH(row + 1, col + 2, level + 1);
        }
      }
      if (col > 0){
        if (board[row - 1][col + 2] == 0){
            board[row - 1][col + 2] = -1;
            return solveH(row - 1, col + 2, level + 1);
          }
        }
      }
    }
    if (col > 1){
      if (row < board.length - 1){
        if (board[row + 1][col - 2] == 0){
          board[row + 1][col - 2] = -1;
          return solveH(row + 1, col - 2, level + 1);
        }
      }
      if (col > 0){
        if (board[row - 1][col +- 2] == 0){
          board[row - 1][col - 2] = -1;
          return solveH(row - 1, col - 2, level + 1);
        }
      }
    }
  }
}
