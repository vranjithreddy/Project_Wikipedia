package com.raghav.processor.post;

import com.mongodb.*;
import com.raghav.db.ConnectionFactory;
import com.raghav.model.Article;
import com.raghav.processor.ProcessInfo;
import com.raghav.processor.Processor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.function.Function;

public class MigrateToMongoProcessor implements Processor{
    final Function<List<Article>, List<DBObject>> transformer = new Function<List<Article>, List<DBObject>>(){

        public List<DBObject> apply(List<Article> articles) {
            List<DBObject> dbObjects = new ArrayList<>();
            for(Article article : articles){
                final DBObject o = new BasicDBObject();
                o.put("id", article.getId());
                o.put("title", article.getTitle());
                o.put("body", article.getBody());
                o.put("reference", article.getReferences());
                o.put("url", article.getUrl());
                dbObjects.add(o);
            }

            return dbObjects;
        }
    };


    private DBCollection getCollection() throws IOException {
        Properties properties = ConnectionFactory.loadProp();

        final MongoClient client = new MongoClient(properties.getProperty("mongo.host"), Integer.valueOf(properties.getProperty("mongo.port")));
        final DB db = client.getDB(properties.getProperty("mongo.db"));
        final boolean auth = db.authenticate(properties.getProperty("mongo.user"), properties.getProperty("mongo.password").toCharArray());
        if(!auth){
            throw new RuntimeException("Invalid Mongo authentication......");
        }
        return  db.getCollection(properties.getProperty("mongo.collection"));
    }

    public void process(ProcessInfo info) throws Exception {
        getCollection().insert(transformer.apply(info.getResults()));
    }
}
