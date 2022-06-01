package ma.nttsquad.nttecomcore.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AwsS3PreSignedRequest {

    private String filePath;
    private String method;

}
