package com.asaqib.LibraryMgmtSystm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class LibraryController {	
	
	Logger logger = LoggerFactory.getLogger(LibraryController.class);
	
	@Autowired
	LibraryRepository repo;
	
	@RequestMapping("/")
	public String home() {
		return "Application Running Successfully...";
	}

	@PostMapping("/addBooks")
	public Books addBooks(@RequestBody Books books) {
		
		logger.info(" Books Added Successfully...");
		return repo.save(books);

	}
	
	@GetMapping("/getBooks")
	@ResponseBody
	public List<Books> getBooks() {
		return (List<Books>)repo.findAll();
	}
	
	
	@DeleteMapping(value = "/deleteBooks/{isbn}")
	public Map<String, Boolean> deleteBooks(@PathVariable("isbn") int isbn)throws Exception{
	Books book =
        repo
            .findById(isbn)
            .orElseThrow(() -> new BookNotFoundException("Book not found on :: " + isbn));
    repo.delete(book);
    Map<String, Boolean> response = new HashMap<>();
    response.put("deleted", Boolean.TRUE);
    return response;
  }
	
	@GetMapping("/books/{id}")
    public ResponseEntity<Books> getBookId(@PathVariable(value = "id") int isbn)
      throws BookNotFoundException {
        Books book = repo.findById(isbn)
           .orElseThrow(() -> new BookNotFoundException("Book not found for this id :: " + isbn));
        return ResponseEntity.ok().body(book);
    }

	 @PutMapping("/updateBooks/{id}")
	    public ResponseEntity<Books> updateEmployee(@PathVariable(value = "id") int isbn,
	      @Valid @RequestBody Books bookDetails) throws BookNotFoundException {
		 
	        Books book= repo.findById(isbn)
	           .orElseThrow(() -> new BookNotFoundException("Book not found for this id :: " + isbn));
	        
	        book.setAuthor(bookDetails.getAuthor());
	        book.setTitle(bookDetails.getTitle());
	        book.setPrice(bookDetails.getPrice());
	        final Books updatedBook = repo.save(book);
	        logger.info("Updated Successfully...");
	        return ResponseEntity.ok(updatedBook);
	    }
	
	
}