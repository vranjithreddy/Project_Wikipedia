package com.raghav.processor.post;

import com.raghav.model.Article;
import com.raghav.processor.ProcessInfo;
import com.raghav.processor.Processor;
import com.raghav.processor.ToTextConvertor;

public class PrintResultProcessor implements Processor, ToTextConvertor{
    public void process(ProcessInfo info) {
        System.out.println("------------ RESULTS ----------");
        for(Article a : info.getResults()){
            System.out.println(toText(a));
        }
        System.out.println("------------------------------");
    }

    public String toText(Article a){
        return a.toString();
    }
}
