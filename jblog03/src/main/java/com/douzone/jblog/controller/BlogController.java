package com.douzone.jblog.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.security.Auth;
import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;
import com.douzone.jblog.service.FileuploadService;
import com.douzone.jblog.service.PostService;

@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {
	@Autowired
	private BlogService blogService;
	@Autowired
	private FileuploadService fileuploadService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private PostService postService;
	@Autowired
	private ServletContext servletContext;
		
	
	@RequestMapping({"", "/{categoryNo}", "/{categoryNo}/{postNo}"})
	public String blog(
			@PathVariable("id")String id,
			@PathVariable("categoryNo")Optional<Long> categoryNo,
			@PathVariable("postNo")Optional<Long> postNo,
			Model model) {
		Long category = 0L;
		Long post = 0L;

		if(postNo.isPresent()) {
			//postNo 값이 들어왔고 postNo타입이 long이면
			post = postNo.get();
			category = categoryNo.get();
		} else if(categoryNo.isPresent()) {
			//categoryNo 값이 들어왔고 categoryNo 타입이 long이면
			category = categoryNo.get();
		}
		Map<String, Object> map = blogService.getMainBlog(id, category, post);
		model.addAllAttributes(map);
		
		return "blog/main";
	}
	
	@Auth
	@RequestMapping("/admin/basic")
	public String adminBlogMain(@PathVariable("id") String id, Model model) {
		BlogVo vo = blogService.getBlog(id);
		model.addAttribute("blogVo", vo);
		
		return "blog/admin-basic";
	}
	
	@Auth
	@RequestMapping("/admin/update")
	public String adminBlogUpdate(BlogVo vo, MultipartFile file) {
		String profile = fileuploadService.restore(file);
		if(profile != null) {
			vo.setProfile(profile);
			}
		blogService.updateBlog(vo);
		servletContext.setAttribute("blogvo", vo);
		
		return "redirect:/{id}/admin/basic";
	}
	
	@Auth
	@RequestMapping("/admin/category")
	public String category(@PathVariable("id") String id, Model model) {
		List<CategoryVo> categoryVo = categoryService.getCategory(id);
		model.addAttribute("categoryVo", categoryVo);
		
		return "blog/admin-category";
	}
	
	@Auth
	@RequestMapping(value="/admin/category/add", method = RequestMethod.POST)
	public String categoryAdd(@PathVariable("id") String id, CategoryVo vo) {
		vo.setId(id);
		categoryService.addCategory(vo);
		
		return "redirect:/{id}/admin/category";
	}
	
	@Auth
	@RequestMapping("/admin/category/delete/{no}")
	public String categoryDelete(@PathVariable("id") String id, @PathVariable("no")Long no) {
		postService.delete(no);
		categoryService.delete(id, no);
		
		return "redirect:/{id}/admin/category";
	}
	
	@Auth
	@RequestMapping(value="/admin/write", method = RequestMethod.GET)
	public String write(@PathVariable("id") String id, Model model) {
		List<CategoryVo> list = categoryService.getCategory(id);
		model.addAttribute("categoryList" , list);
		return "blog/admin-write";
	}
	
	@Auth
	@RequestMapping(value="/admin/write", method = RequestMethod.POST)
	public String write(@PathVariable("id") String id, PostVo vo) {
		postService.writePost(vo);

		return "redirect:/{id}/admin/category";
	}
	
}
