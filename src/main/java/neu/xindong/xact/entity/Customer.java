package neu.xindong.xact.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.text.DecimalFormat;
import java.util.Random;
@SuperBuilder
@Data
@AllArgsConstructor
@TableName("customer")
@ToString
public class Customer {

    @TableId
    @Builder.Default
    private Integer id= 00000000;
    private String customerName;
    private String idType;
    private String idNumber;
    private Integer cuacctCls;
    private Integer cuacctStatus;

}
