package com.douzone.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.CategoryRepository;
import com.douzone.jblog.vo.CategoryVo;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;
	
	public void insertCategory(String id) {
		categoryRepository.insert(id);
	}
	
	public void addCategory(CategoryVo vo) {
		categoryRepository.add(vo);
	}
	
	public List<CategoryVo> getCategory(String id) {
		return categoryRepository.findCategory(id);
	}
	
	public void delete(String id, Long no) {
		categoryRepository.delete(id, no);
	}
}
