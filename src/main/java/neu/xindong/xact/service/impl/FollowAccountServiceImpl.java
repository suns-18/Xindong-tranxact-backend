package neu.xindong.xact.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import neu.xindong.xact.dao.FollowAccountDao;
import neu.xindong.xact.entity.FollowAccount;
import neu.xindong.xact.service.FollowAccountService;

import java.util.List;

public class FollowAccountServiceImpl extends ServiceImpl<FollowAccountDao, FollowAccount>
        implements FollowAccountService {
    @Override
    public List<FollowAccount> findFollowAccountByPrimeAccountId(Integer primeAccountId) {
        return query().eq("prime_account_id",primeAccountId).list();
    }
}
