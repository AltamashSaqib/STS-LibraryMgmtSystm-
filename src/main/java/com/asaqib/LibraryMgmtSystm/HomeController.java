package com.asaqib.LibraryMgmtSystm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


import com.asaqib.LibraryMgmtSystm.model.Books;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
//@RequestMapping({"/api"})
public class HomeController {	
	@Autowired
	AccRepo repo;

	@RequestMapping("/")
	public String home() {
		return "Application Running Successfully...";
	}

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
	
	/*
	@DeleteMapping(value = "/deleteBooks/{isbn}")
	public ResponseEntity<String> deleteBooks(@PathVariable("isbn") int isbn)
		throws ResourceNotFoundException{ 
		repo.deleteById(isbn);
	   System.out.println("Book Deleted Successfully...");
	   return new ResponseEntity<>("success", HttpStatus.OK);
	 
	}*/
	
	@DeleteMapping(value = "/deleteBooks/{isbn}")
	public Map<String, Boolean> deleteBooks(@PathVariable("isbn") int isbn)throws Exception{
	Books book =
        repo
            .findById(isbn)
            .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + isbn));
    repo.delete(book);
    Map<String, Boolean> response = new HashMap<>();
    response.put("deleted", Boolean.TRUE);
    return response;
  }
	/*@PutMapping("/updateBooks")
	public String updateBooks(int isbn,String title,String author,String price) {
		
		Books books = repo.findById(isbn).get();
		
			books.setAuthor(author);
			books.setPrice(price);
			books.setTitle(title);
			
			repo.save(books);	
		
		return "update.jsp";
	}*/
	
	@GetMapping("/books/{id}")
    public ResponseEntity<Books> getBookId(@PathVariable(value = "id") int isbn)
      throws ResourceNotFoundException {
        Books book = repo.findById(isbn)
           .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + isbn));
        return ResponseEntity.ok().body(book);
    }

	
	 @PutMapping("/updateBooks/{id}")
	    public ResponseEntity<Books> updateEmployee(@PathVariable(value = "id") int isbn,
	      @Valid @RequestBody Books bookDetails) throws ResourceNotFoundException {
		 System.out.println("Book Updated Successfully...");
	        Books book= repo.findById(isbn)
	           .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + isbn));
	        
	        

	        book.setAuthor(bookDetails.getAuthor());
	        book.setTitle(bookDetails.getTitle());
	        book.setPrice(bookDetails.getPrice());
	        final Books updatedBook = repo.save(book);
	        return ResponseEntity.ok(updatedBook);
	    }
	
	
}