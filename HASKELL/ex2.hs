{-

Write a function sumOdd that given a list of integers computes the sum of the values that are odd.
Hint: consider the functions odd and even of the Prelude.
Goal: Warming up (pt. 2)!
Expected output: Two implementations of sumOdd: one recursive and one using the combinators map, filter, foldl/r from the Haskell Prelude.

-}


sumOdd [] = 0
sumOdd (x:xs) = if odd x then x + sumOdd xs else sumOdd xs

sumOdd' n = sum (filter odd n)

{-
	TRY:
	sumOdd [1,2,2,2,3] 
	sumOdd' [1,2,2,2,3]
-}