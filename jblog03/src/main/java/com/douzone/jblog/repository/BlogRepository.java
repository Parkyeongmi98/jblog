package com.douzone.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.BlogVo;

@Repository
public class BlogRepository {
	@Autowired
	private SqlSession sqlSession;
	
	public BlogVo find() {
		return sqlSession.selectOne("blog.find");
	}
	
	public void update(BlogVo vo) {
		sqlSession.update("blog.update", vo);
	}
}
