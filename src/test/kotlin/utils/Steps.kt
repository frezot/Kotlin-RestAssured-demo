package utils

import io.qameta.allure.Step
import io.qameta.allure.restassured.AllureRestAssured
import io.restassured.RestAssured
import io.restassured.response.Response


@Step("Исполняем GET {path}")
fun callGet(path: String): Response {
    return RestAssured.given().filter(AllureRestAssured()).get("$BASE_URL$path")
}

@Step("Исполняем GET $BASE_URL")
fun touchBaseUrl(): Response {
    return RestAssured.given().filter(AllureRestAssured()).get(BASE_URL)
}
