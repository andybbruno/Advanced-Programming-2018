{-

Write a function sumSucc taking a tree t and 
computing the sum of the elements of succTree t.

-}



data IntTree = Leaf Int | Node (Int, IntTree, IntTree)


tmap f a = case a of 
    Leaf a -> [f a]
    Node (a,b,c) -> [f a] ++ tmap f b ++ tmap f c


succTree t = tmap (+1) t

sumSucc c = foldl (+) 0 (succTree c)

{-

TRY:

let a = (Node(2, Node(3, Leaf 1, Leaf 4) , Leaf 5))
succTree a

-}