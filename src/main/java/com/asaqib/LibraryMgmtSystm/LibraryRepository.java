package com.asaqib.LibraryMgmtSystm;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.asaqib.LibraryMgmtSystm.model.Books;


public interface LibraryRepository extends CrudRepository<Books, Integer>{
	

}
