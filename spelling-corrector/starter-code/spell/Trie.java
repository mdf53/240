package spell;

public class Trie implements ITrie{
    private int wordCount;
    private int nodeCount;
    private Node root;
    public Trie(){
        wordCount = 0;
        root = new Node();
        nodeCount = 1;
    }
    @Override
    public void add(String word) {
        Node node = root;
        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            int index = (int)(c - 'a');
            if(node.getChildren()[index] == null){
                node.getChildren()[index] = new Node();
                nodeCount++;
            }
            node = node.getChildren()[index];
        }
        if(node.getValue()==0){
            wordCount++;
        }
        node.incrementValue();
    }

    @Override
    public Node find(String word) {
        Node node = root;
        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            int index = (int)(c - 'a');
            if(node.getChildren()[index] == null){
                return null;
            }
            node = node.getChildren()[index];
        }
        if(node.getValue() > 0){
            return node;
        }
        return null;
    }

    @Override
    public int getWordCount() {
        return  wordCount;
    }

    @Override
    public int getNodeCount() {
        return nodeCount;
    }

    public String nodeToString(Node goal){
        StringBuilder result = new StringBuilder();
        StringBuilder wip = new StringBuilder();
        nodeToStringRec(result, wip, root, goal);
        return result.toString();
    }

    private void nodeToStringRec(StringBuilder result, StringBuilder wip, Node node, Node goal){
        if(node == goal){
            result.append(wip.toString());
            return;
        }
        for(int i = 0; i < node.getChildren().length; i++){
            if(node.getChildren()[i] != null){
                char c = (char)(i + 'a');
                wip.append(c);
                nodeToStringRec(result, wip, node.getChildren()[i], goal);
                wip.deleteCharAt(wip.length()-1);
            }
        }

    }

    public String toString(){
        StringBuilder result = new StringBuilder();
        StringBuilder wip = new StringBuilder();
        toStringRec(result, wip, root);
        return result.toString();
    }

    private void toStringRec(StringBuilder result, StringBuilder wip, Node node){
        if(node.getValue() > 0){
            result.append(wip.toString());
            result.append("\n");
        }
        for(int i = 0; i < node.getChildren().length; i++){
            if(node.getChildren()[i] != null){
                char c = (char)(i + 'a');
                wip.append(c);
                toStringRec(result, wip, node.getChildren()[i]);
                wip.deleteCharAt(wip.length()-1);
            }
        }

    }

    public int hashCode(){
        int hash = wordCount * nodeCount;
        for(int i = 0; i < root.getChildren().length; i++){
            if(root.getChildren()[i] != null){
                hash = hash ^ i;
            }
        }
        return hash;
    }

    public boolean equals(Object o){
        //check if o is null
        if(o == null){
            return false;
        }
        //check if same class
        if(o.getClass() != this.getClass()){
            return false;
        }
        Trie t = (Trie)o;
        //check to see if they are the same counts
        if(t.getNodeCount() != this.getNodeCount() || t.getWordCount() != this.getWordCount()){
            return false;
        }
        //recursion
        return equalsRec(this.root, t.root);
    }

    private boolean equalsRec(Node n1, Node n2){
        if(n1.getValue() != n2.getValue()){
            return false;
        }
        //check to see if they have children in the same spots
        for(int i = 0; i < n1.getChildren().length; i++){
            if(n1.getChildren()[i] != null && n2.getChildren()[i] == null || n1.getChildren()[i] == null && n2.getChildren()[i] != null){
                return false;
            }
        }
        //recurse over the children
        for(int i = 0; i < n1.getChildren().length; i++){
            if(n1.getChildren()[i] != null){
                if(!equalsRec(n1.getChildren()[i], n2.getChildren()[i])){
                    return false;
                }
            }
        }
        return true;
    }
}
