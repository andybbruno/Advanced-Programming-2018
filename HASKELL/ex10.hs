{-

Implement a tail recursive version of the map and filter combinators.
Goal: Trying to write tail recursive functions.
Expected output: A file containing the required combinators.

-}

import Prelude

tailMap f [a] x = if length x == 1 
then [a] ++ [f x]
else tailMap f ([a] ++ [f x]) (tail x)