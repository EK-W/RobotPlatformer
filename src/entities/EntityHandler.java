package entities;

import geometry.Polygon2D;
import geometry.Vector2D;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import debug.Debug;
import main.Main;
import util.Sorting;

public class EntityHandler {
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	
	//Who says this needs to be a singleton? Maybe there's a unique entityHandler for every level!
	public EntityHandler(){
		//Add walls on the edges of the screens
		double width = Main.screenSize.getWidth();
		double height = Main.screenSize.getHeight();
		Entity west = new Entity(new Polygon2D(new Vector2D(-100, 0), new Vector2D(0, 0), 
				new Vector2D(0, height), new Vector2D(-100, height)),EntityProperties.WALL);
		Entity east = new Entity(new Polygon2D(new Vector2D(width, 0), new Vector2D(width + 100, 0),
				new Vector2D(width + 100, height), new Vector2D(width, height)), EntityProperties.WALL);
		Entity north = new Entity(new Polygon2D(new Vector2D(0, 0), new Vector2D(0, -100),
				new Vector2D(width, -100), new Vector2D(width, 0)), EntityProperties.WALL);
		Entity south = new Entity(new Polygon2D(new Vector2D(0, height + 100), new Vector2D(0, height),
				new Vector2D(width, height), new Vector2D(width, height + 100)), EntityProperties.WALL);
		entities.add(west);
		Debug.remove(west);
		entities.add(east);
		Debug.remove(east);
		entities.add(north);
		Debug.remove(north);
		entities.add(south);
		Debug.remove(south);
	}
	
	public void update(){
		for(int i = 0; i < entities.size(); i++){
			entities.get(i).beforeCollision(1);
		}
		detectCollisions();
		for(int i = 0; i < entities.size(); i++){
			entities.get(i).afterCollision(1);
		}
		
	}
	
	private void detectCollisions(){
		//Insertionsort is best because it works well with nearly sorted lists
		Sorting.insertionSort(entities, (Entity e) -> {return e.getBounds().AABB().getX();});
		LinkedList<Entity> toCheck = new LinkedList<Entity>();
		for(Entity i: entities){
			Iterator<Entity> iter = toCheck.iterator();
			while(iter.hasNext()){
				Entity j = iter.next();
				if(j.getBounds().AABB().getMaxX() < i.getBounds().AABB().getMinX()){
					iter.remove();
				} else if(j.getBounds().AABB().intersects(i.getBounds().AABB()) && 
						(i.getProperties().pushable || j.getProperties().pushable)){
					Vector2D intersection = Polygon2D.intersection(i.getBounds(), j.getBounds());
					if(intersection != null){
						dealWithCollision(intersection, i, j);
					}
				}
			}
			toCheck.add(i);
		}
	}
	private void dealWithCollision(Vector2D intersection, Entity a, Entity b){
		//This is a clever little thing.
		double divisor = (a.getProperties().pushable? 1 : 0) + (b.getProperties().pushable? 1 : 0);
		if(a.getProperties().pushable){
			a.getBounds().translate(intersection.divide(divisor).negate());
		}
		if(b.getProperties().pushable){
			b.getBounds().translate(intersection.divide(divisor));
		}
		changeVelocities(a, b);
	}
	private void changeVelocities(Entity a, Entity b){
		
	}
	
	public void addEntity(Entity e){
		if(!entities.contains(e)){
			entities.add(e);
		}
	}
	
	public void paintEntities(Graphics2D g){
		for(Entity i: entities){
			g.setColor(i.getColor());
			g.fill(i.getBounds().toPolygon());		
		}
	}
}
