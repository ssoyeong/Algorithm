import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// 트리 순회

public class BJ1991 {

    static class Node {
        
        char name;
        Node left;
        Node right;

        Node(char name) {
            this.name = name;
            this.left = null;
            this.right = null;
        }
    }

    static int n;
    static Node root;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            createNode(st.nextToken().charAt(0), st.nextToken().charAt(0), st.nextToken().charAt(0));
        }
       
        preorder(root);
        sb.append("\n");
        inorder(root);
        sb.append("\n");
        postorder(root);
        sb.append("\n");
        
        System.out.println(sb.toString());
    }

    private static void createNode(char name, char left, char right) {

        Node node = new Node(name);
        if(left != '.') node.left = new Node(left);
        if(right != '.') node.right = new Node(right);

        if(root == null) {
            root = node;
        }
        else {
            searchNode(root, node);
        }
    }

    private static void searchNode(Node root, Node node) {

        if(root == null) return;
        else{
            if(node.name == root.name) {
                root.left = node.left;
                root.right = node.right;
            }
            else{
                searchNode(root.left, node);
                searchNode(root.right, node);
            }
        }
    }

    private static void preorder(Node x) {
        
        if(x == null) return;
        sb.append(x.name);
        preorder(x.left);
        preorder(x.right);
    }

    private static void inorder(Node x) {
        
        if(x == null) return;
        inorder(x.left);
        sb.append(x.name);
        inorder(x.right);
    }

    private static void postorder(Node x) {
        
        if(x == null) return;
        postorder(x.left);
        postorder(x.right);
        sb.append(x.name);
    }
    
}
