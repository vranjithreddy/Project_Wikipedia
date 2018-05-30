package com.raghav.processor;

import java.util.ArrayList;
import java.util.List;

public class ProcessBuilder {
    private final MainProcess main;
    private List<Processor> postProcessor = new ArrayList<Processor>();

    public ProcessBuilder(MainProcess main) {
        this.main = main;
    }


    public ProcessBuilder withProcessProcess(Processor postProcess){
        postProcessor.add(postProcess);
        return this;
    }

    public ProcessManager build(){
        return new ProcessManager(main, postProcessor);
    }



}
