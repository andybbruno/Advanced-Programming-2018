
totalLength [] = 0
totalLength (x:xs) = if (x !! 0 == 'a') then (length x + totalLength' xs) else totalLength' xs

totalLength' xs = foldl (+) 0 (map length ([x | x <- xs , x !! 0 == 'a']))

-- Try:
-- let a = ["a", "aaa", "acdca" ,"baaa" ,"caa"]
-- totalLength a
-- totalLength' a
