
/**
 *
 * @author Andrea Bruno 585457
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collection;
import java.util.LinkedHashSet;

public class WinnerImpl implements Winner {

    private int index;
    private int year;
    private int age;
    private String name;
    private String movie;

    static Collection<Winner> loadData(String[] string) {

        // I used LinkedHashSet because it's the faster data structure in Java
        // for more info: https://github.com/andybbruno/JavaDS_Complexity
        Collection<Winner> winners = new LinkedHashSet<>();

        try {
            for (String path : string) {
                //path = System.getProperty("user.dir") + "/src/" + path;
                BufferedReader buffer = new BufferedReader(new FileReader(path));

                // remove first line
                buffer.readLine();

                // my regular expression
                String regexQuote = "\"";
                String regexComma = ",";

                String line;

                // iterate until the end
                while ((line = buffer.readLine()) != null) {
                    WinnerImpl winner = new WinnerImpl();

                    //parsing using quote char (") as delimiter
                    String[] noQuotes = line.split(regexQuote);
                    //once removed the qoutes, then split by using comma as delimiter
                    String[] noComma = noQuotes[0].split(regexComma);

                    winner.index = Integer.parseInt(noComma[0]);
                    winner.year = Integer.parseInt(noComma[1]);
                    winner.age = Integer.parseInt(noComma[2]);
                    winner.name = noQuotes[1];
                    winner.movie = noQuotes[3];

                    //add this winner to the LinkedHashSet
                    winners.add(winner);
                }
                buffer.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return winners;

    }

    public String toString() {
        String tmp = String.format("%4d | %4d | %3d | %25s | %s",
                this.index, this.year, this.age, this.name, this.movie);
        return tmp;
    }

    @Override
    public int getYear() {
        return this.year;
    }

    @Override
    public int getWinnerAge() {
        return this.age;
    }

    @Override
    public String getWinnerName() {
        return this.name;
    }

    @Override
    public String getFilmTitle() {
        return this.movie;
    }
}
