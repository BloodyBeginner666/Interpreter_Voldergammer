import java.util.ArrayList;
import java.util.List;

public class Instrument {
    List<String> methods = new ArrayList<String>();

    public Instrument(List<String> methods) {
        this.methods = methods;
    }

    public void torture(Liquidant liquidant) {
        for (int i = 0; i < methods.size(); i++) {
            String line = methods.get(i).trim();
            if (line.isEmpty())
                continue;
            String[] parts = line.split(":");
            String command = parts[0].toUpperCase();

            switch (command) {
                // Für Zahlen
                case "UNTERSUCHE":
                    Main.stack.push(liquidant.health);
                    break;
                case "DÖDE":
                    Main.stack.pop();
                    break;
                case "KREUZE":
                    Main.stack.push(Main.stack.pop() + Main.stack.pop());
                    break;
                case "SCHLACHTE":
                    int a = Main.stack.pop();
                    int b = Main.stack.pop();
                    Main.stack.push(a - b);
                    break;
                case "EXEKUTIERE":
                    System.out.println(Main.stack.peek());
                    break;
                case "MARSCHIERE":
                    i = liquidant.health;
                    break;
                case "RICHTE":
                    if (Integer.parseInt(parts[1]) != liquidant.health)
                        i++;
                    break;
                // Für Strings
                case "STUDIERE":
                    Main.stack.push(liquidant.shout.length());
                    break;
                case "SCHREYE":
                    System.out.println(liquidant.shout);
                    break;
            }
        }
    }
}
