package entities;

import main.KeyTracker;
import geometry.Polygon2D;
import geometry.Vector2D;

public class Player extends Entity{
	private static double Horizontal_Movement_Acceleration = 0.25;
	public Player(Polygon2D bounds, EntityProperties properties) {
		super(bounds, properties);
	}
	
	public Player(Polygon2D bounds, Vector2D velocity, EntityProperties properties){
		super(bounds, properties);
	}
	
	@Override
	public void beforeCollision(double timeMult){
		if(KeyTracker.LEFT){
			velocity.set(velocity.subtract(new Vector2D(Horizontal_Movement_Acceleration, 0)));
		}
		if(KeyTracker.RIGHT){
			velocity.set(velocity.add(new Vector2D(Horizontal_Movement_Acceleration, 0)));
		}
//		if(KeyTracker.JUMP && ){
//			
//		}
		bounds.translate(velocity.multiply(timeMult));
	}
}
