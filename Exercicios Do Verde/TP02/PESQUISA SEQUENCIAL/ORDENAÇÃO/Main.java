import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input_string = new String();
        int input_int = 0;

        // Ler o CSV, e passar tudo para uma List
        List<Pokemon> pokemons = ReadCsv.readAllFile("/tmp/pokemon.csv");

        // List que iremos adicionar apenas os selecionados da entrada, para trabalhar so com eles
        List<Pokemon> using = new ArrayList<>();

        // Ler ate FIM, e procurar por ID, e adicionar na List
        while (!(input_string = sc.nextLine()).equals("FIM")) {
            input_int = Integer.parseInt(input_string);
            using.add(PokemonSearch.searchPokemonId(pokemons, input_int));
        }

        // Implementação do Selection Sort baseado no nome dos Pokemons
        int comparisons = 0;
        int movements = 0;
        long startTime = System.nanoTime(); // Início do tempo

        for (int i = 0; i < using.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < using.size(); j++) {
                comparisons++;
                if (using.get(j).getName().compareTo(using.get(minIndex).getName()) < 0) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                movements++;
                Pokemon temp = using.get(i);
                using.set(i, using.get(minIndex));
                using.set(minIndex, temp);
            }
        }

        long endTime = System.nanoTime(); // Fim do tempo
        long duration = endTime - startTime; // Duração do algoritmo

        // Printar instancia por instancia da List
        for (Pokemon p : using) System.out.println(p);

        // Criar arquivo de log
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("matricula_selecao.txt"))) {
            writer.write("859093\t" + comparisons + "\t" + movements + "\t" + (duration / 1_000_000.0) + " ms");
        } catch (IOException e) {
            e.printStackTrace();
        }

        sc.close();
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

        // Setar atributo por atributo
        this.id = Integer.parseInt(aux[0]);
        this.generation = Integer.parseInt(aux[1]);

        this.name = aux[2];
        this.description = aux[3];

        this.types = new ArrayList<>();
        aux[4] = "'" + aux[4] + "'";
        this.types.add(aux[4]);
        if (!aux[5].equals("0")) {
            aux[5] = "'" + aux[5].trim() + "'";
            this.types.add(aux[5]);
        }

        aux[6] = aux[6].replace("\"", "");
        aux[6] = aux[6].replace("[", "");
        aux[6] = aux[6].replace("]", "");
        String[] tmp = aux[6].split(",");
        this.abilities = new ArrayList<>();
        for (String s : tmp) abilities.add(s.trim());

        this.weight = Double.parseDouble(aux[7]);
        this.height = Double.parseDouble(aux[8]);

        this.captureRate = Integer.parseInt(aux[9]);

        this.isLegendary = (aux[10].equals("1"));

        if (aux[11].isEmpty()) {
            this.captureDate = null;
        } else {
            SimpleDateFormat inputDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            this.captureDate = inputDateFormat.parse(aux[11]);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGeneration() {
        return generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<String> types) {
        this.types = types;
    }

    public ArrayList<String> getAbilities() {
        return abilities;
    }

    public void setAbilities(ArrayList<String> abilities) {
        this.abilities = abilities;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getCaptureRate() {
        return captureRate;
    }

    public void setCaptureRate(int captureRate) {
        this.captureRate = captureRate;
    }

    public boolean getIsLegendary() {
        return isLegendary;
    }

    public void setIsLegendary(boolean isLegendary) {
        this.isLegendary = isLegendary;
    }

    public Date getCaptureDate() {
        return captureDate;
    }

    public void setCaptureDate(Date captureDate) {
        this.captureDate = captureDate;
    }

    // Método clone
    public Pokemon clone() {
        try {
            return (Pokemon) super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("Cloning not supported.");
            return null;
        }
    }

    public String toString() {
        SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = (captureDate != null) ? outputDateFormat.format(captureDate) : "Data não disponível";

        return "[#" + id + " -> " + name + ": " + description +
                " - " + types + " - " + abilities +
                " - " + weight + "kg - " + height + "m - " +
                captureRate + "% - " + isLegendary +
                " - " + generation + " gen] - " + formattedDate;
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

            // Pular o cabecalho
            br.readLine();

            // Ler linha por linha
            String csvLine;
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