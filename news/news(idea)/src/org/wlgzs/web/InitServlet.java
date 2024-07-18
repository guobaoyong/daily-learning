package org.wlgzs.web;

import net.sf.json.JSONObject;
import org.wlgzs.dao.NewsDao;
import org.wlgzs.dao.NewsTypeDao;
import org.wlgzs.model.News;
import org.wlgzs.model.NewsType;
import org.wlgzs.util.DbUtil;
import org.wlgzs.util.ResponseUtil;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

/**
 * @author zsh
 * @company wlgzs
 * @create 2018-11-08 20:39
 * @Describe
 */
public class InitServlet extends HttpServlet {

    DbUtil dbUtil = new DbUtil();
    NewsDao newsDao = new NewsDao();
    NewsTypeDao newsTypeDao = new NewsTypeDao();

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        this.refreshSystem(servletContext);
    }

    private void refreshSystem(ServletContext context){
        Connection connection = null;

        try {
            connection = dbUtil.getCon();
            //新闻类别
            List<NewsType> newsTypeList = newsTypeDao.newsTypeList(connection);
            context.setAttribute("newsTypeList",newsTypeList);

            //最近更新
            String sql = "select * from t_news order by publishDate desc limit 0,8";
            List<News> newestNewsList = newsDao.newsList(connection, sql);
            context.setAttribute("newestNewsList",newestNewsList);

            //热点新闻
            sql = "select * from t_news order by click desc limit 0,8";
            List<News> hotNewsList = newsDao.newsList(connection, sql);
            context.setAttribute("hotNewsList",hotNewsList);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dbUtil.closeCon(connection);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session=request.getSession();
        ServletContext application=session.getServletContext();
        this.refreshSystem(application);
        JSONObject result=new JSONObject();
        result.put("success", true);
        try {
            ResponseUtil.write(result, response);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
