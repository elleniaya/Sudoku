public class SudokuPuzzle {

	protected String [][] board;
	private final int ROWS;
	private final int COLUMNS;
	private final int BOXWIDTH;
	private final int BOXHEIGHT;
	private final String [] VALIDVALUES;
	
	public SudokuPuzzle(int rows,int columns,int boxWidth,int boxHeight,String [] validValues) {
		this.ROWS = rows;
		this.COLUMNS = columns;
		this.BOXWIDTH = boxWidth;
		this.BOXHEIGHT = boxHeight;
		this.VALIDVALUES = validValues;
		this.board = new String[ROWS][COLUMNS];
		initializeBoard();
	}

    private void initializeBoard() {
		for(int row = 0; row < this.ROWS; row++) {
			for(int col = 0; col < this.COLUMNS; col++) {
				this.board[row][col] = "";
			}
		}
	}
	
	public int getNumRows() {
		return this.ROWS;
	}
	
	public int getNumColumns() {
		return this.COLUMNS;
	}
	
	public int getBoxWidth() {
		return this.BOXWIDTH;
	}
	
	public int getBoxHeight() {
		return this.BOXHEIGHT;
	}
	
	public String [] getValidValues() {
		return this.VALIDVALUES;
	}

    public void addValue(String value, int row, int column) {
        if (checkValue(value)) {
            this.board[row][column] = value;
        }
    }

    public boolean checkValue(String value) {
        return checkValidValue(value);
    }

    public boolean checkValidValue(String value) {
        for(String str : this.VALIDVALUES) {
			if(str.equals(value)) return true;
		}
        return false;
    }
	
	public String getValue(int row,int col) { 
		if(this.CorrectCoords(row,col)) {
			return this.board[row][col];
		}
		return "";
	}
	
	public String [][] getBoard() {
		return this.board;
	}
	
	public boolean CorrectCoords(int row, int col) {
		return (row <= this.ROWS && col <= this.COLUMNS && row >= 0 && col >= 0);
	}
}
