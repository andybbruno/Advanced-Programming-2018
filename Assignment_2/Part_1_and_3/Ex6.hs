{-
	http://pages.di.unipi.it/corradini/Didattica/AP-18/PROG-ASS/02//assignment2.html
	Exercise 6: Abstract Data Type [Optional]

	@author Andrea Bruno 585457
-}

import qualified Ex1
import Data.List
import Test.HUnit


class MultiSet m where
    empty :: m a
    singleton :: a -> m a
    fromList :: Eq a => [a] -> m a
    isEmpty :: Eq a => m a -> Bool
    mul :: Eq a => a -> m a -> Int
    toList :: m a -> [a]
    sumBag :: Eq a => m a -> m a -> m a


instance MultiSet Ex1.ListBag where
    empty = Ex1.empty
    singleton = Ex1.singleton
    fromList = Ex1.fromList
    isEmpty = Ex1.isEmpty
    mul = Ex1.mul
    toList = Ex1.toList
    sumBag = Ex1.sumBag


{-
The simplest way to describe a new instance of MultiSet,
is its list representation. 
So, what before was [(1,2),(2,1),(3,4)], 
now becomes [1,1,2,3,3,3,3]
-}

data Multi ms = MS [ms] deriving (Show, Eq)

emptyMS = MS []
singletonMS ms2 = MS[ms2]
fromListMS ms2 = MS(ms2)
isEmptyMS (MS ms2) = ms2 == []
mulMS v (MS ms2) = length (filter (\x -> (x==v)) ms2)
toListMS (MS ms2) = ms2
sumBagMS (MS a) (MS b) = MS(a ++ b)


instance MultiSet Multi where
    empty = emptyMS
    singleton = singletonMS
    fromList = fromListMS
    isEmpty = isEmptyMS
    mul = mulMS
    toList = toListMS
    sumBag = sumBagMS



bindMulti :: Multi a -> (a -> Multi ms) -> Multi ms
bindMulti a f = foldr sumBagMS (MS[]) (map f (toListMS a))



applMulti :: Multi (a1 -> a2) -> Multi a1 -> Multi a2
applMulti (MS []) b = emptyMS
applMulti (MS (f:fx)) (MS b) = sumBagMS (MS (map f b)) (applMulti (MS fx) (MS b))


instance Foldable Multi where
    foldr f z (MS a) = foldr f z a

instance Applicative Multi where
    pure  = singletonMS
    (<*>) = applMulti

instance Functor Multi where
    fmap f (MS a) = MS (map f a)

instance Monad Multi where
    return = singletonMS
    (>>=)  = bindMulti


fun :: (MultiSet ms, Eq a)  => ms a -> [a]
fun a = toList(sumBag a a)


test1 = TestCase $ assertBool "test 1" (fun (Ex1.LB([('a',1),('b',1)])) == (sort(fun (MS "ab"))))

test2 = TestCase $ assertEqual "test 2" (fun (Ex1.LB([('a',1),('b',1)]))) "aabb"

test3 = TestCase $ assertEqual "test 3" (fun (MS "bye")) "byebye"

testlist = TestList [
                     TestLabel "test1" test1,
                     TestLabel "test2" test2,
                     TestLabel "test3" test3
                     ]

-- Main
main :: IO ()
main = do
  runTestTT testlist
  return ()

