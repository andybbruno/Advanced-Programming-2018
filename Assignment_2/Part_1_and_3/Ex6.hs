import Ex1

class MultiSet a where
wf :: MultiSet a -> Bool
empty :: MultiSet a
singleton :: a -> MultiSet a
fromList :: [a] -> MultiSet a
isEmpty :: MultiSet a -> Bool
mul :: a -> MultiSet a -> Int
toList :: MultiSet a -> [a]
sumBag :: MultiSet a -> MultiSet a -> MultiSet a


instance MultiSet Listbag where
wf = Ex1.wf
empty = Ex1.empty
singleton = Ex1.singleton
fromList = Ex1.fromList
isEmpty = Ex1.isEmpty
mul = Ex1.mul
toList = Ex1.toList
sumBag = Ex1.sumBag
