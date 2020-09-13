import java.util.*;
public class Vignere{
    public static String generate_key(String s,String key){
        StringBuilder sb = new StringBuilder("");
        int l=s.length();
        int l_key=key.length();
        for(int i=0;i<(int)l/l_key;i++){
            sb.append(key);
        }
        for(int j=0;j<(int)l%l_key;j++){
            sb.append(key.charAt(j));
        }
        return sb.toString();
    }
    public static String encrypt(String s,String key){
        key=generate_key(s,key);
        String ans="";
        int l=s.length();
        for(int i=0;i<l;i++){
            int x = (s.charAt(i)+key.charAt(i))%26;
            x+='A';
            ans+=(char)x;
        }
        return ans;
    }
    public static String decrypt(String s,String key){
        key=generate_key(s,key);
        String ans="";
        int l=s.length();
        for(int i=0;i<l;i++){
            int x = (s.charAt(i)-key.charAt(i)+26)%26;
            x+='A';
            ans+=(char)x;
        }
        return ans;
    }
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        System.out.println("Enter String to encrypt/decrpyt: ");
        String s = scan.nextLine();
        System.out.println("Enter key: ");
        String key= scan.nextLine();
        System.out.println("Press 1.Encrypt\nPress 2.Decrypt");
        int op = scan.nextInt();
        switch(op){
            case 1:
            System.out.println("Encrypted String: "+ encrypt(s.toUpperCase(),key.toUpperCase()));
            break;
            case 2:
            System.out.println("Decrypted String: "+ decrypt(s.toUpperCase(),key.toUpperCase()));
            break;
            default:
            System.out.println("1-->to encrypt and 2-->to decrypt\nenter either 1 or 2");
        }
        scan.close();
    }
}

