package solution_to_algo_problems;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

class TextInput {
    private String value = "";
    public void add(char c){
        char[] original = value.toCharArray();
        char[] charArray = Arrays.copyOf(original, original.length + 1);
        charArray[original.length] = c;
        this.value = new String(charArray);
    }
    
    public String getValue(){
        return this.value;
    }
}

class NumericInput extends TextInput{
	@Override
	public void add(char c){
		if(Character.isDigit(c)){
			super.add(c);
		}
	}
}
class BinaryTreeNode {
    public int value;
    public BinaryTreeNode left, right;

    public BinaryTreeNode(int value, BinaryTreeNode left, BinaryTreeNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }
}

class BinarySearchTree {
    public static boolean contains(BinaryTreeNode root, int value) {
    	BinaryTreeNode currentNode = root;
    	
    	if(currentNode == null)
    		return false;
    	if(currentNode.value == value){
        	return true;
        }else if(currentNode.value < value){
        	return contains(currentNode.right, value);
        }else{
        	return contains(currentNode.left, value);
        }
    }
}

class Folders {
    public static Collection<String> folderNames(String xml, char startingLetter) throws Exception {
    	  throw new UnsupportedOperationException("Waiting to be implemented.");
    }
}

public class HCCodingTest {

	@Test
	public void test() {
		TextInput input = new NumericInput();
		input.add('1');
		input.add('a');
		input.add('0');
		System.out.println(input.getValue());
	}
	@Test
	public void testReverseString(){
		String reversed = new StringBuilder("Deleveled").reverse().toString();
		assertTrue("deleveled".equalsIgnoreCase(reversed));
	}
	
	@Test
	public void testBST(){
		BinaryTreeNode n1 = new BinaryTreeNode(1, null, null);
        BinaryTreeNode n3 = new BinaryTreeNode(3, null, null);
        BinaryTreeNode n2 = new BinaryTreeNode(2, n1, n3);
        
        System.out.println(BinarySearchTree.contains(n2, 3));
	}
	
	@Test
	public void testXmlParsing() throws Exception{
		String xml =
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                "<folder name=\"c\">" +
                    "<folder name=\"program files\">" +
                        "<folder name=\"uninstall information\" />" +
                    "</folder>" +
                    "<folder name=\"users\" />" +
                "</folder>";

        Collection<String> names = Folders.folderNames(xml, 'u');
        for(String name: names)
            System.out.println(name);

	}
}
