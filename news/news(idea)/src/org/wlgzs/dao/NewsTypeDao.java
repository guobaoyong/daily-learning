package org.wlgzs.dao;

import org.wlgzs.model.Link;
import org.wlgzs.model.NewsType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zsh
 * @company wlgzs
 * @create 2018-11-08 15:04
 * @Describe
 */
public class NewsTypeDao {

    public List<NewsType> newsTypeList(Connection connection) throws SQLException {
        List<NewsType> newsTypeList = new ArrayList<>();
        String sql = "select * from t_newsType";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            NewsType newsType = new NewsType();
            newsType.setNewsTypeId(resultSet.getInt("newsTypeId"));
            newsType.setTypeName(resultSet.getString("typeName"));
            newsTypeList.add(newsType);
        }
        return newsTypeList;
    }

    public NewsType getNewsTypeById(Connection con,String newsTypeId)throws Exception{
        NewsType newsType=new NewsType();
        String sql="select * from t_newsType where newsTypeId=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, newsTypeId);
        ResultSet rs=pstmt.executeQuery();
        if(rs.next()){
            newsType.setNewsTypeId(rs.getInt("newsTypeId"));
            newsType.setTypeName(rs.getString("typeName"));
        }
        return newsType;
    }

    public int newsTypeAdd(Connection con,NewsType newsType)throws Exception{
        String sql="insert into t_newsType values(null,?)";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, newsType.getTypeName());
        return pstmt.executeUpdate();
    }

    public int newsTypeUpdate(Connection con,NewsType newsType)throws Exception{
        String sql="update t_newsType set typeName=? where newsTypeId=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, newsType.getTypeName());
        pstmt.setInt(2, newsType.getNewsTypeId());
        return pstmt.executeUpdate();
    }

    public int newsTypeDelete(Connection con,String newsTypeId)throws Exception{
        String sql="delete from t_newsType where newsTypeId=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, newsTypeId);
        return pstmt.executeUpdate();
    }

}
