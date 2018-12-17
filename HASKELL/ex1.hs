{-

Write a function myReplicate that given an integer n and a value v returns a list of length n initialized with v, namely all elements are equal to v.
Goal: Warming up!
Expected output: Two implementations of myReplicate: one recursive and one using the combinators map, filter, foldl/r from the Haskell Prelude.

-}

myReplicate 0 v = []
myReplicate n v = v : (myReplicate (n-1) v)

myReplicate' n v = map (const v) [1 .. n]

{-

TRY:
myReplicate 2 4
myReplicate' 2 4
myReplicate'' 2 4

-}
