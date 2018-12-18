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
wf :: Eq a => ListBag a -> Bool
wf (LB bag) = isUnique (fst (unzip bag))


empty :: ListBag a
empty = LB []


singleton :: a -> ListBag a
singleton a = LB[(a,1)]


-- create a list of tuples like LB, containing each element and its own cardinality
createLB :: Eq a => [a] -> [(a, Int)]
createLB [] = []
createLB (x:xs) = [(x , length (filter (==x) xs) + 1)] ++ createLB (filter (/=x) xs)


fromList :: Eq a => [a] -> ListBag a
fromList x = LB (createLB x)


isEmpty :: Eq a => ListBag a -> Bool
isEmpty (LB bag) = bag == []


-- given a list of tuples 'x' returns all the instances in 'x' equal to 'v'
myFilter :: Eq a => a -> [(a, b)] -> [(a, b)]
myFilter v [] = []
myFilter v x = filter (\y -> (==v) (fst y)) x


mul :: Eq a => a -> ListBag a -> Int
mul v (LB bag) = if (myFilter v bag) == [] then 0 else snd(head(myFilter v bag))

 
-- given a LB-like list of tuples, returns its list representation
genList :: [(a, Int)] -> [a]
genList [] = [] 
genList x = take (snd(head x)) (repeat (fst(head x))) ++ genList (tail x)


toList :: ListBag a -> [a]
toList (LB bag) = genList bag


sumBag :: Eq a => ListBag a -> ListBag a -> ListBag a
sumBag (LB bag) (LB bag') = fromList ((genList bag) ++ (genList bag'))
