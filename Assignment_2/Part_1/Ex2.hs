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

import Ex1

instance Foldable ListBag where
   foldr f z (LB []) = z
   foldr f z (LB bag) = foldr f z (fst (unzip bag))


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


	The mapLB has type:
	    mapLB :: Eq a1 => (a2 -> a1) -> ListBag a2 -> ListBag a1
	Because of Eq, in order to compare two items, they must be of the same type.
	So we can't create an "fmap" as Functor requires.
-}
   
