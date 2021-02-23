package per.guzx.priDiary.pojo;

import org.apache.ibatis.type.Alias;
import org.springframework.security.core.GrantedAuthority;

@Alias("role")
public class PdRole implements GrantedAuthority {

    private String authority;

    @Override
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
