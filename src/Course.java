public class Course extends Item {
	private double hours;
	private double credits;
	private String labinfo;
	private Course prerequisites;
	private Course antirequisites;
	
//	public Course(double hours, double credits, String labinfo, Course prerequisites, Course antirequisites) {
//		// super();
//		this.hours = hours;
//		this.credits = credits;
//		this.labinfo = labinfo;
//		this.prerequisites = prerequisites;
//		this.antirequisites = antirequisites;
//	}

	
//	String type, String name, int ID, String description

	public Course(String name, int ID, String description, double hours, double credits, String labinfo, Course prerequisites, Course antirequisites) {
		super("Course", name, ID, description, null);
		this.hours = hours;
		this.credits = credits;
		this.labinfo = labinfo;
		this.prerequisites = prerequisites;
		this.antirequisites = antirequisites;
}
	 

	


	public double getHours() {
		return hours;
	}


	public void setHours(double hours) {
		this.hours = hours;
	}


	public double getCredits() {
		return credits;
	}


	public void setCredits(double credits) {
		this.credits = credits;
	}


	public String getLabinfo() {
		return labinfo;
	}


	public void setLabinfo(String labinfo) {
		this.labinfo = labinfo;
	}


	public Course getPrerequisites() {
		return prerequisites;
	}


	public void setPrerequisites(Course prerequisites) {
		this.prerequisites = prerequisites;
	}


	public Course getAntirequisites() {
		return antirequisites;
	}


	public void setAntirequisites(Course antirequisites) {
		this.antirequisites = antirequisites;
	}


	@Override
	public String toString() {
		return "Course [hours=" + hours + ", credits=" + credits + ", labinfo=" + labinfo + ", prerequisites="
				+ prerequisites + ", antirequisites=" + antirequisites + "]";
	}


}
