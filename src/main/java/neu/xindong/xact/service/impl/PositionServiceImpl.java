package neu.xindong.xact.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import neu.xindong.xact.dao.PositionDao;
import neu.xindong.xact.entity.OrderInfo;
import neu.xindong.xact.entity.Position;
import neu.xindong.xact.entity.Stock;
import neu.xindong.xact.entity.Transaction;
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
        return query().eq("prime_account_id", primeAccountId).list();
    }

    @Override
    public Position findPositionByStockId(String stockId, Integer primeAccountId) {
        return query()
                .eq("stock_id", stockId)
                .eq("prime_account_id", primeAccountId)
                .last("limit 1")
                .oneOpt()
                .orElse(null);
    }


    @Override
    //委托买  不实现
    //委托卖 冻结股票 可用=余额-委托数量 余额不变
    public boolean reduceShareByOrder(Position position, OrderInfo orderInfo) {
        position.setShareUsable(position.getShareTotal() - orderInfo.getOrderAmount());
        position.setFrozenShareAmount(orderInfo.getOrderAmount());
        position.setUpdateTime(new Date());
        return updateById(position);
    }

    @Override
    //撤单买 不实现
    //撤单卖 可用=余额 余额不变
    public boolean increaseShareByOrder(Position position) {
        position.setShareUsable(position.getShareTotal());
        position.setFrozenShareAmount(0);
        position.setUpdateTime(new Date());
        return updateById(position);
    }

    @Override
    //成交买 余额=余额+成交 可用=可用+成交
    public boolean increaseShareByDeal(Position position, Stock stock, OrderInfo orderInfo, Transaction transaction) {
        if (position.getId() == null) {//之前没买过
            position.setId(RegisterUtil.createOrderId());
            position.setPrimeAccountId(orderInfo.getPrimeAccountId());
            position.setFollowAccountId(orderInfo.getFollowAccountId());
            position.setMarket(stock.getMarket());
            position.setStockId(stock.getId());
            position.setShareTotal(transaction.getAmount());
            position.setShareUsable(transaction.getAmount());
        } else {//之前买过
            position.setShareTotal(position.getShareTotal() + transaction.getAmount());
            position.setShareUsable(position.getShareUsable() + transaction.getAmount());
        }
        position.setFrozenShareAmount(0);
        position.setUpdateTime(new Date());
        return saveOrUpdate(position);
    }

    @Override
    //成交卖 余额=余额-成交 可用不变
    public boolean reduceShareByDeal(Position position, Transaction transaction) {
        position.setShareTotal(position.getShareTotal() - transaction.getAmount());
        position.setUpdateTime(new Date());
        position.setFrozenShareAmount(0);
        return updateById(position);
    }
}
