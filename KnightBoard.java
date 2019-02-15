public class KnightBoard{
  private int[][] board;

  //@throws IllegalArgumentException when either parameter is negative.
  //Initialize the board to the correct size and make them all 0's
  public KnightBoard(int startingRows,int startingCols){
    // -1 on board means there is a knight, 0 means empty
    board = new int[startingRows][startingCols];
    for (int y = 0; y < startingRows; y++){
      for (int x = 0; x < startingCols; x++){
        //clears board
        board[y][x] = 0;
      }
    }
  }

  public String toString(){
    String output = "";
    for (int y = 0; y < board.length; y++){
      for (int x = 0; x < board[0].length; x++){
        //when there is no knight
        if (board[y][x] == 0){
          output += " _";
        }
        //where there is a knight
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
    // checks that given row is within bounds
    if (startingRow < 0 || startingRow >= board.length){
      throw new IllegalArgumentException();
    }
    // checks that given column is within bounds
    if (startingCol < 0 || startingCol >= board[0].length){
      throw new IllegalArgumentException();
    }
    //calls helper
    return solveH(startingRow, startingCol, 0);
  }

  //@throws IllegalStateException when the board contains non-zero values.
  //@throws IllegalArgumentException when either parameter is negative or out of bounds.
  public int countSolutions(int startingRow, int startingCol){
    for (int y = 0; y < board.length; y++){
      for (int x = 0; x < board[0].length; x++){
        //checks that board starts out empty
        if (board[y][x] != 0){
          throw new IllegalStateException();
        }
      }
    }
    // checks that given row is within bounds
    if (startingRow < 0 || startingRow >= board.length){
      throw new IllegalArgumentException();
    }
    // checks that given column is within bounds
    if (startingCol < 0 || startingCol >= board[0].length){
      throw new IllegalArgumentException();
    }
    // calls helper
    return countSolutions(0, 0, 0);
  }

  public int countSolutions(int startingRow, int startingCol, int level){
    //initialize variable
    int count = 0;
    return count;
  }

  // level is the # of the knight
  private boolean solveH(int row ,int col, int level){
    // if the # knights is equal to the number of squares on board, all knights have been placed
    if (level == board.length * board[0].length){
      return true;
    }
    // tests below start square
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
    // tests above start square
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
    //tests to the right of start square
    if (col < board[0].length - 3){
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
    //tests to the left of start square
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
    // if none of the possible squares are empty, then next knight can't be placed
    return false;
  }


  public static void main(String[] args) {
    KnightBoard b = new KnightBoard(4,5);
    System.out.println(b.solve(2,3));
    System.out.println(b);
  }
}
