package com.asaqib.LibraryMgmtSystm;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.asaqib.LibraryMgmtSystm.model.Books;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class HomeController {	
	@Autowired
	AccRepo repo;

	/*@RequestMapping("/")
	public String home() {
		return "bookCreate.jsp";
	}*/

	@PostMapping("/addBooks")
	public String addBooks(@RequestBody Books books) {
		System.out.println(" Books Added Successfully...");
		System.out.println(books);
		repo.save(books);
		return "result.jsp";

	}
	
	/*@GetMapping("/getBooks")
	public ModelAndView getBooks() {

		ModelAndView m = new ModelAndView("getbook.jsp");
		Iterable<Books> books = repo.findAll();
		m.addObject(books);
		return m;

	}*/
	@GetMapping("/getBooks")
	@ResponseBody
	public List<Books> getBooks() {
		return (List<Books>)repo.findAll();

	}
	
	@GetMapping(value = "/deleteBooks")
	public ModelAndView deleteBooks(@RequestParam("isbn") int isbn) {

		System.out.println("Deleted");
		ModelAndView m = new ModelAndView("delete.jsp");
		repo.deleteById(isbn);
		return m;

	}
	@PostMapping("/updateBooks")
	public String updateBooks(int isbn,String title,String author,String price) {
		
		Books books = repo.findById(isbn).get();
		
			books.setAuthor(author);
			books.setPrice(price);
			books.setTitle(title);
			
			repo.save(books);
			
		
		return "update.jsp";
	}
	
}