import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVTools {

    private static final char DEFAULT_SEPARATOR = ',';
    private static final char DEFAULT_QUOTE = '"';

    public static Item[] getFacultyList(){
        
        Item[] facultyList = null;
        String csvFile = "./data/faculty.csv";
    	try {
    		scanner = new Scanner(new File(csvFile));
            facultyList = new Item[Integer.parseInt(scanner.nextLine())];
            scanner.nextLine();
            int counter = 0;
            while (scanner.hasNext()) {
                List<String> line = parseLine(scanner.nextLine());
                int tempChildren[] = new int[line.size()-3];
                for(int i=3, j=0;i < line.size();i++, j++) {
                    tempChildren[j] = Integer.parseInt(line.get(i));
                }
                facultyList[counter] = new Item("faculty", line.get(1), Integer.parseInt(line.get(0)), line.get(2), tempChildren);
                counter++;
            }
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(null != scanner) {
                    scanner.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    	return facultyList;

    }
    
    public static Item findItem(String type, String name) {
    	
    	String csvFile = "";
    	Item result = null;
    	Scanner scanner = null;
    	if (type == "faculty") {
            csvFile = "./data/faculty.csv";
        }else if (type == "department") {
            csvFile = "./data/department.csv";
        }else{
            csvFile = "./data/program.csv";
        }
    	try {
    		scanner = new Scanner(new File(csvFile));
            scanner.nextLine();scanner.nextLine();  //delete first two lines
            while (scanner.hasNext()) {
                List<String> line = parseLine(scanner.nextLine());
                if(line.get(1).equals(name)){
                    int tempChildren[] = new int[line.size()-3];
                    for(int i=3, j=0;i < line.size();i++, j++) {
                    	tempChildren[j] = Integer.parseInt(line.get(i));
                    }
                    result = new Item("faculty", line.get(1), Integer.parseInt(line.get(0)), line.get(2), tempChildren);
                }
            }
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(null != scanner) {
                    scanner.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    	return result;
    	
    }
    
    public static Item findItem(String type, int ID) {
    	
    	String csvFile = "";
    	Item result = null;
    	Scanner scanner = null;
    	if (type == "faculty") {
            csvFile = "./data/faculty.csv";
        }else if (type == "department") {
            csvFile = "./data/department.csv";
        }else{
            csvFile = "./data/program.csv";
        }
    	try {
    		scanner = new Scanner(new File(csvFile));
            scanner.nextLine();scanner.nextLine();  //delete first two lines
            while (scanner.hasNext()) {
                List<String> line = parseLine(scanner.nextLine());
                if(Integer.parseInt(line.get(0)) == ID){
                	 int tempChildren[] = new int[line.size()-3];
                     for(int i=3, j=0;i<line.size();i++, j++) {
                     	tempChildren[j] = Integer.parseInt(line.get(i));
                     }
                     result = new Item("faculty", line.get(1), Integer.parseInt(line.get(0)), line.get(2), tempChildren);
                }
            }
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(null != scanner) {
                    scanner.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    	return result;
    	
    }
    
    public static Item[] getChildren(String type, String name){
    	Item parent = findItem(type, name);
    	Item[] childList = new Item[parent.children.length];
    	String childtype;
    	if(type == "faculty") {
    		childtype = "department";
    	}else {
    		childtype = "program";
    	}
    	for(int i=0;i<parent.children.length;i++) {
    		childList[i] = findItem(childtype, parent.children[i]);
    	}
    	return childList;
    }
    
    public static Item[] getChildren(String type, int ID){
    	Item parent = findItem(type, ID);
    	Item[] childList = new Item[parent.children.length];
    	String childtype;
    	if(type == "faculty") {
    		childtype = "department";
    	}else {
    		childtype = "program";
    	}
    	for(int i=0;i<parent.children.length;i++) {
    		childList[i] = findItem(childtype, parent.children[i]);
    	}
    	return childList;
    }
    
    public static Item getParent(String type, String name) {
    	Item parent = null;
    	String parenttype = null;
    	if(type == "department") {
    		parenttype = "faculty";
    	}else {
    		parenttype = "program";
    	}
    	Item child = findItem(type,name);
    	parent = findItem(parenttype, child.ID/100);
    	return parent;
    }
    
    public static Item getParent(String type, int ID) {
    	Item parent = null;
    	String parenttype = null;
    	if(type == "department") {
    		parenttype = "faculty";
    	}else {
    		parenttype = "program";
    	}
    	Item child = findItem(type, ID);
    	parent = findItem(parenttype, child.ID/100);
    	return parent;
    }
    
    public static void addNewItem(Item newitem) {
    	String csvFile = "./data/"+newitem.type+".csv";
    	Scanner scanner = null;
    	File fileio = null;
    	File filepast = new File(csvFile);
    	FileOutputStream writer = null;
    	try {
    		scanner = new Scanner(filepast);
    		fileio = new File("./data/"+newitem.type+".temp");
    		fileio.createNewFile();
    		String tempstr = null;
            writer = new FileOutputStream(fileio);
            // “/r/n" for windows... comment for linux and mac 
    		writer.write((Integer.parseInt(scanner.nextLine())+1+"\r\n").getBytes());
    		writer.write((scanner.nextLine()+"\r\n").getBytes());
            while(true) {
            	if(!scanner.hasNext()) {
            		writer.write(("\""+(newitem.ID)+"\",\""+newitem.name+"\",\""+newitem.description+"\",\"").getBytes());
                	for(int i=0;i<newitem.children.length;i++) {
                		if(i != (newitem.children.length-1)) {
                			writer.write((newitem.children[i]+"\",\"").getBytes());
                		}else {
                			writer.write((newitem.children[i]+"\"").getBytes());
                		}
                	}
                	break;
            	}
            	tempstr = scanner.nextLine();
            	List<String> line = parseLine(tempstr);
                if(Integer.parseInt(line.get(0))>newitem.ID) {
                	writer.write(("\""+(newitem.ID)+"\",\""+newitem.name+"\",\""+newitem.description+"\",\"").getBytes());
                	for(int i=0;i<newitem.children.length;i++) {
                		if(i != (newitem.children.length-1)) {
                			writer.write((newitem.children[i]+"\",\"").getBytes());
                		}else {
                			writer.write((newitem.children[i]+"\"\r\n").getBytes());
                		}
                	}
                	writer.write((tempstr+"\r\n").getBytes());
                	break;
                }
                writer.write((tempstr+"\r\n").getBytes());
            }
            while (scanner.hasNext()) {
            	writer.write((scanner.nextLine()+"\r\n").getBytes());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(null != scanner && null != writer) {
                    scanner.close();
                    writer.close();
                    filepast.delete();
                    fileio.renameTo(filepast);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public static List<String> parseLine(String cvsLine) {
        return parseLine(cvsLine, DEFAULT_SEPARATOR, DEFAULT_QUOTE);
    }

    public static List<String> parseLine(String cvsLine, char separators) {
        return parseLine(cvsLine, separators, DEFAULT_QUOTE);
    }

    public static List<String> parseLine(String cvsLine, char separators, char customQuote) {

        List<String> result = new ArrayList<>();

        //if empty, return!
        if (cvsLine == null ) {
            return result;
        }

        if (customQuote == ' ') {
            customQuote = DEFAULT_QUOTE;
        }

        if (separators == ' ') {
            separators = DEFAULT_SEPARATOR;
        }

        StringBuffer curVal = new StringBuffer();
        boolean inQuotes = false;
        boolean startCollectChar = false;
        boolean doubleQuotesInColumn = false;

        char[] chars = cvsLine.toCharArray();

        for (char ch : chars) {

            if (inQuotes) {
                startCollectChar = true;
                if (ch == customQuote) {
                    inQuotes = false;
                    doubleQuotesInColumn = false;
                } else {

                    //Fixed : allow "" in custom quote enclosed
                    if (ch == '\"') {
                        if (!doubleQuotesInColumn) {
                            curVal.append(ch);
                            doubleQuotesInColumn = true;
                        }
                    } else {
                        curVal.append(ch);
                    }

                }
            } else {
                if (ch == customQuote) {

                    inQuotes = true;

                    //Fixed : allow "" in empty quote enclosed
                    if (chars[0] != '"' && customQuote == '\"') {
                        curVal.append('"');
                    }

                    //double quotes in column will hit this!
                    if (startCollectChar) {
                        curVal.append('"');
                    }

                } else if (ch == separators) {

                    result.add(curVal.toString());

                    curVal = new StringBuffer();
                    startCollectChar = false;

                } else if (ch == '\r') {
                    //ignore LF characters
                    continue;
                } else if (ch == '\n') {
                    //the end, break!
                    break;
                } else {
                    curVal.append(ch);
                }
            }

        }

        result.add(curVal.toString());

        return result;
    }

}