/**
 * 
 * @author chris Lara-Betancourt
 *ICS 240 
 *Summer 2020
 *This class will make node that contains a game and two nodes.
 */
public class GameBTNode {
	private Game data;
	private GameBTNode right;
	private GameBTNode left;
	
	public GameBTNode(Game initialData, GameBTNode initialRight, GameBTNode initialLeft) {
		data = initialData;
		right = initialRight;
		left = initialLeft;
	}

	public Game getData() {
		return data;
	}

	public void setData(Game data) {
		this.data = data;
	}

	public GameBTNode getRight() {
		return right;
	}

	public void setRight(GameBTNode right) {
		this.right = right;
	}

	public GameBTNode getLeft() {
		return left;
	}

	public void setLeft(GameBTNode left) {
		this.left = left;
	}
	
	public Game getLeftmostData() {
		if (left == null)
			return data;
		else
			return left.getLeftmostData();
	}
	
	public Game getRightmostData() {
		if (right == null)
			return data;
		else
			return right.getRightmostData();
	}
	
	public boolean isLeaf() {
		return (this.left == null) && (this.right == null);
	}
	
	public static int treeSize(GameBTNode root) {
		if (root == null)
			return 0;
		else
			return 1 + treeSize(root.left) + treeSize(root.right);
	}
	
    public static GameBTNode treeCopy(GameBTNode root) {
		
		if ( root == null)
			return null;
		else {
			GameBTNode leftCopy = treeCopy(root.left);
			GameBTNode rightCopy = treeCopy(root.right);
			return new GameBTNode(root.data,leftCopy,rightCopy);
		}
	}
    
    public void preorderPrint() {
		System.out.print(this.data + "\t");
		if (left != null)
			left.preorderPrint();
		if (right != null)
			right.preorderPrint();
	}
    
    public void postorderPrint() {
		if (left != null)
			left.postorderPrint();
		if (right != null)
			right.postorderPrint();

		System.out.print(this.data + "\t");
	}
    
    public void inorderPrint() {
		if (left != null)
			left.inorderPrint();
		System.out.print(data + "\t");
		if (right != null)
			right.inorderPrint();
	}
    
    public GameBTNode removeRightmost() {

		if (right == null)
			// the right most node is at the root becuase there is no right child
			return left;
		else {
			// a recursive call removes the rightmost node if my own right child
			right = right.removeRightmost();
			return this;
		}

	}
    /**
     * Will remove the left most node.
     * @return
     */
    public GameBTNode removeLeftmost() {

		if (left == null)
			// the right most node is at the root becuase there is no right child
			return right;
		else {
			// a recursive call removes the rightmost node if my own right child
			left = left.removeLeftmost();
			return this;
		}

	}
    /**
     * Will add all the element in the binary tree.
     * @return will return the sum of all the nodes.
     */
    public int treeSum() {
    	int left = 0;
        int right =0;
		if (isLeaf())
			return this.getData().getCopies();
		else
			if(this.getLeft()!= null) {
				left = getLeft().treeSum();
			}if(this.getRight()!=null) {
				right = getRight().treeSum();
			}
			return this.getData().getCopies() + left + right;
	}
    
	
	

}
