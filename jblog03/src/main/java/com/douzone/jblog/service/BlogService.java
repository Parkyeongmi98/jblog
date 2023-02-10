package com.douzone.jblog.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.BlogRepository;
import com.douzone.jblog.repository.CategoryRepository;
import com.douzone.jblog.repository.PostRepository;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;

@Service
public class BlogService {
	@Autowired
	private BlogRepository blogRepository;
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	
	public void insertBlog(String id) {
		blogRepository.insert(id);
	}
	
	public BlogVo getBlog(String id) {
		return blogRepository.find(id);
	}
	
	public void updateBlog(BlogVo vo) {
		blogRepository.update(vo);
	}
	
	public Map<String, Object> getMainBlog(String id, Long categoryNo, Long postNo) {
		BlogVo blog = blogRepository.find(id); // 유저에 맞는 블로그
		Map<String, Object> map = new HashMap<>();
		
		// 카테고리 리스트 불러오기
		List<CategoryVo> categoryList = categoryRepository.findCategory(id);
		// 카테고리 선택 안하면 list 제일 앞 category에 해당하는 게시글 리스트
		if(categoryNo == 0) {
			categoryNo = categoryList.get(0).getNo();
		}
		
		// 카테고리에 해당하는 게시글 리스트 불러오기
		List<PostVo> postList = postRepository.findByPostList(categoryNo);
		PostVo postVo = null;
		
		if(!postList.isEmpty()) { // 게시글이 비어있지않으면
			// 카테고리 게시글 중 제일 최근 작성된 게시글 찾기
			postVo = postList.get(0);			
		}
		if(postNo != 0) { // 게시글번호가 0이 아니면
			// 게시글번호에 맞는 게시글 찾기
			postVo = postRepository.findByPostNo(postNo);
		}
		map.put("blogvo", blog);
		map.put("category", categoryList);
		map.put("postlist", postList);
		map.put("post", postVo);

		return map;
	}
}
