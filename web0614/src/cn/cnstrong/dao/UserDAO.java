package cn.cnstrong.dao;

import java.util.ArrayList;

import cn.cnstrong.pojo.User;

public interface UserDAO {
	public int Register(User user);
	public ArrayList<User> queryAll();
	public User queryOne(User user);//查询一个用户
}
