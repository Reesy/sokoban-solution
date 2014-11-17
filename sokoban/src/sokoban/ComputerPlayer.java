/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban;

import java.util.List;

/**
 *
 * @author steven
 */
public class ComputerPlayer {
    
    GameDisplay display;
    
    public ComputerPlayer(GameState state){
        display = new GameDisplay(state);
        // your code goes here ...
    }
    
    public List<GameState> getSolution(){
        // your code goes here ...
        return null;
    }
    
    public void showSolution(List<GameState> solution) {               
        for (GameState st : solution) {            
            display.updateState((GameState) st);
            try {
                Thread.sleep(500); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) throws Exception{
        GameState state = args.length==0?new GameState("src/levels/level3.txt"):new GameState(args[0]);        
        long t1 = System.currentTimeMillis();
        ComputerPlayer player = new ComputerPlayer(state);  
        List<GameState> solution = player.getSolution();
        long t2 = System.currentTimeMillis();
        System.out.println("Time: " + (t2-t1));
        player.showSolution(solution);
    }
     
}
