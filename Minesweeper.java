import java.lang.Math;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.lang.ArrayIndexOutOfBoundsException;
import java.util.Scanner;

public class Minesweeper {
		private boolean[][] hasMine; 
		private int[][] mineNear; 
		private String[][] board; 
		private int row;
		private int col;
		private int mine;

		public static void main(String args[]) throws ArrayIndexOutOfBoundsException, InputMismatchException {
			int row;
			int col;
			int mine;
			
			Scanner input = new Scanner(System.in);
			System.out.print("Choose Beginner, Intermediate, Expert, or Custom: ");
			String str = input.nextLine();
			
			if (str.toLowerCase().equals("beginner")) {
				row = 9; 
				col = 9;
				mine = 10;
				Minesweeper x = new Minesweeper(row, col, mine);
			}
			
			else if (str.toLowerCase().equals("intermediate")) {
				row = 16; 
				col = 16;
				mine = 40;
				Minesweeper x = new Minesweeper(row, col, mine);
			}
			
			else if (str.toLowerCase().equals("expert")) {
				row = 16; 
				col = 30;
				mine = 99;
				Minesweeper x = new Minesweeper(row, col, mine);
			}
			
			else if (str.toLowerCase().equals("custom")) {
				System.out.print("Enter number of rows: ");
			    row = input.nextInt();
				System.out.print("Enter number of columns: ");
				col = input.nextInt();
				System.out.print("Enter number of mines: ");
				mine = input.nextInt();
				Minesweeper x = new Minesweeper(row, col, mine);
			}
			
			else
				System.out.println("Error");
		}

		public Minesweeper(int row, int col, int mine) {
			this.mine = mine;
			this.row = row;
			this.col = col;
			
			if (mine > row * col) 
				System.out.println("Too many mines");

			hasMine = new boolean[row][col];
			mineNear = new int[row][col];
			board = new String[row][col];
			startBoard();
			play();
			
		}
		
		public void startBoard() {
			int temp = mine;
			while (temp > 0) {
				int r = (int)(row * Math.random());
				int c = (int)(col * Math.random());
				if (!hasMine[r][c]) {
					hasMine[r][c] = true;
					temp--;
				}			
			}
			
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					if (hasMine[i][j]) {
						for (int k = i - 1; k <= i + 1; k++)
							for (int l = j - 1; l <= j + 1; l++)
								if (l >= 0 && k >= 0 && l < col && k < row)
									mineNear[k][l] += 1;
					}
					board[i][j] = "|_|";
				}
			}
		}
		
		

		public void play()  throws ArrayIndexOutOfBoundsException  {
			int space = 0;
			boolean cont = true;
			
			while (cont && space < (row * col) - mine) {
				print();
				cont = game(row(), column());
				space++;
			}
			
			if (cont) {
				for (int i = 0; i < row; i++) {
					for (int j = 0; j < col; j++) {
						if (hasMine[i][j])
							System.out.print("X ");
						else
							System.out.print("_");
					}
					System.out.println();
				}
				System.out.println("You Win!");
			}
				
			else {
				for (int i = 0; i < row; i++) {
					for (int j = 0; j < col; j++) {
						if (hasMine[i][j])
							System.out.print("X ");
						else
							System.out.print(board[i][j]);
					}
					System.out.println();
				}
				System.out.println("Game Over!");
			}	
		}
		
		public void print() {
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++)
					System.out.print(board[i][j]);
				System.out.println();
			}
		}

		public boolean game(int row, int col) throws ArrayIndexOutOfBoundsException {
			if (hasMine[row][col])
				return false;
			else 
				board[row][col] = mineNear[row][col] + " ";
			return true;
		}

		public int row() {
			int row = 0;
			try { 
				Scanner input = new Scanner(System.in);
				System.out.print("Enter row number: ");
				 row = input.nextInt();
			} catch (java.lang.ArrayIndexOutOfBoundsException ex) {
				System.out.println("Index out of bounds.");
			} catch (InputMismatchException ex) {
				System.out.println("Try again");
			}
			return row - 1;
		}

		public int column() {
			int col = 0;
			try {
			Scanner input = new Scanner(System.in);
			System.out.print("Enter column number: ");
			col = input.nextInt();
			} catch (java.lang.ArrayIndexOutOfBoundsException ex) {
				System.out.println("Index out of bounds.");
			} catch (InputMismatchException ex) {
				System.out.println("Try again");
			}
			return col - 1;
		}
}
