package huffmancode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;
import javax.swing.JOptionPane;


public class huffmanCode {

    static ArrayList<Node> operationArrayList  = new ArrayList<Node>() ;
    static ArrayList<Node> unEditedString  = new ArrayList<Node> ();
    static TreeMap<Character,String> tree = new TreeMap<>();
    static String userInput  ;
    static char[] tempCharArray  ;
    static String encodedString ;
    static String decodedString ;
    static ArrayList<Node> operationArrayList2  = new ArrayList<Node>() ;
    static ArrayList<Node> unEditedString2  = new ArrayList<Node> ();
    static String input2  ;
    static char[] temp2  ;
    static String encodedString2 ;
    static String decodedString2 ;


    public static void main(String[] args) {

        input1Operations();
        input2Operations();
        
    }


    public static void Input1(){
        userInput = JOptionPane.showInputDialog("Enter String to be encoded");
        //PUTING THE STRING INTO ARRAY OF CHARACTERS
        tempCharArray = userInput.toCharArray();
        //ADDING EACH CHARACTER TO ARRAYLIST "STRING" WITH NO REPETITIONS
        for(int i=0;i<tempCharArray.length;i++){
            String t = tempCharArray[i] + "";
            Node n = new Node (t);
            if(!contains(operationArrayList,t)){
            operationArrayList.add(n);
            unEditedString.add(n);
            }
            else if(contains(operationArrayList,t)) {
                int index = getIndex(operationArrayList,t);
                operationArrayList.get(index).freq++;

            }
        }
    }
    public static void Input2(){
        input2 = JOptionPane.showInputDialog("Enter String to be encoded");
        //PUTING THE STRING INTO ARRAY OF CHARACTERS
        temp2 = input2.toCharArray();
        //ADDING EACH CHARACTER TO ARRAYLIST "STRING" WITH NO REPETITIONS
        for(int i=0;i<temp2.length;i++){
            String t = temp2[i] + "";
            Node n = new Node (t);
            if(!contains(operationArrayList2,t)){
            operationArrayList2.add(n);
            unEditedString2.add(n);
            }
            else if(contains(operationArrayList2,t)) {
                int index = getIndex(operationArrayList2,t);
                operationArrayList2.get(index).freq++;

            }
        }
    }
    public static boolean checkInput2(){
        boolean same = true ;
        for(int i=0;i<temp2.length;i++){
            if(!contains(unEditedString,temp2[i]+"")){
                same = false;
                break;
            }
        }
        return same ;
    }
    public static void ConcatenatingNodes(){
        //tree
        //ADDING EACH TWO CHARACTERS TOGETHER UNTIL YOU END UP WITH ONLY ONE WORD
        Collections.sort(operationArrayList);
        while(operationArrayList.size() > 1){

            Node n = (Node) operationArrayList.get(0) ;
            Node m = (Node) operationArrayList.get(1);
            int tempFreq = n.freq + m.freq ;
            Node nw = new Node (n.data+m.data);
            nw.addChild(n, m);
            operationArrayList.remove(0);
            operationArrayList.remove(0);
            operationArrayList.add(nw);
            nw.freq = tempFreq;
            Collections.sort(operationArrayList);
                    }
    }
    public static void Traversee(Node n ,String s){
        if(n.left != null){
            Traversee(n.left,s+"0");
        }
        if (n.right!=null){
            Traversee(n.right,s+"1");
        }
        else if ( n.right == null  && n.left==null)
        {
            tree.put(n.data.charAt(0), s);
            }
        }
    public static boolean contains(ArrayList<Node> list, String name) {
    for (Node item : list) {
        if (item.getData().equals(name)) {
            return true;
        }
    }
    return false;
}
    public static int getIndex(ArrayList<Node> list, String name) {
    int c=0;
         for(int i =0 ; i <list.size();i++){
             if(list.get(i).data.equals(name)){
                 break;
             }
             c++;
    }
    return c;
    }
    public static void decode(String s,Node l){
         decodedString="";
         for(int i = 0;i<s.length();){
            Node node = l ;
            while(node.left!=null && node.right !=null && i < s.length()){
                if((s.charAt(i)) == '1')
                    node = node.right;
                else
                    node = node.left;
                i++;

            }
            decodedString += node.data;
         }
         System.out.println("decodedString Text is = " + decodedString);
     }
    public static void input2Operations(){
        Input2();
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("Second Input Section");
        if(checkInput2() == true){
            System.out.println("Second Input Characters&Frequencies");
            for(int i = 0 ; i <operationArrayList2.size();i++ ){
            System.out.println("'"+operationArrayList2.get(i).data + "'"+ "  :  " + operationArrayList2.get(i).freq);
            System.out.println("---------------");
            }
             encodedString2 = "";
        for(int j = 0 ; j < input2.length() ; j++){
            encodedString2 = encodedString2 + tree.get(input2.charAt(j));
        }
            System.out.println("Encoded String Input 2 is : " + encodedString2);
            decodedString2 = "";
            decodedString2 = encodedString2 ;
            Node rootNode = operationArrayList.get(0);
            Node n =  rootNode ;
            decode(decodedString2,n);
        }

        else {
            System.out.println("The String you Entered contains Characters that couldn't be found in the Tree ");
        }
    }
    public static void input1Operations(){
        Input1();
        System.out.println("Characters&Their Frequency");
        for(int i = 0 ; i <operationArrayList.size();i++ ){
            System.out.println("'"+operationArrayList.get(i).data + "'"+ "  :  " + operationArrayList.get(i).freq);
            System.out.println();
        }


        ConcatenatingNodes();


        String tempStringForCode ="";
        Node rootNode = operationArrayList.get(0);
        Node n =  rootNode ;
        System.out.println(operationArrayList.size());
        Traversee(rootNode,tempStringForCode);

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("Characters after Encoding : ");
        System.out.println();
        for(int j=0;j<unEditedString.size();j++){
            System.out.println(tree.get(unEditedString.get(j).data.charAt(0)) + " : " + unEditedString.get(j).data);
         }
        System.out.println("The Initial Message is : " + userInput);

        encodedString = "";
        for(int j = 0 ; j < userInput.length() ; j++){
            encodedString = encodedString + tree.get(userInput.charAt(j));
        }
        System.out.println("The Message after Encoding : "  + encodedString);
        decodedString = encodedString ;
        decode(decodedString,n);
    }

}
