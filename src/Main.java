import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {

        ArrayList<BankStatement> bankStatements = new ArrayList<>();
        List<String> lines = null;
        String contractor = null;
        String currency = null;
        TreeMap<String, Double> transactions = new TreeMap<>();
        try {
            lines = Files.readAllLines(Paths.get("data/movementList.csv"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String line : lines) {
            String[] fragments = line.split(",");
            String[] subFragments = fragments[5].split("\\s{2,}");
            for (String subFragment : subFragments) {
                if (subFragment.contains("\\")) {
                    contractor = subFragment.substring(subFragment.indexOf("\\"));
                }
                if (subFragment.contains("/")) {
                    contractor = subFragment.substring(subFragment.indexOf("/"));
                }
                if (subFragment.contains("RUR") || subFragment.contains("EUR") || subFragment.contains("USD")) {
                    currency = subFragment.substring(0, 3);
                }
            }
            if (fragments[0].equals("Тип счёта")) {
                continue;
            }
            if (fragments.length == 8) {
                bankStatements.add(new BankStatement(contractor + " " + currency, Double.parseDouble(fragments[6]), Double.parseDouble(fragments[7])));
            }
            if (fragments.length == 9) {
                bankStatements.add(new BankStatement(contractor + " " + currency, Double.parseDouble(fragments[6]), Double.parseDouble(fragments[7].replace("\"", "") + "." + fragments[8].replace("\"", ""))));
            }

        }

        System.out.println("Все поступления по счету " + bankStatements.stream().mapToDouble(d -> d.getArrivalMoney()).sum() + " руб.");
        System.out.println("Все расходы по счету " + bankStatements.stream().mapToDouble(d -> d.getExpenses()).sum() + " руб.");

        for (BankStatement bankStatement : bankStatements) {
            if (bankStatement.getExpenses() > 0) {
                updateValue(transactions, bankStatement.getContractor(), bankStatement.getExpenses());
            }
        }

        System.out.println("=================================================" + "\n" + "Расходы по контрагентам: ");
        printMap(transactions);
    }

    private static void updateValue(Map<String, Double> map, String key, Double value) {
        if (map.containsKey(key)) {
            map.put(key, map.get(key) + value);
        } else {
            map.put(key, value);
        }
    }

    private static void printMap(Map<String, Double> map) {
        for (String key : map.keySet()) {
            System.out.println(key + "\t=>\t" + map.get(key) + " руб.");
        }
    }
}
