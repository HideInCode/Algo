package Strings;

public class SimpleSearch {

    private static final int END_POSITION = -1;

    public static int search(String pat, String txt) {
        int M = pat.length();
        int N = txt.length();

        for (int i = 0; i < N - M; i++) {
            int j;
            for (j = 0; j < M; j++) {
                if (txt.charAt(i + j) != pat.charAt(j)) {
                    break;
                }
            }
            if (j == M) {
                return i;
            }
        }
        return END_POSITION;
    }

    public static int reSearch(String pat, String txt) {
        int txtIndex, patIndex;
        int M = pat.length();
        int N = txt.length();
        for (txtIndex = 0, patIndex = 0; txtIndex < N && patIndex < M; txtIndex++) {
            if (txt.charAt(txtIndex) == pat.charAt(patIndex)) {
                patIndex++;
            } else {
                txtIndex -= patIndex;
                patIndex = 0;
            }
        }

        if (patIndex == M) {
            return txtIndex - M;
        } else {
            return END_POSITION;
        }
    }

    public static void main(String[] args) {
        String txt = "pjrbadmapijqwpjrwqrj";
        String pat = "baad";
        int search = search(pat, txt);
        System.out.println(search);

        int reSearch = reSearch(pat, txt);
        System.out.println(reSearch);
    }
}
