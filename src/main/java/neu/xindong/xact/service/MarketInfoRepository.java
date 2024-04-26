package neu.xindong.xact.service;

import neu.xindong.xact.entity.MarketInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public interface MarketInfoRepository extends CrudRepository<MarketInfo, String> {
    @Override
    <S extends MarketInfo> S save(S entity);

    Optional<MarketInfo> findByStockId(String s);

    boolean existsByStockId(String s);

    Page<MarketInfo> findMarketInfosByMarketTime(Date marketTime,
                                                 Pageable page);

    Page<MarketInfo> findAll(Pageable page);

    @Override
    long count();

    void deleteByStockId(String s);

    @Override
    void delete(MarketInfo entity);

    @Override
    void deleteAll();
}
