package org.example.demoqa.api.assertions.conditions;

import io.restassured.response.ValidatableResponse;
import lombok.RequiredArgsConstructor;
import org.example.demoqa.api.assertions.Condition;
import org.junit.jupiter.api.Assertions;

@RequiredArgsConstructor
public class StatusCodeCondition implements Condition {
    private final Integer statusCode;

    @Override
    public void check(ValidatableResponse response) {
        int actualStatusCode = response.extract().statusCode();
        Assertions.assertEquals(statusCode, actualStatusCode);

        //response.assertThat().statusCode(statusCode);
    }
}
