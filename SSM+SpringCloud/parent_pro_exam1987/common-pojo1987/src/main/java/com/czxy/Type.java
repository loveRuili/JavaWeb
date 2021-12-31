package com.czxy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "t_news_type")
public class Type {
    @Id
    @Column(name = "id")
    private Integer tid;
    @Column(name = "name")
    private String tname;
}
