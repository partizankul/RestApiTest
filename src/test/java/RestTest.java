import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;
import pojos.CreateUserRequest;
import pojos.CreateUserResponse;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class RestTest {
    private final RequestSpecification REQ_SPEC =
            new RequestSpecBuilder().setBaseUri("https://reqres.in/api")
                    .setBasePath("/users")
                    .setContentType(ContentType.JSON)
                    .build();

    @Test
    public void getUser(){ //метод get можно сверять статускод или полученные данные
        given()
                .spec(REQ_SPEC)
                .when().get()
                .then().statusCode(200)
                .body("data[1].email", equalTo("janet.weaver@reqres.in"));
    }
    @Test public void createUser(){  // метод POST создаем юзера
        CreateUserRequest rq = CreateUserRequest.builder()
        .name("morpheus")
        .job("leader")
                .build();

        CreateUserResponse rs = given()
                .baseUri("https://reqres.in/api").basePath("/users").contentType(ContentType.JSON)
                .body(rq)
                .when().post()
                .then().extract()
                .as(CreateUserResponse.class);

        assertThat(rs).isNotNull()
                .extracting(CreateUserResponse::getName)
                .isEqualTo(rq.getName());
    }

}
