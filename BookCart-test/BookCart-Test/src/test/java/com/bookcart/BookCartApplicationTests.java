//package com.bookcart;
//
//import org.junit.Before;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultMatcher;
//import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.client.RestTemplate;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.hamcrest.Matchers.*;
//
//import java.util.Optional;
//import com.bookcart.controller.BookController;
//import com.bookcart.dao.BookRepository;
//import com.bookcart.model.Book;
//import com.bookcart.service.BookService;
//import com.bookcart.service.BookServiceImp;
//import com.bookcart.service.TransactionService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.ObjectWriter;
//
//
//@RunWith(SpringRunner.class)
//@AutoConfigureMockMvc
//@WebAppConfiguration
//@SpringBootTest
//class BookCartApplicationTests {
//	
//	@MockBean
//	private BookServiceImp bookService;
//	
//	@Autowired
//	BookService bookservice;
//	
//	@MockBean
//	TransactionService transactionService;
//	
//
//	@Autowired
//	private BookController homeController;
//
//	@MockBean
//	Book book;
//		
//	@MockBean
//	private BookRepository bookRepository;
//	
//	@Autowired
//	private MockMvc mockMvc;
//	
//	ObjectMapper objectMapper= new ObjectMapper();
//	ObjectWriter objectWriter= objectMapper.writer();
//	
//	   Book RECORD_1 = new Book(3, 455, "story1", "a1", 183.3, 7);
//	   Book RECORD_2 = new Book(13, 2455, "story3", "a12", 813.3, 5);
//	   Book RECORD_3 = new Book(1234, 455, "story12", "a13", 833.3, 7);
//	
//	@Before
//	public void setUp() {
//		MockitoAnnotations.openMocks(this);
//		this.mockMvc=MockMvcBuilders.standaloneSetup(homeController).build();
//	}
//	
//	
//	@Test
//	public void AddBooksSuccessTest() throws Exception {
//		Book book = Book.builder()
//				.id(12)
//				.isbnNo(423)
//				.title("Subtle art of not giving a fuck")
//				.author("John")
//				.price(300)
//				.noOfCopies(5)
//				.build();
//			
//	Mockito.when(bookRepository.save(book)).thenReturn(book);
//	
//	String content = objectWriter.writeValueAsString(book);
//	
//	MockHttpServletRequestBuilder mockRequest= MockMvcRequestBuilders.post("/v1/books")
//			.contentType(MediaType.APPLICATION_JSON)
//			.accept(MediaType.APPLICATION_JSON)
//			.content(content);
//	
//	mockMvc.perform(mockRequest)
//			.andExpect(status().isOk())			
//			.andExpect(jsonPath("$", notNullValue()))
//		    .andExpect(jsonPath("$.author", is("John")));
//			
//	}
//
//	@Test
//	public void UpdateSuccessfulTest() throws Exception {
//		Book updatedbook =Book.builder()
//				.id(3)
//				.isbnNo(455)
//				.title("asgmsds")
//				.author("ashana")
//				.price(2121)
//				.noOfCopies(5)
//				.build();
//		
//	    Mockito.when(bookRepository.findById(RECORD_1.getId())).thenReturn(java.util.Optional.ofNullable(RECORD_1));
//	    Mockito.when(bookRepository.save(updatedbook)).thenReturn(updatedbook);
//	    
//		String updatedContent = objectWriter.writeValueAsString(updatedbook);
//		
//		MockHttpServletRequestBuilder mockRequest= MockMvcRequestBuilders.put("/v1/books/3")
//				.contentType(MediaType.APPLICATION_JSON)
//				.accept(MediaType.APPLICATION_JSON)
//				.content(updatedContent);
//		
//		mockMvc.perform(mockRequest)
//		.andExpect(status().isOk());	
//		
//		
//	}
//		
//		@Test
//		public void deleteBookById_success() throws Exception {
//		    Mockito.when(bookRepository.findById(RECORD_2.getId())).thenReturn(Optional.of(RECORD_2));
//
//		    mockMvc.perform(MockMvcRequestBuilders
//		            .delete("/v1/book/13")
//		            .contentType(MediaType.APPLICATION_JSON))
//		            .andExpect(status().isOk());
//		
//	}
//
//	
//
//}
