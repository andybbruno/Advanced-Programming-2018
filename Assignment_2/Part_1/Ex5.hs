{-
	http://pages.di.unipi.it/corradini/Didattica/AP-18/PROG-ASS/02//assignment2.html
	Exercise 5: Multisets as Monad [Optional]

	@author Andrea Bruno 585457
-}

import Ex1
import Ex2



returnLB :: Eq a => [(a, Int)] -> Maybe (ListBag a)
returnLB x = case x of
   []   -> Nothing
   _    -> if wf(LB (x)) == True then Just (LB (x)) else Nothing



-- START TEST --
{-

-- Nothing --
returnLB []

-- Nothing --
returnLB [(1,3),(2,5),(3,-1)]

-- Just --
returnLB [(1,3),(2,5)]


-}