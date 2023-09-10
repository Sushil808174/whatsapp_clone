package com.skumar.exception;

import java.time.LocalDate;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class ErrorDetails {

	private String message;
	private String description;
	private LocalDate localDate;
}
