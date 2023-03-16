package com.hoangtm14.spring.service.impl;

import com.hoangtm14.spring.constants.Constants;
import com.hoangtm14.spring.constants.MessageCode;
import com.hoangtm14.spring.exception.BadRequestException;
import com.hoangtm14.spring.model.dto.response.UploadApiResponse;
import com.hoangtm14.spring.service.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Slf4j
@Service
public class UploadServiceImpl implements UploadService {

    public static final String FILE = "file";

    @Value("${api.upload.url}")
    private String apiUrl;
    @Value("${api.upload.key}")
    private String apiKey;
    @Value("${api.upload.secret}")
    private String apiSecret;


    @Override
    public String uploadFile(MultipartFile file) {
        log.info("uploadFile" + Constants.BEGIN_SERVICE);
        try {
            if (file.isEmpty()) {
                throw new BadRequestException(MessageCode.E_FILE_REQUIRED.getCode(), MessageCode.E_FILE_REQUIRED.getMessage());
            }
            //set req header
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            headers.setBasicAuth(apiKey, apiSecret);

            //set req body
            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add(FILE, file.getResource());

            HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(body, headers);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<UploadApiResponse> response = restTemplate.exchange(apiUrl, HttpMethod.POST, entity, UploadApiResponse.class);

            return Objects.requireNonNull(response.getBody()).getUrl();

        } catch (HttpClientErrorException | HttpServerErrorException e) {
            if (e instanceof HttpClientErrorException) {
                log.error("HttpClientErrorException", e);
                throw new BadRequestException();
            } else {
                log.error("HttpServerErrorException", e);
                throw new BadRequestException();
            }

        } finally {
            log.info("uploadFile" + Constants.END_SERVICE);
        }
    }
}
