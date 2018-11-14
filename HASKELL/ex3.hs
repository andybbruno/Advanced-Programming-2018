{-

Write a function repl that given a list xs and a integer n returns a list containing the elements of xs replicated n times.
Hint: you can use the function createList of Exercise 1.
Goal: Playing with lists.
Expected output: Two implementations of repl: one recursive and one using the combinators map, filter, foldl/r from the Haskell Prelude.

-}

repl n [] = []
repl 0 x = []
repl 1 x = x
repl n (x:xs) =  map (const x) [1 .. n] ++ repl n xs

repl' n xs = concat [map (const x) [1 .. n] | x<-xs]

{-
	TRY:
	repl 2 ['a','n','d']
	repl' 2 ['a','n','d']
-}