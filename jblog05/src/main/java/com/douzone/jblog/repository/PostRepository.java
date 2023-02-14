package com.douzone.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.PostVo;

@Repository
public class PostRepository {
	@Autowired
	private SqlSession sqlSession;

	public void insert(PostVo vo) {
		sqlSession.insert("post.insert", vo);
	}

	public void deleteAllPost(Long no) {
		sqlSession.delete("post.delete", no);
	}

	public List<PostVo> findByPostList(Long categoryNo) {
		return sqlSession.selectList("post.findByCategory", categoryNo);
	}

	public PostVo findByPost(Long postNo) {
		return sqlSession.selectOne("post.findByPost", postNo);
	}
}
