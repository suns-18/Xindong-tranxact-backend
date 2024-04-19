package neu.xindong.xact.dto.request;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import neu.xindong.xact.entity.FollowAccount;

import java.util.List;
@SuperBuilder
@Data
public class AccountRegisterRequest {
    private String customerName;
    private String idType;
    private String idNumber;
    private Integer cuacctCls;
    private Integer cuacctStatus;
    private List<FollowAccount> followAccountList;
}
