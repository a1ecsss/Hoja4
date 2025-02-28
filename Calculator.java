public class Calculator {
    private static Calculator instance;
    private IStack<Character> stack;
    private IStack<Double> numberStack;

    private Calculator() {}

    public static Calculator Init() {
        if (instance == null) {
            instance = new Calculator();
        }
        return instance;
    }

    public void setStack(IStack<Character> stack) {
        this.stack = stack;
    }

    public void setNumberStack(IStack<Double> numberStack) {
        this.numberStack = numberStack;
    }

    public String infixToPostfix(String infixExpression) {
        StringBuilder postfix = new StringBuilder();
        String cleanedExpression = infixExpression.replaceAll("\\s+", "");
        
        for (char ch : cleanedExpression.toCharArray()) {
            if (Character.isDigit(ch)) {
                postfix.append(ch).append(' ');
            } else if (ch == '(') {
                stack.push(ch);
            } else if (ch == ')') {
                while (!stack.isEmpty() && stack.pop() != '(') {
                    postfix.append(stack.pop()).append(' ');
                }
                stack.pop(); // Remove '(' from stack
            } else {
                while (!stack.isEmpty() && precedence(ch) <= precedence(stack.pop())) {
                    postfix.append(stack.pop()).append(' ');
                }
                stack.push(ch);
            }
        }
        while (!stack.isEmpty()) {
            postfix.append(stack.pop()).append(' ');
        }
        return postfix.toString().trim();
    }

    private int precedence(char operator) {
        switch (operator) {
            case '+': case '-': return 1;
            case '*': case '/': return 2;
            case '^': return 3;
            default: return -1;
        }
    }

    public double evaluatePostfix(String postfixExpression) {
        for (String token : postfixExpression.split("\\s+")) {
            if (token.matches("\\d+(\\.\\d+)?")) { 
                numberStack.push(Double.parseDouble(token));
            } else {
                if (numberStack.isEmpty()) {
                    throw new IllegalArgumentException("Error: No hay suficientes operandos en la expresión.");
                }
                double b = numberStack.pop();
                
                if (numberStack.isEmpty()) {
                    throw new IllegalArgumentException("Error: No hay suficientes operandos en la expresión.");
                }
                double a = numberStack.pop();
    
                switch (token) {
                    case "+": numberStack.push(a + b); break;
                    case "-": numberStack.push(a - b); break;
                    case "*": numberStack.push(a * b); break;
                    case "/": 
                        if (b == 0) throw new ArithmeticException("Error: División por cero.");
                        numberStack.push(a / b);
                        break;
                    case "^": numberStack.push(Math.pow(a, b)); break;
                    default:
                        throw new IllegalArgumentException("Operador desconocido: " + token);
                }
            }
        }
    
        if (numberStack.isEmpty()) {
            throw new IllegalArgumentException("Error: Expresión inválida.");
        }
    
        return numberStack.pop();
    }
}
