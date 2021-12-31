package com.czxy.service;

import com.czxy.BaseResult;
import com.czxy.News;
import com.czxy.NewsVo;

import java.util.List;

public interface NewsService {
    BaseResult selectNewsByCondition(NewsVo newsVo);

    void editNews(News news);

    News selectNewsByNid(Integer uid);

    void addNews(News news,String authorization);

    void delNewsByNid(int parseInt);
}
