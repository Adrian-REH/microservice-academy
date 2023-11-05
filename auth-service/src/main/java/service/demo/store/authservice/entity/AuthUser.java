package service.demo.store.authservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_authuser",schema = "security")
public class AuthUser {
    @Id
    private String id;
    @Column(name = "user_name")
    private String userName;
    private String password;

}
