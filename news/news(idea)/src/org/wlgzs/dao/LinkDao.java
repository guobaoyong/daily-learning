package org.wlgzs.dao;

import org.wlgzs.model.Link;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zsh
 * @company wlgzs
 * @create 2018-11-08 14:59
 * @Describe
 */
public class LinkDao {

    public List<Link> linkList(Connection connection,String sql) throws SQLException {
        List<Link> linkList = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            Link link = new Link();
            link.setLinkId(resultSet.getInt("linkId"));
            link.setLinkName(resultSet.getString("linkName"));
            link.setLinkUrl(resultSet.getString("linkUrl"));
            link.setLinkEmail(resultSet.getString("linkEmail"));
            link.setOrderNum(resultSet.getInt("orderNum"));
            linkList.add(link);
        }
        return linkList;
    }

    public int LinkAdd(Connection connection,Link link) throws SQLException {
        String sql = "insert into t_link values(null,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,link.getLinkName());
        preparedStatement.setString(2,link.getLinkUrl());
        preparedStatement.setString(3,link.getLinkEmail());
        preparedStatement.setInt(4,link.getOrderNum());
        return preparedStatement.executeUpdate();
    }

    public int linkUpdate(Connection con,Link link)throws Exception{
        String sql="update t_link set linkName=?,linkUrl=?,linkEmail=?,orderNum=? where linkId=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, link.getLinkName());
        pstmt.setString(2, link.getLinkUrl());
        pstmt.setString(3, link.getLinkEmail());
        pstmt.setInt(4, link.getOrderNum());
        pstmt.setInt(5, link.getLinkId());
        return pstmt.executeUpdate();
    }

    public int linkDelete(Connection con,String linkId)throws Exception{
        String sql="delete from t_link where linkId=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, linkId);
        return pstmt.executeUpdate();
    }

    public Link getLinkById(Connection con,String linkId)throws Exception{
        String sql="select * from t_link where linkId=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, linkId);
        ResultSet rs=pstmt.executeQuery();
        Link link=new Link();
        while(rs.next()){
            link.setLinkId(rs.getInt("linkId"));
            link.setLinkName(rs.getString("linkName"));
            link.setLinkUrl(rs.getString("linkUrl"));
            link.setLinkEmail(rs.getString("linkUrl"));
            link.setOrderNum(rs.getInt("orderNum"));
        }
        return link;
    }

}
