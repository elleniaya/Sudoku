public class SudokuPuzzle {

	protected String [][] board;
	protected boolean [][] mutable;
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
		this.mutable = new boolean[ROWS][COLUMNS];
		initializeBoard();
		initializeMutableSlots();
	}

    private void initializeBoard() {
		for(int row = 0; row < this.ROWS; row++) {
			for(int col = 0; col < this.COLUMNS; col++) {
				this.board[row][col] = "";
			}
		}
	}
	
	private void initializeMutableSlots() {
		for(int row = 0; row < this.ROWS; row++) {
			for(int col = 0; col < this.COLUMNS; col++) {
				this.mutable[row][col] = true;
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
		if(this.inRange(row,col)) {
			return this.board[row][col];
		}
		return "";
	}
	
	public String [][] getBoard() {
		return this.board;
	}
	
	public boolean inRange(int row,int col) {
		return row <= this.ROWS && col <= this.COLUMNS && row >= 0 && col >= 0;
	}
	
	public boolean boardFull() {
		for(int r = 0; r < this.ROWS; r++) {
			for(int c = 0;c < this.COLUMNS;c++) {
				if(this.board[r][c].equals("")) return false;
			}
		}
		return true;
	}
	
	public void makeSlotEmpty(int row,int col) { 
		this.board[row][col] = "";
	}
	
	@Override
	public String toString() { 
		String str = "Game Board:\n";
		for(int row=0; row < this.ROWS;row++) {
			for(int col = 0; col < this.COLUMNS; col++) {
				str += this.board[row][col] + " ";
			}
			str += "\n";
		}
		return str + "\n";
	}
	
}
