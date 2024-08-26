import java.util.*;

public class Main {
    public static String alterar (String str, char a, char b) {
        String nova = new String("");
        for(int i=0; i<str.length(); i++){
            if (str.charAt(i)==a) {
                nova+=b;

            }else{
                nova+=str.charAt(i);
            }
        }
        return(nova);
    }
    public static void main(String[] args) {
        boolean fim=false;
        Scanner scanf = new Scanner(System.in);
        Random gerador = new Random();
        gerador.setSeed(4);
        while (fim==false) {
            String str = new String("");
            str = scanf.nextLine();
            if(str.equals("FIM")){
                break;
            }
            char a = (char)((Math.abs(gerador.nextInt()) % 26) + 'a');
            char b = (char)((Math.abs(gerador.nextInt()) % 26) + 'a');
            System.out.println(alterar(str, a, b));
        }
        scanf.close();
    }
    
}
