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
public class TreeNode<T extends Comparable<T>>
{
	
	public T ValueOfNode;
        
	public TreeNode<T> LeftChildNode;
	public TreeNode<T> RightChildNode;
        
        public boolean LeafNode;
	public TreeNode<T> ParentNode;
        
	private String NodeColor;
	
	public TreeNode(TreeNode<T> ParentNodee)	
	{
		ValueOfNode = null;
		LeftChildNode = RightChildNode=null;
		this.ParentNode = ParentNodee;
		NodeColor = "BLACK";
		LeafNode = true;
	}
        public String orderTree()
	{return  ValueOfNode + ", ";}
        
	public TreeNode(T ValueofNodee ,String colour)			
	{
		super();
		this.ValueOfNode = ValueofNodee;
		this.NodeColor = colour;
		LeftChildNode = RightChildNode = ParentNode = null;
		LeafNode = false;
	}
	
        
	public void SetNodeColor(String setNodeColor)
	{
		if("BLACK".equals(setNodeColor) || "RED".equals(setNodeColor)) {
                    this.NodeColor = setNodeColor;
                } else {
                    System.out.println("input color error!");
                }
	}
        public String getNodeColor()
	{
		return NodeColor;
	}
	
}
