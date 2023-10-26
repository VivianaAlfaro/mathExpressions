public class ResultStack {
    private DoubleStack stack;

    public ResultStack(int capacity) {
        stack = new DoubleStack(capacity);
    }

    public void evaluatePostfixExpression(String postfix) {
        String[] tokens = postfix.split(" ");

        for (String token : tokens) {
            if (isNumeric(token)) {
                double value = Double.parseDouble(token);
                stack.push(value);
                System.out.println("Valor en la pila: " + value);
            } else {
                char operator = token.charAt(0);
                double operand2 = stack.pop();
                double operand1 = stack.pop();
                double result = performOperation(operand1, operand2, operator);
                stack.push(result);
                System.out.println("Operación: " + operand1 + " " + operator + " " + operand2 + " = " + result);
            }
        }
    }

    public double getResult() {
        if (!stack.isEmpty()) {
            return stack.peek();
        }
        throw new IllegalStateException("La pila está vacía. No se puede obtener un resultado.");
    }

    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private double performOperation(double operand1, double operand2, char operator) {
        switch (operator) {
            case '+':
                return operand1 + operand2;
            case '-':
                return operand1 - operand2;
            case '*':
                return operand1 * operand2;
            case '/':
                if (operand2 != 0) {
                    return operand1 / operand2;
                } else {
                    throw new ArithmeticException("División por cero");
                }
            default:
                System.out.println(operator);
                throw new IllegalArgumentException("Operador no válido: " + operator);
        }
    }
}



class DoubleStack {
    private double[] elements;
    private int top;

    public DoubleStack(int capacity) {
        elements = new double[capacity];
        top = -1;
    }

    public void push(double result) {
        elements[++top] = result;
    }

    public double pop() {
        if (!isEmpty()) {
            return elements[top--];
        }
        return 0.0;
    }

    public double peek() {
        if (!isEmpty()) {
            return elements[top];
        }
        return 0.0;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public int size() {
        return top + 1;
    }

    public int getPrecedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
        }
        return 0;
    }
}