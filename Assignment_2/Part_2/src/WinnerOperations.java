
/**
 *
 * @author Andrea Bruno 585457
 */
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WinnerOperations {

    //a simple separator used to split the results
    private final static String EOF = "\n\n";

    /**
     * ...method oldWinners that given a Stream<Winner> returns a new
     * Stream<String> containing the names of the winners that are older than 35
     * sorted alphabetically.
     */
    public static Stream<String> oldWinners(Stream<Winner> stream) {

        // first I apply a filter based on the age
        Stream<String> myStream = stream.filter(w -> w.getWinnerAge() > 35)
                // then I apply "sorted" by passing a comparator created on the fly with a
                // lambda
                // which basically compare two winners by exploiting the "compareTo" of the
                // strings
                .sorted((w1, w2) -> w1.getWinnerName().compareTo(w2.getWinnerName()))
                // finally get winners names
                .map(x -> x.getWinnerName())
                //remove duplicates
                .distinct();

        myStream = Stream.concat(myStream, Stream.of(EOF));
        return myStream;
    }

    /**
     * ... method extremeWinners that given a Stream<Winner> returns a
     * Stream<String> containing the names of all the youngest and of all the
     * oldest winners, sorted in inverse alphabetical ordering.
     */
    public static Stream<String> extremeWinners(Stream<Winner> stream) {

        // Since a stream cannot be processed twice, I use an ArrayList to store as a
        // cache.
        // In particular, I used "collect" by passing a new ArrayList as supplier in
        // which I add all elements of the stream
        List<Winner> list = stream.collect(Collectors.toList());

        // retrieve the the minimum age and the maximum age
        int min = list.stream().mapToInt(w -> w.getWinnerAge()).min().getAsInt();
        int max = list.stream().mapToInt(w -> w.getWinnerAge()).max().getAsInt();

        // filter by applying the minimum to the stream
        Stream<String> winners = list.stream().filter(x -> x.getWinnerAge() == min || x.getWinnerAge() == max)
                // to get the inverse order I simply swap "w2" with "w1" within "compareTo"
                .map(w -> w.getWinnerName()).sorted((w1, w2) -> w2.compareTo(w1));

        winners = Stream.concat(winners, Stream.of(EOF));
        return winners;
    }

    /**
     * ... method multiAwardedFilm that given a Stream<Winner> returns a
     * Stream<String> containing the title of films who won two prizes. The
     * elements of the stream must be in chronological order, i.e. in increasing
     * order of year of the corresponding records in the database.
     */
    public static Stream<String> multiAwardedFilm(Stream<Winner> stream) {
        // temporary data structure
        Set<String> temp = new HashSet<String>();

        // to get all duplicates I simply add the titles to the HashSet
        // if this action fails, then it means that this key is already into the set.
        Stream<String> awdFilm = stream
                .filter(e -> !temp.add(e.getFilmTitle()))
                // to sort I simply create a lambda with a comparator
                .sorted((o1, o2) -> o1.getYear() - o2.getYear())
                .map(x -> x.getFilmTitle());

        awdFilm = Stream.concat(awdFilm, Stream.of(EOF));
        return awdFilm;
    }

    /**
     * ... method runJobs that given a Stream<Function<Stream<T>,Stream<U>>> of
     * jobs and a Collection<T> coll returns a Stream<U> obtained by
     * concatenating the results of the execution of all the jobs on the data
     * contained in coll.
     */
    public static <T, U> Stream<U> runJobs(Stream<Function<Stream<T>, Stream<U>>> input, Collection<T> coll) {

        Stream<U> runFun = input
                // Apply "coll" to every function in the stream of functions
                .map(x -> x.apply(coll.stream()))
                // Store the result of these function in a List
                .collect(Collectors.toList())
                // get the stream from that list
                .stream()
                // finally do a "reduce" by simply concatenating
                // all the results given from the functions
                .reduce(Stream::concat)
                // this avoid the Optional type made by "reduce"
                .orElseGet(Stream::empty);

        return runFun;
    }

    public static void main(String[] args) {

        String[] data = new String[2];

        data[0] = "oscar_age_male.csv";
        data[1] = "oscar_age_female.csv";

        Collection<Winner> dataLoaded = WinnerImpl.loadData(data);

        // create the stream of functions
        Stream<Function<Stream<Winner>, Stream<String>>> winnFunc = Stream.of(
                WinnerOperations::oldWinners,
                WinnerOperations::extremeWinners, 
                WinnerOperations::multiAwardedFilm);

        Stream<String> results = runJobs(winnFunc, dataLoaded);
        results.forEach(System.out::println);
    }
}
