package AVL;

public class Node {
    int key;

    int bf;//平衡因子

    Node left;
    Node right;

    Node parent;

    Node(int key,Node parent){
        this.key = key;
        this.parent = parent;
    }
}
