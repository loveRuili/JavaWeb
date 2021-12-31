package com.czxy;

import lombok.Data;

import java.util.Date;

@Data
public class NewsVo {
    private Integer pageSize;
    private Integer pageNum;
    private String title;
    private Integer news_type_id;
    private Date start_time;
    private Date end_time;
}
