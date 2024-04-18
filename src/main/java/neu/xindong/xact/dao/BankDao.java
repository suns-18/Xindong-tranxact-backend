package neu.xindong.xact.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import neu.xindong.xact.entity.Bank;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BankDao extends BaseMapper<Bank> {
}
