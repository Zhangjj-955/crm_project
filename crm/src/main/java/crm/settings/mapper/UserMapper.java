package crm.settings.mapper;

import crm.settings.domain.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    User selectUserByNameAndPwd(Map<String, Object> map);

    List<User> selectAllUser();
}