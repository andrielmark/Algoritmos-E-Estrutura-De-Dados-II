import java.util.Scanner;

class Matriz {
    private int linhas;
    private int colunas;
    private int[][] elementos;

    public Matriz(int linhas, int colunas) {
        this.linhas = linhas;
        this.colunas = colunas;
        this.elementos = new int[linhas][colunas];
    }

    public void setElemento(int linha, int coluna, int valor) {
        elementos[linha][coluna] = valor;
    }

    public Matriz soma(Matriz outra) {
        if (this.linhas != outra.linhas || this.colunas != outra.colunas) {
            throw new IllegalArgumentException("Dimensões incompatíveis para soma!");
        }
        Matriz resultado = new Matriz(this.linhas, this.colunas);
        for (int i = 0; i < this.linhas; i++) {
            for (int j = 0; j < this.colunas; j++) {
                resultado.setElemento(i, j, this.elementos[i][j] + outra.elementos[i][j]);
            }
        }
        return resultado;
    }

    public Matriz multiplicacao(Matriz outra) {
        if (this.colunas != outra.linhas) {
            throw new IllegalArgumentException("Dimensões incompatíveis para multiplicação!");
        }
        Matriz resultado = new Matriz(this.linhas, outra.colunas);
        for (int i = 0; i < this.linhas; i++) {
            for (int j = 0; j < outra.colunas; j++) {
                int soma = 0;
                for (int k = 0; k < this.colunas; k++) {
                    soma += this.elementos[i][k] * outra.elementos[k][j];
                }
                resultado.setElemento(i, j, soma);
            }
        }
        return resultado;
    }

    public void mostrarDiagonalPrincipal() {
        for (int i = 0; i < Math.min(this.linhas, this.colunas); i++) {
            System.out.print(this.elementos[i][i] + " ");
        }
        System.out.println();
    }

    public void mostrarDiagonalSecundaria() {
        for (int i = 0; i < Math.min(this.linhas, this.colunas); i++) {
            System.out.print(this.elementos[i][this.colunas - i - 1] + " ");
        }
        System.out.println();
    }

    public void exibir() {
        for (int i = 0; i < this.linhas; i++) {
            for (int j = 0; j < this.colunas; j++) {
                System.out.print(this.elementos[i][j] + " ");
            }
            System.out.println();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int casos = sc.nextInt();

        for (int caso = 0; caso < casos; caso++) {
            int linhas1 = sc.nextInt();
            int colunas1 = sc.nextInt();
            Matriz matriz1 = new Matriz(linhas1, colunas1);
            for (int i = 0; i < linhas1; i++) {
                for (int j = 0; j < colunas1; j++) {
                    matriz1.setElemento(i, j, sc.nextInt());
                }
            }

            int linhas2 = sc.nextInt();
            int colunas2 = sc.nextInt();
            Matriz matriz2 = new Matriz(linhas2, colunas2);
            for (int i = 0; i < linhas2; i++) {
                for (int j = 0; j < colunas2; j++) {
                    matriz2.setElemento(i, j, sc.nextInt());
                }
            }

            matriz1.mostrarDiagonalPrincipal();
            matriz1.mostrarDiagonalSecundaria();

            try {
                Matriz soma = matriz1.soma(matriz2);
                soma.exibir();
            } catch (IllegalArgumentException e) {
                System.out.println("Impossível somar as matrizes");
            }

            try {
                Matriz multiplicacao = matriz1.multiplicacao(matriz2);
                multiplicacao.exibir();
            } catch (IllegalArgumentException e) {
                System.out.println("Impossível multiplicar as matrizes");
            }
        }

        sc.close();
    }
}
