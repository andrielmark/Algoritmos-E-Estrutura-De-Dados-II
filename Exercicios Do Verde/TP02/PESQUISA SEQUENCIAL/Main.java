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
        long startTime = System.currentTimeMillis(); // Início da contagem de tempo
        int numComparacoes = 0; // Contador de comparações

        boolean pesquisado = false;
        Scanner sc = new Scanner(System.in);

        String input_string = new String();
        int input_int = 0;

        // Ler o CSV, e passar tudo para uma List
        List<Pokemon> pokemons = ReadCsv.readAllFile("/tmp/pokemon.csv");

        // List que iremos adicionar apenas os selecionados da entrada, para trabalhar so com eles
        List<Pokemon> using = new ArrayList<>();

        // Ler ate FIM, e procurar por ID, e adicionar na List se não for nulo
        while (!(input_string = sc.nextLine()).equals("FIM")) {
            try {
                input_int = Integer.parseInt(input_string);
                Pokemon foundPokemon = PokemonSearch.searchPokemonId(pokemons, input_int);
                if (foundPokemon != null) {
                    using.add(foundPokemon);
                } else {
                    System.err.println("Pokémon com ID " + input_int + " não encontrado.");
                }
            } catch (NumberFormatException e) {
                System.err.println("Entrada inválida: " + input_string);
            }
        }

        // Segunda parte: procurar por nome na lista using
        while (!(input_string = sc.nextLine()).equals("FIM")) {
            pesquisado = false;  // Reinicializa antes de cada pesquisa
            for (Pokemon p : using) {
                numComparacoes++; // Incrementa a contagem de comparações
                if (input_string.equals(p.getName())) {
                    pesquisado = true;
                    break;
                }
            }
            if (pesquisado) {
                System.out.println("SIM");
            } else {
                System.out.println("NAO");
            }
        }

        sc.close();
        
        // Calcula o tempo de execução
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        // Criar o arquivo de log
        String fileName = "matricula_sequencial.txt";
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("859093\t" + executionTime + "\t" + numComparacoes + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Pokemon {
    // Atributos privados
    private int id;
    private int generation;
    private String name;
    private String description;
    private ArrayList<String> types;
    private ArrayList<String> abilities;
    private double weight;
    private double height;
    private int captureRate;
    private boolean isLegendary;
    private Date captureDate;

    // Construtores
    public Pokemon() {
        this.id = 0;
        this.generation = 0;
        this.name = "";
        this.description = "";
        this.types = new ArrayList<>();
        this.abilities = new ArrayList<>();
        this.weight = 0.0;
        this.height = 0.0;
        this.captureRate = 0;
        this.isLegendary = false;
        this.captureDate = new Date();
    }

    public Pokemon(int id, int generation, String name, String description, ArrayList<String> types, 
                   ArrayList<String> abilities, double weight, double height, int captureRate, 
                   boolean isLegendary, Date captureDate) {
        this.id = id;
        this.generation = generation;
        this.name = name;
        this.description = description;
        this.types = types;
        this.abilities = abilities;
        this.weight = weight;
        this.height = height;
        this.captureRate = captureRate;
        this.isLegendary = isLegendary;
        this.captureDate = captureDate;
    }

    public Pokemon(String[] aux) throws Exception {
        for (int i = 0; i < aux.length; i++) if (aux[i].isEmpty()) aux[i] = "0";

        this.id = Integer.parseInt(aux[0]);
        this.generation = Integer.parseInt(aux[1]);
        this.name = aux[2];
        this.description = aux[3];

        this.types = new ArrayList<>();
        aux[4] = "'" + aux[4] + "'";
        this.types.add(aux[4]);
        if (aux[5] != "0") {
            aux[5] = "'" + aux[5].trim() + "'";
            this.types.add(aux[5]);
        }

        aux[6] = aux[6].replace("\"", "").replace("[", "").replace("]", "");
        String[] tmp = aux[6].split(",");
        this.abilities = new ArrayList<>();
        for (String s : tmp) abilities.add(s.trim());

        this.weight = Double.parseDouble(aux[7]);
        this.height = Double.parseDouble(aux[8]);

        this.captureRate = Integer.parseInt(aux[9]);

        this.isLegendary = aux[10].equals("1");

        if (aux[11].isEmpty()) {
            this.captureDate = null;
        } else {
            SimpleDateFormat inputDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            this.captureDate = inputDateFormat.parse(aux[11]);
        }
    }

    public int getId() { return id; }
    public String getName() { return name; }
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
