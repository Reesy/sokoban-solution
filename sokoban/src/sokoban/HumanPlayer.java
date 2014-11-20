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
    
    public HumanPlayer(GameState state){
        this.state=state;
        display = new GameDisplay(state);
        display.addKeyListener(this);
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
            System.out.println(state.isGoalState());
            System.out.println(state.playerCol + " " + state.playerRow); 
       //     int test = state.goalPositions(0);
            
            int test;
            int test2;
       
  
            test = state.goalPositions.get(0).col;
            test2 = state.goalPositions.get(0).row;
            System.out.println(test + " " + test2);
            display.updateState(newState);
        }
    }

    
    public void keyReleased(KeyEvent e) {        
    }

     public static void main(String[] args) throws Exception{
        GameState state = args.length==0?new GameState("src/levels/level5.txt"):new GameState(args[0]);
        HumanPlayer player = new HumanPlayer(state);                
    }
    
}
