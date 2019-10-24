package com.hcl.lms.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class BookRequestDetail {
	@Id
	@GeneratedValue
	private Integer requestId;
	private Integer bookId;
	private Integer userId;
}
