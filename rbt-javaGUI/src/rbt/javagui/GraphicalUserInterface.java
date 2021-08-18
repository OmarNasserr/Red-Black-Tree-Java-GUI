/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rbt.javagui;

/**
 *
 * @author MGTC
 * @param <T>
 */
import java.awt.Color;
import javax.swing.JFrame;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Graphics;

@SuppressWarnings("serial")
public class GraphicalUserInterface extends JPanel
{
	
	public int flag;
	public int WidthOfWindow = 1500;
	public int HeightOfWindow = 700;
	public int LROffset = 270;
	public int DownOffset = 75;
	public int Node = 50;
	public int Level = 50;
	public RBTree RBTree;
	public GraphicalUserInterface(RBTree RBTree)
	{
		this.RBTree = RBTree;
		JFrame f = new JFrame("RBTree");
		f.setLocation(200, 0);
		f.setSize(WidthOfWindow,HeightOfWindow);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		f.getContentPane().add(this);
		this.setBackground(Color.white);
		
		
	}
	
        private void PaintTree(TreeNode PaintNode, Graphics2D Graphic, int c1, int c2, int Level)
	{
		if(PaintNode != null)
		{
			int RightNode = Node/2;
			int NextLeftNode = c1 - LROffset +Level*Level;
			int NextRightNode = c1 + LROffset - Level*Level;
			int NextNodeY = c2 + DownOffset;
			if(PaintNode.LeafNode ==false)
			{
				Graphic.setColor(Color.BLACK);
				Graphic.setStroke(new BasicStroke(2));
				if(PaintNode.LeftChildNode.LeafNode == false) {
                                    Graphic.drawLine(NextLeftNode + RightNode, NextNodeY + RightNode, c1 + RightNode , c2 + RightNode);
                                }
				if(PaintNode.RightChildNode.LeafNode == false) {
                                    Graphic.drawLine(NextRightNode + RightNode, NextNodeY + RightNode, c1 + RightNode, c2 + RightNode);
                                }
				if(PaintNode.getNodeColor().equals("RED")) {
                                    Graphic.setColor(Color.RED);
                                } else {
                                    Graphic.setColor(Color.BLACK);
                                }
				Graphic.fillOval(c1, c2, Node - Level, Node - Level);
				Graphic.setColor(Color.GRAY);
				Font font = new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 17);
				Graphic.setFont(font);
				Graphic.drawString(PaintNode.ValueOfNode.toString(), c1 + RightNode - 10, c2+ RightNode);
				
			}
			
			PaintTree(PaintNode.LeftChildNode, Graphic, NextLeftNode,  NextNodeY, Level + 1);
			PaintTree(PaintNode.RightChildNode, Graphic, NextRightNode, NextNodeY, Level + 1);
		}
	}
        @Override
	public void paint(Graphics g)
	{
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		PaintTree(RBTree.RBTree, g2, WidthOfWindow/2-25, 0, 0);
		
	}
	
}

