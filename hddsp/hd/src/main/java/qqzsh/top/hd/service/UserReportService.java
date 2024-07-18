package qqzsh.top.hd.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import qqzsh.top.hd.pojo.vo.UserVO;
import qqzsh.top.hd.pojo.vo.UsersReportVO;

import java.util.List;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-06-04 20:07
 * @description
 */
public interface UserReportService {

    List<UsersReportVO> queryAll(Page page);

    Integer update(Integer id,String field,String value);

    Integer delete(Integer id);

    Integer sum();

    Integer sumBySelect(String value);

    List<UsersReportVO> queryAllBySelect(Page page,String value);

    List<UsersReportVO> queryAllByStatus(Page page,String value);
}
