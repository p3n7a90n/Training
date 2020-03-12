package com.company;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;


class SharedResources implements Callable<List<AtomicInteger>>
{
   private  List<AtomicInteger> shared=new ArrayList<>();
   public List<AtomicInteger> read() {

       return  shared;
   }

   public void write(AtomicInteger temp) {

       shared.add(temp);

   }

    @Override
    public List<AtomicInteger> call() throws Exception {
        return shared;
    }
}
public class Main {

    public static void main(String[] args)  throws InterruptedException, ExecutionException
    {

        ExecutorService es = Executors.newFixedThreadPool(2);

        SharedResources s1=new SharedResources();
        AtomicInteger ai=new AtomicInteger(2);

        s1.write(ai);
        Future temp=es.submit(s1);
        System.out.println(temp.get());
        Future temp2=es.submit(new SharedResources());
          System.out.println(temp2.get());



    }
}

