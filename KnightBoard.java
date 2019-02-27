import java.util.Collections;
import java.util.ArrayList;

public class KnightBoard{
  private int[][] board;
  private int[][] optimizer;

  //@throws IllegalArgumentException when either parameter is negative.
  //Initialize the board to the correct size and make them all 0's
  public KnightBoard(int startingRows,int startingCols){
    if (startingRows < 0 || startingCols < 0){
      throw new IllegalArgumentException();
    }
    // 0 means empty, >0 means knight, fills board with zeroes
    board = new int[startingRows][startingCols];
    for (int y = 0; y < startingRows; y++){
      for (int x = 0; x < startingCols; x++){
        //clears board
        board[y][x] = 0;
      }
    }
    optimizer = new int[startingRows][startingCols];
    // all knight moves
    int[][] moves = {{2, 1}, {2, -1}, {1, 2}, {1, -2}, {-2, 1}, {-2, -1}, {-1, 2}, {-1, -2}};
    // optimized board shows # possible moves
    for (int y = 0; y < startingRows; y++){
      for (int x = 0; x < startingCols; x++){
        int numMoves = 0;
        for (int move = 0; move < moves.length; move++){
          // stores would-be new vals
          int newrow = startingRows + moves[move][1];
          int newcol = startingCols + moves[move][0];
          //checks if it is in bounds
          if (newrow >= 0 && newrow < board.length && newcol >= 0 && newcol < board[0].length && board[newrow][newcol] == 0){
            numMoves ++;
          }
        }
        optimizer[y][x] = numMoves;
      }
    }
  }

  public String toString(){
    String output = "";
    for (int y = 0; y < board.length; y++){
      for (int x = 0; x < board[0].length; x++){
        //when there is no knight
        if (board[y][x] == 0){
          output += "  _";
        }
        //where there is a knight
        else{
          if (board[y][x] < 10){
            output += "  " + board[y][x];
          }
          else{
            output += " " + board[y][x];
          }
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
    return solveH(startingRow, startingCol, 1);
  }

  private boolean solveH(int row ,int col, int level){
    // if the # knights is equal to the number of squares on board, all knights have been placed
    board[row][col] = level;
    int[][] moves = {{2, 1}, {2, -1}, {1, 2}, {1, -2}, {-2, 1}, {-2, -1}, {-1, 2}, {-1, -2}};
    // stores old val
    int optVal = optimizer[row][col];
    optimizer[row][col] = -1;
    if (level == board.length * board[0].length){
      return true;
    }
    for (int move = 0; move < moves.length; move++){
      // stores would-be new vals
      int newrow = row + moves[move][1];
      int newcol = col + moves[move][0];
      if (newrow >= 0 && newrow < board.length && newcol >= 0 && newcol < board[0].length && board[newrow][newcol] == 0){
        if (optimizer[newrow][newcol] > 0){
          optimizer[newrow][newcol] -= 1;
        }
      }
    }
    ArrayList<Integer> possibleMoves = possibleMoves(row, col);
    for (int move = 0; (move * 2 + 1) < possibleMoves.size(); move++){
      // stores would-be new vals
      int newrow = row + possibleMoves.get(move*2);
      int newcol = col + possibleMoves.get(move*2 + 1);
      if (newrow >= 0 && newrow < board.length && newcol >= 0 && newcol < board[0].length && board[newrow][newcol] == 0){
        if (solveH(newrow, newcol, level + 1)){
          return true;
        }
      }
    }
    //clears piece
    board[row][col] = 0;
    //resets optimizer board
    optimizer[row][col] = optVal;
    for (int move = 0; (move * 2 + 1) < possibleMoves.size(); move++){
      int newrow = row + possibleMoves.get(move*2);
      int newcol = col + possibleMoves.get(move*2 + 1);
      if (newrow >= 0 && newrow < board.length && newcol >= 0 && newcol < board[0].length && board[newrow][newcol] == 0){
        optimizer[newrow][newcol] += 1;
      }
    }
    // if none of the possible squares are empty, then next knight can't be placed
    return false;
  }

  //gives all possible moves for a certain square
  private ArrayList<Integer> possibleMoves(int r, int c){
    // will sort the maximum number of moves to the minimum
    ArrayList<Integer> numMoves = new ArrayList<Integer>();
    // will store the row, col, and "place" in descending order from which has the most moves
          //will have to loop through by threes
    ArrayList<Integer> moveChoices = new ArrayList<Integer>();
    int[][] moves = {{2, 1}, {2, -1}, {1, 2}, {1, -2}, {-2, 1}, {-2, -1}, {-1, 2}, {-1, -2}};
    for (int move = 0; move < moves.length; move++){
      // stores would-be new vals
      int newrow = r + moves[move][1];
      int newcol = c + moves[move][0];
      if (newrow >= 0 && newrow < board.length && newcol >= 0 && newcol < board[0].length && board[newrow][newcol] == 0){
        if (optimizer[newrow][newcol] != -1){
          numMoves.add(optimizer[newrow][newcol]);
          Collections.sort(numMoves);
          //puts in reverse order
          Collections.reverse(numMoves);
          moveChoices.add(2 * numMoves.indexOf(optimizer[newrow][newcol]), moves[move][1]);
          moveChoices.add(2 * numMoves.indexOf(optimizer[newrow][newcol]) + 1, moves[move][0]);
        }
      }
    }
      return moveChoices;
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
    return countSolutions(startingRow, startingCol, 1);
  }

  // level is the # of the knight
  private int countSolutions(int row ,int col, int level){
    //keeps track of solutions
    int count = 0;
    //stores all possible knight moves
    int[][] moves = {{2, 1}, {2, -1}, {1, 2}, {1, -2}, {-2, 1}, {-2, -1}, {-1, 2}, {-1, -2}};
    // if the # knights is equal to the number of squares on board, all knights have been placed
    if (level == board.length * board[0].length){
      return 1;
    }
    //checks all possible knight moves at given location
    for (int y = 0; y < moves.length; y++){
      //System.out.println(toString());
      board[row][col] = level;
      //stores where the knight ould go next
      int newrow = row + moves[y][1];
      int newcol = col + moves[y][0];
      //checks if it is in bounds
      if (newrow >= 0 && newrow < board.length && newcol >= 0 && newcol < board[0].length && board[newrow][newcol] == 0){
        count += countSolutions(newrow, newcol, level + 1);
      }
    }
    //if knight can't be placed;
    board[row][col] = 0;
    return count;
  }
}
