package com.bercea.assigment2.model.command;

import com.bercea.assigment2.model.query.BookQueryDTO;
import com.bercea.assigment2.model.query.UserQueryDTO;

public class BookOrderCommandDTO {
	private BookQueryDTO bookQueryDTO;
	private UserQueryDTO userQueryDTO;
	
	public BookOrderCommandDTO() {}
	
	
	public BookOrderCommandDTO(BookQueryDTO bookQueryDTO, UserQueryDTO userQueryDTO) {
		super();
		this.bookQueryDTO = bookQueryDTO;
		this.userQueryDTO = userQueryDTO;
	}

	public BookQueryDTO getBookQueryDTO() {
		return bookQueryDTO;
	}
	public void setBookQueryDTO(BookQueryDTO bookQueryDTO) {
		this.bookQueryDTO = bookQueryDTO;
	}
	public UserQueryDTO getUserQueryDTO() {
		return userQueryDTO;
	}
	public void setUserQueryDTO(UserQueryDTO userQueryDTO) {
		this.userQueryDTO = userQueryDTO;
	}
	
	
	
}
