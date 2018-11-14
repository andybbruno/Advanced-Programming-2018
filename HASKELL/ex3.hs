repl n [] = []
repl 0 x = []
repl 1 x = x
repl n (x:xs) =  map (const x) [1 .. n] ++ repl n xs

repl' n xs = concat [map (const x) [1 .. n] | x<-xs]

-- TRY:
-- repl 2 ['a','n','d']
-- repl' 2 ['a','n','d']
