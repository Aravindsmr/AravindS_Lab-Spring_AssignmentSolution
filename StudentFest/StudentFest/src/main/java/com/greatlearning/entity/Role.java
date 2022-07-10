/**
 * 
 */
package com.greatlearning.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author Aravind
 *
 */
@Entity
@Table(name = "roles")
@Data
public class Role {

	@Id
	@GeneratedValue
	@Column(name = "role_id")
	private int id;

	@Column(name = "name")
	private String name;

}
