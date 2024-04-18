package neu.xindong.xact.service;

import com.baomidou.mybatisplus.extension.service.IService;
import neu.xindong.xact.entity.SysUser;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

public interface SysUserService extends IService<SysUser>, UserDetailsService {
    SysUser findSysUserByUsername(String username);
}
