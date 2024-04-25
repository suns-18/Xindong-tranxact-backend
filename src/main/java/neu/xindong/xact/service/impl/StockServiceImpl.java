package neu.xindong.xact.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import neu.xindong.xact.dao.StockDao;
import neu.xindong.xact.entity.Stock;
import neu.xindong.xact.service.StockService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StockServiceImpl extends ServiceImpl<StockDao, Stock>
        implements StockService {
    @Override
    public List<Stock> findAll() {
        return query().list();
    }

    @Override
    public List<Stock> findStockLikeId(String id) {
        return query().like("id", id).list();
    }
}
