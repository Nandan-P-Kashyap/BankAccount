package com.account.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USERS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntityDto {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
	@SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
	@Column(name = "user_id")
	private Long userId;

    @Column(name = "username", length = 50, nullable = false, unique = true)
    private String username;

    @Column(name = "password", length = 255)
    private String password;
    
    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "role", length = 20, nullable = false)
    private String role;

    @Column(name = "branch_id", length = 36)
    private String branchId; // consider ManyToOne if you want to relate to BranchEntity

    @Column(name = "status", length = 20)
    private String status; // e.g. ACTIVE, INACTIVE
}
