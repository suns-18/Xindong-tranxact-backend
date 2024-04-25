package neu.xindong.xact.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import neu.xindong.xact.entity.Bank;
import neu.xindong.xact.entity.Customer;
import neu.xindong.xact.entity.FollowAccount;
import neu.xindong.xact.entity.PrimeAccount;

import java.util.List;
@SuperBuilder
@AllArgsConstructor
@Data
public class AccountRegisterRequest {
    private Customer customer;
    private List<Integer> market;
    private Bank bank;
    private PrimeAccount primeAccount;
}
