package neu.xindong.xact.service;

import com.baomidou.mybatisplus.extension.service.IService;
import neu.xindong.xact.entity.FollowAccount;

import java.util.List;

public interface FollowAccountService extends IService<FollowAccount> {
    List<FollowAccount> findFollowAccountByPrimeAccountId(Integer primeAccountId);
}
