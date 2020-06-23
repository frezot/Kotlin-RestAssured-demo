package utils

import io.qameta.allure.Step
import io.qameta.allure.restassured.AllureRestAssured
import io.restassured.RestAssured
import io.restassured.builder.ResponseSpecBuilder
import io.restassured.path.json.JsonPath
import io.restassured.response.Response
import org.hamcrest.Matchers.*
import org.joda.time.DateTime


@Step("Исполняем GET {path}")
fun callGet(path: String): Response {
    return RestAssured.given().filter(AllureRestAssured()).get("$BASE_URL$path")
}

@Step("Исполняем GET $BASE_URL")
fun touchBaseUrl(): Response {
    return RestAssured.given().filter(AllureRestAssured()).get(BASE_URL)
}

@Step("Получаем информацию о пользователе [{number}]")
fun getInfoAboutUser(number: Int): Response {
    return callGet("/api/test/user/$number")
}

@Step("Проверяем что пользовательские данные не противоречат правилам сервиса и здравому смыслу")
fun userResultCorrespondAppRules(resp: Response, userId: Int) {

    val correctAnswer = ResponseSpecBuilder()
        .expectStatusCode(200)
        .expectBody("result.id", equalTo(userId))
        .expectBody("result.age", `is`(both(greaterThan(14)).and(lessThan(150))))
        .expectBody("result.gender", isOneOf("male", "female"))
        .build()

    resp.then().spec(correctAnswer)

    // на нашел как красиво проверить даты из коробки, пришлось размотать руками. возможно лишний велосипед
    val registrationDate = DateTime(JsonPath.from(resp.asString()).getString("result.registrationDate"))
    assert(registrationDate.isBeforeNow) { "Ошибка! registrationDate в будущем" }
    assert(registrationDate.isAfter(DateTime("2015-01-01"))) { "Ошибка! registrationDate старше сервиса знакомств" }
    // TODO: дата просто из головы, но у любого сервиса есть дата регистрации первого аккаунта

}
