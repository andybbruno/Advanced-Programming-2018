{-

Write a function totalLength that given a list of strings xs computes the sum of the lengths of the strings starting with the character 'A'.
Goal: Test your skills with lists and strings.
Expected output: Two implementations of totalLength: one recursive and one using the combinators map, filter, foldl/r from the Haskell Prelude.

-}

totalLength [] = 0
totalLength (x:xs) = if (x !! 0 == 'a') then (length x + totalLength' xs) else totalLength' xs

totalLength' xs = foldl (+) 0 (map length ([x | x <- xs , x !! 0 == 'a']))

{-
TRY:
let a = ["a", "aaa", "acdca" ,"baaa" ,"caa"]
totalLength a
totalLength' a
-}