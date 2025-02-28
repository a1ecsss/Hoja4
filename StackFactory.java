public class StackFactory {
    private static StackFactory instance;

    private StackFactory() {}

    public static StackFactory Init() {
        if (instance == null) {
            instance = new StackFactory();
        }
        return instance;
    }

    public <T> IStack<T> chooseStack(String type, boolean isDoubly) {
        switch (type.toLowerCase()) {
            case "arraylist":
                return new StackArrayList<>();
            case "vector":
                return new StackVector<>();
            case "list":
                return new StackList<>(isDoubly);
            default:
                throw new IllegalArgumentException("Tipo de Lista invalida: " + type);
        }
    }
}
