package neu.xindong.xact.util;

import java.text.DecimalFormat;
import java.util.Random;

public class RegisterUtil {
    // 工具方法：生成客户代码：一个固定8位的随机数字ID，不足8位时补零
    public static Integer createCustomerId() {
        Random random = new Random();
        int randomNumber = random.nextInt(90000000); // 生成0至89999999之间的随机数
        DecimalFormat decimalFormat = new DecimalFormat("00000000");//不足8位的前面补0
        return Integer.parseInt(decimalFormat.format(randomNumber));
    }

    // 工具方法：生成客户代码：一个固定8位的随机数字ID，不足8位时补零
    public static Integer createOrderId() {
        Random random = new Random();
        int randomNumber = random.nextInt(9000000); // 生成0至8999999之间的随机数
        DecimalFormat decimalFormat = new DecimalFormat("0000000");//不足7位的前面补0
        return Integer.parseInt(decimalFormat.format(randomNumber));
    }
    public static Integer createBankId() {
        Random random = new Random();
        int randomNumber = random.nextInt(900000); // 生成0至8999999之间的随机数
        DecimalFormat decimalFormat = new DecimalFormat("000000");//不足7位的前面补0
        return Integer.parseInt(decimalFormat.format(randomNumber));
    }

    //工具方法：生成从账户：一个固定10位的随机字符串ID
    public static String createFollowAccountId(int market, int cls) {
        String account;
        Random random = new Random();
        // 生成一个9位的随机数字
        String randomNumber = String.format("%09d", random.nextInt(1000000));
        if (market == 0) {
            account = "0" + randomNumber;
        } else if (market == 1 && cls == 3) {
            account = "B" + randomNumber;
        } else {
            account = "A" + randomNumber;
        }
        return account;
    }
}
