package basic_version;

public class ConsoleCanvas {

	private int xSize, ySize;
    private char[][]arena;
    private int xS, yS;
	
    ConsoleCanvas(int xS, int yS, String studentID){
    	this.xS = xS;
    	this.yS = yS;
		xSize = xS + 2;
		ySize = yS + 2;
		arena = new char[xSize][ySize];
		addBorder(' ', '#', studentID);
	}
    public int getX() {
    	return xS;
    }
	
    public int getY() {
    	return yS;
    }
    
	private void addBorder(char spaces, char border, String studentID){
		int top = Math.max((xSize - 8) / 2, 0);
		//create 2D array using col and row,
		//fill the array with spaces
		
        // Add border to the array
        for (int x = 0; x < xSize; x++) {
            for (int j = 0; j < ySize; j++) {
                if (x>0 && x < xSize-1 && j>0 && j<ySize-1) {
                    arena[x][j] = spaces;
                }
                else if(x>=top && x<8+top && j==0) arena[x][j] = studentID.charAt(x-top);
                else arena[x][j] = border;
                
            }
        }    
	}
	
	void ShowIt(int x, int y, char robot){
			arena[x+1][y+1] = robot;
		}
	
	public void clearPos(int x, int y) {
		arena[x + 1][y + 1] = ' ';
	}
	public String toString() {
		String ans = "";
				for (int j = 0; j< ySize; j++) {
					for(int x=0; x<xSize; x++)ans += arena[x][j];
						ans += '\n';
				}
		return ans;
	}


    public static void main(String[] args) {
    	ConsoleCanvas c = new ConsoleCanvas(10, 5, "31017224");
    	c.ShowIt(4, 3, 'B');
    	System.out.println(c.toString());
    } 
}