package com.virusvaccine.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;


@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = AgencySignUpRequest.class, name = SignUpRequest.AGENCY_TYPE),
        @JsonSubTypes.Type(value = UserSignupRequest.class, name = SignUpRequest.USER_TYPE)
})
public interface SignUpRequest {

    boolean validatePassword();

    boolean isAgency();

    String AGENCY_TYPE = "agency";
    String USER_TYPE = "user";

}

