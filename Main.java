import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StackFactory stackFactory = StackFactory.Init();
        Calculator calculator = Calculator.Init();

        System.out.println("¡Bienvenido al Evaluador de Expresiones!");
        while (true) {
            System.out.println("\nElige una opción:");
            System.out.println("1. Ingresar una expresión infija para evaluar");
            System.out.println("2. Salir");
            System.out.print("Tu elección: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 2) {
                System.out.println("Saliendo del programa. ¡Hasta luego!");
                break;
            } else if (choice == 1) {
                System.out.print("Ingresa la expresión infija: ");
                String infixExpression = scanner.nextLine();
                
                System.out.println("Elige la implementación de la pila:");
                System.out.println("1. ArrayList");
                System.out.println("2. Vector");
                System.out.println("3. Lista");
                System.out.print("Tu elección: ");
                int stackChoice = scanner.nextInt();
                scanner.nextLine();
                
                IStack<Character> stack;
                IStack<Double> numberStack;
                boolean esDoble = false;
                String tipoSeleccionado;

                if (stackChoice == 1) {
                    tipoSeleccionado = "arraylist";
                    stack = stackFactory.chooseStack(tipoSeleccionado, false);
                    numberStack = stackFactory.chooseStack(tipoSeleccionado, false);
                } else if (stackChoice == 2) {
                    tipoSeleccionado = "vector";
                    stack = stackFactory.chooseStack(tipoSeleccionado, false);
                    numberStack = stackFactory.chooseStack(tipoSeleccionado, false);
                } else if (stackChoice == 3) {
                    System.out.println("Elige la implementación de la lista:");
                    System.out.println("1. Lista simplemente enlazada");
                    System.out.println("2. Lista doblemente enlazada");
                    System.out.print("Tu elección: ");
                    int listChoice = scanner.nextInt();
                    scanner.nextLine();
                    esDoble = (listChoice == 2);
                    tipoSeleccionado = "list";
                    stack = stackFactory.chooseStack(tipoSeleccionado, esDoble);
                    numberStack = stackFactory.chooseStack(tipoSeleccionado, esDoble);
                } else {
                    System.out.println("Elección invalida. Usando la pila predeterminada (ArrayList).");
                    tipoSeleccionado = "arraylist";
                    stack = stackFactory.chooseStack(tipoSeleccionado, false);
                    numberStack = stackFactory.chooseStack(tipoSeleccionado, false);
                }

                calculator.setStack(stack);
                calculator.setNumberStack(numberStack);
                
                String postfix = calculator.infixToPostfix(infixExpression);
                double result = calculator.evaluatePostfix(postfix);
                
                System.out.println("Expresión en Postfix: " + postfix);
                System.out.println("Resultado Evaluado: " + result);
            } else {
                System.out.println("Elección inválida. Intenta nuevamente.");
            }
        }
        scanner.close();
    }
}

