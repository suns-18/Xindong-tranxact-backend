package neu.xindong.xact.dto.response;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import neu.xindong.xact.entity.FollowAccount;
import neu.xindong.xact.entity.PrimeAccount;

@SuperBuilder
@Data
public class AccountsResp {
    private PrimeAccount primeAccount;
    private FollowAccount followAccount;
}
