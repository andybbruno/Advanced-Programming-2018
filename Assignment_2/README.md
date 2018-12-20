# Advanced Programming 2018 - Solutions to <a href="http://pages.di.unipi.it/corradini/Didattica/AP-18/PROG-ASS/02//assignment2.html">Assignment 2 </a>

Andrea Bruno 585457

## Exercise 1
### Constructors and operations

```Haskell

module Ex1
(
  wf,
  empty,
  singleton,
  fromList,
  isEmpty,
  mul,
  toList,
  sumBag,
  ListBag(LB)
) 
where


data ListBag a = LB [(a, Int)] deriving (Show, Eq)

-- check whether an array contains all unique elements
-- it works by applying 'notElem' to x and (&&) on its own tail
isUnique :: Eq a => [a] -> Bool
isUnique [] = True
isUnique (x:xs) = x `notElem` xs && isUnique xs


-- wf applies 'isUnique' to all the first elements of the 'ListBag'
-- and checks if there are multiplicity less than 1 
wf :: Eq a => ListBag a -> Bool
wf (LB bag) = isUnique (fst (unzip bag)) && (not (any (<1) (snd (unzip bag))))


-- an empty ListBag
empty :: ListBag a
empty = LB []


-- a singleton of ListBag
singleton :: a -> ListBag a
singleton a = LB[(a,1)]


-- given a list like [1,1,1,1,2,2,3,4,5,5]
-- it creates a list of tuples, containing first the element and after its own cardinality
createLB :: Eq a => [a] -> [(a, Int)]
createLB [] = []
createLB (x:xs) = [(x , length (filter (==x) xs) + 1)] ++ createLB (filter (/=x) xs)


-- Given a list, returns a ListBag by using createLB
fromList :: Eq a => [a] -> ListBag a
fromList x = LB (createLB x)



--Check whether a ListBag is empty
isEmpty :: Eq a => ListBag a -> Bool
isEmpty (LB bag) = bag == []


-- given an element 'v' and a list of tuples 'x' in this format ([(1,2),(3,1),(4,2),(8,1)])
-- it filters out only the tuple belonging to 'v'
-- it works by appliyng (==v) to all the first elements of each tuple
myFilter :: Eq a => a -> [(a, b)] -> [(a, b)]
myFilter v [] = []
myFilter v x = filter (\y -> (==v) (fst y)) x


-- given an element 'v' and a ListBag, it returns the muliplicty of the element 'v' wihitin the ListBag
mul :: Eq a => a -> ListBag a -> Int
mul v (LB bag) = if (myFilter v bag) == [] then 0 else snd(head(myFilter v bag))

 
-- given a list of tuples in this format ([(1,2),(3,1),(4,2),(8,1)])
-- it returns the list representation according to each element multiplicity --> [1,1,3,4,4,8]
genList :: [(a, Int)] -> [a]
genList [] = [] 
genList x = take (snd(head x)) (repeat (fst(head x))) ++ genList (tail x)


-- Given a ListBag return its own list representation
toList :: ListBag a -> [a]
toList (LB bag) = genList bag


-- Given two ListBag it merges them in a single ListBag.
-- The main idea is to transform every ListBag into its list representation
-- and after, exploiting ++ we merge the two lists. 
-- Finally, once obtained a single list, I transform it to a ListBag.
sumBag :: Eq a => ListBag a -> ListBag a -> ListBag a
sumBag (LB bag) (LB bag') = fromList ((genList bag) ++ (genList bag'))

```

## Exercise 2
### Mapping and folding

```Haskell
module Ex2
(
    mapLB,
) 
where

import Ex1 (
   ListBag(LB),
   empty,
   singleton,
   fromList,
   isEmpty,
   mul,
   toList,
   sumBag )


-- Create an istance of Foldable by implementing the foldr.
-- Basically the foldr over a ListBag is made by applying 
-- recursively (from left to right) the function f, 
-- starting from z, at each first component of the tuples in the ListBag. 
instance Foldable ListBag where
   foldr f z (LB []) = z
   foldr f z (LB bag) = foldr f z (fst (unzip bag))


-- mapLB applies f at each element of ListBag, 
-- and returns a ListBag containing the results of each step.
-- For instance: 
-- Prelude> mapLB odd (LB([(1,2),(3,1),(4,2),(8,1)]))
-- LB [(True,3),(False,3)]
mapLB :: Eq b => (a -> b) -> ListBag a -> ListBag b
mapLB f bag = fromList (map f (toList bag))
```


It's not possible to create an instance of Functor
because the fmap required by *Functor* has type:
```Haskell
    fmap :: (a -> b) -> f a -> f b
```
which basically says: give me a function that takes "a" and return "b"
and a box with an "a" (or several of them) inside it 
and I'll give you a box with a "b" (or several of them) inside it.



> _If you will give me a blueberry for each apple that I give you (a -> b),
and I have a box of apples (f a), then I can get a box of blueberries (f b)._



Our *mapLB* has type:
```Haskell
    mapLB :: Eq b => (a -> b) -> ListBag a -> ListBag b
``` 
Basically, we get a *ListBag* and we return another *ListBag*.

So it's completely different from the "fmap" that *Functor* requires.


# Exercise 3 
## Basic structures

```Java

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

```

# Exercise 4
## Methods using the Stream API

```Java
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
```

# Exercise 5
## Multisets as Monad (Optional)


# Exercise 6
## Abstract Data Type (Optional)
