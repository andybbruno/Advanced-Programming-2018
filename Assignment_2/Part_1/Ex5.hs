{-
	http://pages.di.unipi.it/corradini/Didattica/AP-18/PROG-ASS/02//assignment2.html
	Exercise 5: Multisets as Monad [Optional]

	@author Andrea Bruno 585457
-}

import Ex1
import Ex2


returnLB :: ListBag a -> Maybe (ListBag a)
returnLB (LB a) = 
   case a of
      [] -> Nothing
      a -> Just (LB a)


bindLB g (LB y) = 
    case y of
        [] -> Nothing
        Just (LB y) -> g y
