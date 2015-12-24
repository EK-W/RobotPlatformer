package entities;

import java.util.List;

import debug.Debuggable;
import geometry.Vector2D;

public class EntityProperties implements Debuggable{
	//Because accelerationMult probably isn't gonna be different for most of the presets
	public static final Vector2D DEFAULT_ACCELERATION_MULT = new Vector2D(0.975, 0.975);
	public static final Vector2D DEFAULT_GRAVITY = new Vector2D(0, 0.0001);
	//Just to save space
	private static final Vector2D ZERO = new Vector2D(0, 0);
	/**
	 * For things that shouldn't, under any circumstances, move from their original positions
	 */
	public static final EntityProperties WALL = new EntityProperties(false, false, ZERO, ZERO);
	/**
	 * For moving platforms that don't care about going through walls.
	 */
	//accelerationMult is (1, 1) because there's no need to have platforms decelerate
	public static final EntityProperties MOVING_PLATFORM = new EntityProperties(false, true, new Vector2D(1, 1), ZERO);
	/**
	 * For things that can be pushed/fall/move around. For things like the player and things the player can push
	 */
	public static final EntityProperties MOVABLE_OBJECT 
		= new EntityProperties(true, true, DEFAULT_ACCELERATION_MULT, DEFAULT_GRAVITY);
	public final boolean pushable;
	public final boolean canMove;
	public final Vector2D accelerationMult;
	//Might as well give this an x dimension... who knows? That might be helpful...
	//the speed at which something falls relative to its weight
	public final Vector2D gravityMult;
	
	public EntityProperties(boolean pushable, boolean canMove, Vector2D accelerationMult, Vector2D gravityMult){
		this.pushable = pushable;
		this.canMove = canMove;
		this.accelerationMult = accelerationMult;
		this.gravityMult = gravityMult;
	}

	@Override
	public void addDebugInfo(List<String> addTo) {
		addTo.add(this.getClass().getName() + ": @" + Integer.toHexString(this.hashCode()));
		addTo.add((pushable? "Pushable " : "") + (canMove? "Can_Move " : ""));
		addTo.add("AccelerationMult: " + accelerationMult.toString() + ", GravityMult: " + gravityMult.toString());
	}
}
