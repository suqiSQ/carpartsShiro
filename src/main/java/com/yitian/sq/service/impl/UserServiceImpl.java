package com.yitian.sq.service.impl;

import com.yitian.sq.constant.SysConstant;
import com.yitian.sq.dao.CompanyMapper;
import com.yitian.sq.dao.MenuMapper;
import com.yitian.sq.dao.RoleMapper;
import com.yitian.sq.dao.UserMapper;
import com.yitian.sq.model.Company;
import com.yitian.sq.model.Menu;
import com.yitian.sq.model.Role;
import com.yitian.sq.model.User;
import com.yitian.sq.service.UserService;
import com.yitian.sq.util.SHA1Util;
import com.yitian.sq.vo.RegisterVo;
import com.yitian.sq.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public User findUserByLoginNameAndPassword(UserVo vo) {
        return userMapper.findUserByLoginNameAndPassword(vo);
    }

    @Override
    public List<Menu> findMenuByUser(User user) {
        return menuMapper.findMenuByUser(user);
    }

    @Override
    public Boolean findUserByLoginName(String loginName) {
        User user=userMapper.findUserByLoginName(loginName);
        if(user==null){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Boolean findUserByPhone(String phone) {
        User user = userMapper.findUserByPhone(phone);
        if (user == null) {
            return true;
        } else{
            return false;
        }
    }

    @Override
    public boolean findUserByEmail(String email) {
        User user= userMapper.findUserByEmail(email);
        if(user==null){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 注册在一个事务中
     * @param vo
     */
    @Override
    public void insertUserAndCompany(RegisterVo vo) {
        Company company=new Company();
        company.setAddress(vo.getAddress());
        company.setCity(vo.getCity());
        company.setCompanycode(UUID.randomUUID().toString());
        company.setCompanyname(vo.getCompanyname());
        company.setContry(vo.getContry());
        company.setCreatetime(new Date());
        company.setDelstatus(0);
        company.setLeader(vo.getUsername());
        company.setMain(vo.getMain());
        company.setPhone(vo.getPhone());
        company.setPrime(vo.getPrime());
        company.setProcince(vo.getProcince());
        company.setQq(vo.getQq());
        company.setSingleBrand(vo.getSingleBrand());
        company.setSingleParts(vo.getSingleParts());
        company.setZone1(vo.getZone1());
        company.setZone2(vo.getZone2());
        company.setTel1(vo.getTel1());
        company.setTel2(vo.getTel2());
        companyMapper.insertSelective(company);
        User user= new User();
        user.setCompanyId(company.getId().toString());
        user.setCreatetime(new Date());
        user.setEmail(vo.getEmail());
        user.setLoginName(vo.getLoginName());
        user.setManageLevel(1);
        user.setPassword(SHA1Util.encode(vo.getPassword()));
        user.setPhone(vo.getPhone());
        user.setQq(vo.getQq());
        user.setRoleId(SysConstant.ADMIN_ROLE);
        user.setUsername(vo.getUsername());
        user.setUserStatus(SysConstant.USER_STATUS_ON);
        userMapper.insertSelective(user);

    }

    @Override
    public User findUserByLoginNameAndEmail(UserVo vo) {
        return userMapper.findUserByLoginNameAndEmail(vo);
    }

    @Override
    public void updateUserPassword(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public List<Role> findRoleByUser(User user) {
        return roleMapper.findRoleByUser(user);
    }

    @Override
    public User findUserByName(String loginName) {
        return userMapper.findUserByLoginName(loginName);
    }
}
