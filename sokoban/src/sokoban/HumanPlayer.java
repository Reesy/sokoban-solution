/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import sokoban.GameState.Position;

/**
 *
 * @author steven
 */
public class HumanPlayer implements KeyListener{

    GameState state;
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
       
            //manhattan distance y + row, x + collumn
            
            
            test = state.goalPositions.get(0).col;
            test2 = state.goalPositions.get(0).row;
            ////test code
            
            int playerX = state.playerCol;    //this gets the player position on the x Axis
        	int playerY = state.playerRow; 	  //this gets the player position on the y Axis
            
            
            
 /*           for(int K = 0; K < state.goalPositions.size(); K++){
        		//this holds the x axis position of the currently accessed goal
        		int tempX = state.goalPositions.get(K).col;
        		int tempY = state.goalPositions.get(K).row;
        		
        		int currentXDistance = Math.abs(playerX - tempX);
        		int currentYDistance = Math.abs(playerY - tempY);
        		
        		System.out.println("currentXDistance: " + currentXDistance);
        		System.out.println("currentYDistance: " + currentYDistance);
        		
        		System.out.println("Manhattan Distance: " + (currentXDistance + currentYDistance));
        		
        	}*/
        	int manhattendist = 0;
            
        	for(int K = 0; K < state.goalPositions.size(); K++){
        		
        	
        		
        		int tempX = state.goalPositions.get(K).col;
        		int tempY = state.goalPositions.get(K).row;
        		
        		
        		//this holds the x axis position of the currently accessed goal
        		int currentXDistance = Math.abs(playerX - tempX);
        		int currentYDistance = Math.abs(playerY - tempY);
        		
        		int tempManhattenDistance =  currentXDistance + currentYDistance;
        				
        		//this is used to store the manhattendistance of the first goal.
        		if(K == 0){
        			
        			manhattendist = tempManhattenDistance;
        			
        		//if the manhattendistance for the currently checked goal is less than the one already stored.
        		}else if(tempManhattenDistance < manhattendist){
        			//assign the new shorter distance as the lowest manhatten distance.
        			manhattendist = tempManhattenDistance;
        			
        		}
        	
        	
        		
      
        	/*	System.out.println("currentXDistance: " + currentXDistance);
        		System.out.println("currentYDistance: " + currentYDistance);
        		
        		System.out.println("Manhattan Distance: " + (currentXDistance + currentYDistance));*/
        		
        		
        		
        	}
            
        	//System.out.println("lowest manhattan distance: " + manhattendist);
            System.out.println(initialtoNodeDistance(newState));
            
            
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

     public static void main(String[] args) throws Exception{
        GameState state = args.length==0?new GameState("src/levels/level6.txt"):new GameState(args[0]);
        HumanPlayer player = new HumanPlayer(state);                
    }
    
}
