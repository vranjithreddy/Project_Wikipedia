package com.raghav;

import com.raghav.processor.*;
import com.raghav.processor.main.FetchLimitedBatchProcessor;
import com.raghav.processor.main.LeadingTitleProcessor;
import com.raghav.processor.main.QueryByIdProcessor;
import com.raghav.processor.post.FileResultsProcessor;
import com.raghav.processor.post.MigrateToMongoProcessor;
import com.raghav.processor.post.PrintResultProcessor;
import com.raghav.processor.post.ReverseContentPrintProcessor;

import java.io.File;

public class Main7 {

    public  enum Program{
        QUERY_BY_ID(new QueryByIdProcessor()),
        QUERY_LIKE_TITLE(new LeadingTitleProcessor()),
        QUERY_1000_DOC(new FetchLimitedBatchProcessor(1000)),

        CLOSE_CONNECTION_PROCESSOR(new PrintResultProcessor()),
        PRINT_RESULT_PROCESSOR(new PrintResultProcessor()),
        FILE_RESULT_PROCESSOR(new FileResultsProcessor()),
        REVERSE_CONTENT_RESULT_PROCESSOR(new ReverseContentPrintProcessor()),
        MIGRATE_TO_MONGO(new MigrateToMongoProcessor())
        ;


        private Processor processor;
        Program(Processor processor) {
            this.processor = processor;
        }

        public Processor getProcessor() {
            return processor;
        }
    }

    public static void main(String[] args) throws Exception {
        new com.raghav.processor.ProcessBuilder((MainProcess)Program.QUERY_BY_ID.getProcessor())
                .withProcessProcess(Program.PRINT_RESULT_PROCESSOR.getProcessor()).build().execute();

        new com.raghav.processor.ProcessBuilder((MainProcess)Program.QUERY_LIKE_TITLE.getProcessor())
                .withProcessProcess(Program.CLOSE_CONNECTION_PROCESSOR.getProcessor())
                .withProcessProcess(Program.PRINT_RESULT_PROCESSOR.getProcessor())
                .build().execute();


        new com.raghav.processor.ProcessBuilder((MainProcess)Program.QUERY_LIKE_TITLE.getProcessor())
                .withProcessProcess(Program.CLOSE_CONNECTION_PROCESSOR.getProcessor())
                .withProcessProcess(Program.FILE_RESULT_PROCESSOR.getProcessor())
                .build().execute();

        new com.raghav.processor.ProcessBuilder((MainProcess)Program.QUERY_LIKE_TITLE.getProcessor())
                .withProcessProcess(Program.CLOSE_CONNECTION_PROCESSOR.getProcessor())
                .withProcessProcess(Program.REVERSE_CONTENT_RESULT_PROCESSOR.getProcessor())
                .build().execute();

        new com.raghav.processor.ProcessBuilder((MainProcess)Program.QUERY_1000_DOC.getProcessor())
                .withProcessProcess(Program.CLOSE_CONNECTION_PROCESSOR.getProcessor())
                .withProcessProcess(Program.MIGRATE_TO_MONGO.getProcessor())
                .build().execute();

    }



}
