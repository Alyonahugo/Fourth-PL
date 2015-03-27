package beans;

import static java.lang.Math.abs;
import java.util.Random;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import static simplex.method.Simplex.test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Alyokna
 */
@ManagedBean
@SessionScoped
public class SpinnerController {

    private double A = 2;
    private double B = 5;
    private double number1;//x1
    private double output = 1.0304053245845644;
    private double input = -2.5555555555555554;

    public double getOutput() {
        return output;
    }

    public void setOutput(double output) {
        this.output = output;
    }

   /* public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }*/

    public double getInput() {
        return input;
    }

    public void setInput(double input) {
        this.input = input;
    }

    private double number2;//x2

    private double number3;//x3

    private double number4;//x4

    private double number5;//x5

    private double number6;//x6

    private double number11;//x11

    private double number21;//x21

    private double number31;//x31

    private double number41;//x41

    private double number51;//x51

    private double number61;//x61

    private double y1;//y1

    private double y2;//y2

    private double y3;//y3

    private double y4;//y4

    private double y5;//y5

    private double y6;//y6

    private double y11;//y11

    private double y21;//y21

    private double y31;//y31

    private double y41;//y41

    private double y51;//y51

    private double y61;//y61    

    public double getNumber11() {
        return number11;
    }

    public void setNumber11(double number11) {
        this.number11 = number11;
    }

    public double getNumber21() {
        return number21;
    }

    public void setNumber21(double number21) {
        this.number21 = number21;
    }

    public double getNumber31() {
        return number31;
    }

    public void setNumber31(double number31) {
        this.number31 = number31;
    }

    public double getNumber41() {
        return number41;
    }

    public void setNumber41(double number41) {
        this.number41 = number41;
    }

    public double getNumber51() {
        return number51;
    }

    public void setNumber51(double number51) {
        this.number51 = number51;
    }

    public double getNumber61() {
        return number61;
    }

    public void setNumber61(double number61) {
        this.number61 = number61;
    }

    public double getY11() {
        return y11;
    }

    public void setY11(double y11) {
        this.y11 = y11;
    }

    public double getY21() {
        return y21;
    }

    public void setY21(double y21) {
        this.y21 = y21;
    }

    public double getY31() {
        return y31;
    }

    public void setY31(double y31) {
        this.y31 = y31;
    }

    public double getY41() {
        return y41;
    }

    public void setY41(double y41) {
        this.y41 = y41;
    }

    public double getY51() {
        return y51;
    }

    public void setY51(double y51) {
        this.y51 = y51;
    }

    public double getY61() {
        return y61;
    }

    public void setY61(double y61) {
        this.y61 = y61;
    }

    private double number7;//похибка

    private String number8;// range похибка

    private double[] masX;

    private String result = "немає розв'язків";
    
    private String speed = "";

    private Random rand = new Random();

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getResult() {
        // if (number1 != 0.0 && number7 != 0.0 && number6 != 0 && number5 != 0 && number4 != 0 && number3 != 0 && number2 != 0){
        calculate();
        // }
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setMasX(double[] masX) {
        this.masX = masX;
    }

    public String getNumber8() {
        return number8;
    }

    public void setNumber8(String number8) {
        this.number8 = number8;
    }

    public double getY1() {
        y1 = (((A * number1 + B + 2 * abs(number7) * rand.nextDouble() - abs(number7))* 10000000000000l)/ 1 ) / 10000000000000l;
        System.out.println(" --------------------3.5 % 10   -------3.5 / 10------------------ y1 " + (3.5 % 10) + " - " + (3.5 / 10));
        return y1;
    }

    public void setY1(double y1) {
        this.y1 = y1;
    }

    public double getY2() {
        y2 = A * number2 + B + 2 * abs(number7) * rand.nextDouble() - abs(number7);
        return y2;
    }

    public void setY2(double y2) {
        this.y2 = y2;
    }

    public double getY3() {
        y3 = A * number3 + B + 2 * abs(number7) * rand.nextDouble() - abs(number7);
        return y3;
    }

    public void setY3(double y3) {
        this.y3 = y3;
    }

    public double getY4() {
        y4 = A * number4 + B + 2 * abs(number7) * rand.nextDouble() - abs(number7);
        return y4;
    }

    public void setY4(double y4) {
        this.y4 = y4;
    }

    public double getY5() {
        y5 = A * number5 + B + 2 * abs(number7) * rand.nextDouble() - abs(number7);
        return y5;
    }

    public void setY5(double y5) {
        this.y5 = y5;
    }

    public double getY6() {
        y6 = A * number6 + B + 2 * abs(number7) * rand.nextDouble() - abs(number7);
        return y6;
    }

    public void setY6(double y6) {
        this.y6 = y6;
    }

    public double getNumber1() {
        return number1;
    }

    public void setNumber1(double number1) {
        this.number1 = number1;
    }

    public double getNumber2() {
        return number2;
    }

    public void setNumber2(double number2) {
        this.number2 = number2;
    }

    public double getNumber3() {
        return number3;
    }

    public void setNumber3(double number3) {
        this.number3 = number3;
    }

    public double getNumber4() {
        return number4;
    }

    public void setNumber4(double number4) {
        this.number4 = number4;
    }

    public double getNumber5() {
        return number5;
    }

    public void setNumber5(double number5) {
        this.number5 = number5;
    }

    public double getNumber6() {
        return number6;
    }

    public void setNumber6(double number6) {
        this.number6 = number6;
    }

    public double getNumber7() {
        return number7;
    }

    public void setNumber7(double number7) {
        this.number7 = number7;
        createRangeError();
    }

    private void createRangeError() {
        if (number7 > -number7) {
            number8 = "[ " + (-number7) + " ; " + (number7) + " ]";
        } else {
            number8 = "[ " + (-number7) + " ; " + (number7) + " ]";
        }
    }

    private void calculate() {
        double[][] Arr = {
            {  number1,  1, -1, 0, 0, 0, 0, 0 },
            {  number2,  1, 0, -1, 0, 0, 0, 0},
            {  number3,  1,  0, 0, -1, 0, 0, 0},
            {  number4,  1,  0, 0, 0, -1, 0, 0 },
            {  number5,  1,  0, 0, 0, 0, -1, 0 },
            {  number6,  1,  0, 0, 0, 0, 0, -1 },
            {  -number1,  -1, -1, 0, 0, 0, 0, 0 },
            {  -number2,  -1, 0, -1, 0, 0, 0, 0},
            {  -number3,  -1,  0, 0, -1, 0, 0, 0},
            {  -number4,  -1,  0, 0, 0, -1, 0, 0 },
            {  -number5,  -1,  0, 0, 0, 0, -1, 0 },
            {  -number6,  -1,  0, 0, 0, 0, 0, -1 }
//            {  -8,  -1,  0 },
           };
        double[] c = { 1, 1, -1, -1, -1, -1, -1, -1 };
        double[] b = { y1, y2 , y3, y4, y5, y6, -y1, -y2 , -y3, -y4, -y5, -y6}; 
        double[] x = test(Arr, b, c);
        double x1 = x[0];
        double x2 = x[1];
        speed = "Швидкість " + x1;
        result = "Початкове розташування = " +x2;
        //re//turn CreateResult.createRes(masX, number7);
//        double[][] Arr = {
//            {number1,  1, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//            {number2,  1, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//            {number3,  1, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//            {number4,  1, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0},
//            {number5,  1, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0},
//            {number6,  1, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0},
//            {number11, 1, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0},
//            {number21, 1, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0},
//            {number31, 1, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0},
//            {number41, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0},
//            {number51, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0},
//            {number61, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1},
//            {-number1, -1, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//            {-number2, -1,  0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//            {-number3, -1,  0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//            {-number4, -1,  0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0},
//            {-number5, -1,  0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0},
//            {-number6, -1,  0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0},
//            {-number11, -1, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0},
//            {-number21, -1, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0},
//            {-number31, -1, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0},
//            {-number41, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0},
//            {-number51, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0},
//            {-number61, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1}
//        };
//        double[] c = {1, 1, -1, -1, -1, 
//                      -1, -1, -1, -1, -1,
//                      -1, -1, -1, -1, -1};
//        double[] b = {y1, y2, y3, y4, y5, y6,
//                        y11, y21, y31, y41, y51, y61,
//                        - y1, -y2, -y3, -y4, -y5, -y6,
//                        -y11, -y21, -y31, -y41, -y51, -y61
//                    };
//        double[] x = test(Arr, b, c);
//        double x1 = x[0];
//        double x2 = x[1];
//        result = "A = " + x1 + ", B = " + x2;
    }

    public double getA() {
        return A;
    }

    public void setA(double A) {
        this.A = A;
    }

    public double getB() {
        return B;
    }

    public void setB(double B) {
        this.B = B;
    }
}
