package com.company;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main(String[] args)  throws InterruptedException, ExecutionException
    {    Person p1=new Person(1,LocalDate.now(),"test");
        Person p2=new Person(2,LocalDate.now(),"testing");
      //  ExecutorService es = Executors.newSingleThreadExecutor();
        ExecutorService es = Executors.newFixedThreadPool(2);



        Future temp=es.submit(new Task());
            System.out.println(temp.get());
            Future temp2=es.submit(new Task());
            System.out.println(temp2.get());



    }
}


class Task implements Callable<List<Person>>
{
    List<Person> common=new ArrayList<>();
    //Person p1=new Person(1,LocalDate.now(),"test");







    @Override
    public List<Person> call() throws Exception {
        return  common;
    }
}

class Person
{

    private int id;
    private LocalDate dob;
    private String jobs;
    private ReentrantLock lock = new ReentrantLock(true);



    public Person(int id, LocalDate dob, String jobs)
    {
        this.dob=dob;
        this.id=id;
        this.jobs=jobs;
    }

    @Override

    public String toString() {
        String res="";
        if(!this.lock.isLocked())
        {   this.lock.lock();
            try {
                res= "Person{" +
                        "id=" + id +
                        ", dob=" + dob +
                        ", jobs='" + jobs + '\'' +
                        ", lock=" + lock +
                        '}';


            } catch (Exception e) {
                e.printStackTrace();
            }
            finally
            {
                this.lock.unlock();
            }
        }
        else {
            try {
                wait(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

       return  res;
    }



}
