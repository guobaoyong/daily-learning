package qqzsh.top.hd.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Select;
import qqzsh.top.hd.pojo.UsersReport;
import qqzsh.top.hd.pojo.vo.UserVO;
import qqzsh.top.hd.pojo.vo.UsersReportVO;

import java.util.List;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-05-31 17:37
 * @description
 */
public interface UsersReportMapper extends BaseMapper<UsersReport> {

    @Select("SELECT users_report.* , users.username as deal_user,videos.video_path as path,users.username as touser " +
            "FROM users_report " +
            "LEFT JOIN users on users_report.deal_user_id = users.id  " +
            "LEFT JOIN videos on users_report.deal_video_id = videos.id")
    List<UsersReportVO> queryAll(Page page);

    @Select("SELECT users_report.* , users.username as deal_user,videos.video_path as path,users.username as touser " +
            "FROM users_report " +
            "LEFT JOIN users on users_report.deal_user_id = users.id  " +
            "LEFT JOIN videos on users_report.deal_video_id = videos.id " +
            "WHERE users_report.title like '%${value}%' or users_report.content " +
            "like '%${value}%'")
    List<UsersReportVO> queryAllBySelect(Page page,String value);

    @Select("SELECT users_report.* , users.username as deal_user,videos.video_path as path,users.username as touser " +
            "FROM users_report " +
            "LEFT JOIN users on users_report.deal_user_id = users.id  " +
            "LEFT JOIN videos on users_report.deal_video_id = videos.id " +
            "WHERE users_report.status = ${value}")
    List<UsersReportVO> queryAllByStatus(Page page,String value);
}
