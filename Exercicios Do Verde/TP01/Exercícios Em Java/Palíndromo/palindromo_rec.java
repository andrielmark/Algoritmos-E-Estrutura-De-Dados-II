import java.util.*;

public class palindromo_rec{
    public static boolean recursivo(int i, int tamanho, String palavra, int j) {
        if( i < tamanho){
            if(palavra.charAt(i)!=palavra.charAt(j)){
            return false;
            }else{
                return recursivo(i+1, tamanho, palavra, j-1);
            }
        }else{
            return true;
     }

    }
    public static void main(String[] args) {
        Scanner scanf = new Scanner(System.in);
        boolean palindromo = true;
        boolean fim = false;
        while(fim == false){
            palindromo = true;
            String palavra = scanf.nextLine();
            if(palavra.equals("FIM")){
                break;
            }
            int tamanho = palavra.length();
            int ultimocaractere = tamanho - 1;
            if (recursivo(0, tamanho, palavra, ultimocaractere)) {
                System.out.println("SIM");
            }else{
                System.out.println("NAO");
            }
        }
        scanf.close();
    }
}