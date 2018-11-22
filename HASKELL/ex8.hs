{-

Recall the higher-order combinator map from the Prelude. Implement it using the combinator foldl.
Goal: Experimenting with combinators.
Expected output: A file containing the required implementation of the map combinator.

-}

:{

myMap f [] = []

myMap f (x:xs) = foldl (++) [] ([f x] : [myMap f xs])

:}

{-
Apply the ++ which combines two lists, starting from [],
on the list composed by f(x) concatenated (:) with the list of f applied to the tail xs


TRY:

myMap (>0) [1,2,3]

myMap reverse ["abc","cda","1234"]


-}