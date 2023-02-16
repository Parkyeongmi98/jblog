package com.douzone.jblog.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.CategoryVo;

@Repository
public class CategoryRepository {
	@Autowired
	private SqlSession sqlSession;
	
	public void insert(String id) {
		sqlSession.insert("category.insertDefault", id);
	}
	
	public void add(CategoryVo vo) {
		sqlSession.insert("category.add", vo);
	}
		
	public List<CategoryVo> findCategory(String id) {
		return sqlSession.selectList("category.findCategory", id);
	}
	
	public void delete(String id, Long no) {
		Map<String, Object> map = Map.of("id", id, "no", no);
		sqlSession.delete("category.delete", map);
	}
}
