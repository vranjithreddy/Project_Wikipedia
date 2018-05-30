package com.raghav.util;

import com.raghav.model.Article;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ModelUtility {


    public static Article convert(ResultSet rs) throws SQLException {
        final Article article = new Article();
        article.setId(rs.getInt("id"));
        article.setTitle(rs.getString("title"));
        article.setTitle(rs.getString("body"));
        article.setReferences(rs.getString("reference"));
        article.setUrl(rs.getString("url"));
        return article;
    }

}
