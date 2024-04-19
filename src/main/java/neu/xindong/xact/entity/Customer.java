package neu.xindong.xact.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.text.DecimalFormat;
import java.util.Random;
@SuperBuilder
@Data
@TableName("customer")
@ToString
public class Customer {

    @TableId
    @Builder.Default
    private Integer id= generateRandomId();
    private String customerName;
    private String idType;
    private String idNumber;
    private Integer cuacctCls;
    private Integer cuacctStatus;

    // 工具方法：生成一个固定8位的随机数字ID，不足8位时补零
    private static Integer generateRandomId() {
        Random random = new Random();
        int randomNumber = random.nextInt(90000000); // 生成0至89999999之间的随机数
        DecimalFormat decimalFormat = new DecimalFormat("00000000");//不足8位的前面补0
        return Integer.parseInt(decimalFormat.format(randomNumber));
    }
}
