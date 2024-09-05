import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanf = new Scanner(System.in);
        int n = 1;
        int m = 1;

        while (n != 0 && m != 0) {
            n = scanf.nextInt();
            m = scanf.nextInt();

            if (n == 0 && m == 0) {
                break;
            }

            int[] array = new int[n];
            for (int i = 0; i < n; i++) {
                array[i] = scanf.nextInt();
            }

            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    int moduloA = array[j] % m;
                    int moduloB = array[j + 1] % m;

                    if (moduloA > moduloB) {
                        int temp = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = temp;
                    } else if (moduloA == moduloB) {
                        boolean verificaA = array[j] % 2 != 0;
                        boolean verificaB = array[j + 1] % 2 != 0;

                        if (verificaA && !verificaB) {
                        } else if (!verificaA && verificaB) {
                            int temp = array[j];
                            array[j] = array[j + 1];
                            array[j + 1] = temp;
                        } else if (verificaA && verificaB) {
                            if (array[j] < array[j + 1]) {
                                int temp = array[j];
                                array[j] = array[j + 1];
                                array[j + 1] = temp;
                            }
                        } else if (!verificaA && !verificaB) {
                            if (array[j] > array[j + 1]) {
                                int temp = array[j];
                                array[j] = array[j + 1];
                                array[j + 1] = temp;
                            }
                        }
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                System.out.println(array[i]);
            }
        }

        scanf.close();
    }
}
