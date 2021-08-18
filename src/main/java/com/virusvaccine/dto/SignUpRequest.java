package com.virusvaccine.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import static com.virusvaccine.dto.SignUpRequest.AGENCY_TYPE;
import static com.virusvaccine.dto.SignUpRequest.USER_TYPE;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = AgencySignUpRequest.class, name = AGENCY_TYPE),
        @JsonSubTypes.Type(value = UserSignupRequest.class, name = USER_TYPE)
})
public interface SignUpRequest {

    boolean validatePassword();

    boolean isAgency();

    String AGENCY_TYPE = "agency";
    String USER_TYPE = "user";
}
