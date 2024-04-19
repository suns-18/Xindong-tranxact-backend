package neu.xindong.xact.dto.response;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@SuperBuilder
@Data
public class SysUserAuthorize {
    private Integer id;
    private String username;
    private String token;
    private Date expireTime;
}