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
wf :: Eq a => ListBag a -> Bool
wf (LB bag) = isUnique (fst (unzip bag))


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
Basically, we get ListBag and we return another ListBag.
So it's completely different from the "fmap" that *Functor* requires.
