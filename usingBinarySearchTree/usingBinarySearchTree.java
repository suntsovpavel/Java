package usingBinarySearchTree;

public class usingBinarySearchTree {
    public static void main(String[] args) {
        
    }

    public class BinarySearchTree {
        
        private TreeNode head;
    
        //У всех узлов дерева поиска значение value уникально
        //У каждого узла правый потомок имеет значение value больше, а левый потомок - меньше
        private class TreeNode{
             private int value;
             private TreeNode left;
             private TreeNode right;
             private TreeNode parent;
        }
        public boolean find(int value){
            TreeNode currentNode = head;
            while(currentNode != null){
                if(currentNode.value == value)return true;
                else if(value > currentNode.value){
                    currentNode = currentNode.right;
                }else{
                    currentNode = currentNode.left;
                }
            }       
            return false;                 
        }
    }
}
