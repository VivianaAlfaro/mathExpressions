public class MainClient { //Main class client

    public static void main(String[] args) {

        String infixExpression = "(1+2/3*(4+5)-6)"; // This show be the entry from the user

        // Instance of CustomQueue
        String postfixExpression = CustomQueue.infixToPostfix(infixExpression); //Convert the infixExpression on postfixExpression
        CustomQueue postfixQueue = CustomQueue.postfixToQueue(postfixExpression); // Save the postfix on a queue

        System.out.println("Expresión Infija: " + infixExpression);
        System.out.println("Expresión Postfija: " + postfixExpression);
        System.out.println("Cola de Expresión Postfija: " + postfixQueue.size());

        while (!postfixQueue.isEmpty()) {
            System.out.println(postfixQueue.dequeue());
        }
    }
}

