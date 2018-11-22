{-

Consider the following definition of binary trees:
data IntTree = Leaf Int | Node (Int, IntTree, IntTree)
Implement tmap, a "tree version" of the map combinator. More precisely, the function tmap should take a function f and a tree t and should apply f to each value in t.
Using tmap implement the function succTree taking a tree t and computing a tree whose elements are the successors of the values in t.
Write a function sumSucc taking a tree t and computing the sum of the elements of succTree t.
Goal: Experimenting with trees.
Expected output: A file containing the three required functions.

-}


data IntTree = Leaf Int | Node (Int, IntTree, IntTree)


tmap f a = case a of 
    Leaf a -> [f a]
    Node (a,b,c) -> [f a] ++ tmap f b ++ tmap f c



{-

TRY:

let a = (Node(2, Node(3, Leaf 1, Leaf 4) , Leaf 5))
tmap (>2) a

-}
