package crm.settings.service;

import crm.settings.domain.User;
import crm.settings.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service("UserService")
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;
    @Override
    public User queryUserByLoginActAndPwd(Map<String, Object> map) {
        return userMapper.selectUserByNameAndPwd(map);
    }

    @Override
    public List<User> queryAllUser() {
        return userMapper.selectAllUser();
    }
}
