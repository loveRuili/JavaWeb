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
@Table(name = "t_user")
public class User {
    @Id
    @Column(name = "id")
    private Integer uid;
    private String username;
    private String nickname;
    private String password;

    transient String repassword;

    transient String mobile;
    transient String code;
}
