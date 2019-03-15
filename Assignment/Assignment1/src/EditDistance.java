/**
 * Created by wtg on 29/8/17.
 */

public class EditDistance {
    //The distance of delete
    public static int d = 1;
    //The distance of insert
    public static int ins = 1;
    //The distance of replace
    public static int r = 1;
    //The distance of match
    public static int m = 0;

    public static int calc(String src, String dst) {
        int[][] distance = new int[src.length() + 1][dst.length() + 1];
        distance[0][0] = 0;

        for (int i = 0; i <= src.length(); i++) {
            distance[i][0] = i * ins;
        }

        for (int i = 0; i <= dst.length(); i++) {
            distance[0][i] = i * d;
        }

        for (int i = 1; i <= src.length(); i++) {
            for (int j = 1; j <= dst.length(); j++) {
                int t = src.charAt(i - 1) == dst.charAt(j - 1) ? m : r;
                distance[i][j] = Math.min(Math.min(distance[i-1][j] + ins, distance[i][j-1]) + d, distance[i-1][j-1] + t);
            }
        }
        return distance[src.length()][dst.length()];
    }
}

