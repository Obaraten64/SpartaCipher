public class SpartaCipher {
    private static char[][] matrix;

    public static String code(String str, int key) {
        if (key <= 1) {
            makeMatrixNull();
            return "ERROR: Key is too small, no sense to code text";
        }

        StringBuilder sb = new StringBuilder();
        int index = 0;
        int numberOfRows = (str.length() % key != 0)? (str.length() / key + 1) : (str.length() / key);
        matrix = new char[numberOfRows][key];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (index < str.length()) {
                    matrix[i][j] = str.charAt(index++);
                } else {
                    break;
                }
            }
        }

        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                sb.append(matrix[j][i]);
            }
        }
        return sb.toString();
    }

    public static String decode(String str, int key) {
        if (key <= 1) {
            makeMatrixNull();
            return "ERROR: Key is too small, no sense to decode text";
        }

        StringBuilder sb = new StringBuilder();
        int index = 0;
        int numberOfRows = (str.length() % key != 0)? (str.length() / key + 1) : (str.length() / key);
        matrix = new char[numberOfRows][key];

        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (index < str.length()) {
                    matrix[j][i] = str.charAt(index++);
                } else {
                    break;
                }
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                sb.append(matrix[i][j]);
            }
        }
        return sb.toString();
    }

    public static String showMatrix() {
        StringBuilder str = new StringBuilder();
        str.append("<html><body>");
        try {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    if (matrix[i][j] != ' ') {
                        str.append(matrix[i][j]).append(" ");
                    } else {
                        str.append("SPC").append(" ");
                    }
                }
                str.append("<br>");
            }
        } catch (NullPointerException exception) {
            return "ERROR: Matrix not created, back to main screen";
        }
        str.append("</body></html>");
        return str.toString();
    }

    public static void makeMatrixNull() {
        matrix = null;
    }
}
