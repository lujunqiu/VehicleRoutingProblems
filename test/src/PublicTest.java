/**
 * Created by qiu on 17-5-9.
 */
public class PublicTest {
    public static void main(String[] args) {
        System.out.println(getEditDistance("ab", "abaaa"));
        System.out.println(getEditDistance("abcca", "abaaa"));
        System.out.println(getEditDistance("ab", "aba"));
        System.out.println(getEditDistance("cafe", "coffee"));
    }

    private static int getEditDistance(String s, String t) {
        int dp[][];
        int n;//字符串s的长度
        int m;//字符串t的长度
        int i;
        int j;
        char s_i;//字符串s的第i个字符
        char t_j;//字符串t的第j个字符

        n = s.length();
        m = t.length();

        if (n == 0) {
            return m;
        }
        if (m == 0) {
            return n;
        }

        dp = new int[n + 1][m + 1];
        //初始化第一列
        for (i = 0; i <= n; i++) {
            dp[i][0] = i;
        }
        //初始化第一行
        for (j = 0; j <= m; j++) {
            dp[0][j] = j;
        }

        //根据状态转移方程完成DP表
        for (i = 1; i <= n; i++) {
            for (j = 1; j <= m; j++) {
                s_i = s.charAt(i - 1);
                t_j = t.charAt(j - 1);
                int f;
                if (s_i == t_j) {
                    f = 0;
                } else {
                    f = 1;
                }
                dp[i][j] = mininum(dp[i - 1][j] + 1, dp[i][j - 1] + 1, dp[i - 1][j - 1] + f);
            }
        }
        return dp[n][m];
    }
    /*
    ３个数取最小值方法
     */
    private static int mininum(int a, int b, int c) {
        int min = a < b ? a : b;
        return min < c ? min : c;
    }
}
