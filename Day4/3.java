package com.company;


import java.util.concurrent.*;

class SharedResource{

    SharedResource(String res)

    {
        System.out.print(res);
    }
}

class Handler extends Thread
{
    Semaphore sm;
    String name;
    public Handler(Semaphore sm,String name)
    {   super(name);
        this.name=name;
        this.sm=sm;
    }

    @Override
    public  void run()
    {    if(this.getName().equals("A"))
    {
        try {
            sm.acquire();
            SharedResource s1 =new SharedResource("hello");
        }
       catch (InterruptedException e) {
            e.printStackTrace();
        }
        sm.release();


    }
    else
    {

        try {
            sm.acquire();
            SharedResource s1 =new SharedResource("world");
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        sm.release();
    }

    }
}

public class Main {

    public static void main(String[] args)  {

    Semaphore sm=new Semaphore(1);
       Handler h1 = new Handler(sm, "A");
        Handler h2 = new Handler(sm, "B");


        h1.start();
        h2.start();

        

    }



}
