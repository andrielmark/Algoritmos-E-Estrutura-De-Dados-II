import java.util.*;

public class palindromo{
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
            for(int i = 0; i < tamanho; i++){
                if(palavra.charAt(i)!=palavra.charAt(ultimocaractere)){
                    palindromo=false;
                }
                ultimocaractere--;
            }
            if (palindromo==false) {
                System.out.println("NAO");
            }else{
                System.out.println("SIM");
            }
        }
        scanf.close();
    }
}