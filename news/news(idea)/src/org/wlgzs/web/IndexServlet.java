package org.wlgzs.web;

import org.wlgzs.dao.LinkDao;
import org.wlgzs.dao.NewsDao;
import org.wlgzs.dao.NewsTypeDao;
import org.wlgzs.model.Link;
import org.wlgzs.model.News;
import org.wlgzs.model.NewsType;
import org.wlgzs.util.DbUtil;
import org.wlgzs.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zsh
 * @company wlgzs
 * @create 2018-11-08 15:20
 * @Describe
 */
public class IndexServlet extends HttpServlet {

    DbUtil dbUtil = new DbUtil();
    NewsDao newsDao = new NewsDao();
    NewsTypeDao newsTypeDao = new NewsTypeDao();
    LinkDao linkDao = new LinkDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Connection connection = null;

        try {
            connection = dbUtil.getCon();

            //新闻类别
            List<NewsType> newsTypeList = newsTypeDao.newsTypeList(connection);
            req.setAttribute("newsTypeList",newsTypeList);

            //图片新闻
            String sql = "select * from t_news where isImage = 1 order by publishDate desc limit 0,5";
            List<News> imageNewsList = newsDao.newsList(connection, sql);
            req.setAttribute("imageNewsList",imageNewsList);

            //新闻头条
            sql = "select * from t_news where isHead = 1 order by publishDate desc limit 0,1";
            List<News> headNewsList = newsDao.newsList(connection, sql);
            News headNews = headNewsList.get(0);
            headNews.setContent(StringUtil.Html2Text(headNews.getContent()));
            req.setAttribute("headNews",headNews);

            //最近更新
            sql = "select * from t_news order by publishDate desc limit 0,8";
            List<News> newestNewsList = newsDao.newsList(connection, sql);
            req.setAttribute("newestNewsList",newestNewsList);

            //热点新闻
            sql = "select * from t_news where isHot = 1 order by publishDate desc limit 0,8";
            List<News> hotNewsList = newsDao.newsList(connection, sql);
            req.setAttribute("hotNewsList",hotNewsList);

            //类别新闻
            List allIndexList = new ArrayList();
            if (newsTypeList!=null && newsTypeList.size() != 0){
                for (int i = 0;i<newsTypeList.size();i++){
                    NewsType newsType = newsTypeList.get(i);
                    sql = "select * from t_news,t_newsType where typeId = newsTypeId and typeId =" + newsType.getNewsTypeId()+" order by publishDate desc limit 0,8";
                    List<News> OneSubList = newsDao.newsList(connection, sql);
                    allIndexList.add(OneSubList);
                }
            }
            req.setAttribute("allIndexList",allIndexList);

            //友情链接
            sql = "select * from t_link order by orderNum";
            List<Link> linkList = linkDao.linkList(connection, sql);
            req.setAttribute("linkList",linkList);

            //转发主页
            req.getRequestDispatcher("index.jsp").forward(req,resp);

        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
