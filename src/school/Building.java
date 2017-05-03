package school;

public class Building implements Displayable{

	private int x;
	private int y;
	private String name;
	
	public Building(String name, int x, int y){
		this.name = name;
		this.x = x;
		this.y = y;
	}
	
	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return x;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return y;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

}
