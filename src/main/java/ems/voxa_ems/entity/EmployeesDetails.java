package ems.voxa_ems.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class EmployeesDetails {
@Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
public Long id;
@Column(nullable = false)
public String name;
@Column(unique = true,nullable = false)
public String email;
@Column(unique = true,nullable = false)
public Long mobile;
@Column(nullable =false)
public String address;
@Column(nullable = false)
public String department;
@Column(nullable = false)
public int salary;

}
