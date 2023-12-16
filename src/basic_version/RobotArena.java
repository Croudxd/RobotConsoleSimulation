package basic_version;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import basic_version.Robot.Directions;




public class RobotArena {

	private int arenaLength;
	private int arenaWidth;
	private Robot robot;
	private Robot[] robotsAr;
	private static int count;
	private int maxRobots = 15;
	
	
	
	
	public RobotArena(int arenaLength, int arenaWidth) {
		this.arenaLength = arenaLength;
		this.arenaWidth = arenaWidth;
		robotsAr = new Robot[maxRobots];
		
		
	}
	
	public Directions randomDirection() {
		Random random = new Random();
		return Directions.values()[random.nextInt(Directions.values().length)];
	}
	
	
	public void addRobot() {
		Directions dir = randomDirection();
		int y,x;
		Random random;
		random = new Random();
//		do {
//			x = random.nextInt(arenaLength);
//			y = random.nextInt(arenaWidth);
//			robot = new Robot(x, y, dir);
//		if (count < robotsAr.length) {
//			robotsAr[count] = robot;
//			count++;
//			break;
//		} else { 
//			count = 0; 
//			}
//		}
//		while(getRobotAt(x, y) == null);
		
		while(true) {
			x = random.nextInt(arenaLength-1);
			y = random.nextInt(arenaWidth-1);
			
			if(getRobotAt(x, y) == null) {
				
				robot = new Robot(x, y, dir);
				if (count < robotsAr.length) {
					robotsAr[count] = robot;
					count++;
					
				} else throw new RuntimeException("Cant add more robots");
				break;
					 
			}
		}
	}
		
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("Arena is size " + arenaLength + "x" + arenaWidth + ":\n");
		for(int x = 0; x < robotsAr.length; x++) {
			if (robotsAr[x] == null) {
				break;
			} else 
			result.append(robotsAr[x] + "\n");
		}
		return result.toString();
	}
	
	public Robot getRobotAt(int x, int y) {
		if (this.robot !=null && this.robot.isHere(x, y) == true) {
			return this.robot;
		} else
		return null;
	}
	
	public int getX() {
		return arenaWidth;
	}
	
	public int getY() {
		return arenaLength;
	}
	
	public String fileString() {
		StringBuilder str = new StringBuilder();
		str.append(arenaLength);
		str.append(" ");
		str.append(arenaWidth);
		str.append(";");
		for (Robot r : robotsAr) {
			if(r==null) {
				break;
			} else {
			
				int x = r.getX();
				int y = r.getY();
				Directions dir = r.getDir();
				int id = r.getID();
				str.append(" "+ x + " " + y + " " + dir + " " + id + ";");
		}
		
		
		
		
	}
		return str.toString();
	}


	public void showRobots(ConsoleCanvas c) {
		
		for(Robot r : robotsAr) {
			if(r==null) {
				return;
			} 
//			if (r.getX() > arenaLength && r.getY() > arenaWidth) {
			r.displayRobot(c);
			
		}
	}
	public boolean canMoveHere(int x, int y) {
		if (getRobotAt(x, y) == null && y <= arenaWidth-1 && x <= arenaLength-1 && x >= 0 && y >= 0) {
			
			return true;
		} else return false;
		
	}
	
	public void moveAllRobots(ConsoleCanvas c){
		for(Robot r : robotsAr)
			if ( r !=null) {
				r.tryToMove(this, c);
			}
	}
	

	public void addRobotToArray(String str) {
		clearRobots();
		List<Robot> newrobot = Robot.robotFromData(str);
		for(Robot robot : newrobot) {
			if (robot !=null) {
				if (count < robotsAr.length) {
					
					robotsAr[count] = robot;
					count++;
				} else {
					throw new RuntimeException("Cant add more robots");
				}
			}
		}
	}
	public void setSize(int length, int width) {
		this.arenaLength = length;
		this.arenaWidth = width;
	}
	
	public void clearRobots() {
		Arrays.fill(robotsAr, null);
		count = 0;
	}
		
		

		
	public static void main(String[] args) {
//		RobotArena a = new RobotArena(20, 10);
//
//		a.addRobot();
//		a.addRobot();
//		a.addRobot();
//		
//		
//		System.out.println(a.toString());
//		a.moveAllRobots();
		
		// print arena size and where robot is
	}

}
