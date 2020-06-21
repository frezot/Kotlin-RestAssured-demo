package utils

import io.qameta.allure.Step
import io.restassured.module.jsv.JsonSchemaValidator
import io.restassured.response.ValidatableResponse
import io.restassured.specification.RequestSpecification

fun RequestSpecification.When(): RequestSpecification {
    // https://github.com/rest-assured/rest-assured/wiki/Usage#avoid-escaping-when-keyword
    return this.`when`()
}

@Step("Ответ содержит правильные заголовки (Transfer-Encoding, Connection, Server, Content-Type)")
fun ValidatableResponse.assertHeadersIsCorrect(): ValidatableResponse {
    return this.assertThat()
        .header("Transfer-Encoding", "chunked")
        .header("Connection", "keep-alive")
        .header("Server", "nginx/1.16.1")
        .header("Content-Type", "application/json;charset=UTF-8")
}

@Step("Ответ совпадает с json-схемой")
fun ValidatableResponse.assertBodyCorrespondToScheme(filename: String): ValidatableResponse {
    return this.assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(filename))
}

@Step("Ответ имеет HTTP-код [{code}]")
fun ValidatableResponse.assertStatusCode(code: Int): ValidatableResponse {
    return this.assertThat().statusCode(code)
}
