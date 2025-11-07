import java.io.IOException;
import java.util.*;

public class Main {
    //Speicher
    static Stack<Integer> stack = new Stack<>();
    static String shout = "";
    static Map<String, Liquidant> liquidants = new HashMap<>();
    static Map<String, Instrument> instruments = new HashMap<>();


    public static void main(String[] args) throws IOException {

        // Import des Code
        Scanner sc = new Scanner(System.in);
        System.out.println("Gib den Ort deiner Voldergammer an!");
        String path =  sc.nextLine();
        sc.close();
        FileReader fileReader = new FileReader(path);
        List<String> code = fileReader.readFile();


        //Befehle
        for (int i = 0; i < code.size(); i++) {
            String line = code.get(i).trim();
            if (line.isEmpty())
                continue;
            String[] parts =  line.split("-");
            String command = parts[0].toUpperCase();

            switch (command) {
                // Für Zahlen
                case "BEFEHLE":
                    stack.push(Integer.parseInt(parts[1]));
                    break;
                case "ELIMINIERE":
                    stack.pop();
                    break;
                case "KREUZE":
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "SCHLACHTE":
                    int a  = stack.pop();
                    int b = stack.pop();
                    stack.push(a - b);
                    break;
                case "VERNICHTE":
                    System.out.println(stack.pop());
                    break;
                case "MARSCHIERE":
                    i = Integer.parseInt(parts[1])-1;
                    break;
                case "RICHTE":
                    if (Integer.parseInt(parts[1]) != stack.peek())
                        i++;
                    break;
                // Für Strings
                case "SCHREYBE":
                    shout = parts[1];
                    break;
                case "STUDIERE":
                    stack.push(shout.length());
                    break;
                case "SCHREYE":
                    System.out.println(shout);
                    break;
                // Für Liquidanten
                case "INTERNIERE":
                    liquidants.put(parts[1], new Liquidant(Integer.parseInt(parts[2]), parts[3]));
                    break;
                case "UNTERSUCHE":
                    stack.push(liquidants.get(parts[1]).health);
                    break;
                case "DÖDE":
                    liquidants.remove(parts[1]);
                // Für Instrumente
                case "BAUE":
                    String iCommandString = parts[2];
                    List<String> iCommands = new ArrayList<>(Arrays.asList(iCommandString.split("_")));
                    instruments.put(parts[1], new Instrument(iCommands));
                    break;
                case "VOLDERE":
                    String nameOfLiquidant = parts[1];
//                    if (!liquidants.containsKey(nameOfLiquidant)){
//
//                    }
                    instruments.get(parts[2]).torture(liquidants.get(nameOfLiquidant));
                    liquidants.get(nameOfLiquidant).health --;
                    if (liquidants.get(nameOfLiquidant).health < 0)
                        liquidants.remove(nameOfLiquidant);
                    break;
            }
        }
    }
}


