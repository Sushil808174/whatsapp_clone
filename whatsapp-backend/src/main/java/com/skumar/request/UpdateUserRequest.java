package com.skumar.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class UpdateUserRequest {
	
	private String full_name;
	private String profilePicture;
	
	
}
