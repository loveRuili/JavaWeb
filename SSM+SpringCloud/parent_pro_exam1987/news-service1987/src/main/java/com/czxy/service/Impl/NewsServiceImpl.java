package com.czxy.service.Impl;

import com.czxy.*;
import com.czxy.mapper.NewsMapper;
import com.czxy.service.NewsService;
import com.github.pagehelper.PageHelper;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {
    @Resource
    private NewsMapper newsMapper;
    @Resource
    private UserFeign userFeign;
    @Resource
    private TypeFeign typeFeign;
    @Override
    public BaseResult selectNewsByCondition(NewsVo newsVo) {
        //1.条件查询
        Example example = new Example(News.class);
        Example.Criteria criteria = example.createCriteria();
        if (newsVo.getTitle()!=null&&newsVo.getTitle()!=""){
            criteria.andLike("title","%"+newsVo.getTitle()+"%");
        }
        if (newsVo.getNews_type_id()!=null&&newsVo.getNews_type_id()!=0){
            criteria.andEqualTo("news_type_id",newsVo.getNews_type_id());
        }
        if (newsVo.getStart_time()!=null){
            criteria.andGreaterThanOrEqualTo("pub_date",newsVo.getStart_time());
        }
        if (newsVo.getEnd_time()!=null){
            criteria.andLessThanOrEqualTo("pub_date",newsVo.getEnd_time());
        }
        //2.条件查询总条数
        int total = newsMapper.selectByExample(example).size();
        //3.分页查询
        PageHelper.startPage(newsVo.getPageNum(),newsVo.getPageSize());
        List<News> news = newsMapper.selectByExample(example);
        //4.填充news和type
        for (News news1 : news) {
            news1.setUser(userFeign.selectUserByUid(news1.getUid()));
            news1.setType(typeFeign.selectTypeByTid(news1.getNews_type_id()));
        }
        return BaseResult.success(news,total);
    }

    @Override
    public void editNews(News news) {
        newsMapper.updateByPrimaryKey(news);
    }

    @Override
    public News selectNewsByNid(Integer uid) {
        News news = newsMapper.selectByPrimaryKey(uid);
        return news;
    }

    @Override
    public void addNews(News news,String authorization) {
        Claims claims = JWTUtil.parseToken(authorization, "user");
        System.out.println(claims);
        String userId = claims.get("userId").toString();
        news.setUid(Integer.parseInt(userId));
        newsMapper.insert(news);
    }

    @Override
    public void delNewsByNid(int nid) {
        newsMapper.deleteByPrimaryKey(nid);
    }
}
