

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public class SodukuSolverTest {
    public SudokuSolver sudokuSolver;

    @BeforeEach                                         
    public void setUp() {
        sudokuSolver = new SudokuSolver();
        int grid[][] = {
            { 5, 3, 0,  0, 7, 0,  0, 0, 0 },
            { 6, 0, 0,  1, 9, 5,  0, 0, 0 },
            { 0, 9, 8,  0, 0, 0,  0, 6, 0 },
    
            { 8, 0, 0,  0, 6, 0,  0, 0, 3 },
            { 4, 0, 0,  8, 0, 3,  0, 0, 1 },
            { 7, 0, 0,  0, 2, 0,  0, 0, 6 },
    
            { 0, 6, 0,  0, 0, 0,  2, 8, 0 },
            { 0, 0, 0,  4, 1, 9,  0, 0, 5 },
            { 0, 0, 0,  0, 8, 0,  0, 7, 9 } };
            
        sudokuSolver.init(grid);
        
    }
    @Test
    public void unsolvableNbr2(){
        int grid[][] = {
            { 5, 5, 0,  0, 0, 0,  0, 0, 0 },
            { 0, 0, 0,  0, 0, 0,  0, 0, 0 },
            { 0, 0, 0,  0, 0, 0,  0, 0, 0 },
    
            { 0, 0, 0,  0, 0, 0,  0, 0, 0 },
            { 0, 0, 0,  0, 0, 0,  0, 0, 0 },
            { 0, 0, 0,  0, 0, 0,  0, 0, 0 },
    
            { 0, 0, 0,  0, 0, 0,  0, 0, 0 },
            { 0, 0, 0,  0, 0, 0,  0, 0, 0 },
            { 0, 0, 0,  0, 0, 0,  0, 0, 0 } };

        sudokuSolver.init(grid);

        assertFalse(sudokuSolver.solve());
    }
    @Test
    public void unsolvable(){
        int grid[][] = {
            { 1, 2, 3,  0, 0, 0,  0, 0, 0 },
            { 4, 5, 6,  0, 0, 0,  0, 0, 0 },
            { 0, 0, 0,  7, 0, 0,  0, 0, 0 },
    
            { 0, 0, 0,  0, 0, 0,  0, 0, 0 },
            { 0, 0, 0,  0, 0, 0,  0, 0, 0 },
            { 0, 0, 0,  0, 0, 0,  0, 0, 0 },
    
            { 0, 0, 0,  0, 0, 0,  0, 0, 0 },
            { 0, 0, 0,  0, 0, 0,  0, 0, 0 },
            { 0, 0, 0,  0, 0, 0,  0, 0, 0 } };

        sudokuSolver.init(grid);

        assertFalse(sudokuSolver.solve());
    }
    @Test
    public void emptyBoard(){
        sudokuSolver.clear();
        sudokuSolver.solve();
        int[][] temp = sudokuSolver.getBoard();

        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp.length; j++) {          
                assertTrue(sudokuSolver.checkIfLegal(i, j, temp[i][j]));
            }
        }
    }
    @Test
    public void checkIfEmpty(){
        assertTrue(sudokuSolver.checkIfEmpty(0, 2));
        assertTrue(sudokuSolver.checkIfEmpty(0, 3));
        assertTrue(sudokuSolver.checkIfEmpty(0, 6));

        assertFalse(sudokuSolver.checkIfEmpty(0, 0));
        assertFalse(sudokuSolver.checkIfEmpty(0, 1));
        assertFalse(sudokuSolver.checkIfEmpty(1, 0));
        

    }
    @Test 
    public void init(){

        int grid[][] = {
            { 0, 0, 8,  0, 0, 9,  0, 6, 2 },
            { 0, 0, 0,  0, 0, 0,  0, 0, 5 },
            { 1, 0, 2,  5, 0, 0,  0, 0, 0 },
    
            { 0, 0, 0,  2, 1, 0,  0, 9, 0 },
            { 0, 5, 0,  0, 0, 0,  6, 0, 0 },
            { 6, 0, 0,  0, 0, 0,  0, 2, 8 },
    
            { 4, 1, 0,  6, 0, 8,  0, 0, 0 },
            { 8, 6, 0,  0, 3, 0,  1, 0, 0 },
            { 0, 0, 0,  0, 0, 0,  4, 0, 0 } };
            

        sudokuSolver.init(grid);

        int[][] boardToTest = sudokuSolver.getBoard();
        
        for (int i = 0; i < boardToTest.length; i++) {
            for (int j = 0; j < boardToTest.length; j++) {
                assertTrue(boardToTest[i][j] == grid[i][j]);
            }
        }
    }
    @Test
    public void solve(){

        int solvedgrid[][] = {
            { 5, 3, 4,  6, 7, 8,  9, 1, 2 },
            { 6, 7, 2,  1, 9, 5,  3, 4, 8 },
            { 1, 9, 8,  3, 4, 2,  5, 6, 7 },
    
            { 8, 5, 9,  7, 6, 1,  4, 2, 3 },
            { 4, 2, 6,  8, 5, 3,  7, 9, 1 },
            { 7, 1, 3,  9, 2, 4,  8, 5, 6 },
    
            { 9, 6, 1,  5, 3, 7,  2, 8, 4 },
            { 2, 8, 7,  4, 1, 9,  6, 3, 5 },
            { 3, 4, 5,  2, 8, 6,  1, 7, 9 } };

        assertTrue(sudokuSolver.solve());
        int grid[][] = sudokuSolver.getBoard();
            
        for (int i = 0; i < solvedgrid.length; i++) {
            for (int j = 0; j < solvedgrid.length; j++) {
                assertTrue(grid[i][j] == solvedgrid[i][j]);
            }
        }
    }

    @Test
    public void add(){

        sudokuSolver.add(0, 1, 0);
        sudokuSolver.add(5, 1, 4);
        sudokuSolver.add(3, 7, 8);
        sudokuSolver.add(1, 4, 9);

        int[][] temp = sudokuSolver.getBoard();

        assertTrue(temp[0][1] == 0);
        assertTrue(temp[5][1] == 4);
        assertTrue(temp[3][7] == 8);
        assertTrue(temp[1][4] == 9);

    }
    @Test
    public void clear(){
        sudokuSolver.clear();

        int[][] temp = sudokuSolver.getBoard(); 

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                assertTrue(temp[i][j] == 0);
            }
        }
    }
    @Test
    public void remove(){
        sudokuSolver.remove(0, 0);
        sudokuSolver.remove(1, 0);

        int[][] temp = sudokuSolver.getBoard();

        assertTrue(temp[0][0] == 0);
        assertTrue(temp[1][0] == 0);
    }

    @Test
    public void getBoard(){
        int[][] tempcorrect = {
            { 5, 3, 0,  0, 7, 0,  0, 0, 0 },
            { 6, 0, 0,  1, 9, 5,  0, 0, 0 },
            { 0, 9, 8,  0, 0, 0,  0, 6, 0 },
    
            { 8, 0, 0,  0, 6, 0,  0, 0, 3 },
            { 4, 0, 0,  8, 0, 3,  0, 0, 1 },
            { 7, 0, 0,  0, 2, 0,  0, 0, 6 },
    
            { 0, 6, 0,  0, 0, 0,  2, 8, 0 },
            { 0, 0, 0,  4, 1, 9,  0, 0, 5 },
            { 0, 0, 0,  0, 8, 0,  0, 7, 9 } };

        int[][] temp = sudokuSolver.getBoard();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                assertTrue(temp[i][j] ==  tempcorrect[i][j]);
            }
        }
        }
  
    @Test
    public void checkIfLegal(){

        assertTrue(sudokuSolver.checkIfEmpty(0, 3));
        assertTrue(sudokuSolver.checkIfLegal(0, 3, 6));
        assertTrue(sudokuSolver.checkIfLegal(8, 1, 1));
        assertFalse(sudokuSolver.checkIfLegal(7, 1, 6));
        assertFalse(sudokuSolver.checkIfLegal(3, 6, 2));
        assertFalse(sudokuSolver.checkIfLegal(5, 8, 7));

    }
    

}
