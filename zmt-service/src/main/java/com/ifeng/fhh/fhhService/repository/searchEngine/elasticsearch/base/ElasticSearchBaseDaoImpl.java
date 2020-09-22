package com.ifeng.fhh.fhhService.repository.searchEngine.elasticsearch.base;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * elasticsearch基础数据访问接口实现
 * <p>
 * Created by licheng1 on 2017/4/28.
 */
public class ElasticSearchBaseDaoImpl implements ElasticSearchBaseDao {


    @Autowired
    protected ElasticSearchTemplate elasticSearchTemplate;

    @Override
    public void setElasticSearchTemplate(ElasticSearchTemplate template) {
        this.elasticSearchTemplate = template;
    }

    @Override
    public ElasticSearchTemplate getElasticSearchTemplate() {
        return this.elasticSearchTemplate;
    }
}
