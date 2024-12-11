import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        long tempoInicio = System.currentTimeMillis(); // Início da contagem de tempo

        Scanner scanner = new Scanner(System.in);

        String entrada;
        int entradaInteiro;

        // Ler o CSV e armazenar os Pokémons em uma lista
        List<Pokemon> pokemons = ReadCsv.readAllFile("/tmp/pokemon.csv");

        // Inicializar a árvore binária de pesquisa
        ArvoreBinariaPesquisa arvore = new ArvoreBinariaPesquisa();

        // Inserir Pokémons na árvore com base nos IDs fornecidos
        while (!(entrada = scanner.nextLine()).equals("FIM")) {
            try {
                entradaInteiro = Integer.parseInt(entrada);
                Pokemon pokemonEncontrado = PokemonSearch.searchPokemonId(pokemons, entradaInteiro);
                if (pokemonEncontrado != null) {
                    arvore.inserir(pokemonEncontrado);
                }
            } catch (NumberFormatException e) {
                System.err.println("Entrada inválida: " + entrada);
            }
        }

        // Pesquisar nomes na árvore e exibir os caminhos
        while (!(entrada = scanner.nextLine()).equals("FIM")) {
            StringBuilder caminho = new StringBuilder("raiz");
            boolean encontrado = arvore.pesquisar(entrada, caminho);
            if (encontrado) {
                System.out.println(entrada + "\n=>" + caminho + " SIM");
            } else {
                System.out.println(entrada + "\n=>" + caminho + " NAO");
            }
        }

        scanner.close();

        // Calcular o tempo de execução
        long tempoFim = System.currentTimeMillis();
        long tempoExecucao = tempoFim - tempoInicio;

        // Criar o arquivo de log
        String nomeArquivo = "859093_arvoreBinaria.txt";
        try (FileWriter writer = new FileWriter(nomeArquivo)) {
            writer.write("859093\t" + tempoExecucao + "\t" + arvore.getNumeroComparacoes() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Pokemon {
    private int id;
    private int geracao;
    private String nome;
    private String descricao;
    private ArrayList<String> tipos;
    private ArrayList<String> habilidades;
    private double peso;
    private double altura;
    private int taxaCaptura;
    private boolean ehLendario;
    private Date dataCaptura;

    public Pokemon() {
        this.id = 0;
        this.geracao = 0;
        this.nome = "";
        this.descricao = "";
        this.tipos = new ArrayList<>();
        this.habilidades = new ArrayList<>();
        this.peso = 0.0;
        this.altura = 0.0;
        this.taxaCaptura = 0;
        this.ehLendario = false;
        this.dataCaptura = new Date();
    }

    public Pokemon(int id, int geracao, String nome, String descricao, ArrayList<String> tipos,
                   ArrayList<String> habilidades, double peso, double altura, int taxaCaptura,
                   boolean ehLendario, Date dataCaptura) {
        this.id = id;
        this.geracao = geracao;
        this.nome = nome;
        this.descricao = descricao;
        this.tipos = tipos;
        this.habilidades = habilidades;
        this.peso = peso;
        this.altura = altura;
        this.taxaCaptura = taxaCaptura;
        this.ehLendario = ehLendario;
        this.dataCaptura = dataCaptura;
    }

    public Pokemon(String[] aux) throws Exception {
        for (int i = 0; i < aux.length; i++) if (aux[i].isEmpty()) aux[i] = "0";

        this.id = Integer.parseInt(aux[0]);
        this.geracao = Integer.parseInt(aux[1]);
        this.nome = aux[2];
        this.descricao = aux[3];

        this.tipos = new ArrayList<>();
        aux[4] = "'" + aux[4] + "'";
        this.tipos.add(aux[4]);
        if (aux[5] != "0") {
            aux[5] = "'" + aux[5].trim() + "'";
            this.tipos.add(aux[5]);
        }

        aux[6] = aux[6].replace("\"", "").replace("[", "").replace("]", "");
        String[] tmp = aux[6].split(",");
        this.habilidades = new ArrayList<>();
        for (String s : tmp) habilidades.add(s.trim());

        this.peso = Double.parseDouble(aux[7]);
        this.altura = Double.parseDouble(aux[8]);

        this.taxaCaptura = Integer.parseInt(aux[9]);

        this.ehLendario = aux[10].equals("1");

        if (aux[11].isEmpty()) {
            this.dataCaptura = null;
        } else {
            SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
            this.dataCaptura = formatoData.parse(aux[11]);
        }
    }

    public int getId() { return id; }
    public String getName() { return nome; }
}

class NoArvore {
    Pokemon dados;
    NoArvore esquerda, direita;

    public NoArvore(Pokemon dados) {
        this.dados = dados;
        this.esquerda = null;
        this.direita = null;
    }
}

class ArvoreBinariaPesquisa {
    private NoArvore raiz;
    private int numeroComparacoes;

    public ArvoreBinariaPesquisa() {
        this.raiz = null;
        this.numeroComparacoes = 0;
    }

    public boolean inserir(Pokemon dados) {
        if (raiz == null) {
            raiz = new NoArvore(dados);
            return true;
        } else {
            return inserirRecursivo(raiz, dados);
        }
    }

    private boolean inserirRecursivo(NoArvore no, Pokemon dados) {
        numeroComparacoes++;
        if (dados.getName().equals(no.dados.getName())) {
            return false; // Elemento já está na árvore
        } else if (dados.getName().compareTo(no.dados.getName()) < 0) {
            if (no.esquerda == null) {
                no.esquerda = new NoArvore(dados);
                return true;
            } else {
                return inserirRecursivo(no.esquerda, dados);
            }
        } else {
            if (no.direita == null) {
                no.direita = new NoArvore(dados);
                return true;
            } else {
                return inserirRecursivo(no.direita, dados);
            }
        }
    }

    public boolean pesquisar(String nome, StringBuilder caminho) {
        return pesquisarRecursivo(raiz, nome, caminho);
    }

    private boolean pesquisarRecursivo(NoArvore no, String nome, StringBuilder caminho) {
        if (no == null) {
            return false; // Não encontrado
        }

        numeroComparacoes++;
        if (nome.equals(no.dados.getName())) {
            return true;
        } else if (nome.compareTo(no.dados.getName()) < 0) {
            caminho.append(" esq");
            return pesquisarRecursivo(no.esquerda, nome, caminho);
        } else {
            caminho.append(" dir");
            return pesquisarRecursivo(no.direita, nome, caminho);
        }
    }

    public int getNumeroComparacoes() {
        return numeroComparacoes;
    }
}

class PokemonSearch {
    public static Pokemon searchPokemonId(List<Pokemon> pokemons, int id) {
        for (Pokemon pokemon : pokemons) {
            if (pokemon.getId() == id) {
                return pokemon;
            }
        }
        return null;
    }
}

class ReadCsv {
    public static List<Pokemon> readAllFile(final String fileToRead) {
        List<Pokemon> personagens = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileToRead));

            // Pular o cabeçalho
            br.readLine();

            // Ler linha por linha
            String csvLine = new String();
            while ((csvLine = br.readLine()) != null) {
                csvLine = lineFormat(csvLine);
                Pokemon pessoa = new Pokemon(csvLine.split(";"));
                personagens.add(pessoa);
            }

            br.close();
        } catch (Exception e) {
            e.printStackTrace();
            personagens = null;
        }

        return personagens;
    }

    private static String lineFormat(String line) {
        char[] array_aux = line.toCharArray();
        boolean in_list = false;
        for (int i = 0; i < array_aux.length; i++) {
            if (!in_list && array_aux[i] == ',') array_aux[i] = ';';
            else if (array_aux[i] == '"') in_list = !in_list;
        }

        return new String(array_aux);
    }
}
