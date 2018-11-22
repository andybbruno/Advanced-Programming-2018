{-

Write a function titlecase that given a string s converts it to titlecase by uppercasing the first letter of every word.
Hint: consider using the function words, unwords of the Prelude and the function toUpper of the module Data.Char. To make accessible this last function in your code use import Data.Char (toUpper).
Goal: Experimenting with strings.
Expected output: Two implementations of titlecase: one recursive and one using the combinators map, filter, foldl/r from the Haskell Prelude.

-}

import Data.Char

titlecase x = map (\y -> toUpper (y!!0) : tail y) x


{-

TRY
let a = ["aaa" , "bbb", "ccc"]
titlecase a

-}