package sokoban;

import java.util.List;

public class MyGameState extends GameState{

	private MyGameState parentState;
	private int f;
	private int g;
	private int h;
	
	
	
	
	
	
	
	
	
	public MyGameState(String filename) throws Exception {
		super(filename);
		// TODO Auto-generated constructor stub
	}
	
	public void setParent(MyGameState state){
		this.parentState = state;
		
	}
	
	public void setF(int newF){
		this.f = newF;
	}

	public void setG(int newG){
		
		this.g = newG;
	}
	
	
	public void setH(int newH){
		this.h = newH;
	}

	
	public MyGameState getParent(){
		
		return this.parentState;
		
	}
	public int getF(){
		
		
		return this.f;
	}
	
	public int getG(){
		
		return this.g;
		
	}
	
	public int getH(){
		
		return this.h;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
