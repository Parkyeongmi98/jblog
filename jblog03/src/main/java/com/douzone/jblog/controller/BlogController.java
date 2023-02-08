package com.douzone.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.service.FileuploadService;

@Controller
@RequestMapping("/blog")
public class BlogController {
	@Autowired
	private BlogService blogService;
	@Autowired
	private FileuploadService fileuploadService;
	
	@RequestMapping("/{id}")
	public String main(@PathVariable("id") String id, Model model) {
		BlogVo vo = blogService.getBlog();
		model.addAttribute("blogVo", vo);
		
		return "blog/main";
	}
	
	@RequestMapping("/{id}/admin/basic")
	public String adminBlogMain(@PathVariable("id") String id, Model model) {
		BlogVo vo = blogService.getBlog();
		model.addAttribute("blogVo", vo);
		
		return "blog/admin-basic";
	}
	
	@RequestMapping("/{id}/admin/basic/update")
	public String adminBlogUpdate(BlogVo vo, MultipartFile file) {
		String profile = fileuploadService.restore(file);
		if(profile != null) {
			vo.setProfile(profile);
		}
		
		blogService.updateBlog(vo);
		
		return "redirect:/blog/{id}/admin/basic";
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
