package qqzsh.top.soufun.service.house;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import qqzsh.top.soufun.ApplicationTests;

import java.io.File;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-08-30 11:21
 * @description
 */
public class QiNiuServiceTests extends ApplicationTests {

    @Autowired
    private IQiNiuService qiNiuService;

    @Test
    public void testUploadFile() {
        String fileName = "D:\\idea_project\\soufun\\tmp\\AidlingerHoehe_ZH-CN11764360351_1920x1080.jpg";
        File file = new File(fileName);

        Assert.assertTrue(file.exists());

        try {
            Response response = qiNiuService.uploadFile(file);
            Assert.assertTrue(response.isOK());
        } catch (QiniuException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDelete() {
        String key = "FhfXX5SzF9JxR3pamMRHR04sAXpt";
        try {
            Response response = qiNiuService.delete(key);
            Assert.assertTrue(response.isOK());
        } catch (QiniuException e) {
            e.printStackTrace();
        }
    }
}
