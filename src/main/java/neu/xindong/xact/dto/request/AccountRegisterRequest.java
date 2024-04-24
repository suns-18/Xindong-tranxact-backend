package neu.xindong.xact.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import neu.xindong.xact.entity.Customer;
import neu.xindong.xact.entity.FollowAccount;

import java.util.List;
@SuperBuilder
@AllArgsConstructor
@Data
public class AccountRegisterRequest {
    private Customer customer;
    private List<FollowAccount> followAccountList;
}
