import java.util.Scanner;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();        
        sc.nextLine();

        for(int i = 0; i < n; i++){
            int tamanho = sc.nextInt();
            sc.nextLine();
            String vagoes = new String();
            vagoes = sc.nextLine();
            String[] partes = vagoes.split(" ");
            int[] vag = new int[partes.length];
            for(int j = 0; j < partes.length; j++){
                vag[j] = Integer.parseInt(partes[j]);
            }
            int count = 0;
            for(int z = 0; z < partes.length - 1; z++){
                for(int y = 0; y < partes.length - z - 1; y++){
                    if(vag[y] > vag[y + 1]){
                        int temp = vag[y];
                        vag[y] = vag[y + 1];
                        vag[y + 1] = temp;
                        count++;
                    }
                }
            }
            System.out.println("Optimal train swapping takes " + count + " swaps.");
        }
    }    
}
