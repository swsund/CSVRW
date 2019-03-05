public class Item {
	protected String type;
	protected String name;
	protected int ID;
	protected String description;
	protected int[] children;
	
	
	public Item(String type, String name, int ID, String description, int[] children) {
	//	super();
		this.type = type;
		this.name = name;
		this.ID = ID;
		this.description = description;
		
		this.children = new int[children.length];
		for(int i = 0;i<children.length;i++) {
			this.children[i] = children[i];
		}
	}
	


	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
