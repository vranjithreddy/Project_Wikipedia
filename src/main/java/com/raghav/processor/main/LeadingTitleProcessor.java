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

public class LeadingTitleProcessor extends AbstractProcessor{

    public List<Article> doProcess(){

        final Scanner scanner = new Scanner(System.in);
        System.out.print("Please type title (partial title allowed): ");
        try{
            String title = scanner.next();
            if(title == null || title.length() == 0){
                throw new RuntimeException("Invalid Value . ");
            }
            return findArticle(title);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return new ArrayList<Article>();

    }




    public List<Article> findArticle(String title){
        List<Article> articles = new ArrayList<Article>();
        Connection connection = null;
        try{
            connection = getConnection();
            final PreparedStatement stmt = connection.prepareStatement(getQuery());
            stmt.setString(1, title+"%");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                articles.add(ModelUtility.convert(rs));
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
        return articles;

    }

    private String getQuery(){
        return "select * from article where title like ?";
    }


}
