package Strings.search;

/**
 * 同暴力查找一样的想法
 * 不同点是:每次都会用hash值去比较,删除掉前一位,加上后一位
 * 关键公式:x[i+1] = (x[i]-t[i]R^(M-1))R+t[i+M]
 * horner算法求hash
 */
public class RabinKarp {
    private static final int CHARSET_SIZE = 256;

    private long prime = 997;
    private long patHash;
    private int patLen;

    //计算R^(M-1),用于减去前一位
    private long RM = 1;

    public RabinKarp(String pat) {
        patLen = pat.length();
        patHash = hash(pat, pat.length());
        //R^(M-1)%Q
        for (int i = 1; i < patLen; i++) {
            RM = (RM * CHARSET_SIZE) % prime;
        }
    }

    private long hash(String key, int len) {
        long hash = 0;
        for (int i = 0; i < len; i++) {
            hash = (hash * CHARSET_SIZE + key.charAt(i)) % prime;
        }
        return hash;
    }

    //可以替换为拉斯维加斯算法
    private boolean check(int index) {
        return true;
    }

    public int search(String txt) {

        long hash = hash(txt, patLen);
        if (hash == patHash && check(0)) {
            return 0;
        }

        for (int i = patLen; i < txt.length(); i++) {
            //减去前一位 x[i+1] = (x[i]-t[i]R^(M-1))R+t[i+M]
            hash = (hash + prime - RM * txt.charAt(i - patLen) % prime) % prime;
            //加上后一位
            hash = (hash * CHARSET_SIZE + txt.charAt(i)) % prime;

            if (hash == patHash) {
                int curIndex = i - patLen + 1;
                //hash匹配后在进行一次简单匹配
                if (check(curIndex)) {
                    return curIndex;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        RabinKarp test = new RabinKarp("牛逼");
        String str = "没那么牛逼吧?";
        int search = test.search(str);
        System.out.println(search);
    }
}
