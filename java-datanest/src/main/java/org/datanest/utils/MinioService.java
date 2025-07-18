package org.datanest.utils;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.core.sync.RequestBody;

@ApplicationScoped
public class MinioService {

  @Inject
  S3Client s3Client;

  public void upload(String objectKey, byte[] data) {
    s3Client.putObject(
        PutObjectRequest.builder()
            .bucket("datanest")
            .key(objectKey)
            .build(),
        RequestBody.fromBytes(data)
    );
  }
}

