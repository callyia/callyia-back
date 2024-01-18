package com.project.Callyia.security.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Log4j2
@Getter
@Setter
@ToString
public class AuthMemberDTO extends User {
  private String email;
  private String password;
  private String gender;
  private String name;
  private String nickname;
  private String phone;
  private String profile_image;

  public AuthMemberDTO(String username, String password, String gender, String name, String nickname, String phone, String profile_image, Collection<? extends GrantedAuthority> authorities) {
    super(username, password, authorities);
    this.email = username;
    this.password = password;
    this.gender = gender;
    this.name = name;
    this.nickname = nickname;
    this.phone = phone;
    this.profile_image = profile_image;
  }


}
