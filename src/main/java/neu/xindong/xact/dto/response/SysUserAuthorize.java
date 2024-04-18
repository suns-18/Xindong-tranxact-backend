package neu.xindong.xact.dto.response;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@SuperBuilder
@Data
public class SysUserAuthorize {
    private String username;
    private String token;
    private Date expireTime;
}