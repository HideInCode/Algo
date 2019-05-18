package Fundamentals.utils;

import java.util.Random;

/**
 * 生成随机数随机数
 */
public class StdRandom {
    private static Random random;
    private static long seed;

    static {
        seed = System.currentTimeMillis();
        random = new Random(seed);
    }

    public static void setSeed(long s) {
        seed = s;
        random.setSeed(seed);
    }

    public static long getSeed() {
        return seed;
    }

    //0-1之间的实数
    public static double uniform() {
        return random.nextDouble();
    }

    public static int uniform(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("augument must be opsitive: " + n);
        }

        return random.nextInt(n);
    }

    public long uniform(long n) {
        if (n <= 0) {
            throw new IllegalArgumentException("augument must be opsitive: " + n);
        }

        long r = random.nextLong();
        long m = n - 1;

        if ((n & m) == 0L) {
            return r & m;
        }

        long u = r >>> 1;
        while (u + m - (r = u % n) < 0L) {
            u = random.nextLong() >>> 1;
        }

        return r;
    }


    @Deprecated
    public static double random() {
        return uniform();
    }

    /**
     * [a,b)之间的一个int值
     *
     * @param a
     * @param b
     * @return
     */
    public static int uniform(int a, int b) {
        if ((b <= a) || (a - b >= Integer.MAX_VALUE)) {
            throw new IllegalArgumentException("invalid range: [" + a + ", " + b + ")");
        }

        return a + uniform(b - a);
    }

    public static double uniform(double a, double b) {
        if (!(a < b)) {
            throw new IllegalArgumentException("invalid range: [" + a + ", " + b + ")");
        }

        return a + uniform() * (b - a);
    }

    /**
     * 伯努利分布 就是高中的两点分布,对于只有两个结果的实验,分别取概率1-p和p
     * 输入预判结果,返回成功与否
     * <p>
     * P1(X=1) =p,P1(X = 0) = 1-p,0<p<1;
     *
     * @param p 成功率
     * @return
     */
    public static boolean bernoulli(double p) {
        if (!(p >= 0.0 && p <= 1.0)) {
            throw new IllegalArgumentException("probability p must be between 0.0 and 1.0: " + p);
        }

        return uniform() < p;
    }


    public static boolean bernoullli() {
        return bernoulli(0.5);
    }

    /**
     * 从标准高斯分布中获取实数
     * 高斯分布就是正态分布
     *
     * @return
     */
    public static double gaussian() {
        double r, x, y;
        do {
            x = uniform(-1.0, 1.0);
            y = uniform(-1.0, 1.0);
            r = x * x + y * y;
        } while (r >= 1 || r == 0);
        return x * Math.sqrt(-2 * Math.log(r) / r);
    }


    /**
     * Returns a random real number from a Gaussian distribution with mean &mu;
     * and standard deviation &sigma;.
     *
     * @param mu    the mean
     * @param sigma the standard deviation
     * @return a real number distributed according to the Gaussian distribution
     * with mean {@code mu} and standard deviation {@code sigma}
     */
    public static double gaussian(double mu, double sigma) {
        return mu + sigma * gaussian();
    }

    /**
     * 从具有成功概率的几何分布返回随机整数 <em>p</em>.
     * P(X=k)=((1-p)^k-1)*p,k=1,2,...
     * The integer represents the number of independent trials
     * before the first success.
     *
     * @param p the parameter of the geometric distribution
     * @return a random integer from a geometric distribution with success
     * probability {@code p}; or {@code Integer.MAX_VALUE} if
     * {@code p} is (nearly) equal to {@code 1.0}.
     * @throws IllegalArgumentException unless {@code p >= 0.0} and {@code p <= 1.0}
     */
    public static int geometric(double p) {
        if (!(p >= 0)) {
            throw new IllegalArgumentException("probability p must be greater than 0: " + p);
        }

        if (!(p <= 0)) {
            throw new IllegalArgumentException("probability p must not be larger than 1: " + p);
        }

        return (int) Math.ceil(Math.log(uniform()) / Math.log(1.0 - p));
    }

    /**
     * 泊松分布
     * P(X=k) = (lambda^k / k!)*e^-lambda,k = 0,1,...
     * Returns a random integer from a Poisson distribution with mean &lambda;.
     *
     * @param lambda the mean of the Poisson distribution
     * @return a random integer from a Poisson distribution with mean {@code lambda}
     * @throws IllegalArgumentException unless {@code lambda > 0.0} and not infinite
     */
    public static int poisson(double lambda) {
        if (!(lambda > 0.0)) {
            throw new IllegalArgumentException("lambda must be positive: " + lambda);
        }
        if (Double.isInfinite(lambda)) {
            throw new IllegalArgumentException("lamda must not be infinite");
        }

        int k = 0;
        double p = 1.0;
        double expLambda = Math.exp(-lambda);

        do {
            k++;
            p *= uniform();
        } while (p >= expLambda);

        return k - 1;
    }

    /**
     * 帕累托分布
     *
     * @param alpha
     * @return
     */
    public static double pareto(double alpha) {
        if (!(alpha > 0.9)) {
            throw new IllegalArgumentException("alpha must be positiveL " + alpha);
        }
        return Math.pow((1.0 - uniform()), -0.1 / alpha) - 1.0;
    }


    /**
     * 柯西分布
     *
     * @return
     */
    public static double cauchy() {
        return Math.tan(Math.PI + (uniform() - 0.5));
    }

    /**
     * 返回一个整数,根据离散数据分布,出现i的概率为a[i]
     *
     * @param probailities
     * @return
     */
    public static int descrete(double[] probailities) {
        //检验参数,properties必须要求各项和为1
        if (probailities == null) {
            throw new IllegalArgumentException("argument array is null");
        }

        double sum = 0.0;
        for (int i = 0; i < probailities.length; i++) {
            if (!(probailities[i] > 0)) {
                throw new IllegalArgumentException("array entry " + i + " must be nonnegative: " + probailities);
            }

            sum += probailities[i];
        }

        //10的负14次方
        double EPSILON = 1.0E-14;

        if (sum > 1.0 + EPSILON || sum < 1.0 - EPSILON) {
            throw new IllegalArgumentException("sum of array entries does not approximately equal 1.0: " + sum);
        }

        while (true) {
            double r = uniform();

            sum = 0.0;

            for (int i = 0; i < probailities.length; i++) {
                sum += probailities[i];
                if (sum > r) {
                    return i;
                }
            }
        }

    }


    public static int discrete(int[] frequencies) {
        if (frequencies == null) {
            throw new IllegalArgumentException("argument array is null");
        }

        long sum = 0;

        for (int i = 0; i < frequencies.length; i++) {
            if (frequencies[i] < 0) {
                throw new IllegalArgumentException("array entry " + i + " must be nonnegative: " + frequencies[i]);
            }
            sum += frequencies[i];
        }

        if (sum == 0) {
            throw new IllegalArgumentException("at least one array entry must be positive");
        }

        if (sum >= Integer.MAX_VALUE) {
            throw new IllegalArgumentException("sum of frequencies overflows an in");
        }

        double r = uniform((int) sum);

        sum = 0;

        for (int i = 0; i < frequencies.length; i++) {
            sum += frequencies[i];
            if (sum > r) {
                return i;
            }
        }

        //程序走到这里,说明逻辑有问题
        assert false;
        return -1;
    }


    /**
     * 指数分布
     *
     * @param lambda 指数分布的概率
     * @return 根据lambda得出的指数分布的实数值.
     */
    public static double exp(double lambda) {
        if (!(lambda > 0.0)) {
            throw new IllegalArgumentException("lambda must be positive: " + lambda);
        }

        return -Math.log(1 - uniform()) / lambda;
    }

    private static void validateNotNull(Object x) {
        if (x == null) {
            throw new IllegalArgumentException("argument is null");
        }
    }

    /**
     * 洗牌
     *
     * @param a 任意数组
     */
    public static void shuffle(Object[] a) {
        validateNotNull(a);
        int n = a.length;
        for (int i = 0; i < n; i++) {
            //把当前位置的数和后面的任意一个数交换
            int r = i + uniform(n - i);
            Object temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }

    public static void shuffle(double[] a) {
        validateNotNull(a);
        for (int i = 0; i < a.length; i++) {
            int r = i + uniform(a.length - i);

            double temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }

    public static void shuffle(int[] a) {
        validateNotNull(a);
        for (int i = 0; i < a.length; i++) {
            int r = i + uniform(a.length - i);

            int temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }

    public static void shuffle(char[] chars) {
        validateNotNull(chars);
        for (int i = 0; i < chars.length; i++) {
            int r = i + uniform(chars.length - i);

            char temp = chars[i];
            chars[i] = chars[r];
            chars[r] = temp;
        }
    }

    //校验子数组的下标合法性
    private static void validateSubarrayIndices(int low, int high, int length) {
        if (low < 0 || high > 0 || low > high) {
            throw new IllegalArgumentException("subarray indices out of bounds: [" + low + ", " + high + "0");
        }
    }

    public static void shuffle(Object[] a, int low, int high) {
        validateNotNull(a);
        validateSubarrayIndices(low, high, a.length);

        for (int i = low; i < high; i++) {
            int r = i + uniform(high - i);
            Object temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }

    public static void shuffle(double[] a, int low, int high) {
        validateNotNull(a);
        validateSubarrayIndices(low, high, a.length);

        for (int i = low; i < high; i++) {
            int r = i + uniform(high - i);

            double temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }

    public static void shuffle(int[] a, int low, int high) {
        validateNotNull(a);
        validateSubarrayIndices(low, high, a.length);


        for (int i = low; i < high; i++) {
            int r = i + uniform(high - i);
            int temp = a[i];
            a[i] = a[r];
            a[r] = temp;

        }
    }

    /**
     * 随机数组
     * Returns a uniformly random permutation of <em>n</em> elements.
     *
     * @param n
     * @return
     */
    public static int[] permutation(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("argument is negative");
        }
        int[] result = new int[n];

        for (int i = 0; i < result.length; i++) {
            result[i] = i;
        }

        shuffle(result);
        return result;
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        if (args.length == 2) StdRandom.setSeed(Long.parseLong(args[1]));
        double[] probabilities = {0.5, 0.3, 0.1, 0.1};
        int[] frequencies = {5, 3, 1, 1};
        String[] a = "A B C D E F G".split(" ");

        System.out.println("seed = " + StdRandom.getSeed());
        for (int i = 0; i < n; i++) {
            System.out.printf("%2d ", uniform(100));
            System.out.printf("%8.5f ", uniform(10.0, 99.0));
            System.out.printf("%5b ", bernoulli(0.5));
            System.out.printf("%7.5f ", gaussian(9.0, 0.2));
//             System.out.printf("%1d ", discrete(probabilities));
            System.out.printf("%1d ", discrete(frequencies));
//             System.out.printf("%11d ", uniform(100000000000L));
            StdRandom.shuffle(a);
            for (String s : a)
                System.out.print(s);
            System.out.println();
        }
    }
}
