package neu.xindong.xact.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import neu.xindong.xact.dao.StockDao;
import neu.xindong.xact.entity.Stock;
import neu.xindong.xact.service.StockService;

import java.util.List;

public class StockServiceImpl extends ServiceImpl<StockDao, Stock>
        implements StockService {
    @Override
    public Stock findStockById(Integer id) {
        return getById(id);
    }

    @Override
    public List<Stock> findAll() {
        return query().list();
    }
}
