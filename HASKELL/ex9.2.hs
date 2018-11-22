{-

Using tmap implement the function succTree taking a tree t and 
computing a tree whose elements are the successors of the values in t.

-}



data IntTree = Leaf Int | Node (Int, IntTree, IntTree)


tmap f a = case a of 
    Leaf a -> [f a]
    Node (a,b,c) -> [f a] ++ tmap f b ++ tmap f c


succTree t = tmap (+1) t


{-

TRY:

let a = (Node(2, Node(3, Leaf 1, Leaf 4) , Leaf 5))
succTree a

-}