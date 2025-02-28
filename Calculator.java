public class Calculator {
    private static Calculator instance;

    private Calculator() {}

    public static Calculator Init() {
        if (instance == null) {
            instance = new Calculator();
        }
        return instance;
    }

    public String infixToPostfix(String infixExpression) {
        IStack<Character> stack = new StackArrayList<>();
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
        IStack<Double> stack = new StackArrayList<>();
        
        for (String token : postfixExpression.split("\\s+")) {
            if (token.matches("\\d+(\\.\\d+)?")) { 
                stack.push(Double.parseDouble(token));
            } else {
                if (stack.isEmpty()) {
                    throw new IllegalArgumentException("Error: No hay suficientes operandos en la expresión.");
                }
                double b = stack.pop();
                
                if (stack.isEmpty()) {
                    throw new IllegalArgumentException("Error: No hay suficientes operandos en la expresión.");
                }
                double a = stack.pop();
    
                switch (token) {
                    case "+": stack.push(a + b); break;
                    case "-": stack.push(a - b); break;
                    case "*": stack.push(a * b); break;
                    case "/": 
                        if (b == 0) throw new ArithmeticException("Error: División por cero.");
                        stack.push(a / b);
                        break;
                    case "^": stack.push(Math.pow(a, b)); break;
                    default:
                        throw new IllegalArgumentException("Operador desconocido: " + token);
                }
            }
        }
    
        if (stack.isEmpty()) {
            throw new IllegalArgumentException("Error: Expresión inválida.");
        }
    
        return stack.pop();
    }
}
