package com.raghav.processor;

import com.raghav.model.Article;

import java.io.IOException;
import java.util.List;

public interface Processor  {

    void process(ProcessInfo info) throws Exception;

}
