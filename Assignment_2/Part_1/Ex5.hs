{-
	http://pages.di.unipi.it/corradini/Didattica/AP-18/PROG-ASS/02//assignment2.html
	Exercise 5: Multisets as Monad [Optional]

	@author Andrea Bruno 585457
-}

import Ex1
import Ex2




newEmpty = LB []

returnLB :: a -> ListBag a
returnLB x = singleton x

bindLB :: Eq a1 => ListBag a2 -> (a2 -> ListBag a1) -> ListBag a1
bindLB a f = foldr sumBag newEmpty (map f (toList a))


-- instance Monad ListBag where
-- return = returnLB
-- (>>=) = bindLB
