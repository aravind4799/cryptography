import java.util.*;
public class Railfence {

    public static String remove_space(String s){
        String[] arr = s.split(" ");
        StringBuilder sb = new StringBuilder("");
        for(String x: arr) sb.append(x);
        return sb.toString();
    }

    public static StringBuilder encrypt(String s,int d){
        s=remove_space(s);
        boolean down=false;
        StringBuilder ans= new StringBuilder("");
        char[][] railfence = new char[d][s.length()];
        int row=0;
        int col=0;
        for(int i=0;i<s.length();i++){
            if(row==0 || row==d-1){
                down=!down;
            }
            railfence[row][col]=s.charAt(i);
            col+=1;
            if(down){
                row+=1;
            }
            else{
                row-=1;
            }
        }
        for(int k=0;k<d;k++){
            for(int j=0;j<s.length();j++){
                if(railfence[k][j]!=' '){
                    ans.append(railfence[k][j]);
                }
            }
        }
        return ans;
        }
    
    public static StringBuilder decrypt(String s,int d){
        s=remove_space(s);
        StringBuilder ans = new StringBuilder("");
        char[][] railfence = new char[d][s.length()];
        int i,j,row,col,k;
        row=0;col=0;k=0;
        boolean down= false;
        for(i=0;i<s.length();i++){
            if(row==0||row==d-1){
                down=!down;
            }
            railfence[row][col]='*';
            col+=1;
            if(down){
                row+=1;
            }
            else{
                row-=1;
            }
        }      
        for(i=0;i<d;i++){
            for(j=0;j<s.length();j++){
                if(railfence[i][j]=='*'){
                    railfence[i][j]=s.charAt(k);
                    k+=1;
                }
            }
        }
        down=false;
        row=0;
        col=0;
        for(i=0;i<s.length();i++){
            if(row==0||row==d-1){
                down=!down;
            }
            if(railfence[row][col]!='*'){
                ans.append(railfence[row][col]);
            }
            col+=1;
            if(down){
                row+=1;
            }
            else{
                row-=1;
            }
        }
        return ans;
        }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter text to encrypt:");
        final String s = scan.nextLine();
        System.out.println("Enter the depth:");
        final int d = scan.nextInt();

            System.out.println("press 1.Encryption\npress 2.Decryption");
            int func = scan.nextInt();

            switch(func){
                case 1:
                System.out.println("Encrypted String: "+encrypt(s,d));
                break;
                case 2:
                System.out.println("Decrypted String: "+decrypt(s, d));
                case 3:
                break;
            }
            
        scan.close();
      

    }

    
}
