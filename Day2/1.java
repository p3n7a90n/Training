package com.company;



class Pattern implements  Runnable
{
    private  int number;
    public  Pattern(int n)
{
    this.number=n;
}


    @Override
    public void run() {


        for(int i=0;i<number;i++) {
            System.out.print(" ") ;

            for (int j = 0; j <=i; j++) {
                System.out.print("* ") ;
            }

            System.out.println();
        }

    }
}
 class InvertedPattern implements  Runnable
{
    private int number;
    public InvertedPattern(int number)
{
    this.number=number;
}

    @Override
    public void run() {

        for (int i= number; i>= 1; i--)
        {
            for (int j=number; j>i;j--)
            {
                System.out.print(" ");
            }
            for (int k=1;k<=i;k++)
            {
                System.out.print("*");
            }
            System.out.println("");
        }

        }


}

public class Main {

    public static void main(String[] args) {

        Pattern p1=new Pattern(5);
        InvertedPattern p2=new InvertedPattern(5);
        Thread t1=new Thread(p1);

        t1.start();
        Thread t2=new Thread(p2);
        t2.start();



    }
}
