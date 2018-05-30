package com.raghav.processor.main;

import com.raghav.model.Article;
import com.raghav.util.ModelUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QueryByIdProcessor extends AbstractProcessor{


    public List<Article> doProcess(){
        List<Article> list = new ArrayList<Article>();
        final Scanner scanner = new Scanner(System.in);
        System.out.print("Please type the index between 1 to 10000 : ");
        try{
            int index = scanner.nextInt();
            if(index < 1 || index > 10000){
                throw new RuntimeException("Invalid Value . Value should be between 1 to 1000...");
            }

            System.out.println("Finding "+ index + " ....");


            Article article = findArticle(index);
            if(article != null ){
                list.add(article);
            }



        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return list;
    }

    public Article findArticle(int index){
        Article article = null;
        Connection connection = null;
        try{
            connection = getConnection();
            final PreparedStatement stmt = connection.prepareStatement(getQuery());
            stmt.setInt(1, index);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                article = ModelUtility.convert(rs);
            }
        }catch (Exception e){
            System.out.printf(e.getMessage());
        }finally {
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return article;

    }

    private String getQuery(){
        return "select * from article where id = ?";
    }


}
