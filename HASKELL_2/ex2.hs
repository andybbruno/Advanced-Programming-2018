{-

Enrich the above expressions with a new constructor Div (Expr a) (Expr a) 
and write an evaluation function safeEval for these extended expressions, 
interpreting Div as integer division. Test the new function with some expressions.
Hint: Function safeEval must be partial, since division by zero is undefined, 
and thus it must return a Maybe value.
Goal: First steps with partial functions.
Expected output: A function safeEval that recursively evaluates extended integer expressions.

-}


data Maybe a = Nothing | Just a  
data Expr a = Const a | Sum (Expr a) (Expr a) | Mul (Expr a) (Expr a) | Div (Expr a) (Expr a) 


eval (Const a) = a
eval (Sum a b) = eval a + eval b
eval (Mul a b) = eval a * eval b
eval (Div a b) = 