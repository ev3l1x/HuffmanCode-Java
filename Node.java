package huffmancode;

public class Node implements Comparable {
    
    public String data ;
    public int freq;
    public Node left ;
    public Node right ;

    public String getData() {
        return data;
    }
 
    public Node(String d) {
        data = d;
        freq = 1;
    }

     public void addChild(Node left,Node right) {
        if(left.freq < right.freq){
            this.left=left;
            this.right=right;
        }
        else{
            this.right=left;
            this.left=right;
        }
    }
     
    public int getFreq() {
        return this.freq;
    }

    @Override
    public int compareTo(Object o) {
    int compareage=((Node)o).getFreq();
        /* For Ascending order*/
        return this.freq-compareage;    
    
    }
    
}
