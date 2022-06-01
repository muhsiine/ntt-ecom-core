package ma.nttsquad.nttecomcore.controller;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class AwsS3PreSignedResponse {

    private String url;

}
