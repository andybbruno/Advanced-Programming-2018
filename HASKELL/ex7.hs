{-

Write a function countVowelPali that given a list of strings xs returns the total number of vowels in strings that are palindromes. For example,
countVowelPali ["anna", "banana", "civic", "mouse"] = 4
Goal: Fun with strings and lists (again :P).
Expected output: Two implementations of countVowelPali: one recursive and one using the combinators map, filter, foldl/r from the Haskell Prelude.

-}

import Data.List

:{

pal s = s == reverse s
vowels xs = map (\y -> length [ x | x <- y , x `elem` ['a','e','i','o','u']]) xs
 


countVowelPali x = foldl (+) 0 (vowels (filter pal x))


countVowelPali' [] = 0
countVowelPali' (x:xs) = if pal x 
    then length [ a | a <- x , a `elem` ['a','e','i','o','u']] + countVowelPali' xs
    else countVowelPali' xs

:}


{-

TRY:


-}