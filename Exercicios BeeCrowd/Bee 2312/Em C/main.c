#include <stdio.h>
#include <string.h>

typedef struct{
    char nome[50];
    int ouro;
    int prata;
    int bronze;
}Pais;

int main(){
        int n = 0;
        char nome[50];
        int ouro = 0;
        int prata = 0;
        int bronze = 0;

        scanf(" %d", &n);
        Pais array[100]; 

        for(int i = 0; i < n; i++){
            scanf(" %s", nome);
            scanf(" %d", &ouro);
            scanf(" %d", &prata);
            scanf(" %d", &bronze);
            strcpy(array[i].nome, nome);
            array[i].ouro = ouro;
            array[i].prata = prata;
            array[i].bronze = bronze;
        }

        for (int i = 0; i < n; i++){
            int maior = i;
            for(int j = i + 1; j < n; j++){
                if(array[maior].ouro != array[j].ouro){
                    if(array[maior].ouro < array[j].ouro){
                        maior=j;
                    }
                } else if(array[maior].prata != array[j].prata){
                    if(array[maior].prata < array[j].prata){
                        maior=j;
                    }
                } else if(array[maior].bronze != array[j].bronze){
                    if(array[maior].bronze < array[j].bronze){
                        maior=j;
                    }
                } else if(strcmp(array[maior].nome, array[j].nome)){
                    maior=j;
                } 
            }
            Pais temp = array[i];
            array[i] = array[maior];
            array[maior] = temp;
        }
        for(int i = 0; i < n; i++){
            printf("%s %d %d %d\n", array[i].nome, array[i].ouro, array[i].prata, array[i].bronze);
        }
        

}

