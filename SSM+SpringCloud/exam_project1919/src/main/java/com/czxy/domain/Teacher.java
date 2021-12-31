package com.czxy.domain;

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

@Table(name = "t_teacher")
@Data//自动生成get/set
@NoArgsConstructor//无参构造
@AllArgsConstructor//全参构造
@ToString//ToString
public class Teacher {
    @Id
    @Column(name = "id")
    private Integer tid;
    @Column(name = "teacher_name")
    private String teacher_name;
    @Column(name = "pwd")
    private String pwd;
    @Column(name = "sex")
    private String sex;
    @Column(name = "salary")
    private Double salary;

    @Column(name = "type_id")
    private Integer type_id;
    private Type type;//一个老师有一种类型
    @Column(name = "school_id")
    private Integer school_id;
    private School school;//一个老师有一个学校

    @Column(name = "hire_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date hire_date;

    @Column(name = "remark")
    private String remark;


}
