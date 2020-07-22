package AVL;

import java.util.*;

public class AVLTree {
    private Node root;

    /**
     * AVL树是否包含某个值
     * @param key
     * @return
     */
    public boolean contains(int key){
        Node cur = root;
        while(cur!=null){
            if(cur.key>key){
                cur = cur.left;
            }else if(cur.key<key){
                cur = cur.right;
            }else{
                return true;
            }
        }
        return false;
    }

    public void insert(int key){
        if(root == null){
            root = new Node(key,null);
            return;
        }
        Node parent = null;
        Node cur = root;
        while(cur!=null){
            if(cur.key==key){
                throw new RuntimeException("key("+key+")重复");
            }else if(cur.key<key){
                parent = cur;
                cur=cur.right;
            }else{
                parent=cur;
                cur = cur.left;
            }
        }

        //此时书中没有key，然后根据key和parent.key的大小关系决定key的位置
        if(key<parent.key){
            cur = parent.left = new Node(key,parent);
        }else{
            cur = parent.right = new Node(key,parent);

        }
        while(true){
            if(cur == parent.left){
                parent.bf++;
            }else{
                parent.bf--;
            }

            if(parent.bf == 0){
                break;
            }else if(parent.bf == 2){//失衡修复
                if(cur.bf == 1){
                    //左左失衡
                    //进行右旋
                    fixLeftLeftLoseBalance(parent);
                }else{
                    //左右失衡
                    //先，然后
                    fixLeftRightLoseBalance(parent);
                }
                break;
            }else if(parent.bf == -2){
                if(cur.bf == -1){
                    //右右失衡
                    fixRightRightLoseBalance(parent);
                }else{
                    //右左失衡
                    fixRightLeftLoseBalance(parent);
                }
                break;
            }else if(parent == root){//调节到根节点且根节点平衡
                break;
            }

            cur = parent;
            parent = parent.parent;
        }




    }

    private void fixLeftRightLoseBalance(Node parent) {

        Node leftOfNode = parent.left;
        Node rightOfLeftOfNode = leftOfNode.right;

        leftRotate(leftOfNode);
        rightRotate(parent);



        //先对c进行右旋
    }

    private void fixRightRightLoseBalance(Node parent) {
        Node right = parent.right;
        leftRotate(parent);

        parent.bf = right.bf = 0;
    }

    private void fixRightLeftLoseBalance(Node parent) {
        Node rightOfParent = parent.right;
        Node leftOfRight = rightOfParent.left;
        rightRotate(rightOfParent);
        leftRotate(parent);

        //更新bf
    }

    private void fixLeftLeftLoseBalance(Node parent) {
        Node left = parent.left;
        rightRotate(parent);

        parent.bf = left.bf = 0;
        //更新bf

    }

    /**
     * AVL树的校验
     */
    public void verify(){
        List<Integer> list = new ArrayList<>();
        inorder(root,list);
        List<Integer> copyOfList = new ArrayList<>(list);
        Collections.sort(copyOfList);
        if(!list.equals(copyOfList)){
            throw new RuntimeException("AVL树的中序遍历不满足有序性");
        }

        //验证每个子树的高度、
        //验证bf
    }
    /**
     * 中序搜素
     * @param root
     * @param inorderList
     * @return
     */


    private void inorder(Node root, List<Integer> inorderList){
        if(root!=null){
            inorder(root.left,inorderList);
            inorderList.add(root.key);
            inorder(root.left,inorderList);
        }

    }

    private void verifyHeight(Node root){
        if(root != null){
            int left = getHeight(root.left);
            int right = getHeight(root.right);
            if(right - left == -1 || right -left ==2){
                throw new RuntimeException("key="+root.key+"高度不平衡");
            }
            verifyHeight(root.left);

        }
    }
    private void verifyBF(Node root){
        if(root != null){
            verifyBF(root.left);
            if(root.bf!=-1 && root.bf !=1 && root.bf!=0){
                throw new RuntimeException("key="+root.key+"的bf不合格");
            }
        }
    }
	public int getHeight(Node root){
        if(root == null){
            return 0;
        }
		return Math.max(getHeight(root.left)+1,getHeight(root.right)+1);
	}

	private void leftRotate(Node parent){//解决右右失衡
        Node pp = parent.parent;
        Node cur = parent.right;
        Node leftOfCur = cur.left;

        cur.parent = pp;

        if(pp == null){
            root = cur;
        }else if(parent == pp.left){
            pp.left = cur;
        }else{
            pp.right = cur;
        }

        cur.left = parent;
        parent.parent = cur;

        parent.right = leftOfCur;

        if(leftOfCur != null){
            leftOfCur.parent = parent;
        }
    }

    private void rightRotate(Node parent){//解决左左失衡
        Node pp = parent.parent;
        Node cur = parent.left;
        Node rightOfCur = cur.right;
        cur.parent = pp;
        if(pp == null){
            root = cur;
        }else if(parent == pp.left){
            pp.left = cur;
        }else{
            pp.right = cur;
        }

        cur.right = parent;
        parent.parent = cur;
        parent.left = rightOfCur;

        if(rightOfCur != null){
            rightOfCur.parent = parent;
        }


    }
}
