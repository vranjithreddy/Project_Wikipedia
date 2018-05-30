package com.raghav.processor.post;

import com.raghav.db.ConnectionFactory;
import com.raghav.processor.ProcessInfo;
import com.raghav.processor.Processor;

import java.io.IOException;

public class CloseConnectionProcessor implements Processor{

    public void process(ProcessInfo info) throws Exception {
        ConnectionFactory.close();
    }
}
