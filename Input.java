public class Input {
    public static int scanInt(String message) {
        while (true) {
            try {
                int resultado = Integer.parseInt(IO.readln(message));
                return resultado;
            } catch (Exception e) {
                IO.println("Valor inválido! Digite um número inteiro.");
            }
        }
    }

    public static double scanDouble(String message) {
        while (true) {
            try {
                double resultado = Double.parseDouble(IO.readln(message));
                return resultado;
            } catch (Exception e) {
                IO.println("Valor inválido! Digite um número decimal");
            }
        }
    }

    public static String scanString(String message) {
        while (true) {
            String resultado = IO.readln(message);
            if (!resultado.isEmpty())
                return resultado.trim();
            else
                IO.println("Valor inválido! Digite um texto");
        }
    }

    // Métodos que aceitam entrada null

    public static int scanInt(String message, boolean aceitaNull) {
        while (true) {
            try {
                int resultado = Integer.parseInt(IO.readln(message));
                return resultado;
            } catch (Exception e) {
                return 0;
            }
        }
    }

    public static double scanDouble(String message, boolean aceitaNull) {
        while (true) {
            try {
                double resultado = Double.parseDouble(IO.readln(message));
                return resultado;
            } catch (Exception e) {
                return 0;
            }
        }
    }

    public static String scanString(String message, boolean aceitaNull) {
        while (true) {
            String resultado = IO.readln(message);
            if (!resultado.isEmpty())
                return resultado.trim();
            else
                return "";
        }
    }
}