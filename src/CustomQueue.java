public class CustomQueue { // Queue data structure
    private String[] elements;
    private int front, rear, size;

    public CustomQueue(int capacity) { // receives the length of the infix expression
        size = capacity;
        elements = new String[capacity];
        front = rear = -1;
    }

    public boolean isFull() { // Checks if the queue is full
        return (rear == size - 1);
    }

    public boolean isEmpty() { // Checks if the queue is empty
        return (front == -1);
    }

    public void enqueue(String item) { //Adds an item (the element) to the queue if it is not full.
        if (isFull()) {
            System.out.println("La cola está llena. No se puede agregar más elementos.");
            return;
        }
        
        if (isEmpty()) { //Moves the back pointer and front pointer if the queue was empty
            front = 0;
        }
        
        elements[++rear] = item;
    }

    public String dequeue() { // Removes and returns the element at the front of the queue if it is not empty.
        if (isEmpty()) {
            System.out.println("La cola está vacía. No se puede eliminar ningún elemento.");
            return null;
        }

        String item = elements[front];
        
        if (front == rear) {
            front = rear = -1;
        } else {
            front++;
        }
        
        return item;
    }

    public int size() { // Calculates the size of the queue
        if (isEmpty()) {
            return 0;
        }
        return rear - front + 1;
    }

    public String peek() { //Returns the element at the front of the queue without deleting it
        if (isEmpty()) {
            System.out.println("La cola está vacía. No hay elementos para consultar.");
            return null;
        }
        return elements[front];
    }
    
    public static String infixToPostfix(String infix) { //Convert the infixExpression into a postfixExpression
        StringBuilder postfix = new StringBuilder();
        CustomStack stack = new CustomStack(infix.length());
    
        for (char c : infix.toCharArray()) {
            if (Character.isDigit(c)) {
                postfix.append(c);
            } else if (c == ' ') {
                continue;
            } else if (c == '+' || c == '-' || c == '*' || c == '/' || c == '^' || c == '%') {
                while (!stack.isEmpty() && stack.getPrecedence(stack.peek()) >= stack.getPrecedence(c)) {
                    postfix.append(" ").append(stack.pop());
                }
                stack.push(c);
                postfix.append(" ");
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(" ").append(stack.pop());
                }
                stack.pop();
            }
        }
    
        while (!stack.isEmpty()) {
            postfix.append(" ").append(stack.pop());
        }
    
        return postfix.toString();
    }
    

    public static CustomQueue postfixToQueue(String postfix) { //Splits a postfix expression into tokens and places them in a queue
        CustomQueue queue = new CustomQueue(postfix.length());
        String[] tokens = postfix.split(" ");

        for (String token : tokens) {
            queue.enqueue(token);
        }

        return queue;
    }
}

class CustomStack { // Stack data structure 
    private char[] elements;
    private int top;

    public CustomStack(int capacity) { 
        elements = new char[capacity]; // Add one by one all the elements of the infixExpression
        top = -1;
    }

    public void push(double result) {
        elements[++top] = (char) result;
    }

    public char pop() {
        if (!isEmpty()) {
            return elements[top--];
        }
        return '\0'; // Return '\0' for an empty stack
    }

    public char peek() {
        if (!isEmpty()) {
            return elements[top];
        }
        return '\0'; // Return '\0' for an empty stack
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public int size() {
        return top + 1; // Calcula el tamaño en función de top
    }

    public int getPrecedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^': // Agregado para la potencia
            case '%':
                return 3;
        }
        return 0; // Default for other characters
    }

    public static int performOperation(int operand1, int operand2, char charAt) {
        return 0;
    }
}