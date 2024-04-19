package neu.xindong.xact.dto.request;

import neu.xindong.xact.entity.FollowAccount;

import java.util.List;

public class AccountRegisterRequest {
    private String customerName;
    private String idType;
    private String idNumber;
    private Integer cuacctCls;
    private Integer cuacctStatus;
    private List<FollowAccount> followAccountList;
}
