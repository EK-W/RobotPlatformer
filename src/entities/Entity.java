package entities;

import java.awt.Color;
import java.util.List;

import debug.Debug;
import debug.Debuggable;
import util.BrightColorMaker;
import geometry.Polygon2D;
import geometry.Vector2D;

public class Entity implements Debuggable{
	/*
	 * Lets change some shit up: Entity should not be an abstract class. Instead, it should get its properties from 
	 * an EntityProperties variable. There should be a new abstract class called SpecialEntityActions, and it should have
	 * the before/after collision functions. By default, entities' SpecialEntityAction should be one who's functions 
	 * are the same as the current ones.
	 * 
	 * There should also be a class called "scheduled movement" or something that, when added to an entity, will move the 
	 * entity to a new location over time.
	 */
	
	
	/*
	 * The bug starts to occur when the xVel is greater than half the AABB's width. Probably because of the EPA
	 */
	protected Polygon2D bounds;
	
	protected Vector2D velocity;
	
	protected EntityProperties properties;
	
	protected Color color;
	
	public Entity(Polygon2D bounds, EntityProperties properties){
		this(bounds, new Vector2D(0, 0), properties);
	}
	
	public Entity(Polygon2D bounds, Vector2D velocity, EntityProperties properties){
		this.bounds = bounds;
		this.velocity = velocity;
		this.properties = properties;
		this.color = BrightColorMaker.getRandomColor();
		Debug.add(this);
	}
	
	//Returns an entity to allow for chaining
	public Entity setColor(Color c){
		color = c;
		return this;
	}
	
	//timeMult is for future support of non-constant/non-limited framerates
	public void beforeCollision(double timeMult){
		bounds.translate(velocity.multiply(timeMult));
	}
	
	public void afterCollision(double timeMult){
		velocity.set(velocity.add(properties.gravityMult.multiply(bounds.getArea() * timeMult)));
		//This is probably not the best way to factor in the timeMult but I'm fresh out of fucks to give.
		velocity.set(velocity.subtract(
				velocity.subtract(velocity.multiply(properties.accelerationMult)).multiply(timeMult)));
		//velocity.set(velocity.multiply(accelerationMult));
	}
	
	public Polygon2D getBounds(){
		return bounds;
	}
	
	public Vector2D getVelocity(){
		return velocity;
	}
	
	public EntityProperties getProperties(){
		return properties;
	}
	
	public Color getColor(){
		return color;
	}
	
	@Override
	public void addDebugInfo(List<String> addTo){
		//Almost the same as the default toString method
		addTo.add(this.getClass().getName() + ": @" + Integer.toHexString(this.hashCode()));
		addTo.add("Velocity: " + this.velocity.multiply(100000).round().divide(100000));
		addTo.add("Bounds: " + this.bounds.toString());
		addTo.add("Area: " + Math.round(this.bounds.getArea()) + 
				" Acceleration due to gravity: " + this.properties.gravityMult.multiply(this.bounds.getArea()));
		properties.addDebugInfo(addTo);
		addTo.add("");
	}
}
