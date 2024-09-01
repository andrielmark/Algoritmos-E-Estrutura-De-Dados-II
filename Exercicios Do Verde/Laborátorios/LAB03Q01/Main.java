import java.util.*;

public class Main {
    public static void main(String[] args) {
        String frase = new String();
        Scanner scanf = new Scanner(System.in);
        boolean fim = false;

        while (fim == false) {
            frase = scanf.nextLine();

            if (frase.equals("FIM")) {
                fim = true;
                continue;
            }
            
            Stack<Character> pilha = new Stack<>();
            boolean correto = true;

            for (int i = 0; i < frase.length(); i++) {
                char ch = frase.charAt(i);

                if (ch == '(') {
                    pilha.push(ch);
                } else if (ch == ')') {
                    if (pilha.isEmpty()) {
                        correto = false;
                        break;
                    }
                    pilha.pop();
                }
            }
            
            if (correto && pilha.isEmpty()) {
                System.out.println("correto");
            } else {
                System.out.println("incorreto");
            }
        }
    }
}
