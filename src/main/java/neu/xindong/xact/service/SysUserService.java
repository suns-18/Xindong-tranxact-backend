package neu.xindong.xact.service;

import com.baomidou.mybatisplus.extension.service.IService;
import neu.xindong.xact.entity.SysUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface SysUserService extends IService<SysUser>, UserDetailsService {
    SysUser findSysUserByUsername(String username);
}
