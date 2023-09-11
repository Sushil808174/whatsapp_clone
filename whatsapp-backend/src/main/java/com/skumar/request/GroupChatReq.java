package com.skumar.request;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@RequiredArgsConstructor
@Getter
@Setter
public class GroupChatReq {

	private List<Integer> userId;
	private String chat_name;
	private String chat_image;
	
}
