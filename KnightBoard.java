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

  }

  //@throws IllegalStateException when the board contains non-zero values.
  //@throws IllegalArgumentException when either parameter is negative or out of bounds.
  public int countSolutions(int startingRow, int startingCol){

  }

  // level is the # of the knight
  private boolean solveH(int row ,int col, int level){

  }
}
