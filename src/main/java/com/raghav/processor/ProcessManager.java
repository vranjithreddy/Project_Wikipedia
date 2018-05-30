package com.raghav.processor;

import java.io.IOException;
import java.util.List;

public class ProcessManager {
    private final MainProcess main;
    private final List<Processor> postProcess;

    public ProcessManager(MainProcess main, List<Processor> postProcess) {
        this.main = main;
        this.postProcess = postProcess;
    }

    public void execute() throws Exception {
        final ProcessInfo processInfo = new ProcessInfo();
        main.process(processInfo);
        for(Processor p : postProcess){
            p.process(processInfo);
        }

    }



}
