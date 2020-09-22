package com.ifeng.fhh.fhhService.service.category;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * 分类业务接口
 * <p>
 * Created by chenwj3 on 2017/1/19.
 */
public interface CategoryService {



    /**
     * @return 全量分类信息，转换成map，方便取
     */
    CompletableFuture<Map<String, String>> findAll();

}
