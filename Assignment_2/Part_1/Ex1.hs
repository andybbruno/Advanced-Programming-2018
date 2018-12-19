{-
	http://pages.di.unipi.it/corradini/Didattica/AP-18/PROG-ASS/02//assignment2.html
	Exercise 1: Constructors and operations
  
	@author Andrea Bruno 585457
-}


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
