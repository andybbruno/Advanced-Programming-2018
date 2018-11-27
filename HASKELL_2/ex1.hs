{-

All the exercises below consider (variants of) the following ADT for simple expressions:
data Expr a = Const a | Sum (Expr a) (Expr a) | Mul (Expr a) (Expr a)

Define a recursive evaluation function eval for expressions. 
Test the function on a couple of simple expressions. For example,
eval (Sum (Mul (Const 2) (Const 3)) (Const 4))
should evaluate to 10.
Goal: Warming up!
Expected output: A function eval that recursively evaluates an expression.

-}

data Expr a = Const a | Sum (Expr a) (Expr a) | Mul (Expr a) (Expr a)


eval (Const a) = a
eval (Sum a b) = eval a + eval b
eval (Mul a b) = eval a * eval b