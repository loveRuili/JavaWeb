package com.czxy.controller;

import com.czxy.BaseResult;
import com.czxy.News;
import com.czxy.NewsVo;
import com.czxy.service.NewsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/news")
public class NewsController {
    @Resource
    private NewsService newsServiceImpl;

    @PostMapping
    public BaseResult selectNewsByCondition(@RequestBody NewsVo newsVo){
        BaseResult baseResult = newsServiceImpl.selectNewsByCondition(newsVo);
        return baseResult;
    }
    @PutMapping
    public void editNews(@RequestBody News news){
        newsServiceImpl.editNews(news);
    }
    @GetMapping("/{nid}")
    public BaseResult selectNewsByNid(@PathVariable("nid") Integer uid){
        News news = newsServiceImpl.selectNewsByNid(uid);
        return BaseResult.success(news);
    }
    @PostMapping("/add")
    public void addNews(@RequestBody News news,@RequestHeader("authorization") String authorization){
        System.out.println(authorization);
        newsServiceImpl.addNews(news,authorization);
    }
    @DeleteMapping("/{uids}")
    public void batchDel(@PathVariable("uids") String uids){
        String[] split = uids.split(",");
        for (String nidStr : split) {
            newsServiceImpl.delNewsByNid(Integer.parseInt(nidStr));
        }
    }
}
