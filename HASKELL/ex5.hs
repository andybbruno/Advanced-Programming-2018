{-

Write a function filterOdd that given a list xs returns a new list obtained from xs by removing the elements at odd positions.
Hint: Here "odd positions" means the first, third, fifth, etc position.
Goal: Playing with lists (pt. 2).
Expected output: Two implementations of filterOdd: one recursive and one using the combinators map, filter, foldl/r from the Haskell Prelude.

-}


oddpos [] = []
oddpos (x:xs) = if even (length xs) then x : oddpos xs else oddpos xs


{-
	TRY: 
	let a = [1,2,3,1,2,3]
	oddpos a
-}