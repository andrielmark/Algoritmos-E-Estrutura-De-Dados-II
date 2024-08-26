#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>


void braba01(){
    int ok;
}
int main() {
    char linha[100];
    bool fim =true;
    int contador = 0;
    while(fim==true){
        scanf("%[^\n]%*c", linha);
        if(linha[0]=='F' && linha[1]=='I' && linha[2]=='M'){
            fim=false;
        }
        else{
            for (int i = 0; linha[i] != '\0'; i++) {
                if (linha[i] >= 'A' && linha[i] <= 'Z') {
                    contador++;
                }
            }
            printf("%d\n", contador);
            contador=0;
        }
    }



  return 0;
}