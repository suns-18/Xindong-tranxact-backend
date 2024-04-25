package neu.xindong.xact;

import neu.xindong.xact.entity.FollowAccount;
import neu.xindong.xact.service.impl.FollowAccountServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FollowAccountServiceTest {
    @Autowired
    private FollowAccountServiceImpl followAccountService;
    @Test
    public void findFollowAccountByPrimeAccountId(){
        List<FollowAccount> followAccounts=followAccountService.findFollowAccountByPrimeAccountId(31355654);
        followAccounts.stream().forEach(System.out::println);
    }
    @Test
    public void findFollowAccountByPrimeAccountIdAndMarket(){
        FollowAccount followAccount=followAccountService.findFollowAccountByPrimeAccountIdAndMarket(31355654,1);
        System.out.println(followAccount.toString());
    }
}
