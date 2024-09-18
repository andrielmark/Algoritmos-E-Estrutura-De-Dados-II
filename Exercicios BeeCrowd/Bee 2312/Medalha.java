import java.util.*;

class Noel {
    public static void main(String[] args) {
        Scanner scanf = new Scanner(System.in);

        int n = scanf.nextInt();
        scanf.nextLine();

        String[] lingua = new String[n];
        String[] trad = new String[n];
        for (int i = 0; i < n; i++) {
            lingua[i] = scanf.nextLine();
            trad[i] = scanf.nextLine();
        }

        int m = scanf.nextInt();
        scanf.nextLine();
        
        String[] nome = new String[m];
        String[] nativa = new String[m];
        for (int j = 0; j < m; j++) {
            nome[j] = scanf.nextLine();
            nativa[j] = scanf.nextLine();
        }
        
        for (int z = 0; z < m; z++) {
            System.out.println(nome[z]);

            for (int x = 0; x < n; x++) {
                if (nativa[z].equals(lingua[x])) {
                    System.out.println(trad[x]);
                    break;
                }
            }

            System.out.println();
        }

        scanf.close();
    }

}