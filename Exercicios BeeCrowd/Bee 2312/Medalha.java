import java.util.*;

public class Medalha {
    public static void main(String[] args) {
        Scanner scanf = new Scanner(System.in);
        int n = scanf.nextInt();
        scanf.nextLine();

        Pais[] array = new Pais[n];
        for (int i = 0; i < n; i++) {
            String nome = scanf.next();
            int ouro = scanf.nextInt();
            int prata = scanf.nextInt();
            int bronze = scanf.nextInt();
            scanf.nextLine();
            array[i] = new Pais(nome, ouro, prata, bronze);
        }
        for(int i = 0; i < n; i++){
            int maior = i;
            for(int j = i + 1; j < n; j++){
                if(array[maior].ouro != array[j].ouro){
                    if (array[maior].ouro < array[j].ouro) {
                        maior = j;
                    }
                } else if(array[maior].prata != array[j].prata ){
                    if(array[maior].prata < array[j].prata ){
                        maior = j;
                    }
                }else if(array[maior].bronze != array[j].bronze){
                    if (array[maior].bronze < array[j].bronze){
                        maior = j;
                    }
                }else if (array[maior].nome.compareTo(array[j].nome) > 0) {
                    maior = j;
                }        
                
            }
            Pais temp = array[i];
            array[i] = array[maior];
            array[maior] = temp;
            

        }
        for(int i = 0; i < n; i++){
            System.out.println(array[i].nome + array[i].ouro + array[i].prata + array[i].bronze);
        }
    }

}

class Pais {
    public String nome;
    public int ouro;
    public int prata;
    public int bronze;

    Pais(String nome, int ouro, int prata, int bronze) {
        this.nome = nome;
        this.ouro = ouro;
        this.prata = prata;
        this.bronze = bronze;
    }
}
