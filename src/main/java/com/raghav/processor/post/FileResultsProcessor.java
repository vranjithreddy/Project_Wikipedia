package com.raghav.processor.post;

import com.raghav.model.Article;
import com.raghav.processor.ProcessInfo;
import com.raghav.processor.Processor;
import com.raghav.processor.ToTextConvertor;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileResultsProcessor implements Processor , ToTextConvertor {
    public void process(ProcessInfo info) throws IOException {
        System.out.println("STORING RESULT TO FILE");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter filename you want to store results");
        String filename = scanner.next();
        if(!info.getResults().isEmpty()){
            System.out.println("Storing results in file "+ "");
            FileWriter fileWriter = null;
            try{
                fileWriter = new FileWriter(filename);
                for(Article article : info.getResults()){
                    fileWriter.write(toText(article));
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                if(fileWriter != null){
                    fileWriter.close();
                }
            }
        }
    }

    public String toText(Article article) {
        return article.toString();
    }
}
