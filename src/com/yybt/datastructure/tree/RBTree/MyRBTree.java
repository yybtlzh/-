package com.yybt.datastructure.tree.RBTree;


/**
  * @ClassName: MyRBTree
  * @Description: �������Ŀǰȱ��ɾ����ɾ����ƽ�������ϣ�����񲹳�
  * @author liuzehong
 * @param <T>
 **/
public class MyRBTree<T  extends Comparable<T>> {
	
    private static final boolean RED   = true;
    
	private static final boolean BLACK = false;
	
	private  TreeNode<T>  rootNode;        //���ڵ�
	
	
	public TreeNode<T> getRootNode() {
		return rootNode;
	}
	
	public void setRootNode(TreeNode<T> rootNode) {
		this.rootNode = rootNode;
	}
	
	/**
	 * ǰ�����
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
	 * �������
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
	 * �������
	 */
	public void postOrder(TreeNode<T> treeNode) {

		if(treeNode!=null) {
			midOrder(treeNode.getLeftChild());
			midOrder(treeNode.getRightChild());
			System.out.print(treeNode.getData()+"  "+treeNode.getColor());
		}
	}
	
	/**
	 * ����
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
		//��ͼ��ʾ��ȡY
		TreeNode<T> Y=X.getRightChild();
		//���Y�����Ӳ�Ϊ��
		if (Y.getLeftChild()!=null) {
			//��X���Һ�������ΪY������
			X.setRightChild(Y.getLeftChild());
			//��Y���д������������
			//1.��� X�ĸ���Ϊnull����Y��Ϊ���ڵ�
			if (X.getParentNode()==null) {
				this.rootNode=Y;
			}
			//2.��� X�������ڵ�����ӣ���Y��ΪX�ĸ��ڵ�����ӡ�
			if (X.getParentNode().getLeftChild()==X) {
				X.getParentNode().setLeftChild(Y);
			}
			//3.��� X�������ڵ���Һ��ӣ���Y��ΪX�ĸ��ڵ���Һ���
			if (X.getParentNode().getRightChild()==X) {
				X.getParentNode().setRightChild(Y);
			}
			//�� X�ĸ�����Ϊ Y
			X.setParentNode(Y);
		}
	}
	/**
	 * �ұ���
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
		//��ͼ��ʾ��ȡY
		TreeNode<T> Y=X.getParentNode();
		//���X���Һ��Ӳ�Ϊ��
		if (Y.getRightChild()!=null) {
			//��Y����������ΪX������
			Y.setLeftChild(X.getRightChild());
			//��X���д������������
			//1.��� Y�ĸ���Ϊnull����X��Ϊ���ڵ�
			if (Y.getParentNode()==null) {
				this.rootNode=Y;
			}
			//2.��� Y�������ڵ�����ӣ���X��ΪY�ĸ��ڵ�����ӡ�
			if (Y.getParentNode().getLeftChild()==X) {
				Y.getParentNode().setLeftChild(Y);
			}
			//3.��� Y�������ڵ���Һ��ӣ���Y��ΪX�ĸ��ڵ���Һ���
			if (X.getParentNode().getRightChild()==X) {
				X.getParentNode().setRightChild(Y);
			}
			//�� Y�ĸ�����Ϊ X
			Y.setParentNode(X);
		}
	}
	/**
	 * �ź�ɫ
	 * @param node
	 */
	private void setBlack(TreeNode<T> node) {
        if (node!=null)
            node.setColor(BLACK);
    }
	/**
	 * �ź�ɫ
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
	 * ����Ԫ��
	 */
	@SuppressWarnings("unused")
	private void insert(TreeNode<T> node) {
		//1.����������ڵ�
		TreeNode<T> x = this.rootNode;
        TreeNode<T> y = null;
        int result;
        //�Ӹ��ڵ㿪ʼѭ���Ƚ�
        while (x != null) {
            y = x;
            result= node.getData().compareTo(x.getData());
            if (result < 0) {//�����С��������
                x = x.getLeftChild();
            }else {          //����Ĵ�������
                x = x.getRightChild();
            }
        }
        //��y�ڵ�����Ϊ����ڵ��˫�׽ڵ�
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
        //2.���ýڵ����ɫΪ��ɫ,ʹ�������һ���ڵ㵽�ýڵ������ڵ������·���ϰ�����ͬ��Ŀ�ĺڽڵ㡣
        node.setColor(RED);
        //���µ���������
        insertFixUp(node);
	}
	/**
	 *  1��������Ľڵ��Ǹ��ڵ�ʱ��ֱ��Ϳ�ڼ��ɣ� 
	 *  2����Ҫ����Ľڵ�ĸ��ڵ��Ǻ�ɫ��ʱ��
	 *	3�����Ҫ����Ľڵ�ĸ��ڵ��Ǻ�ɫ�Ҹ��ڵ����游�ڵ����֧��ʱ�� 
	 *      ���Ҫ�����������һ��������ڵ�Ϊ�ڵ������һ��������ڵ�Ϊ�������� 
	 *  4�����Ҫ����Ľڵ�ĸ��ڵ��Ǻ�ɫ�Ҹ��ڵ����游�ڵ����֧��ʱ�� 
	 *	          ���ʱ�����������3�������������һ�����񣬽����3������һ���һ�¾Ϳ����ˡ� 
	 *  5�����Ҫ����Ľڵ�ĸ��ڵ��Ǻ�ɫ��������ڵ�ҲΪ��ɫ��
	 * @param node
	 */
	private void insertFixUp(TreeNode<T> node) {
		 TreeNode<T> parentNode = node.getParentNode();
         if (parentNode != null && isRed(parentNode)) {
        	 TreeNode<T> gprentNode = parentNode.getParentNode();//��Ϊ���ڵ������Ϊ�죬�ش����游�ڵ�
        	// ���Ҫ����Ľڵ�ĸ��ڵ��Ǻ�ɫ�Ҹ��ڵ����游�ڵ����֧��ʱ�� 
             if (parentNode == gprentNode.getLeftChild()) {
            	 TreeNode<T> uncleNode = gprentNode.getRightChild();
            	 //���Ҫ�����������һ��������ڵ�Ϊ�ڵ������һ��������ڵ�Ϊ��������
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
             } //���Ҫ����Ľڵ�ĸ��ڵ��Ǻ�ɫ�Ҹ��ڵ����游�ڵ����֧��ʱ��
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
         //������Ľڵ��Ǹ��ڵ�ʱ��ֱ��Ϳ�ڼ��ɣ� 
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