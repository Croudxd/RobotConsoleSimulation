package basic_version;
import java.util.Scanner;
//import javax.swing.JFileChooser;
import java.io.File; 
import java.util.concurrent.TimeUnit;
import javax.swing.JFileChooser;

/**
 * Simple program to show arena with multiple robots
* @author 
 *
 */
public class RobotInterface {
	
	private Scanner s; // scanner used for input from user
    private RobotArena myArena;	
    private ConsoleCanvas currentCanvas;
    private Scanner input;// arena in which Robots are shown
    private int x = 0;
	private int y = 0;
	private boolean animate = false;
    /**
    	 * constructor for RobotInterface
    	 * sets up scanner used for input and the arena
    	 * then has main loop allowing user to enter commands
     */
    public RobotInterface() {
    	s = new Scanner(System.in);
    	input = new Scanner(System.in);
    	//JFileChooser cha = new JFileChooser();
    	TextFile tf = new TextFile("Text file", "txt");
 	    System.out.println("Enter X value: ");
 	    x = input.nextInt();
 	    System.out.println("Enter Y value: ");
 	    y = input.nextInt();// set up scanner for user input
 	    currentCanvas = new ConsoleCanvas(x, y, "31017224");
  	 	myArena = new RobotArena(x, y);
        char ch = ' ';
        do {
        	System.out.print("Enter (A)dd Robot, get (I)nformation, (D) to Display, (G) to animate robots, (H) to stop robots, (S) to save to a file, (L) to load from file or e(X)it > ");
        	ch = s.next().charAt(0);
        	s.nextLine();
        	switch (ch) {
    			case 'A' :
    			case 'a' :
        					myArena.addRobot();	// add a new Robot to arena
        					break;
        		case 'I' :
        		case 'i' :
        					System.out.print(myArena.toString());
            				break;
        		case 'x' : 	ch = 'X';				// when X detected program ends
        					break;
        		case 'D':
        		case 'd':
        					doDisplay(currentCanvas);
        					break;
        		case 'H':
        		case 'h':
        					animate = false;
        					break;
        		case 'G':
        		case 'g':
        					animate = true;
        					while(animate == true){
	        					myArena.moveAllRobots(currentCanvas);
	        					try {
									TimeUnit.SECONDS.sleep(1);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
	        					doDisplay(currentCanvas);
	        					System.out.println(myArena.toString());
	        					}
        					break;
        		case 'N':
        		case 'n':
        			
        			
        		case 'S':
        		case 's':
        			
        					//save file
        					if(tf.createFile()) {
        						tf.writeAllFile(myArena.fileString());
        					}
        					break;
        					
        		case 'L':
        		case 'l':
        					//load file
        					if(tf.openFile()) {
        						String str = tf.readAllFile();
        						str = str.substring(0, str.length()-1);
        						CanvasFromData(str);
        					}
        					break;
        			
        			
        			
        	}
    		} while (ch != 'X');						// test if end
        
       s.close();									// close scanner
    }
    
    public void doDisplay(ConsoleCanvas c) {
 

		//call showRobots
 
    	myArena.showRobots(c);
  
    	//use canvas.toString
    	System.out.println(c.toString());
    	
    
    }
    public ConsoleCanvas CanvasFromData(String str) {
    	// 14 12; 3 5 North 0; 3 5 North 0; 3 5 North 0;
    	String[] parts = str.split(";");	
    	
    	if (parts.length > 0) {
    		String[] dimensions = parts[0].trim().split(" ");
    		if (dimensions.length >=2) {
    		
    			int y = Integer.parseInt(dimensions[1].trim());
    			int x = Integer.parseInt(dimensions[0].trim());
    			String studentID = "301017224"; 
    			currentCanvas = new ConsoleCanvas(x, y, studentID);
    	    	myArena.addRobotToArray(str);
    	    	myArena.setSize(x, y);
    	    	
    			return currentCanvas;
    				}
    			}
		return null;
    		}
    
    public ConsoleCanvas newArena() {
    	String studentID = "31017224";
    	s = new Scanner(System.in);
    	input = new Scanner(System.in);
 	    System.out.println("Enter X value: ");
 	    x = input.nextInt();
 	    System.out.println("Enter Y value: ");
 	    y = input.nextInt();
    	ConsoleCanvas c = new ConsoleCanvas(x, y, studentID);
    	RobotArena myArena = new RobotArena(x, y);
    	return c; // myArena;
    }
    
    
	public static void main(String[] args) {
		RobotInterface r = new RobotInterface();	// just call the interface

	}

}
