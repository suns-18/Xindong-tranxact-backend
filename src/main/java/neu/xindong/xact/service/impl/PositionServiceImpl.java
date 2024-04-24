package neu.xindong.xact.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import neu.xindong.xact.dao.PositionDao;
import neu.xindong.xact.entity.OrderInfo;
import neu.xindong.xact.entity.Position;
import neu.xindong.xact.entity.Stock;
import neu.xindong.xact.service.PositionService;
import neu.xindong.xact.util.RegisterUtil;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class PositionServiceImpl extends ServiceImpl<PositionDao, Position>
        implements PositionService {
    @Override
    public List<Position> findPositionByPrimeAccountId(Integer primeAccountId) {
        return query().eq("prime_account_id",primeAccountId).list();
    }

    @Override
    public boolean makePosition(Position position) {
        position.setId(RegisterUtil.CreateOrderId());
        position.setUpdateTime(new Date());
        position.setShareTotal(0);
        position.setShareUsable(0);
        return save(position);
    }

    @Override
    //委托买  不实现
    //委托卖 冻结股票 可用=余额-委托数量 余额不变
    public boolean reduceShareByOrder(Position position) {
        return false;
    }

    @Override
    //撤单买 不实现
    //撤单卖 可用=余额 余额不变
    public boolean increaseShareByOrder(Position position) {
        return false;
    }

    @Override
    //成交买 余额=余额+成交 可用=可用+成交
    public boolean increaseShareByDeal(Position position) {
        return false;
    }

    @Override
    //成交卖 余额=余额-成交 可用不变
    public boolean reduceShareByDeal(Position position) {
        return false;
    }
}
