
import java.util.Scanner;

class Node {

    public Node(int val) {

        value = val;
        left = null;
        right = null;

    }

    public int value;
    public Node left;
    public Node right;
    
}

class Tree {

    public void create_balanced_bst(int[] starting_nums) { // Starting nums has to be sorted for this to work

        this.root = create_tree(starting_nums, 0, starting_nums.length - 1);

    }

    public void insert(int value) { 

        this.root = insert_node(this.root, value);

    }

    public void remove(int value) {

        this.root = delete_node(this.root, value);

    }

    public void view_in_order() {

        in_order(this.root);

    }

    public void view_pre_order() {

        pre_order(this.root);

    }

    public void view_post_order() {

        post_order(this.root);

    }

    public Boolean is_empty() {

        if(root == null) { return true; }

        return false;

    }

    private Node root;

    private Node create_tree(int[] starting_nums, int start, int end) {

        if (start > end) { return null; }

        int mid = (start + end) / 2;

        Node node = new Node(starting_nums[mid]); // get middle element as root

        node.left = create_tree(starting_nums, start, mid - 1);
        node.right = create_tree(starting_nums, mid + 1, end); // recursively call create tree to build left and right halves of array

        return node; 

    }

    Node insert_node(Node node, int val) {

        if (node == null) { return new Node(val); }

        if (val <= node.value) {

            node.left = insert_node(node.left, val);

        } // if val < node val then try to insert into left subtree

        else if (val >= node.value) {

            node.right = insert_node(node.right, val);

        } // else, insert into right subtree

        return node;

    }

    Node find_min(Node node) {

        while (node.left != null) { node = node.left; }

        return node;

    }

    Node delete_node(Node node, int val) {

        if (node == null) { return null; }

        if (val < node.value) {

            node.left = delete_node(node.left, val);

        } 

        else if (val > node.value) {

            node.right = delete_node(node.right, val);

        } 

        else { // after node is found

            if (node.left == null) {

                return node.right;

            } 

            else if (node.right == null) {

                return node.left;

            }

            // if node has 2 children, then we find the smallest node in the right subtree and replace the value of the current node with the smallest nodes value. 

            Node temp = find_min(node.right);

            node.value = temp.value;

            node.right = delete_node(node.right, temp.value);

        }

        return node;

    }

    // view functions

    private void in_order(Node node) {

        if (node == null) { return; }

        in_order(node.left);

        System.out.print(node.value + " ");

        in_order(node.right);

    }

    private void pre_order(Node node) {

        if (node == null) { return; }

        System.out.print(node.value + " ");

        pre_order(node.left);
        pre_order(node.right);

    }

    void post_order(Node node) {

        if (node == null) { return; }

        post_order(node.left);
        post_order(node.right);

        System.out.print(node.value + " ");

    }

}


public class Main {

    private static final int[] starting_nums = { 1, 2, 3, 4, 5, 6, 7 };
    private Tree BST = new Tree();
    private Scanner scanner = new Scanner(System.in);

    private void insert_into_tree() {

        System.out.print("\nType number you want to insert: ");

        while(!scanner.hasNextInt()) {

            System.out.print("ERROR. Enter valid integer: ");
            scanner.next();

        }

        int num = scanner.nextInt();

        BST.insert(num);

    }

    private void delete_from_tree() {

        System.out.print("\nType number you want to delete from tree: ");

        while(!scanner.hasNextInt()) {

            System.out.print("ERROR. Enter valid integer: ");
            scanner.next();

        }

        int num = scanner.nextInt();

        BST.remove(num);

    }

    private void program_loop() {

        int choice;

        while(true) {

            System.out.print("--OPTIONS--\n1.) Set BST\n2.) Add Node\n3.) Delete Node\n4.) Print in order\n5.) Print pre order\n6.) Print post order\n7.) Exit Program\nChoice: ");

            while(!scanner.hasNextInt()) {

                System.out.print("ERROR. Enter valid integer: ");
                scanner.next();
    
            }

            choice = scanner.nextInt();

            switch(choice) {

                case 1:
    
                    BST.create_balanced_bst(starting_nums);

                    break;
    
                case 2:
    
                    insert_into_tree(); 
    
                    break;
    
                case 3:

                    if (BST.is_empty()) { System.out.println("Tree has not been created yet OR is empty\n"); break; }
    
                    delete_from_tree();
    
                    break;
    
                case 4:

                    if (BST.is_empty()) { System.out.println("Tree has not been created yet OR is empty\n"); break; }
    
                    BST.view_in_order();
    
                    break;
    
                case 5:

                    if (BST.is_empty()) { System.out.println("Tree has not been created yet OR is empty\n"); break; }
    
                    BST.view_pre_order();
    
                    break;
    
                case 6: 

                    if (BST.is_empty()) { System.out.println("Tree has not been created yet OR is empty\n"); break; }
    
                    BST.view_post_order();
    
                    break;
    
                case 7:
    
                    scanner.close();
    
                    return;
    
                default:
    
                    System.out.println("ERROR: Invalid option");
    
                    break;
    
            }

        }

    }

    public static void main() {

        Main main = new Main();

        main.program_loop();

    }

}
