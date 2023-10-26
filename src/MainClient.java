public class MainClient { //Main class client

    public static void main(String[] args) {

        String infixExpression = "(1+3) * 3 & 4 | (5^2) | 6 & 7 % 2"; // This show be the entry from the user

        // Instance of CustomQueue
        String postfixExpression = CustomQueue.infixToPostfix(infixExpression); //Convert the infixExpression on postfixExpression
        CustomQueue postfixQueue = CustomQueue.postfixToQueue(postfixExpression); // Save the postfix on a queue

        // Instance of ResultStack and evaluate the postfix expression
        ResultStack resultStack = new ResultStack(postfixExpression.length());
        resultStack.evaluatePostfixExpression(postfixExpression);
   
        // Get the result
        double result = resultStack.getResult();
   
        System.out.println("Expresión Infija: " + infixExpression);
        System.out.println("Expresión Postfija: " + postfixExpression);
        System.out.println("Cola de Expresión Postfija: " + postfixQueue.size());
        System.out.println("Resultado: " + result);

        //while (!postfixQueue.isEmpty()) {
            //System.out.println(postfixQueue.dequeue());
        //}
    }
} 

