package com.czxy;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "t_news")
public class News {
    @Id
    @Column(name = "id")
    private Integer nid;
    private Integer news_type_id;
    private String title;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date pub_date;
    private String mark;
    private String origin_url;
    private String content;
    private Integer uid;

    //一个消息有一个分类
    private Type type;
    //一个消息有一个用户(作者)
    private User user;
}
