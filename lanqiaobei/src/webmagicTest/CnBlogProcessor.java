package webmagicTest;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zsh
 * @site qqzsh.top
 * @company wlgzs
 * @create 2019-04-08 20:35
 * @Description
 */
public class CnBlogProcessor  implements PageProcessor {

    // 抓取网站的相关配置，包括：编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);
    // 文章数量
    private static int size = 0;
    // 文章集合
    private static List<CnBlogs> cnBlogses = new ArrayList<>();

    // 抽取逻辑类
    @Override
    public void process(Page page) {
        // 如果是精华文章的列表页
        if (page.getUrl().regex("https://www.cnblogs.com/pick/.*").match()) {
            List<String> list = page.getHtml().xpath("//div[@id='post_list']").links().regex("^(.*\\.html)$").all();
            // 去重复URL
            list = list.stream().distinct().collect(Collectors.toList());
            // 添加到待爬取队列
            page.addTargetRequests(list);
            page.addTargetRequests(page.getHtml().xpath("//div[@class='pager']").links().all());
        } else {
            size++;
            CnBlogs cnBlogs = new CnBlogs();
            // 标题
            cnBlogs.setTitle(page.getHtml().xpath("//div[@class='post']/h1[@class='postTitle']/a/text()").get());
            // 作者
            cnBlogs.setAuthor(page.getHtml().xpath("//a[@id='Header1_HeaderTitle']/text()").get());
            // 发布日期
            cnBlogs.setDateTime(page.getHtml().xpath("//div[@class='postDesc']/span[@id='post-date']/text()").get());
            // URL
            cnBlogs.setUrl(page.getUrl().toString());

            synchronized (cnBlogses) {
                cnBlogses.add(cnBlogs);
            }
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new CnBlogProcessor()).thread(5).run();
        System.out.println(cnBlogses.size());
    }
}
