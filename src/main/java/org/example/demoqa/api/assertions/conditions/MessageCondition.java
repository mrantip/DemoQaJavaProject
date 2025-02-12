package org.example.demoqa.api.assertions.conditions;

import io.restassured.response.ValidatableResponse;
import lombok.RequiredArgsConstructor;
import org.example.demoqa.api.assertions.Condition;
import org.example.demoqa.api.models.accountmodels.Info;
import org.junit.jupiter.api.Assertions;

@RequiredArgsConstructor
public class MessageCondition  implements Condition {

    private final String expectedMessage;

    @Override
    public void check(ValidatableResponse response) {
        Info info = response.extract().as(Info.class);
        Assertions.assertEquals(expectedMessage, info.getMessage());

//        Info info = response.extract().jsonPath().getObject("info", Info.class);

        //response.body("info.message", equalTo(expectedMessage));
    }

}
