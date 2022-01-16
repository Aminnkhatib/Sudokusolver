import java.util.ArrayList;
import java.util.Arrays;



/** 
 * a class that is used to solve sudoku boards
 * 
 */
public class SudokuSolver implements SudokuSolverI {

    int[][] board;
    /** 
     * Constructor for the SudokuSolver
     * 
     */
    public SudokuSolver(){
 
    }
    /**
     * Prints out the SudokuaBoard in the console
     * 
     * @param SudokuBoard The Board to be printed
     */
    public void Print(int[][] SudokuBoard){
        for (int[] col: SudokuBoard) {
            for (int value: col) {
                System.out.print(value);
                System.out.print(" ");
            }
            System.out.print("\n");
        }
    }

    /**
     * 
     * Takes a positon in the matrix and tests if value is valid in
     * the row and column of that position
     * 
     * @param x position of the number to be tested  
     * @param y position of the number to be tested
     * @param value the number to be tested  
     * @return returns a bolean value depending on if value is valid in position x and y
     */
    private boolean isValidInRowNCol(int x , int y, int value){
        for (int i = 0; i < board.length; i++) {
            if (((board[x][i] == value && i != y) || (board[i][y] == value && i != x))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Takes a positon in the matrix and tests if value is valid in
     * the closest block, the block is the current 3x3 block that the position given
     * recides in.
     * 
     * @param row position of the number to be tested  
     * @param col position of the number to be tested
     * @param value the number to be tested  
     * @return returns a bolean value depending on if value is valid in position x and y
     */
    private boolean isValidInCurrentBlock(int row , int col, int value){
        int localBlockRow = row - row % 3;
        int localBlockCol = col - col % 3;
        
        for (int i = localBlockRow; i < localBlockRow + 3; i++) {
            for (int j = localBlockCol; j < localBlockCol + 3; j++) {
                if (board[i][j] == value && (i != row && j != col)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean checkIfLegal(int row, int col, int value) {
        return (isValidInCurrentBlock(row, col, value) ? isValidInRowNCol(row, col, value) : false);
    }
    @Override
    public boolean checkIfEmpty(int row, int col) {  
        return board[row][col] == 0;
    }
    @Override
    public void init(int[][] start) {
        board = start;
    }
    @Override
    public int[][] getBoard() {
        return Arrays.stream(board).map(int[]::clone).toArray(int[][]::new);
    }

    /**
     * recursive backtracking method used to solve a sudoku board
     * 
     * @param currentrow current row from where to solve
     * @param currentcol current col from where to solve
     * @return returns a boolean if placement sucessful
     */
    private boolean solverec(int currentrow, int currentcol){
        int row = 0;
        int col = 0;
        boolean isEmpty = false;

        for (int rowi = 0; rowi < board.length; rowi++) {
            for (int coli = 0; coli < board.length; coli++) {
                if(checkIfEmpty(rowi, coli) ){
                    row = rowi;
                    col = coli;
                    isEmpty = true;
                    break;
                }
            }
            if(isEmpty){
                break;
            }
        }
        if(!isEmpty){
            return true;
        }
        
        for (int value = 1; value <= 9; value++) {
        
            if(checkIfLegal(row, col, value))
            {
                board[row][col] = value;
                int nextcol = (col+1)%9;
                int nextrow = row +(nextcol/7);

                if(solverec(nextrow, nextcol)){
                    return true;
                }else{
                    board[row][col] = 0;
                }
            }
        }
        return false;

    }
    @Override
    public boolean solve() {

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {

             if(board[i][j] != 0 &&!checkIfLegal(i,j,board[i][j])){
                 return false;
             }

            }
        }
        return solverec(0,0);
    }
    @Override
    public void add(int row, int col, int value) {
        board[row][col] = value;
        
    }
    
    @Override
    public void clear() {
        board = new int[9][9];
        
    }
    @Override
    public void remove(int row, int col) {
        board[row][col] = 0;
        
    }
}
