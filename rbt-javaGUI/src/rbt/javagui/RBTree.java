package rbt.javagui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MGTC
 * @param <T>
 */

public class RBTree<T extends Comparable<T>>
{
        public RBTree()
	{
		RBTree = null;
	}
	public TreeNode<T> RBTree;
	
	
        private void DisplayOrderedTree(TreeNode<T> node)
	{
		if(node != null)
		{
			DisplayOrderedTree(node.LeftChildNode);
			if(node.LeafNode == false) {
                            System.out.print(node.orderTree());
                        }
			DisplayOrderedTree(node.RightChildNode);
		}
	}
	public void Display()
	{
		System.out.print("[");
		DisplayOrderedTree(RBTree);
		System.out.print("]");
		System.out.println();
        }


        private void RotateTreeRight(TreeNode<T> NodeToRotateTree)
	{
		TreeNode<T> ParentNode = NodeToRotateTree.LeftChildNode;
		if(NodeToRotateTree.ParentNode != null)
		{
			if(NodeToRotateTree.ParentNode.LeftChildNode == NodeToRotateTree)
			{
				NodeToRotateTree.ParentNode.LeftChildNode = ParentNode;
				ParentNode.ParentNode = NodeToRotateTree.ParentNode;
			}
			else
			{
				NodeToRotateTree.ParentNode.RightChildNode = ParentNode;
				ParentNode.ParentNode = NodeToRotateTree.ParentNode;
			}
		}
		else
		{
			RBTree = ParentNode;
			ParentNode.ParentNode = null;
		}
		NodeToRotateTree.LeftChildNode = NodeToRotateTree.LeftChildNode.RightChildNode;
		ParentNode.RightChildNode = NodeToRotateTree;
		
		NodeToRotateTree.LeftChildNode.ParentNode = NodeToRotateTree;		
		NodeToRotateTree.ParentNode = ParentNode;
	}
	private void RotateTreeLeft(TreeNode<T> NodeToRotateTree)
	{
		TreeNode<T> ParentNode = NodeToRotateTree.RightChildNode;
		if(NodeToRotateTree.ParentNode != null)
		{
			if(NodeToRotateTree.ParentNode.LeftChildNode == NodeToRotateTree)
			{
				NodeToRotateTree.ParentNode.LeftChildNode = ParentNode;
				ParentNode.ParentNode = NodeToRotateTree.ParentNode;
			}
			else
			{
				NodeToRotateTree.ParentNode.RightChildNode = ParentNode;
				ParentNode.ParentNode = NodeToRotateTree.ParentNode;
			}
		}
		else
		{
			RBTree = ParentNode;
			ParentNode.ParentNode = null;
		}
		NodeToRotateTree.RightChildNode = NodeToRotateTree.RightChildNode.LeftChildNode;
		ParentNode.LeftChildNode = NodeToRotateTree;
		
		NodeToRotateTree.RightChildNode.ParentNode = NodeToRotateTree;		
		NodeToRotateTree.ParentNode = ParentNode;
	}
	
	public TreeNode<T> RBTreeSearch(T value)
	{																			
		TreeNode<T> Parent = RBTree;
		TreeNode<T> PreNode = null;
		while(true)
		{
                    switch (value.compareTo(Parent.ValueOfNode)) {
                        case 0:
                            return Parent;
                        case -1:
                            PreNode = Parent;
                            Parent = Parent.LeftChildNode;
                            if(Parent.LeafNode == true) {
                                return PreNode;
                    }
                            break;
                        case 1:
                            PreNode = Parent;
                            Parent = Parent.RightChildNode;
                            if(Parent.LeafNode == true) {
                                return PreNode;
                    }
                            break;
                        default:
                            break;
                    }
		}
	}
	
	private void AdjustAFterInsertion(TreeNode<T> InsertedNode)
	{
		TreeNode<T> parentNode = InsertedNode.ParentNode;
		if(parentNode.getNodeColor().equals("BLACK"))		
		{
		}
		else		
		{																		
			TreeNode<T> UncleNode = InsertedNode.ParentNode.ParentNode;																			
				if(UncleNode.LeftChildNode.getNodeColor().equals("RED") && UncleNode.RightChildNode.getNodeColor().equals("RED"))	
				{
					UncleNode.LeftChildNode.SetNodeColor("BLACK");
					UncleNode.RightChildNode.SetNodeColor("BLACK");
					if(UncleNode.ParentNode == null) {
                                        } else
					{
						UncleNode.SetNodeColor("RED");
						AdjustAFterInsertion(UncleNode);
					}
				}
				else	
				{
					if(UncleNode.LeftChildNode.LeftChildNode == InsertedNode)
					{
						UncleNode.SetNodeColor("RED");
						parentNode.SetNodeColor("BLACK");
						RotateTreeRight(UncleNode);
						
					}
					else if(UncleNode.LeftChildNode.RightChildNode == InsertedNode)
					{
						RotateTreeLeft(parentNode);
						UncleNode.SetNodeColor("RED");
						InsertedNode.SetNodeColor("BLACK");			
						RotateTreeRight(UncleNode);
					}
					else if(UncleNode.RightChildNode.RightChildNode == InsertedNode)
					{
						UncleNode.SetNodeColor("RED");
						parentNode.SetNodeColor("BLACK");
						RotateTreeLeft(UncleNode);
					}
					else if(UncleNode.RightChildNode.LeftChildNode == InsertedNode)
					{
						RotateTreeRight(parentNode);
						UncleNode.SetNodeColor("RED");
						InsertedNode.SetNodeColor("BLACK");
						RotateTreeLeft(UncleNode);
					}
				}
		}
	}
        
	public void RBTreeInsertNode(T NodeToBeInserted)
	{
		if(RBTree == null)		
		{
			RBTree = new TreeNode<>(NodeToBeInserted, "BLACK");
			RBTree.LeftChildNode = new TreeNode<>(RBTree);
			RBTree.RightChildNode = new TreeNode<>(RBTree);
		}
		else									
		{
			TreeNode<T> node = null;
			node = RBTreeSearch(NodeToBeInserted);
			if(NodeToBeInserted.compareTo(node.ValueOfNode) == 0)
			{
				System.out.println("the element is alredy exist! please try again");
			}
			else
			{
				TreeNode<T> InsertedNode = new TreeNode<>(NodeToBeInserted, "RED");
				InsertedNode.LeftChildNode = new TreeNode<>(InsertedNode);
				InsertedNode.RightChildNode = new TreeNode<>(InsertedNode);
				InsertedNode.ParentNode = node;
				if(NodeToBeInserted.compareTo(node.ValueOfNode) == -1)
				{
					node.LeftChildNode = InsertedNode;
				}
				else
				{
					node.RightChildNode = InsertedNode;
				}
					
				AdjustAFterInsertion(InsertedNode);
			}
		}
	}
	
	private void AdjustAfterDeletingBlackNode(TreeNode<T> deletedNode, String ChildNodes)
	{
		if(deletedNode.LeftChildNode.getNodeColor().equals("RED"))	
		{
			deletedNode.SetNodeColor("RED");
			deletedNode.LeftChildNode.SetNodeColor("BLACK");
			RotateTreeRight(deletedNode);
			AdjustAfterDeletingBlackNode(deletedNode, ChildNodes);		
		}
		else if(deletedNode.RightChildNode.getNodeColor().equals("RED"))		
		{
			deletedNode.SetNodeColor("RED");
			deletedNode.RightChildNode.SetNodeColor("BLACK");
			RotateTreeLeft(deletedNode);
			AdjustAfterDeletingBlackNode(deletedNode, ChildNodes);
		}
		else	
		{
			if(ChildNodes.equals("LEFT"))		
			{
				if(deletedNode.RightChildNode.RightChildNode.getNodeColor().equals("RED"))		
				{
					deletedNode.RightChildNode.RightChildNode.SetNodeColor("BLACK");
					if(deletedNode.getNodeColor().equals("RED"))	
					{
						deletedNode.SetNodeColor("BLACK");
						deletedNode.RightChildNode.SetNodeColor("RED");
					}
					RotateTreeLeft(deletedNode);
				}
				else if(deletedNode.RightChildNode.LeftChildNode.getNodeColor().equals("RED"))	
				{
					deletedNode.RightChildNode.SetNodeColor("RED");
					deletedNode.RightChildNode.LeftChildNode.SetNodeColor("BLACK");
					RotateTreeRight(deletedNode.RightChildNode);					
					
					deletedNode.RightChildNode.RightChildNode.SetNodeColor("BLACK");
					if(deletedNode.getNodeColor().equals("RED"))	
					{
						deletedNode.SetNodeColor("BLACK");
						deletedNode.RightChildNode.SetNodeColor("RED");
					}
					RotateTreeLeft(deletedNode);
				}
				else		
				{
					if(deletedNode.getNodeColor().equals("RED"))		
					{
						deletedNode.SetNodeColor("BLACK");
						deletedNode.RightChildNode.SetNodeColor("RED");
					}
					else		
					{
						deletedNode.RightChildNode.SetNodeColor("RED");
						if(deletedNode.ParentNode == null) {
                                                } else
						{
							if(deletedNode.ParentNode.LeftChildNode == deletedNode) {
                                                            AdjustAfterDeletingBlackNode(deletedNode.ParentNode, "LEFT");
                                                        } else {
                                                            AdjustAfterDeletingBlackNode(deletedNode.ParentNode, "RIGHT");
                                                        }
						}
					}
				}
			}
			else		
			{
				if(deletedNode.LeftChildNode.LeftChildNode.getNodeColor().equals("RED"))		
				{
					deletedNode.LeftChildNode.LeftChildNode.SetNodeColor("BLACK");
					if(deletedNode.getNodeColor().equals("RED"))
					{
						deletedNode.SetNodeColor("BLACK");
						deletedNode.LeftChildNode.SetNodeColor("RED");
					}
					RotateTreeRight(deletedNode);
				}
				else if(deletedNode.LeftChildNode.RightChildNode.getNodeColor().equals("RED"))	
				{
					deletedNode.LeftChildNode.SetNodeColor("RED");
					deletedNode.LeftChildNode.RightChildNode.SetNodeColor("BLACK");
					RotateTreeLeft(deletedNode.LeftChildNode);
					
					deletedNode.LeftChildNode.LeftChildNode.SetNodeColor("BLACK");
					if(deletedNode.getNodeColor().equals("RED"))	
					{
						deletedNode.SetNodeColor("BLACK");
						deletedNode.LeftChildNode.SetNodeColor("RED");
					}
					RotateTreeRight(deletedNode);
				}
				else		
				{
					if(deletedNode.getNodeColor().equals("RED"))
					{
						deletedNode.SetNodeColor("BLACK");
						deletedNode.LeftChildNode.SetNodeColor("RED");
					}
					else	
					{
						deletedNode.LeftChildNode.SetNodeColor("RED");
						if(deletedNode.ParentNode == null) {
                                                } else
						{
							if(deletedNode.ParentNode.LeftChildNode == deletedNode) {
                                                            AdjustAfterDeletingBlackNode(deletedNode.ParentNode, "LEFT");
                                                        } else {
                                                            AdjustAfterDeletingBlackNode(deletedNode.ParentNode, "RIGHT");
                                                        }
						}
					}
				}
			}
		}
		
	}
        private T FollowingNode(TreeNode<T> DeletedNode)
	{
		TreeNode<T> Parent = null;
		Parent = DeletedNode.RightChildNode;
		while(Parent.LeftChildNode.LeafNode == false)
		{
			Parent = Parent.LeftChildNode;
		}
		return Parent.ValueOfNode;
	}
	public void RBTreeDeleteNode(T NodeToBeDeleted)
	{
		TreeNode<T> DeletedNode = null;
		DeletedNode = RBTreeSearch(NodeToBeDeleted);
		if(DeletedNode.ValueOfNode.compareTo(NodeToBeDeleted) != 0)
		{
			System.out.println("The tree doesn't have this element..");
			return;
		}
		else
		{
			if(DeletedNode.LeftChildNode.LeafNode == false && DeletedNode.RightChildNode.LeafNode == false)	
			{
				T sValue = FollowingNode(DeletedNode);
				RBTreeDeleteNode(FollowingNode(DeletedNode));				
				DeletedNode.ValueOfNode = sValue;
			}
			else		
			{
				if(DeletedNode.getNodeColor().equals("RED"))		
				{
					if(DeletedNode.ParentNode.LeftChildNode == DeletedNode)
					{
						if(DeletedNode.LeftChildNode.LeafNode == false)
						{
							DeletedNode.ParentNode.LeftChildNode = DeletedNode.LeftChildNode;
							DeletedNode.LeftChildNode.ParentNode = DeletedNode.ParentNode;
						}
						else if(DeletedNode.RightChildNode.LeafNode == false)
						{
							DeletedNode.ParentNode.LeftChildNode = DeletedNode.RightChildNode;
							DeletedNode.RightChildNode.ParentNode = DeletedNode.ParentNode;
						}
						else	
						{
							DeletedNode.ParentNode.LeftChildNode = DeletedNode.LeftChildNode;
						}
						DeletedNode.LeftChildNode = DeletedNode.RightChildNode = DeletedNode.ParentNode = null;
					}
					else		
					{
						if(DeletedNode.LeftChildNode.LeafNode == false)
						{
							DeletedNode.ParentNode.RightChildNode = DeletedNode.LeftChildNode;
							DeletedNode.LeftChildNode.ParentNode = DeletedNode.ParentNode;
							
						}
						else if(DeletedNode.RightChildNode.LeafNode == false)
						{
							DeletedNode.ParentNode.RightChildNode = DeletedNode.RightChildNode;
							DeletedNode.RightChildNode.ParentNode = DeletedNode.ParentNode;
						}
						else	
						{
							DeletedNode.ParentNode.RightChildNode = DeletedNode.LeftChildNode;
						}
						DeletedNode.LeftChildNode = DeletedNode.RightChildNode = DeletedNode.ParentNode = null;
					}
				}
				else		
				{
					if(RBTree == DeletedNode)		
					{
						if(DeletedNode.LeftChildNode.LeafNode == false)
						{
							DeletedNode.LeftChildNode.SetNodeColor("BLACK");
							RBTree = DeletedNode.LeftChildNode;
							DeletedNode.LeftChildNode.ParentNode = null;
							return;
						}
						RBTree = null;		
						return;
					}
					if(DeletedNode.LeftChildNode.LeafNode == false)	
					{
						if(DeletedNode.ParentNode.LeftChildNode == DeletedNode)
						{
							DeletedNode.ParentNode.LeftChildNode = DeletedNode.LeftChildNode;
							DeletedNode.LeftChildNode.ParentNode = DeletedNode.ParentNode;
						}
						else
						{
							DeletedNode.ParentNode.RightChildNode = DeletedNode.LeftChildNode;
							DeletedNode.LeftChildNode.ParentNode = DeletedNode.ParentNode;
						}
						DeletedNode.LeftChildNode.SetNodeColor("BLACK");
						DeletedNode.LeftChildNode = DeletedNode.RightChildNode = DeletedNode.ParentNode = null;
					}
					else if(DeletedNode.RightChildNode.LeafNode == false)	
					{
						if(DeletedNode.ParentNode.LeftChildNode == DeletedNode)
						{
							DeletedNode.ParentNode.LeftChildNode = DeletedNode.RightChildNode;
							DeletedNode.RightChildNode.ParentNode = DeletedNode.ParentNode;
						}
						else
						{
							DeletedNode.ParentNode.RightChildNode = DeletedNode.RightChildNode;
							DeletedNode.RightChildNode.ParentNode = DeletedNode.ParentNode;
						}
						DeletedNode.RightChildNode.SetNodeColor("BLACK");
						DeletedNode.LeftChildNode = DeletedNode.RightChildNode = DeletedNode.ParentNode = null;
					}
					else		
					{
						if(DeletedNode.ParentNode.LeftChildNode == DeletedNode)
						{
							DeletedNode.ParentNode.LeftChildNode = DeletedNode.LeftChildNode;
							AdjustAfterDeletingBlackNode(DeletedNode.ParentNode, "LEFT");		
						}
						else
						{
							DeletedNode.ParentNode.RightChildNode = DeletedNode.LeftChildNode;
							AdjustAfterDeletingBlackNode(DeletedNode.ParentNode, "RIGHT");		
						}
						
						DeletedNode.LeftChildNode = DeletedNode.RightChildNode = DeletedNode.ParentNode = null;
					}
				}
			}
		}
		System.gc();
	}

	
}

