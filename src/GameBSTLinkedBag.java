/**
 * Chris Lara-Betancourt 
 * ICS 240 
 * Summer 2020
 * This class will store nodes contains elements of the movie class. 
 */
import java.util.Iterator;

public class GameBSTLinkedBag{
	private GameBTNode root;

	public GameBSTLinkedBag() {
		root = null;
	}
	/**
	 * Will add elements into the BST tree.
	 * @param element is the game the user wants to add into the BST tree.
	 */
    public void add(Game element){
		
		//Create IntBTNode with data = element
		GameBTNode newNode = new GameBTNode(element,null,null);
		
		
		if (root == null) //if the tree is empty, the new node becomes the root
			root = newNode;
		else{
			
			//if the tree is not empty, start from the root and go down the tree 
			GameBTNode cursor = root;
			GameBTNode parentOfCursor = null;
			
			while (cursor != null){
				//need to keep track of parent of the new node
				parentOfCursor = cursor;
				if ( cursor.getData().compareTo(element)>0)
					cursor = cursor.getLeft();
				else
					cursor = cursor.getRight();
			}
			//at this point of time, the new element can be inserted as a child of parent
	
			if (parentOfCursor.getData().compareTo(element)>0)
				parentOfCursor.setLeft(newNode);
			else
				parentOfCursor.setRight(newNode);
		}
		
		
      }
    /**
     * Will let the user know the size of the binary tree.
     * @return the size of the tree.
     */
    public int size() { 
	  return GameBTNode.treeSize(this.root); 
    }
    /**
     * Will search the binary tree to find a game element that matches the title that was passed in.
     * @param title is the  title of the game the user wants to find.
     */
    public void search(String title) {
    	Game compare = new Game(title,0);
    	GameBTNode cursor = root;
    	while (cursor!= null) {
    		if(cursor.getData().equals(compare)) {
    			System.out.println(cursor.getData().toString());
    		}
    		if(cursor.getData().compareTo(compare)>0) {
    			cursor = cursor.getLeft();
    		}else {
    			cursor = cursor.getRight();
    		}
    	}
    }
    /**
     * Will find out how many times a certain game is in the binary tree.
     * @param game is the game the user want to find
     * @return the number of times that element was found.
     */
    public int countOfOccurance(Game game) {
    	GameBTNode cursor = root;
    	int count = 0;
    	while (cursor!= null) {
    		if(cursor.getData().equals(game)) {
    			count += 1;
    		}
    		if(cursor.getData().compareTo(game)>0) {
    			cursor = cursor.getLeft();
    		}else {
    			cursor = cursor.getRight();
    		}
    	}
    	return count;
    }
    /**
     * Will find if the binary tree was a certain game stored in it. With just the title of the game.
     * @param title is the game title to find.
     * @return Will return true or false based if the game was found.
     */
    public boolean contains(String title) {
    	boolean isTrue = false;
    	Game compare = new Game(title,0);
    	GameBTNode cursor = root;
    	while (cursor!= null) {
    		if(cursor.getData().equals(compare)) {
    			isTrue = true;
    			return isTrue;
    		}
    		if(cursor.getData().compareTo(compare)>0) {
    			cursor =cursor.getLeft();
    		}else {
    			cursor = cursor.getRight();
    		}
    	}
    	
    	return isTrue;
    }
    /**
     * Will find the total sum of all the nodes.
     * @return the sum of the tree.
     */
    public int total() {
    	int sum = 0;
    	sum = root.treeSum();
    	return sum;
    	
    }
    /**
     * Will take in a node and two games that will determine the bounds.
     * @param node is the root that will be passed in
     * @param low the game place holder for the lower bounds
     * @param high the game place holder for the higher bounds
     * @return will return all the games in between the two bounds
     */
    public int countRangeAux(GameBTNode node, Game low, Game high) {
    	int count = 0;
    	
    	if(node == null) {
    		return 0;
    	}
    	if(node.getData().compareTo(low)>0 && node.getData().compareTo(high)<0) {
    		count = 1;
    	}
    	return count
    			+countRangeAux(node.getLeft(), low, high)
    			+countRangeAux(node.getRight(),low,high);
    }
    /**
     * Will take in to game to find games that are between them.
     * @param low the lower game bounds
     * @param high the higher game bounds
     * @return will return all the game in between the two games.
     */
    public int countRange(Game low, Game high) {
    	return countRangeAux(root,low,high);
    }
    /**
     * Will remove a game instance from the binary tree.
     * @param target is the game we are trying to remove.
     * @return will return true or false based on if the game was removed.
     */
    public boolean remove(Game target) {
    	GameBTNode cursor = root;
    	GameBTNode parentOfCursor = null;
    	boolean isTrue = true;
    	
    	while(cursor!=null && cursor.getData()!=target) {
    		parentOfCursor = cursor;
    		if(cursor.getData().compareTo(target)>0) {
    			cursor = cursor.getLeft();
    		}else {
    			cursor = cursor.getRight();
    		}
    	}
    	
    	if(cursor == null) 
			return false;
    	else if(cursor.getLeft()==null) {
    		if(cursor== root) {
    			root = cursor.getRight();
    		}
    		else {
    			if(cursor== parentOfCursor.getLeft()) {
    				parentOfCursor.setLeft(cursor.getRight());
    			}else {
    				parentOfCursor.setRight(cursor.getRight());
    			}
    		}
    	}else {
    		cursor.setData(cursor.getLeft().getRightmostData());
			cursor.setLeft(cursor.getLeft().removeRightmost());
    	}
    	
    	return isTrue;
    }
    /**
     * Will display the binary tree from low to high
     */
	public void displayLowToHigh(){
		if (root != null)
			root.inorderPrint();
	}
	
	
	/**
	 * Will display the binary tree from low to high
	 * @param cur is the root bing passed in.
	 */
	private void display(GameBTNode cur){
		if(cur!=null){
			display(cur.getLeft());
			System.out.println(cur.getData().toString());
			display(cur.getRight());
		}
	}

	/**
	 * This method displays all the nodes in a BST tree
	 * The method is also a public method that calls the private display method
	 * from low to high I implemented in a different way.

	 */
	public void display() {
		display(root);
	}
	/**
	 * Will copy all the game element in collection class being passed in.
	 * @param other is the collection class being passed in.
	 * @param cur would be the starting node 
	 */
	public void addAllAux(GameBSTLinkedBag other, GameBTNode cur) {	//bag.addAll(other)
		if(cur!=null){
			addAllAux(other,cur.getLeft());

			this.add(cur.getData());
			
			addAllAux(other, cur.getRight());
		}
		
	}
	
	public void addAll(GameBSTLinkedBag other) {
		this.addAllAux(other, other.root);
	}

	
	

}
