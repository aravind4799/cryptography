import java.util.Scanner;
import java.util.*;

class PlayFair{

    public static String encrypt(char[][] playfair_matrix,String key,StringBuilder text){

        String ans="";
        int i,j,a_row=0,a_col=0,b_row=0,b_col=0;

        for(i=0;i<text.length();i+=2){
            char a=text.charAt(i);
            char b;
            if(i+1>text.length()){
                b = 'x';
            }
            else{
                b=text.charAt(i+1);
            }

            if(a==b){
                b='x';
            }
            

            for(i=0;i<5;i++){
                for(j=0;j<5;j++){
                    if(playfair_matrix[i][j]==a){
                        a_row=i;
                        a_col=j;
                    }
                    if(playfair_matrix[i][j]==b){
                        b_row=i;
                        b_col=j;
                    }
                }
            }
    


            if(a_col==b_col){
                ans+=playfair_matrix[(a_row+1)%5][a_col];
                ans+=playfair_matrix[(b_row+1)%5][b_col];
            }
            else if(a_row==b_row){
                ans+=playfair_matrix[a_row][(a_col+1)%5];
                ans+=playfair_matrix[b_row][(b_col+1)%5];
            }
            else{
                ans+=playfair_matrix[a_row][b_col];
                ans+=playfair_matrix[b_row][a_col];
            }

        }
        

        return ans;
    }

    public static void main(String args[]) {
        int i,j,x;
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter Key:");
        String key = scan.nextLine();
        System.out.println("Enter plain text:");
        String text = scan.nextLine();

        // need to replace j from the plain text with i

        StringBuilder plain_text = new StringBuilder(text); 
        for(x=0;x<text.length();x++){

            if(text.charAt(x)=='j'){
                plain_text.setCharAt(x,'i');
            }

            if(text.charAt(x)=='J'){
                plain_text.setCharAt(x,'I');
            }
        }

        List<Character> list = new ArrayList<Character>();
        for(x=0;x<key.length();x++){
            if(!list.contains(key.charAt(x))){
                list.add(key.charAt(x));
            }
        }

        String alphabet = "abcdefghiklmnopqrstuvwxyz";

        for(i=0;i<alphabet.length();i++){
            if(!list.contains(alphabet.charAt(i))){
                list.add(alphabet.charAt(i));
            }
        }

        char[][] playfair_matrix = new char[5][5];
        x=0;
        for(i=0;i<5;i++){
            for(j=0;j<5;j++){
                playfair_matrix[i][j]=list.get(x);
                x+=1;
            }
        }
        System.out.println();
        System.out.println("playfair_matrix:");
        for(i=0;i<5;i++){
            for(j=0;j<5;j++){
                System.out.print(playfair_matrix[i][j]+" ");
            }
            System.out.println();
        }

        System.out.println(encrypt(playfair_matrix,key,plain_text));

        
   
        
    
    }

}

