sumOdd [] = 0
sumOdd (x:xs) = if odd x then x + sumOdd xs else sumOdd xs

sumOdd' n = sum (filter odd n)