import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;


public class Arquivo {
    public static void main(String[] args) {
        Scanner scanf = new Scanner(System.in);
        int n = scanf.nextInt();
        try{
            RandomAccessFile arquivao = new RandomAccessFile("arquivao.txt", "rw");
            for (int i=0; i < n; i++) {
                double num = scanf.nextDouble();
                arquivao.writeDouble(num);
            }
            arquivao.close();
            
            arquivao = new RandomAccessFile("arquivao.txt", "r");

            for(int i=n-1; i>=0; i--){
                arquivao.seek(i*8);
                
                double valor = arquivao.readDouble();
                if(valor == Math.floor(valor)){
                    System.out.println((int) valor);
                }else{
                System.out.println(valor);
                }
            }
        }
        catch(IOException e){
            e.printStackTrace();
        } finally {
            scanf.close();
        }
    }
    
}
