/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import sokoban.GameState.Position;

/**
 *
 * @author steven
 */
public class HumanPlayer implements KeyListener{

    GameState state;
    MyGameState MyState;
    
    GameDisplay display;
    int initialPlayerStateX;
    int initialPlayerStateY;
    
    public HumanPlayer(GameState state){
        this.state=state;
        display = new GameDisplay(state);
        display.addKeyListener(this);
        initialPlayerStateX = state.playerCol;
        initialPlayerStateY = state.playerRow;
    }
   
    public HumanPlayer(MyGameState MyState){
        this.MyState=MyState;
        
        display = new GameDisplay(MyState);
        display.addKeyListener(this);
        initialPlayerStateX = MyState.playerCol;
        initialPlayerStateY = MyState.playerRow;
    }
    
    public void keyTyped(KeyEvent e) {        
    }

    
    public void keyPressed(KeyEvent e) {        
        GameState newState = null;
        if(e.getKeyCode()==KeyEvent.VK_UP)
            newState = state.moveUp();
        else if(e.getKeyCode()==KeyEvent.VK_DOWN)
            newState = state.moveDown();
        else if(e.getKeyCode()==KeyEvent.VK_RIGHT)
            newState = state.moveRight();
        else if(e.getKeyCode()==KeyEvent.VK_LEFT)
            newState = state.moveLeft();
        if(newState!=null){
            state=newState;
//            state.printState();
         //   System.out.println(state.isGoalState());
          //  System.out.println(state.playerCol + " " + state.playerRow); 
       //     int test = state.goalPositions(0);
            
            int test;
            int test2;
            int g;
            int cost = 1;
            int temp_g;
            //manhattan distance y + row, x + collumn
            
            
            test = state.goalPositions.get(0).col;
            test2 = state.goalPositions.get(0).row;
            ////test code
            
            int playerX = state.playerCol;    //this gets the player position on the x Axis
        	int playerY = state.playerRow; 	  //this gets the player position on the y Axis

            
        	//System.out.println("lowest manhattan distance: " + manhattendist);
/*            System.out.println("Distance to start node   / g_score: " + initialtoNodeDistance(newState));
            System.out.println("Distance to closest goal / h_score: " + manhattanDistance(newState));*/
            temp_g = manhattanDistance(newState);
            g = temp_g += cost;
/*            System.out.println("f Value                           : " + f_distance(initialtoNodeDistance(newState), manhattanDistance(newState)));
         //   System.out.println("Size of legal actions             : " + getLegalActions(newState));
            for(int i = 0;i < getLegalActions(newState).size(); i++){
            	System.out.println(getLegalActions(newState).get(i));
            	System.out.println("NODE F DISTANCE: " + f_distance(initialtoNodeDistance(getLegalActions(newState).get(i)) + temp_g, manhattanDistance(getLegalActions(newState).get(i))) );
            }
          */
            //evaluation block state for player instead of block:::
            System.out.println("Block state: " + newState.isAgainstWall(newState.playerRow, newState.playerCol));
            
            //simple corner Checker
            if(newState.isAgaintWall = 1 && )
            
            
            //evaluation of block state north.
        //    System.out.println("BLOCK STATES: " + newState.isFailSafe(newState.goalPositions.get(0).row, newState.goalPositions.get(0).col));
            
        //    System.out.println("Get goal positions: " + newState.goalPositions.get(0).col);
         //   System.out.println(test + " " + test2);
          //  System.out.println("Number of goal positions: " + state.goalPositions.size());
            display.updateState(newState);
        }
    }

    public int initialtoNodeDistance(GameState stateN){
    	
    	int currentPX = stateN.playerCol;
    	int currentPY = stateN.playerRow;
    	
    	int currentXDist = Math.abs(currentPX - initialPlayerStateX);
    	int currentYDist = Math.abs(currentPY - initialPlayerStateY);
    	
    	return currentXDist + currentYDist;
    }
    public void keyReleased(KeyEvent e) {        
    }

    public List<GameState> getLegalActions(GameState state){
    	//A list containing legal leaf-nodes from given state.
    	
    	List LegalStates = new ArrayList<GameState>(); 
    	//LegalStates.add(state); // may need to remove
    	
    	
    	if(state.moveLeftLegal() == true){
    		LegalStates.add(state.moveLeft());
    	}
    	if(state.moveUpLegal() == true){
    		LegalStates.add(state.moveUp());
    	}
    	if(state.moveRightLegal() == true){
    		LegalStates.add(state.moveRight());
    	}
    	if(state.moveDownLegal() == true){
    		LegalStates.add(state.moveDown());
    	}
    	return LegalStates;
    }
    public int manhattanDistance(GameState state){
    	//gets player position
    	int playerX = state.playerCol;    //this gets the player position on the x Axis
    	int playerY = state.playerRow; 	  //this gets the player position on the y Axis

    	int manhattendist = 0;
        
    	//this loop returns the Manhattan distance towards the closest goal position.
    	for(int K = 0; K < state.goalPositions.size(); K++){
    		//x and y Co-ords of currently accessed goal.
    		int tempX = state.goalPositions.get(K).col;
    		int tempY = state.goalPositions.get(K).row;
    		
    		//this holds the x axis position of the currently accessed goal
    		int currentXDistance = Math.abs(playerX - tempX);
    		int currentYDistance = Math.abs(playerY - tempY);
    		
    		int tempManhattenDistance =  currentXDistance + currentYDistance;
    				
    		//checks for first goal position for initialisation purposes.
    		if(K == 0){
    			//this is used to store the manhattendistance of the first goal.
    			manhattendist = tempManhattenDistance;
    			
    		//if the manhattendistance for the currently checked goal is less than the one already stored.
    		}else if(tempManhattenDistance < manhattendist){
    			//assign the new shorter distance as the lowest manhatten distance.
    			manhattendist = tempManhattenDistance;
    			
    		}
    	}
    	
    	return manhattendist;
    }

    public int f_distance(int g, int h){
    	
    	return h + g;
    	
    	
    }
    public static void main(String[] args) throws Exception{
        GameState state = args.length==0?new GameState("src/levels/level6.txt"):new GameState(args[0]);
        HumanPlayer player = new HumanPlayer(state);                
    }
    
}
