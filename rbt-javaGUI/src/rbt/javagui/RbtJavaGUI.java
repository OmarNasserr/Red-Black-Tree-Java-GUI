/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rbt.javagui;

import java.util.Scanner;

/**
 *
 * @author MGTC
 */
public class RbtJavaGUI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        RBTree<Integer> RBtree = new RBTree<>();
        GraphicalUserInterface GUI = new GraphicalUserInterface(RBtree);
        Scanner inputScanner = new Scanner(System.in);
        String choice;
        LOOP:
        while (true) {
            System.out.println("OPTIONS: ");
            System.out.println("Enter an integer element to insert ");
            System.out.println("or Enter 'delete' to delete an element ");
            System.out.println("or Enter 'print' to display the ordered RBTree");
            System.out.println("or Enter 'clear' to delete the whole RBTree ");
            choice = inputScanner.nextLine();
            switch (choice) {
                case "delete":
                    try {
                        System.out.println("Enter the element you wish to delete");
                        choice = inputScanner.nextLine();
                        int x = Integer.parseInt(choice);
                        RBtree.RBTreeDeleteNode(x);
                        GUI.repaint();
                    } catch (NumberFormatException e) {
                        System.out.println("Incorrect input, please enter a valid input");
                    } catch (NullPointerException e) {
                        System.out.println("the element doesn't exist in the RBTree");
                    }
                    break;
                case "clear":
                    RBtree.RBTree = null;
                    GUI.repaint();
                    break LOOP;
                case "print":
                    if (RBtree.RBTree == null) {
                        System.out.println("The RBTree is Empty, try to insert elements first..");
                    } else {
                        RBtree.Display();
                    }
                    break;
                default:
                    try {
                        int temp = Integer.parseInt(choice);
                        RBtree.RBTreeInsertNode(temp);
                        GUI.repaint();
                    } catch (NumberFormatException e) {
                        System.out.println("Incorrect input, please enter a valid input");
                    }
                    break;
            }
        }
    }

}
