package finalproject;

import java.util.*;
import java.io.*;


public class ChessSudoku
{
	/* SIZE is the size parameter of the Sudoku puzzle, and N is the square of the size.  For 
	 * a standard Sudoku puzzle, SIZE is 3 and N is 9. 
	 */
	public int SIZE, N;

	/* The grid contains all the numbers in the Sudoku puzzle.  Numbers which have
	 * not yet been revealed are stored as 0. 
	 */
	public int grid[][];

	/* Booleans indicating whether of not one or more of the chess rules should be 
	 * applied to this Sudoku. 
	 */
	public boolean knightRule;
	public boolean kingRule;
	public boolean queenRule;

	
	// Field that stores the same Sudoku puzzle solved in all possible ways
	public HashSet<ChessSudoku> solutions = new HashSet<ChessSudoku>();


	/* The solve() method should remove all the unknown characters ('x') in the grid
	 * and replace them with the numbers in the correct range that satisfy the constraints
	 * of the Sudoku puzzle. If true is provided as input, the method should find finds ALL 
	 * possible solutions and store them in the field named solutions. */
	public void solve(boolean allSolutions) {
		this.solveSingle(allSolutions);
		
		for (ChessSudoku c: this.solutions) {
			this.grid = c.grid;
			break;
			
		}
		

	}

	
	
	private boolean rowOk(int row, int num) {
		for (int i = 0; i<this.N; i++) {
			if (this.grid[row][i] == num) {
				return false;
			}
		}
		return true;
	}
	
	private boolean colOk(int col, int num) {
		for (int i = 0; i<this.N; i++) {
			if (this.grid[i][col] == num) {
				return false;
			}
		}
		return true;
	}
	
	
	private boolean boxOk(int row, int col, int num) {
		int startRow = row - row%SIZE;
		int startCol = col - col%SIZE;
		
		for (int a = startRow; a < startRow+SIZE;a++) {
			for (int b = startCol; b < startCol+SIZE;b++) {
				if (this.grid[a][b] == num) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	
	private boolean knightOk(int i, int j, int num) {
		
		if (this.knightRule) {
			if (((i-1)<this.N && (i-1)>=0) && ((j+2)<this.N && (j+2) >=0)) {
				if (this.grid[i-1][j+2] == num) {
					return false;
				}
			}
				
				
			if (((i-2)<this.N && (i-2)>=0) && ((j+1)<this.N && (j+1) >=0)) {
				if (this.grid[i-2][j+1] == num) {
					return false;
				}
			}

			if (((i-2)<this.N && (i-2)>=0) && ((j-1)<this.N && (j-1) >=0)) {
				if (this.grid[i-2][j-1] == num) {
					return false;
				}
			}
			
			if (((i-1)<this.N && (i-1)>=0) && ((j-2)<this.N && (j-2) >=0)) {
				if (this.grid[i-1][j-2] == num) {
					return false;
				}
			}

			
			if (((i+1)<this.N && (i+1)>=0) && ((j-2)<this.N && (j-2) >=0)) {
				if (this.grid[i+1][j-2] == num) {
					return false;
				}
			}

			
			if (((i+2)<this.N && (i+2)>=0) && ((j-1)<this.N && (j-1) >=0)) {
				if (this.grid[i+2][j-1] == num) {
					return false;
				}
			}

			if (((i+2)<this.N && (i+2)>=0) && ((j+1)<this.N && (j+1) >=0)) {
				if (this.grid[i+2][j+1] == num) {
					return false;
				}
			}
			
			
			if (((i+1)<this.N && (i+1)>=0) && ((j+2)<this.N && (j+2) >=0)) {
				if (this.grid[i+1][j+2] == num) {
					return false;
				}
			}

	}
		return true;
	}
	
	
	
	private boolean kingOk(int i, int j, int num) {
		if (this.kingRule) {
			if (((i+1)<this.N && (i+1)>=0) && ((j-1)<this.N && (j-1) >=0)) {
				if (this.grid[i+1][j-1] == num) {
					return false;
				}
			}

			
			if (((i+1)<this.N && (i+1)>=0) && ((j+1)<this.N && (j+1) >=0)) {
				if (this.grid[i+1][j+1] == num) {
					return false;
				}
			}

			if (((i-1)<this.N && (i-1)>=0) && ((j+1)<this.N && (j+1) >=0)) {
				if (this.grid[i-1][j+1] == num) {
					return false;
				}
			}
			
			
			if (((i-1)<this.N && (i-1)>=0) && ((j-1)<this.N && (j-1) >=0)) {
				if (this.grid[i-1][j-1] == num) {
					return false;
				}
			}
			
			}
		return true;
	}
	
	
	
	private boolean queenOk(int i, int j, int num) {
		if (this.queenRule) {
		if (num == this.N) {
		//check for upper right diagonal
		int q = i;
		int r = j;
		
		while(q >= 0 && r <this.N) {
			if (this.grid[q][r] == num) {
				return false;
			}
			q--;
			r++;
		}
		
		//checks for upper left diagonal
		q = i;
		r = j;
		while(q >= 0 && r >= 0) {
			if (this.grid[q][r] == num) {
				return false;
			}
			q--;
			r--;
		}
		
		
		//checks for lower right diagonal
		q = i;
		r = j;
		while(q < this.N && r <this.N) {
			if (this.grid[q][r] == num) {
				return false;
			}
			q++;
			r++;
		}
		
		//checks for lower left diagonal
		q = i;
		r = j;
		while(q < this.N && r >= 0) {
			if (this.grid[q][r] == num) {
				return false;
			}
			q++;
			r--;
		}
		
		}
		return true;
		
		

		
		//System.out.println("after queenRule:" + choices.toString());
		}
		return true;
	}
	
	
	
	private boolean check(int row, int col, int num) {
		
		return rowOk(row, num) && colOk(col, num) && boxOk(row, col, num) && knightOk(row,col,num) && kingOk(row, col, num) && queenOk(row, col, num);
	}
	
	
	private int num() {
		
		int square = 0;
		for (int row = 0; row< this.N;row++) {
			for (int col = 0;col<this.N;col++) {
				if (this.grid[row][col] == 0) {
					square++;
				}
			}
		}
		
		return square;
		
	}
	
	private int[][] deepCopy(int[][] array){
	
		int[][] array2 = new int[this.N][];
		for (int i = 0; i<N;i++) {
			array2[i] = Arrays.copyOf(array[i], this.N);
		}
		
		return array2;

	}
	
	private boolean solveSingle(boolean allSolutions) {
		
		int emptySquare = this.num();
		
		
		int currSquare = 0;
		
		
		for (int row = 0; row< this.N;row++) {
			for (int col = 0;col<this.N;col++) {
				if (this.grid[row][col] == 0) {

					
					currSquare++;
					
					for (int i = 1; i <= this.N;i++) {
						if (check(row,col,i)) {
							
							this.grid[row][col] = i;
							
							if (solveSingle(allSolutions)) {
								
								if (emptySquare == currSquare) {
									if (allSolutions) {//we want more than 1 sol
										
										ChessSudoku tmp = new ChessSudoku(this.SIZE);
										tmp.grid = deepCopy(this.grid);
										tmp.kingRule = this.kingRule;
										tmp.queenRule = this.queenRule;
										tmp.knightRule = this.knightRule;
										
										
										this.solutions.add(tmp);
										this.grid[row][col] = 0;
										return false;
									}
									else {//just want 1 sol

										return true;
									}
								}

								return true;
							

							
							}
							
							else {
								this.grid[row][col] = 0;

							}
							
						}
					}

					return false;
					
					
				}
				
				
			}
		}
		
		return true;
	}
	


	/* Default constructor.  This will initialize all positions to the default 0
	 * value.  Use the read() function to load the Sudoku puzzle from a file or
	 * the standard input. */
	public ChessSudoku( int size ) {
		SIZE = size;
		N = size*size;

		grid = new int[N][N];
		for( int i = 0; i < N; i++ ) 
			for( int j = 0; j < N; j++ ) 
				grid[i][j] = 0;
	}


	/* readInteger is a helper function for the reading of the input file.  It reads
	 * words until it finds one that represents an integer. For convenience, it will also
	 * recognize the string "x" as equivalent to "0". */
	static int readInteger( InputStream in ) throws Exception {
		int result = 0;
		boolean success = false;

		while( !success ) {
			String word = readWord( in );

			try {
				result = Integer.parseInt( word );
				success = true;
			} catch( Exception e ) {
				// Convert 'x' words into 0's
				if( word.compareTo("x") == 0 ) {
					result = 0;
					success = true;
				}
				// Ignore all other words that are not integers
			}
		}

		return result;
	}


	/* readWord is a helper function that reads a word separated by white space. */
	static String readWord( InputStream in ) throws Exception {
		StringBuffer result = new StringBuffer();
		int currentChar = in.read();
		String whiteSpace = " \t\r\n";
		// Ignore any leading white space
		while( whiteSpace.indexOf(currentChar) > -1 ) {
			currentChar = in.read();
		}

		// Read all characters until you reach white space
		while( whiteSpace.indexOf(currentChar) == -1 ) {
			result.append( (char) currentChar );
			currentChar = in.read();
		}
		return result.toString();
	}


	/* This function reads a Sudoku puzzle from the input stream in.  The Sudoku
	 * grid is filled in one row at at time, from left to right.  All non-valid
	 * characters are ignored by this function and may be used in the Sudoku file
	 * to increase its legibility. */
	public void read( InputStream in ) throws Exception {
		for( int i = 0; i < N; i++ ) {
			for( int j = 0; j < N; j++ ) {
				grid[i][j] = readInteger( in );
			}
		}
	}


	/* Helper function for the printing of Sudoku puzzle.  This function will print
	 * out text, preceded by enough ' ' characters to make sure that the printint out
	 * takes at least width characters.  */
	void printFixedWidth( String text, int width ) {
		for( int i = 0; i < width - text.length(); i++ )
			System.out.print( " " );
		System.out.print( text );
	}


	/* The print() function outputs the Sudoku grid to the standard output, using
	 * a bit of extra formatting to make the result clearly readable. */
	public void print() {
		// Compute the number of digits necessary to print out each number in the Sudoku puzzle
		int digits = (int) Math.floor(Math.log(N) / Math.log(10)) + 1;

		// Create a dashed line to separate the boxes 
		int lineLength = (digits + 1) * N + 2 * SIZE - 3;
		StringBuffer line = new StringBuffer();
		for( int lineInit = 0; lineInit < lineLength; lineInit++ )
			line.append('-');

		// Go through the grid, printing out its values separated by spaces
		for( int i = 0; i < N; i++ ) {
			for( int j = 0; j < N; j++ ) {
				printFixedWidth( String.valueOf( grid[i][j] ), digits );
				// Print the vertical lines between boxes 
				if( (j < N-1) && ((j+1) % SIZE == 0) )
					System.out.print( " |" );
				System.out.print( " " );
			}
			System.out.println();

			// Print the horizontal line between boxes
			if( (i < N-1) && ((i+1) % SIZE == 0) )
				System.out.println( line.toString() );
		}
	}


	/* The main function reads in a Sudoku puzzle from the standard input, 
	 * unless a file name is provided as a run-time argument, in which case the
	 * Sudoku puzzle is loaded from that file.  It then solves the puzzle, and
	 * outputs the completed puzzle to the standard output. */
	public static void main( String args[] ) throws Exception {
		InputStream in = new FileInputStream("queenMultipleSolutions3x3.txt");

		// The first number in all Sudoku files must represent the size of the puzzle.  See
		// the example files for the file format.
		int puzzleSize = readInteger( in );
		if( puzzleSize > 100 || puzzleSize < 1 ) {
			System.out.println("Error: The Sudoku puzzle size must be between 1 and 100.");
			System.exit(-1);
		}

		ChessSudoku s = new ChessSudoku( puzzleSize );
		
		// You can modify these to add rules to your sudoku
		s.knightRule = false;
		s.kingRule = false;
		s.queenRule = false;
		
		// read the rest of the Sudoku puzzle
		s.read( in );

		System.out.println("Before the solve:");
		s.print();
		System.out.println();

		// Solve the puzzle by finding one solution.
		s.solve(false);

		// Print out the (hopefully completed!) puzzle
		System.out.println("After the solve:");
		s.print();
	}
}

