package com.skumar.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
public class Chat {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer chatId;
	private String chat_name;
	private String chat_image;
	
	@Column(name="is_group")
	private boolean isGroup;
	
	@Column(name="create_by")
	@ManyToOne
	@JoinColumn(name="user_id")
	private Users createdBy;
	
	@ManyToMany
	private Set<Users> users = new HashSet<>();
	
	@OneToMany
	private List<Message> message = new ArrayList<>();
}
