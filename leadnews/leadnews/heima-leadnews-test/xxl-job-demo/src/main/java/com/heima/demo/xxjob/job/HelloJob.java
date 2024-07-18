package com.heima.demo.xxjob.job;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 高翔宇
 * @since 2024/4/18 周四 下午2:58
 */
@Component
public class HelloJob {
    @XxlJob("demoJobHandler")
    public void helloJob() {
        System.out.println("简单任务执行了...");
    }
    /**
     * 测试分片任务
     */
    @XxlJob("shardingJobHandler")
    public void shardingJob() {
        System.out.println("分片任务执行了...");
        int shardIndex = XxlJobHelper.getShardIndex();
        int shardTotal = XxlJobHelper.getShardTotal();
        System.out.println("分片参数：分片索引 = " + shardIndex + ", 分片总数 = " + shardTotal);
        List<Integer> list = getList();
        for (Integer i : list) {
            if (i % shardTotal == shardIndex) {
                System.out.println("分片" + shardIndex + "执行了：" + i);
            }
        }
    }

    private List<Integer> getList(){
        ArrayList<Integer> integers = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            integers.add(i);
        }
        return integers;
    }
}
