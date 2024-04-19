package neu.xindong.xact.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import neu.xindong.xact.dao.PositionDao;
import neu.xindong.xact.entity.Position;
import neu.xindong.xact.service.PositionService;

import java.util.List;

public class PositionServiceImpl extends ServiceImpl<PositionDao, Position>
        implements PositionService {
    @Override
    public List<Position> findPositionByPrimeAccountId(Integer primeAccountId) {
        return query().eq("prime_account_id",primeAccountId).list();
    }
}
