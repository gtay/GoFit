package entities;

public class Exercise {
	private int id;
	private String name;
	private int weight;
	private int sets;
	private int reps;
	
	public int getID() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public int getSets() {
		return sets;
	}
	
	public int getReps() {
		return reps;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public void setSets(int sets) {
		this.sets = sets;
	}
	
	public void setReps(int reps) {
		this.reps = reps;
	}
	
	public void setID(int id) {
		this.id = id;
	}
}
