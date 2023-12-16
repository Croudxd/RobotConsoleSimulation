package basic_version;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Robot {

	private int y;
	private int x;
	private int idCounter;
	private Directions dir;
	private static int id = 0;
	
	enum  Directions{
	    North,
	    East,
	    South,
	    West
	  }
	
	
	public Robot(int y, int x, Directions dir) {
		this.y = y;
		this.x = x;
		this.dir = dir;
		idCounter = id;
		id++;
	
	}
	
	
	public String toString() {
		return "Robot " + idCounter + " is at " + y + "," + x + " traveling: " + dir;
	}
	

	
	
	public Directions nextDirection(Directions c) {
		  int index =  c.ordinal();
		  int nextIndex = index + 1;
		  Directions[] dirs = Directions.values();
		  nextIndex %= dirs.length;
		  return dirs[nextIndex];
	}
	
	public void tryToMove(RobotArena arena, ConsoleCanvas c){
		// x, y 
		int nX, nY;
		nX = x;
		nY = y;
		switch(dir) {
		case North:
			nY ++;
			break;
		case East:
			nX++;
			break;
		case South:
			nY --;
			break;
		case West:
			nX--;
			break;
		}
		if (arena.canMoveHere(nX, nY) == true) {
			
			c.clearPos(x, y);
			
			x = nX;
			y = nY;
			
			arena.showRobots(c);
		} else {
			dir = nextDirection(dir);
		}
		
	}
	
	public boolean isHere(int sx, int sy) {
		if (sx == x && sy == y) {
			return true;
		} else {
			return false;
		}
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Directions getDir() {
		return dir;
		
	}
	
	public int getID() {
		return id; 
	}
	
	public void displayRobot(ConsoleCanvas c) {
		
		c.ShowIt(x, y, 'R');
	}
	
	public static List<Robot> robotFromData(String str) {
		clearID();
		String[] parts = str.split(";");
		List<Robot> robots = new ArrayList<>();
		//remove first part (x, y)
		// 14 12;// 3 5 North 0; 3 5 North 0; 3 5 North 0;
		int x, y, id;
		String dir;
		if (parts.length > 1) {
			String[] robotInfo = Arrays.copyOfRange(parts, 1, parts.length);
			
			for (String data : robotInfo) {
				String[] dataParts = data.trim().split("\\s+");
				if (dataParts.length >=4) {
					x = Integer.parseInt(dataParts[0]);
					y = Integer.parseInt(dataParts[1]);
					dir = dataParts[2];
					id = Integer.parseInt(dataParts[3]);
					Directions direction = Directions.valueOf(dir);
					Robot robot = new Robot(x, y, direction);
					robots.add(robot);
				}
				
			}
		}
		return robots;
	}
	
	public static void clearID() {
		id = 0;
		
	}


public static void main(String[] args) {
	//Robot d = new Robot(5, 3, Directions.South);		// create Robot
	//Robot b = new Robot(2 , 10, Directions.North);
	//System.out.println(d.toString());	// print where is
	//System.out.println(b.toString());
	}

}

