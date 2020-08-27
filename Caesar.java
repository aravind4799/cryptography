import java.util.Scanner;

class Caesar {

    public static String encrypt_decrypt(String strng,int n,int function){

        String ans="";
  
        for(int i=0;i<strng.length();i++){
            char c=strng.charAt(i);
            if(Character.isUpperCase(c)){
                if(function == 1){
                    char ch = (char)(((int)strng.charAt(i)+n-65)%26+65);
                    ans+=ch;
                }
                else{
                    char ch = (char)(((int)strng.charAt(i)-n-65)%26+65);
                    ans+=ch;
                }
               
            }
            else{
                if(function==1){
                    char ch = (char)(((int)strng.charAt(i)+n-97)%26+97);
                    ans+=ch;
                }
                else{
                    char ch = (char)(((int)strng.charAt(i)-n-97)%26+97);
                    ans+=ch;
                }
               
            }
        }
        return ans;
        
    }

   
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in); 
       
        System.out.println("  enter text to encrypt:");
        String strng = sc.nextLine();
        System.out.println("  enter the shift value:");
        int n = sc.nextInt();
        System.out.println("  perform? 1.encrypt or 2.decrypt");
        int function=sc.nextInt();
        System.out.println(" string after given operation:"+  encrypt_decrypt(strng,n,function));
      
    }
}
   