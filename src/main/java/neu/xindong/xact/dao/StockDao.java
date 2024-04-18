package neu.xindong.xact.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import neu.xindong.xact.entity.Stock;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StockDao extends BaseMapper<Stock> {
}
