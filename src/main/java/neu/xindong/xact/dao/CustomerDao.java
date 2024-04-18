package neu.xindong.xact.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import neu.xindong.xact.entity.Customer;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomerDao extends BaseMapper<Customer> {
}
