package com.example.analyticsservice.swagger.server.dto;

import java.util.*;

import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.*;
import io.swagger.v3.oas.annotations.media.*;
import org.springframework.validation.annotation.*;

/**
 * ApiErrorResponseDto
 */
@Validated

public class ApiErrorResponseDto {

    @JsonProperty("code")
    private Integer code = null;

    @JsonProperty("message")
    private String message = null;

    public ApiErrorResponseDto code(Integer code) {
        this.code = code;
        return this;
    }

    /**
     * Get code
     *
     * @return code
     **/
    @Schema(description = "")
    @NotNull

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public ApiErrorResponseDto message(String message) {
        this.message = message;
        return this;
    }

    /**
     * Get message
     *
     * @return message
     **/
    @Schema(description = "")
    @NotNull

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ApiErrorResponseDto apiErrorResponseDto = (ApiErrorResponseDto) o;
        return Objects.equals(this.code, apiErrorResponseDto.code) &&
               Objects.equals(this.message, apiErrorResponseDto.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, message);
    }

    @Override
    public String toString() {

        String sb = "class ApiErrorResponseDto {\n" +
                    "    code: " + toIndentedString(code) + "\n" +
                    "    message: " + toIndentedString(message) + "\n" +
                    "}";
        return sb;
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

}
