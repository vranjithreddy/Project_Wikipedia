package com.raghav.processor.post;

import com.mysql.jdbc.StringUtils;
import com.raghav.model.Article;
import com.raghav.processor.ProcessInfo;
import com.raghav.processor.Processor;
import com.raghav.processor.ToTextConvertor;

import java.io.IOException;

public class ReverseContentPrintProcessor implements Processor, ToTextConvertor {

    public void process(ProcessInfo info) throws IOException {
        for(Article a : info.getResults()){
            System.out.println(toText(a));
        }
    }

    public String toText(Article article){
        String body = article.getBody();
        StringBuilder builder = new StringBuilder();
        for(String wrd : body.split(" ")){
            builder.append(reverse(wrd)).append(" ");
        }

        return builder.toString();

    }

    private String reverse(String str){
        return new StringBuilder(str).reverse().toString();
    }
}
