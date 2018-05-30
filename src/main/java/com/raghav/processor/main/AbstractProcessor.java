package com.raghav.processor.main;

import com.raghav.db.ConnectionFactory;
import com.raghav.model.Article;
import com.raghav.processor.MainProcess;
import com.raghav.processor.ProcessInfo;

import java.sql.Connection;
import java.util.List;

public abstract class AbstractProcessor implements MainProcess {

    public final void process(ProcessInfo processInfo) {
        processInfo.getResults().addAll(this.doProcess());
    }
    abstract List<Article> doProcess();


    protected  Connection getConnection() throws Exception {
        return ConnectionFactory.getConnection();
    }

}
