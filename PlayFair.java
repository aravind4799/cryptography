import java.util.*;

class PlayFair{
    static boolean flag= false;
    public static String encrypt_decrypt(char[][] playfair_matrix,String key,String text,HashMap<Character,String> hashmap,int val){
        //replace empty space and J TO I
        text=text.replaceAll(" ","");
        text=text.replaceAll("J","I");

        int i,minus;

        StringBuilder ans = new StringBuilder("");
        List<String> split = new ArrayList<>();
        //if the length OF TEXT is odd need to add Z to make the length even
        int l=text.length();
        if(l%2!=0){
            text+='Z';
            l+=1;
            flag=true;
        }
        System.out.println(text);
        for(i=0;i+1<l;i+=2){
            String sub = text.substring(i,i+2);
            split.add(sub);
        }
        System.out.println(split);
        // [WH, AT, SA, PX, PZ]
        if(val==1){            
            minus=+1;
        }
        else{
            minus=-1;
        }      
        for(i=0;i<split.size();i++){
            char a=(char)split.get(i).charAt(0);
            char b=(char)split.get(i).charAt(1);
            int a_x=Integer.parseInt(String.valueOf(hashmap.get(a).charAt(0)));
            int a_y=Integer.parseInt(String.valueOf(hashmap.get(a).charAt(1)));
            int b_x=Integer.parseInt(String.valueOf(hashmap.get(b).charAt(0)));
            int b_y=Integer.parseInt(String.valueOf(hashmap.get(b).charAt(1)));

            if(a_x==b_x){
                ans.append(playfair_matrix[a_x][(a_y+minus+5)%5]);
                ans.append(playfair_matrix[b_x][(b_y+minus+5)%5]);
                // System.out.println(ans);
            }
            else if(a_y==b_y){
                ans.append(playfair_matrix[(a_x+minus+5)%5][a_y]);
                ans.append(playfair_matrix[(b_x+minus+5)%5][b_y]);
                // System.out.println(ans);
            }
            else{
                ans.append(playfair_matrix[a_x][b_y]);
                ans.append(playfair_matrix[b_x][a_y]);
                // System.out.println(ans);
            }
        }
        return ans.toString();
    }

    public static void main(String args[]) {
        int i,j,x;
        String encrypted_string="";
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter Key:");
        String key = scan.nextLine();
        key=key.toUpperCase();
        System.out.println("Enter plain text to encrypt/decrypt:");
        String text = scan.nextLine();
        text=text.toUpperCase();
        
        // add the given key to the list without duplicates
        List<Character> list = new ArrayList<Character>();
        for(x=0;x<key.length();x++){
            if(!list.contains(key.charAt(x))){
                list.add(key.charAt(x));
            }
        }
        // fill remaining alphabets;
        String alphabet = "ABCDEFGHIKLMNOPQRSTUVWXYZ";      
        for(i=0;i<alphabet.length();i++){
            if(!list.contains(alphabet.charAt(i))){
                list.add(alphabet.charAt(i));
            }
        }
        // System.out.println(list);
        char[][] playfair_matrix = new char[5][5];
        HashMap<Character,String> hashmap = new HashMap<Character,String>();
        x=0;
        for(i=0;i<5;i++){
            for(j=0;j<5;j++){
                playfair_matrix[i][j]=list.get(x);
                String location = Integer.toString(i)+Integer.toString(j);
                hashmap.put(list.get(x),location);
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
        List<Character> dup_char = new ArrayList<>();
        List<Integer> dup_index =new ArrayList<>();
        for(i=0;i<text.length()-1;i++){
            String sub = text.substring(i,i+2);
            if(sub.charAt(0)==sub.charAt(1)){
                dup_char.add(sub.charAt(0));
                dup_index.add(i);
                text=text.substring(0,i+1)+"X"+text.substring(i+1);
            }
        }
        System.out.println(text);
        // System.out.println(text);
        //WHATSAPP = WHATSAPXP //adding fillers
        //if text =WHATSAPPP then =WHATSAPXPXP
        System.out.println("Press 1.Encrypt\nPress 2.Decrypt\n Press 0.Exit");
        int func = scan.nextInt();      
        while(func!=0){
        switch(func){
            case 1:
            encrypted_string = encrypt_decrypt(playfair_matrix,key,text,hashmap,1);
            System.out.println("Encrypted_String: "+ encrypted_string);
            break;
            case 2:
            String ans=encrypt_decrypt(playfair_matrix,key,encrypted_string,hashmap,0);
            //need to remove the filler make use of dup hashmap
            System.out.println(ans);
            for(i=dup_index.size()-1;i>=0;i--){
                if(ans.charAt(dup_index.get(i))== dup_char.get(i)){
                    ans=ans.substring(0,dup_index.get(i)+1)+ans.substring(dup_index.get(i)+2);
                }
            }
            if(flag){
                int l=ans.length();
                ans=ans.substring(0,l-1);
            }
            System.out.println("Decrypted_String:"+ans);
            break;
            default:
            System.out.println("Press 1 or 2");
        }
        System.out.println("Press 1.Encrypt\nPress 2.Decrypt\nPress 0.Exit");
        func = scan.nextInt();
    }
    scan.close();
    }
}

