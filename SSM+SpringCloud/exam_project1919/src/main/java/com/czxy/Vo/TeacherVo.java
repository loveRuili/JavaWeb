package com.czxy.Vo;

import lombok.Data;
import lombok.ToString;

import java.util.Date;
@Data
@ToString
public class TeacherVo {
    private String teacher_name;
    private String school_name;
    private Double minSalary;
    private Double maxSalary;
    private Date startHire_date;
    private Date endHire_date;
    private Integer pageNum;
    private Integer pageSize;
}
