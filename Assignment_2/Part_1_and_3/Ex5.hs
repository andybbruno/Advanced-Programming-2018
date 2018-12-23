{-
	http://pages.di.unipi.it/corradini/Didattica/AP-18/PROG-ASS/02//assignment2.html
	Exercise 5: Multisets as Monad [Optional]

	@author Andrea Bruno 585457
-}

module Ex5
(
    returnLB,
    bindLB
) 
where

import Ex1
import Ex2

import Test.HUnit
import Data.List


-- returnLB wraps every element into a ListBag
-- by applying the singleton function
returnLB :: a -> ListBag a
returnLB x = singleton x


-- Map f on each element of a, and then join the intermediate results using sumBag
bindLB :: Eq a1 => ListBag a2 -> (a2 -> ListBag a1) -> ListBag a1
bindLB a f = foldr sumBag (LB []) (map f (toList a))


-- instance Monad ListBag where
-- return = returnLB
-- (>>=) = bindLB


testreturnLB = TestCase $ assertBool "test 1" (wf (returnLB 'a'))

testreturnLB_2 = TestCase $ assertEqual "test 2" (toList (returnLB 42)) ([42])

testbindLB = TestCase $ assertEqual "test 3" (toList (bindLB (singleton 1) (\x -> singleton (x+1)))) [2]

testbindLB_2 = TestCase $ assertEqual "test 4" (toList (bindLB (fromList "ciao") (\x -> singleton ('a')))) "aaaa"


testlist = TestList [
                     TestLabel "testreturnLB" testreturnLB,
                     TestLabel "testreturnLB_2" testreturnLB_2,
                     TestLabel "testbindLB" testbindLB,
                     TestLabel "testbindLB_2" testbindLB_2
                     ]
-- Main
main :: IO ()
main = do
  runTestTT testlist
  return ()
