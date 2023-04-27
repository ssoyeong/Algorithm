import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

// 이진 검색 트리

public class BJ5639 {

    private static class Node {

        int key;
        Node left;
        Node right;

        Node(int key) {
            this.key = key;
        }

        void insert(int input) {
            
            if(input < this.key) {
                if(this.left == null) {
                    this.left = new Node(input);
                }
                else {
                    this.left.insert(input);
                }
            }
            else {
                if(this.right == null) {
                    this.right = new Node(input);
                }
                else {
                    this.right.insert(input);
                }
            }
        }
    }

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        Node root = new Node(Integer.parseInt(br.readLine()));

        String input;

        while((input = br.readLine()) != null) {
            root.insert(Integer.parseInt(input));
        }

        postOrder(root);
        System.out.println(sb.toString());
    }

    private static void postOrder(Node node) {

        if(node == null) return;

        postOrder(node.left);
        postOrder(node.right);
        sb.append(node.key + "\n");
    }

}
