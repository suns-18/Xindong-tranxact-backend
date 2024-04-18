package neu.xindong.xact.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import neu.xindong.xact.dao.SysUserMapper;
import neu.xindong.xact.entity.SysUser;
import neu.xindong.xact.service.SysUserService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl
        extends ServiceImpl<SysUserMapper, SysUser>
        implements SysUserService {
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        SysUser user = findSysUserByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException("用户名或密码错误");

        return User
                .withUsername(username)
                .password(user.getPassword())
                .build();
    }

    public SysUser findSysUserByUsername(String username) {
        return this.query()
                .eq("username", username)
                .one();
    }
}
