package com.douzone.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.UserRepository;
import com.douzone.jblog.vo.UserVo;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public void joinUser(UserVo vo) {
		userRepository.joinUser(vo);
	}
	
	public UserVo getUser(UserVo vo) {
		return userRepository.findByIdAndPassword(vo.getId(), vo.getPassword());
	}
	
	public void getUserId(String id) {
		userRepository.findById(id);
	}
}
