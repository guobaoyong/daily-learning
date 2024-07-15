package qqzsh.top.bingimages.config;

import org.springframework.core.io.Resource;
import java.nio.file.Path;

/**
 * @author zsh
 * @site https://www.qqzsh.top
 * @create 2020-03-27 9:38
 * @Description
 */
public interface StorageService {

    Path load(String filename);

    Resource loadAsResource(String filename);

}
