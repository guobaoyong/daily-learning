package qqzsh.top.soufun.service.house;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;

import java.io.File;
import java.io.InputStream;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-08-30 11:15
 * @description 七牛云服务
 */
public interface IQiNiuService {

    Response uploadFile(File file) throws QiniuException;

    Response uploadFile(InputStream inputStream) throws QiniuException;

    Response delete(String key) throws QiniuException;

}
