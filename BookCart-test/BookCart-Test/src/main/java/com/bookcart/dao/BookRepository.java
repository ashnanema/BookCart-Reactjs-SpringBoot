package com.bookcart.dao;

import java.util.List;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bookcart.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
	
	@Query("SELECT b FROM Book b WHERE b.author LIKE %:author%")
	List<Book> searchByAuthorLike(@Param("author") String author);
	@Query("SELECT b FROM Book b WHERE b.title LIKE %:title%")
	List<Book> searchByNameLike(@Param("title") String title);

	@Query("SELECT b FROM Book b WHERE isbnNo = :isbn")
	List<Book> searchByIsbn(@Param("isbn") Integer isbn);
	
	@Lock(LockModeType.PESSIMISTIC_READ)
	void deleteById(Integer id);
	
	@Lock(LockModeType.PESSIMISTIC_READ)
	Book save(Book book);
	
	@Query("SELECT b from Book b where b.author LIKE %:author% and b.title LIKE %:title% and isbnNo = :isbn"  )
	List<Book> searchByMultipleParams(@Param("author") String author 
			,@Param("title") String title ,
			@Param("isbn") Integer isbn);
	@Query("SELECT b from Book b where b.author Like %:author% and b.title LIKE %:title%")
	List<Book> searchByAuthorAndTitle(@Param("author") String Author ,@Param("title") String Title);
	
	@Query("SELECT b from Book b where b.author Like %:author% and b.isbnNo = :isbn")
	List<Book> searchByAuthorAndIsbnNo(@Param("author") String Author ,@Param("isbn") Integer isbnNo);
	
	@Query("SELECT b from Book b where b.title Like %:title% and b.isbnNo = :isbn")
	List<Book> searchByIsbnNoAndTitle(@Param("isbn") Integer isbn_no ,@Param("title") String Title);
	
}
