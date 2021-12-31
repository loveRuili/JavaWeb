package com.czxy.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_type")
@Data//自动生成get/set
@NoArgsConstructor//无参构造
@AllArgsConstructor//全参构造
@ToString//ToString
public class Type{
    @Id
    @Column(name = "id")
    private Integer typeId;
    @Column(name = "city")
    private String city;

}
