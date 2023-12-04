package com.ibm.controller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.ibm.kafka.Producer;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;

@Controller("/produce")
public class BatchController {
    
    @Inject
    Producer producer;
    
    @Post("/batch/{numOfThreads}/{numMessages}")
    public boolean batchProduce(@PathVariable Integer numOfThreads, @PathVariable Integer numMessages){
        ExecutorService executor = Executors.newFixedThreadPool(numOfThreads);

        for (int i=0; i<numOfThreads; i++){
            ThreadProducer threadProducer = new ThreadProducer(i, producer, numMessages);
            executor.submit(threadProducer);
        }
        
        return true;
    }

    class ThreadProducer implements Runnable {
        int numOfMessages;
        String threadId;
        Producer producer;
        ThreadProducer(int threadId, Producer producer, int numOfMessages){
            this.threadId = "" + threadId;
            this.producer = producer;
            this.numOfMessages = numOfMessages;
        }

        @Override
        public void run() {
                for(int j=0; j<numOfMessages; j++){
                    producer.sendChat(threadId, ""+j);
                }   
        }
        
    }
}
