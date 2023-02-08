package com.douzone.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.douzone.jblog.service.BlogService;

@Controller
@RequestMapping("/jblog")
public class BlogController {
	@Autowired
	private BlogService blogService;
	
	@RequestMapping("/{id}")
	public String main(@PathVariable("id") String id) {
		return "blog/main";
	}
	
	@RequestMapping("/{id}/admin/basic")
	public String admin(@PathVariable("id") String id) {
		
		return "blog/admin-basic";
	}
	
	@RequestMapping("/{id}/admin/category")
	public String category(@PathVariable("id") String id) {
		
		return "blog/admin-category";
	}
	
	@RequestMapping("/{id}/admin/write")
	public String write(@PathVariable("id") String id) {
		
		return "blog/admin-write";
	}
}
