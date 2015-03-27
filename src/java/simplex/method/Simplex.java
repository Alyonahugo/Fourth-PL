/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simplex.method;

/**
 *
 * @author Olena_Polishchuk
 */
public class Simplex {
    private static final double EPSILON = 1.0E-10;
    private double[][] a;   // tableaux
    private int M;          // number of constraints
    private int N;          // number of original variables

    private int[] basis;    // basis[i] = basic variable corresponding to row i
                            // only needed to print out solution, not book

    // sets up the simplex tableaux
    public Simplex(double[][] A, double[] b, double[] c) {
        M = b.length;
        N = c.length;
        System.out.println("M N " + M + " " + N);
        a = new double[M+1][N+M+1];
        for (int i = 0; i < M; i++){
            for (int j = 0; j < N; j++){
                
                a[i][j] = A[i][j];
                System.out.println( a[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("----------");
        for (int i = 0; i < M; i++){
            
            a[i][N+i] = 1.0;
            System.out.println("a[i][N+i] " + a[i][N+i]);
        }
        for (int j = 0; j < N; j++){
            a[M][j]   = c[j];
            
            System.out.println("a[M][j] " + a[M][j] );
        }
        for (int i = 0; i < M; i++){
            a[i][M+N] = b[i];
            
            System.out.println("a[i][M+N] " + a[i][M+N]);
        }
        
        for (int i = 0; i < M + 1; i++){
            for (int j = 0; j < N+M+1; j++){
                
                System.out.print( a[i][j] + " ");
            }
            System.out.println(" ");
        }
        basis = new int[M];
        for (int i = 0; i < M; i++) basis[i] = N + i;

        solve();

        // check optimality conditions
        assert check(A, b, c);
    }

    // run simplex algorithm starting from initial BFS
    private void solve() {
        while (true) {

            // find entering column q
            int q = bland();
            System.out.println("q " + q);
            if (q == -1) break;  // optimal

            // find leaving row p
            int p = minRatioRule(q);
            System.out.println("p = " + p + " q = " + q);
            if (p == -100) throw new ArithmeticException("Linear program is unbounded");

            // pivot
            pivot(p, q);

            // update basis
            basis[p] = q;
        }
    }

    // lowest index of a non-basic column with a positive cost
    private int bland() {
        System.out.println("bland()");
        for (int j = 0; j < M + N; j++){
             System.out.print(a[M][j] + "-");
            if (a[M][j] > 0)
            {
                System.out.print(a[M][j] + "-");
                return j;
            }
            System.out.println("*");
        }
        return -1;  // optimal
    }

   // index of a non-basic column with most positive cost
    private int dantzig() {
        int q = 0;
        for (int j = 1; j < M + N; j++)
            if (a[M][j] > a[M][q]) q = j;

        if (a[M][q] <= 0) return -1;  // optimal
        else return q;
    }

    // find row p using min ratio rule (-1 if no such row)
    private int minRatioRule(int q) {
        int p = -1;
        for (int i = 0; i < M; i++) {
            if (a[i][q] <= 0) continue;
            else if (p == -1) p = i;
            else if ((a[i][M+N] / a[i][q]) < (a[p][M+N] / a[p][q])) p = i;
        }
        return p;
    }

    // pivot on entry (p, q) using Gauss-Jordan elimination
    private void pivot(int p, int q) {

        // everything but row p and column q
        for (int i = 0; i <= M; i++)
            for (int j = 0; j <= M + N; j++)
                if (i != p && j != q) a[i][j] -= a[p][j] * a[i][q] / a[p][q];

        // zero out column q
        for (int i = 0; i <= M; i++)
            if (i != p) a[i][q] = 0.0;

        // scale row p
        for (int j = 0; j <= M + N; j++)
            if (j != q) a[p][j] /= a[p][q];
        a[p][q] = 1.0;
    }

    // return optimal objective value
    public double value() {
        for (int i = 0; i< M + 1; i++){
            for (int j = 0; j < M+N +1; j++){
                System.out.print(a[i][j]);
            }
            System.out.println("");
        }
        return -a[M][M+N];
    }

    // return primal solution vector
    public double[] primal() {
        double[] x = new double[N];
        for (int i = 0; i < M; i++)
            if (basis[i] < N) x[basis[i]] = a[i][M+N];
        return x;
    }

    // return dual solution vector
    public double[] dual() {
        double[] y = new double[M];
        for (int i = 0; i < M; i++)
            y[i] = -a[M][N+i];
        return y;
    }


    // is the solution primal feasible?
    private boolean isPrimalFeasible(double[][] A, double[] b) {
        double[] x = primal();

        // check that x >= 0
        for (int j = 0; j < x.length; j++) {
            if (x[j] < 0.0) {
                StdOut.println("x[" + j + "] = " + x[j] + " is negative");
                return false;
            }
        }

        // check that Ax <= b
        for (int i = 0; i < M; i++) {
            double sum = 0.0;
            for (int j = 0; j < N; j++) {
                sum += A[i][j] * x[j];
            }
            if (sum > b[i] + EPSILON) {
                StdOut.println("not primal feasible");
                StdOut.println("b[" + i + "] = " + b[i] + ", sum = " + sum);
                return false;
            }
        }
        return true;
    }

    // is the solution dual feasible?
    private boolean isDualFeasible(double[][] A, double[] c) {
        double[] y = dual();

        // check that y >= 0
        for (int i = 0; i < y.length; i++) {
            if (y[i] < 0.0) {
                StdOut.println("y[" + i + "] = " + y[i] + " is negative");
                return false;
            }
        }

        // check that yA >= c
        for (int j = 0; j < N; j++) {
            double sum = 0.0;
            for (int i = 0; i < M; i++) {
                sum += A[i][j] * y[i];
            }
            if (sum < c[j] - EPSILON) {
                StdOut.println("not dual feasible");
                StdOut.println("c[" + j + "] = " + c[j] + ", sum = " + sum);
                return false;
            }
        }
        return true;
    }

    // check that optimal value = cx = yb
    private boolean isOptimal(double[] b, double[] c) {
        double[] x = primal();
        double[] y = dual();
        double value = value();

        // check that value = cx = yb
        double value1 = 0.0;
        for (int j = 0; j < x.length; j++)
            value1 += c[j] * x[j];
        double value2 = 0.0;
        for (int i = 0; i < y.length; i++)
            value2 += y[i] * b[i];
        if (Math.abs(value - value1) > EPSILON || Math.abs(value - value2) > EPSILON) {
            StdOut.println("value = " + value + ", cx = " + value1 + ", yb = " + value2);
            return false;
        }

        return true;
    }

    private boolean check(double[][]A, double[] b, double[] c) {
        return isPrimalFeasible(A, b) && isDualFeasible(A, c) && isOptimal(b, c);
    }

    // print tableaux
    public void show() {
        StdOut.println("M = " + M);
        StdOut.println("N = " + N);
        for (int i = 0; i <= M; i++) {
            for (int j = 0; j <= M + N; j++) {
                StdOut.printf("%7.2f ", a[i][j]);
            }
            StdOut.println();
        }
        StdOut.println("value = " + value());
        for (int i = 0; i < M; i++)
            if (basis[i] < N) StdOut.println("x_" + basis[i] + " = " + a[i][M+N]);
        StdOut.println();
    }


    public static double[] test(double[][] A, double[] b, double[] c) {
        Simplex lp = new Simplex(A, b, c);
        StdOut.println("value = " + lp.value());
        double[] x = lp.primal();
        for (int i = 0; i < x.length; i++)
            StdOut.println("x[" + i + "] = " + x[i]);
        double[] y = lp.dual();
        for (int j = 0; j < y.length; j++)
            StdOut.println("y[" + j + "] = " + y[j]);
        return x;
    }

    public static void test1() {
        double[][] A = {
            { -1,  1,  0 },
            {  1,  4,  0 },
            {  2,  1,  0 },
            {  3, -4,  0 },
            {  0,  0,  1 },
        };
        double[] c = { 1, 1, 1 };
        double[] b = { 5, 45, 27, 24, 4 };
        test(A, b, c);
    }


    // x0 = 12, x1 = 28, opt = 800
    public static void test2() {
        double[] c = {  13.0,  23.0 };
        double[] b = { 480.0, 160.0, 1190.0 };
        double[][] A = {
            {  5.0, 15.0 },
            {  4.0,  4.0 },
            { 35.0, 20.0 },
        };
        test(A, b, c);
    }

    // unbounded
    public static void test3() {
        double[] c = { 2.0, 3.0, -1.0, -12.0 };
        double[] b = {  3.0,   2.0 };
        double[][] A = {
            { -2.0, -9.0,  1.0,  9.0 },
            {  1.0,  1.0, -1.0, -2.0 },
        };
        test(A, b, c);
    }

    // degenerate - cycles if you choose most positive objective function coefficient
    public static void test4() {
           double[][] A = {
            {  0,  1, -1, 0, 0, 0, 0, 0 },
            {  3.1,  1, 0, -1, 0, 0, 0, 0},
            {  4,  1,  0, 0, -1, 0, 0, 0},
            {  7,  1,  0, 0, 0, -1, 0, 0 },
            {  8,  1,  0, 0, 0, 0, -1, 0 },
            {  10,  1,  0, 0, 0, 0, 0, -1 }
//            {  -8,  -1,  0 },
           };
        double[] c = { 1, 1, -1, -1, -1, -1, -1, -1 };
        double[] b = { 4.9, 11.2 , 13.1, 18.7, 21.3, 24.8};
        test(A, b, c);
//        double[][] A = {
//            { 0,  -1,  -1 },
//            {  -3,  -1,  0 },
//            {  -4,  -1,  0 },
//            {  -7, -1,  0 },
//            {  -8,  -1,  0 },
//        };
//        double[] c = { -1, -1, -1 };
//        double[] b = { -5, -11, -13, -19, -21 };
//        test(A, b, c);
   //     double[][] A =   { 
//                            { 0.0, -1.0, -1.0},
//                            { -3.0, -1.0, 0.0 },
////                            { -4.0, -1.0, 0.0 },
//                            { -7, -1, 0, 0, 0, -1, 0, 0 },
//                            { -8, -1, 0, 0, 0, 0, -1, 0 },
//                            { 0, 1, -1, 0, 0, 0 },
//                            { 3, 1, 0, -1, 0, 0  },
//                            { 4, 1, 0, 0, -1, 0 },
//                            { 7, 1, 0, 0, 0, -1},                            
//                            { 8, 1, 0, 0, 0, 0 },
//                            { 10, 1, 0, 0, 0, 0 } 
//        };
//    
//
//    double[] b = {  5, 10, 12, 19, 21, 25};
//    double[] c = {  1.0, 1.0, 1.0, 1.0, 1.0, 1.0};
////        double[] c = { 10.0, -57.0, -9.0, -24.0 };
////        double[] b = {  0.0,   0.0,  1.0 };
////        double[][] A = {
////            { 0.5, -5.5, -2.5, 9.0 },
////            { 0.5, -1.5, -0.5, 1.0 },
////            { 1.0,  0.0,  0.0, 0.0 },
////        };
 //       test(A, b, c);
    }
    // 2 5 000  22



    // test client
    public static void main(String[] args) {

//        try                           { test1();             }
//        catch (ArithmeticException e) { e.printStackTrace(); }
//        StdOut.println("--------------------------------");

//        try                           { test2();             }
//        catch (ArithmeticException e) { e.printStackTrace(); }
//        StdOut.println("--------------------------------");
//
//        try                           { test3();             }
//        catch (ArithmeticException e) { e.printStackTrace(); }
//        StdOut.println("--------------------------------");
//
//        try                           { test4();             }
//        catch (ArithmeticException e) { e.printStackTrace(); }
//        StdOut.println("--------------------------------");
//
//double[][] A =   { { 0, -1, -1, 0, 0 , 0, 0, 0},
//                   { -3, -1, 0, -1, 0, 0, 0, 0 },
//                   { -4, -1, 0, 0, -1, 0, 0, 0 },
//                   { -7, -1, 0, 0, 0, -1, 0, 0 },
//                   { -8, -1, 0, 0, 0, 0, -1, 0 }, 
//                   { -10, -1, 0, 0, 0, 0, 0, -1},
//                   { 0, 1, -1, 0, 0, 0, 0, 0, 0 },
//                   { 3, 1, 0, -1, 0, 0, 0, 0, 0 },
//                   { 4, 1, 0, 0, -1, 0, 0, 0 },
//                   { 7, 1, 0, 0, 0, -1, 0, 0 }, 
//                   { 8, 1, 0, 0, 0, 0, -1, 0 },
//                   { 10, 1, 0, 0, 0, 0, 0, -1 }                  
//    
//   };
//    double[] b = { -5.0, -11.5, -13, -19, -21, -25, 5, 11, 13, 19, 21, 25};
//    double[] c = { -1, -1, -1, -1, -1, -1, -1, -1};
//        int M = Integer.parseInt(args[0]);
//        int N = Integer.parseInt(args[1]);
//        double[] c = new double[N];
//        double[] b = new double[M];
//        double[][] A = new double[M][N];
//        for (int j = 0; j < N; j++)
//            c[j] = StdRandom.uniform(1000);
//        for (int i = 0; i < M; i++)
//            b[i] = StdRandom.uniform(1000);
//        for (int i = 0; i < M; i++)
//            for (int j = 0; j < N; j++)
//                A[i][j] = StdRandom.uniform(100);
         test4();
       // Simplex lp = new Simplex(A, b, c);
       // StdOut.println(lp.value());
    }

}
