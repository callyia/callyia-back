package com.project.Callyia.service;

import com.project.Callyia.entity.Member;
import com.project.Callyia.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

//@Log4j2
//@Service
//@RequiredArgsConstructor
//public class MemberDetailService implements UserDetailsService {
//  private final MemberRepository memberRepository;
//
//  @Override
//  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//    Member result = memberRepository.findByEmail(email);
//
//    if(result.getEmail() != null) {
//      throw new UsernameNotFoundException("Check Email");
//    }
//
//
//    return null;
//  }
//}
