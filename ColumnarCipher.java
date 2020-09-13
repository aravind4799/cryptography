import java.util.*;

public class ColumnarCipher{

    public static String remove_space(String s){
        StringBuilder sb = new StringBuilder("");
        String[] arr = s.split(" ");
        for(String i:arr){
            sb.append(i);
        }
        return sb.toString();
    }

    public static String encrypt(String s, int l, ArrayList<Integer> seq){
        s=remove_space(s);
        StringBuilder ans = new StringBuilder("");
        int i,j,k,row_count=0;
        int len = s.length();
        if(len%l!=0){
             row_count=(int)len/l+1;
        }
        else{
            row_count=(int)len/l;
        }

        int remainder = l*row_count - len;
        for(i=remainder;i>0;i--){
            s+="X";
        }
        char[][] a = new char[row_count][l];
        k=0;
        for(i=0;i<row_count;i++){
            for(j=0;j<l;j++){
                a[i][j]=s.charAt(k);
                k+=1;
            }
        }
        for(i=0;i<seq.size();i++){
            for(j=0;j<row_count;j++){
                ans.append(a[j][seq.indexOf(i+1)]);
            }
        }
        return ans.toString();
    }
    public static String decrypt(String s,int l,ArrayList<Integer> seq){

        StringBuilder ans = new StringBuilder("");
        int i,j,k=0,row_count=0;
        int len = s.length();
       
        if(len%l!=0){
            row_count=(int)len/l+1;
        }
        else{
            row_count=(int)len/l;
        }
        
        char[][] a = new char[row_count][l];
        for(i=0;i<seq.size();i++){
            for(j=0;j<row_count;j++){
                a[j][seq.indexOf(i+1)]=s.charAt(k);
                k+=1;
            }
        }
        for(i=0;i<row_count;i++){
            for(j=0;j<l;j++){
                if(a[i][j]=='X'){
                    continue;
                }
                ans.append(a[i][j]);
            }
        }
        return ans.toString();
    }

    public static void main(final String[] agrs) {
        final Scanner scan = new Scanner(System.in);
        System.out.println("Enter text to decrypt/encrypt:");
        final String s = scan.nextLine();
        System.out.println("Enter length of key:");
        final int l = scan.nextInt();
        System.out.println("Enter the key sequene:");
        ArrayList<Integer> seq = new ArrayList<Integer>();
        for(int i=0;i<l;i++){
            seq.add(scan.nextInt());
        }
        System.out.println("Press 1.Encrypt\nPress 2.Decrypt");
        final int resp=scan.nextInt();
        if(resp==1){
            System.out.println("Encrypted text: "+encrypt(s,l,seq));
        }
        else if(resp==2){
            System.out.println("Decrypted text:"+decrypt(s,l,seq));
        }
        else{
            System.out.println("press either 1 or 2");
        }
        scan.close();
    }
}
