package neu.xindong.xact.service;

import neu.xindong.xact.entity.MarketInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.Optional;

public interface MarketInfoRepository extends CrudRepository<MarketInfo, String> {
    @Override
    <S extends MarketInfo> S save(S entity);

    Optional<MarketInfo> findByStockCode(String s);

    boolean existsByStockCode(String s);

    Page<MarketInfo> findMarketInfosByMarketTime(Date marketTime);

    @Override
    Page<MarketInfo> findAll();

    @Override
    long count();

    void deleteByStockCode(String s);

    @Override
    void delete(MarketInfo entity);

    @Override
    void deleteAll();
}
