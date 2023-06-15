package com.bookcart.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BuyRequest {
	
	private Integer isbnNo;
	
	private int copies;

	@Override
	public String toString() {
		return "BuyRequest [isbnNO=" + isbnNo + ", quantity=" + copies + "]";
	}
		
	
	
}
