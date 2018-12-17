{-
	http://pages.di.unipi.it/corradini/Didattica/AP-18/PROG-ASS/02//assignment2.html
	Exercise 2: Constructors and operations
-}

import Ex1


instance Foldable ListBag where
   foldr f z (LB []) = z
   foldr f z (LB bag) = foldr f z (fst (unzip bag))



mapLB _ (LB []) = Nothing
mapLB f bag = Just (fromList (map f (toList bag)))


mapLB' f bag = fromList (map f (toList bag))