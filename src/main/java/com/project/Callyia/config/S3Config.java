package com.project.Callyia.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

//S3Config가 스프링의 Java기반 설정 클래스임을 나타냄
@Configuration
public class S3Config {

  //application-properties에 설정해둔 값들을 해당 변수에 주입한다.
  @Value("${cloud.aws.credentials.accessKey}")
  private String accessKey;

  @Value("${cloud.aws.credentials.secretKey}")
  private String secretKey;

  @Value("${cloud.aws.region.static}")
  private String region;

  //amazonS3Client 메서드를 S3Client 빈을 생성하고 반환한다.
  @Bean
  public S3Client amazonS3Client() {
    AwsCredentials credentials = AwsBasicCredentials.create(accessKey, secretKey);

    return S3Client.builder()
        .region(Region.of(region))  //AWS S3클라이언트를 설정해둔 ap-northeast-2 즉 서울로 지정한다.
        .credentialsProvider(StaticCredentialsProvider.create(credentials)) //AWS 자격 증명을 제공하는 방법을 설정한다. accessKey와 secretKey 지정
        .build();
  }
}