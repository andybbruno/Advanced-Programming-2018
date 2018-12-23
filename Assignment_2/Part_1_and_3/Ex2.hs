{-
	http://pages.di.unipi.it/corradini/Didattica/AP-18/PROG-ASS/02//assignment2.html
	Exercise 2: Constructors and operations

	@author Andrea Bruno 585457
-}



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


{-
	It's not possible to create an instance of Functor
	because the fmap required by Functor has type:
	    fmap :: (a -> b) -> f a -> f b
	which basically says: give me a function that takes "a" and return "b"
	and a box with an "a" (or several of them) inside it 
	and I'll give you a box with a "b" (or several of them) inside it.


	Or: 
	<< If you will give me a blueberry for each apple that I give you (a -> b), 
	and I have a box of apples (f a), then I can get a box of blueberries (f b). >>


	Our mapLB has type:
	    mapLB :: Eq b => (a -> b) -> ListBag a -> ListBag b
	So, because of the Eq constrain, we can't create an "fmap" as Functor requires.
-}
   
