package william.eshop.model.address;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import lombok.Data;
import william.eshop.constants.CommonFlag;
import william.eshop.vo.address.UserAddressVO;

/**
 * @Author zhangshenao
 * @Date 2020-04-28
 * @Description 收货地址实体类
 */
@Data
@Table(name = "user_address")
public class UserAddress {
    @Id
    private String id;  //主键

    private String userId;  //关联用户id

    private String receiver;    //收件人姓名

    private String mobile;      //收件人手机号

    private String province;        //省份

    private String city;    //城市

    private String district;    //区县

    private String detail;  //详细地址

    private String extend;  //扩展字段

    private int isDefault = CommonFlag.NO.getValue();  //是否默认地址

    private Date createdTime;    //创建时间

    private Date updatedTime;    //更新时间

    public UserAddressVO toVO() {
        UserAddressVO vo = new UserAddressVO();
        BeanUtils.copyProperties(this, vo);
        vo.setDefaultAddress(CommonFlag.YES.getValue() == isDefault);
        return vo;
    }
}
