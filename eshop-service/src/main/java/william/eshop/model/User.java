package william.eshop.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @Author zhangshenao
 * @Date 2019-11-21
 * @Description 用户实体
 */
@Data
@Table(name = "users")
public class User {
    @Id
    private String id;

    private String username;

    private String password;

    private String nickname;

    private String realname;

    private String face;

    private String mobile;

    private String email;

    private int sex;

    private Date birthday;

    @Column(name = "created_time")
    private Date createdTime;

    @Column(name = "updated_time")
    private Date updatedTime;
}
