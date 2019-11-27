package com.yybt.datastructure.tree.RBTree;


/**
  * @ClassName: MyRBTree
  * @Description: 红黑树。目前缺少删除和删除的平衡调整，希望大神补充
  * @author liuzehong
 * @param <T>
 **/
public class MyRBTree<T  extends Comparable<T>> {
	
    private static final boolean RED   = true;
    
	private static final boolean BLACK = false;
	
	private  TreeNode<T>  rootNode;        //根节点
	
	
	public TreeNode<T> getRootNode() {
		return rootNode;
	}
	
	public void setRootNode(TreeNode<T> rootNode) {
		this.rootNode = rootNode;
	}
	
	/**
	 * 前序遍历
	 * @param treeNode
	 */
	public  void  preOrder(TreeNode<T> treeNode) {
		if(treeNode!=null) {
			System.out.print(treeNode.getData()+"  "+treeNode.getColor());
			preOrder(treeNode.getLeftChild());
			preOrder(treeNode.getRightChild());
		}
	}
	/**
	 * 中序遍历
	 * @param treeNode
	 */
	public void midOrder(TreeNode<T> treeNode) {
		
		if(treeNode!=null) {
			midOrder(treeNode.getLeftChild());
			System.out.print(treeNode.getData()+"  "+treeNode.getColor());
			midOrder(treeNode.getRightChild());
		}
	} 
	
	/**
	 * 后序遍历
	 */
	public void postOrder(TreeNode<T> treeNode) {

		if(treeNode!=null) {
			midOrder(treeNode.getLeftChild());
			midOrder(treeNode.getRightChild());
			System.out.print(treeNode.getData()+"  "+treeNode.getColor());
		}
	}
	
	/**
	 * 左旋
	 *        a                 a
	 *      /                  /
	 *     X                  Y 
	 *    /  \              /  \
	 *   c    Y            X    f
	 *       / \          / \   
	 *      e   f        c   e    
	 * 
	 */
	public void leftRotate(TreeNode<T> X) {
		//如图所示获取Y
		TreeNode<T> Y=X.getRightChild();
		//如果Y的左孩子不为空
		if (Y.getLeftChild()!=null) {
			//将X的右孩子设置为Y的左孩子
			X.setRightChild(Y.getLeftChild());
			//对Y进行处理，分三种情况
			//1.如果 X的父亲为null，则将Y设为根节点
			if (X.getParentNode()==null) {
				this.rootNode=Y;
			}
			//2.如果 X是它父节点的左孩子，则将Y设为X的父节点的左孩子”
			if (X.getParentNode().getLeftChild()==X) {
				X.getParentNode().setLeftChild(Y);
			}
			//3.如果 X是它父节点的右孩子，则将Y设为X的父节点的右孩子
			if (X.getParentNode().getRightChild()==X) {
				X.getParentNode().setRightChild(Y);
			}
			//将 X的父亲设为 Y
			X.setParentNode(Y);
		}
	}
	/**
	 * 右边旋
	 *        a                 a
	 *      /                  /
	 *     Y                  X
	 *    / \                / \
	 *   X   b              e   Y
	 *  / \                    / \   
	 * e   f                  f   b   
	 * 
	 */
	public void rightRotate(TreeNode<T> X) {
		//如图所示获取Y
		TreeNode<T> Y=X.getParentNode();
		//如果X的右孩子不为空
		if (Y.getRightChild()!=null) {
			//将Y的左孩子设置为X的左孩子
			Y.setLeftChild(X.getRightChild());
			//对X进行处理，分三种情况
			//1.如果 Y的父亲为null，则将X设为根节点
			if (Y.getParentNode()==null) {
				this.rootNode=Y;
			}
			//2.如果 Y是它父节点的左孩子，则将X设为Y的父节点的左孩子”
			if (Y.getParentNode().getLeftChild()==X) {
				Y.getParentNode().setLeftChild(Y);
			}
			//3.如果 Y是它父节点的右孩子，则将Y设为X的父节点的右孩子
			if (X.getParentNode().getRightChild()==X) {
				X.getParentNode().setRightChild(Y);
			}
			//将 Y的父亲设为 X
			Y.setParentNode(X);
		}
	}
	/**
	 * 着黑色
	 * @param node
	 */
	private void setBlack(TreeNode<T> node) {
        if (node!=null)
            node.setColor(BLACK);
    }
	/**
	 * 着红色
	 * @param node
	 */
	private void setRed(TreeNode<T> node) {
        if (node!=null)
        	node.setColor(RED);
    }
    
    private boolean isRed(TreeNode<T> node) {
        return node!=null&&node.getColor()==RED;
    }
    @SuppressWarnings("unused")
	private boolean isBlack(TreeNode<T> node) {
        return !isRed(node);
    }
	
	/**
	 * 插入元素
	 */
	@SuppressWarnings("unused")
	private void insert(TreeNode<T> node) {
		//1.二叉树插入节点
		TreeNode<T> x = this.rootNode;
        TreeNode<T> y = null;
        int result;
        //从根节点开始循环比较
        while (x != null) {
            y = x;
            result= node.getData().compareTo(x.getData());
            if (result < 0) {//插入的小，往左走
                x = x.getLeftChild();
            }else {          //插入的大，往右走
                x = x.getRightChild();
            }
        }
        //将y节点设置为插入节点的双亲节点
        node.setParentNode(y); 
        if (y==null) {
          this.rootNode = node;
           return;
        }
    	result = node.getData().compareTo(y.getData());
        if (result < 0) {
            y.setLeftChild(node); 
        }else {
            y.setRightChild(node);
        }
        //2.设置节点的颜色为红色,使其满足从一个节点到该节点的子孙节点的所有路径上包含相同数目的黑节点。
        node.setColor(RED);
        //重新调整二叉树
        insertFixUp(node);
	}
	/**
	 *  1、当插入的节点是根节点时，直接涂黑即可； 
	 *  2、当要插入的节点的父节点是黑色的时候
	 *	3、如果要插入的节点的父节点是红色且父节点是祖父节点的左支的时候。 
	 *      这个要分两种情况，一种是叔叔节点为黑的情况，一种是叔叔节点为红的情况。 
	 *  4、如果要插入的节点的父节点是红色且父节点是祖父节点的右支的时候； 
	 *	          这个时候的情况跟情况3所表述的情况是一个镜像，将情况3的左和右互换一下就可以了。 
	 *  5、如果要插入的节点的父节点是红色并且叔叔节点也为红色，
	 * @param node
	 */
	private void insertFixUp(TreeNode<T> node) {
		 TreeNode<T> parentNode = node.getParentNode();
         if (parentNode != null && isRed(parentNode)) {
        	 TreeNode<T> gprentNode = parentNode.getParentNode();//因为父节点存在且为红，必存在祖父节点
        	// 如果要插入的节点的父节点是红色且父节点是祖父节点的左支的时候。 
             if (parentNode == gprentNode.getLeftChild()) {
            	 TreeNode<T> uncleNode = gprentNode.getRightChild();
            	 //这个要分两种情况，一种是叔叔节点为黑的情况，一种是叔叔节点为红的情况。
                 if (uncleNode != null && isRed(uncleNode)) {
                     setBlack(parentNode);
                     setBlack(uncleNode);
                     setRed(gprentNode);
                     insertFixUp(gprentNode);
                 } else {
                     if (parentNode.getRightChild() == node) {
                         leftRotate(parentNode);
                         insertFixUp(parentNode);
                     } else if (parentNode.getLeftChild() == node) {
                         setBlack(parentNode);
                         setRed(gprentNode);
                         rightRotate(gprentNode);
                     }
                 }
             } //如果要插入的节点的父节点是红色且父节点是祖父节点的右支的时候；
             else {
            	 TreeNode<T> uncleNode = gprentNode.getLeftChild();
                 if (uncleNode != null && isRed(uncleNode)) {
                    setBlack(parentNode); 
                    setBlack(uncleNode); 
                    setRed(gprentNode);
                     insertFixUp(gprentNode);
                 } else {
                     if (parentNode.getLeftChild() == node) {
                         rightRotate(parentNode);
                         insertFixUp(parentNode);
                     } else if (parentNode.getRightChild() == node) {
                         setBlack(parentNode);
                         setRed(gprentNode);
                         leftRotate(gprentNode);
                     }
                 }
             }
         }
         //当插入的节点是根节点时，直接涂黑即可； 
		 setBlack(rootNode);
	 }
	
	/**
	 * @return 
	 * 
	 */
	public  TreeNode<T> getRootNode(TreeNode<T> node) {
		// TODO Auto-generated method stub
          if (node==null) 
			return node;
          while (node.getParentNode()!=null) {
        	  TreeNode<T> parent=  node.getParentNode();
        	  node= parent;
		}
		return node;
          
	}
	
	
	
}