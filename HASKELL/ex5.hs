{-

Write a function filterOdd that given a list xs returns a new list obtained from xs by removing the elements at odd positions.
Hint: Here "odd positions" means the first, third, fifth, etc position.
Goal: Playing with lists (pt. 2).
Expected output: Two implementations of filterOdd: one recursive and one using the combinators map, filter, foldl/r from the Haskell Prelude.

-}

oddpos [] = []
oddpos x = if length x > 1 then head x : oddpos (tail(tail x)) else x


oddpos' [] = []
oddpos' (x:[]) = [x]
oddpos' (x:y:xs) = x : oddpos(xs)

{-

TRY: 
let a = [1..50]
oddpos a
oddpos' a

-}