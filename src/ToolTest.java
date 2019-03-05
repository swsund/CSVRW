

public class ToolTest {

    public static void main(String[] args) {
    	int test[] = {1,2,333,3332};
    	Item newfaculty = new Item("department","Department of TEST2",511,"This is a test example",test);
    	CSVTools.addNewItem(newfaculty);
    }

}