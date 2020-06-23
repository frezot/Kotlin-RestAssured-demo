package tests

import io.qameta.allure.Description
import io.qameta.allure.Feature
import io.qameta.allure.Severity
import io.qameta.allure.SeverityLevel
import io.restassured.path.json.JsonPath
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import utils.callGet
import utils.getInfoAboutUser
import utils.userResultCorrespondAppRules


@DisplayName("Functional tests")
class FunctionalTests {

    @Test
    @DisplayName("Корректность гендерной фильтрации")
    @Description("Проверим, что никакой из id не возвращается для обоих полов")
    @Feature("Фильтрация")
    @Severity(SeverityLevel.NORMAL)
    fun testUsersGetting() {
        val males = JsonPath.from(callGet("/api/test/users?gender=male").asString()).getList<Int>("result")
        val females = JsonPath.from(callGet("/api/test/users?gender=female").asString()).getList<Int>("result")
        val bothGender = males.intersect(females)
        assert(bothGender.isEmpty()) { "Пользователи $bothGender возвращаются и по параметру (male) и (female)" }
    }

    @Test
    @DisplayName("Корректность registrationDate")
    @Description("Дата не может быть в будущем, и не должна быть старее сервиса")
    @Feature("Пользовательские данные")
    @Severity(SeverityLevel.NORMAL)
    fun testUserResponse1() {
        val userId = 15
        val response = getInfoAboutUser(userId)
        userResultCorrespondAppRules(response, userId)
    }

    @Test
    @DisplayName("Проверка корректности age в профиле")
    @Description("Допустимые значения 14..150")
    @Feature("Пользовательские данные")
    @Severity(SeverityLevel.NORMAL)
    fun testUserResponse2() {
        val userId = 300
        val response = getInfoAboutUser(userId)
        userResultCorrespondAppRules(response, userId)
    }

    @Test
    @DisplayName("Идентификатор пользователя в запросе и ответе должны совпадать")
    @Description("___")
    @Feature("Пользовательские данные")
    @Severity(SeverityLevel.NORMAL)
    fun testUserResponse3() {
        val userId = 911
        val response = getInfoAboutUser(userId)
        userResultCorrespondAppRules(response, userId)
    }

    @Test
    @DisplayName("Проверка корректности gender пользователя")
    @Description("Допускаются только значения male/female")
    @Feature("Пользовательские данные")
    @Severity(SeverityLevel.NORMAL)
    fun testUserResponse4() {
        val userId = 94
        val response = getInfoAboutUser(userId)
        userResultCorrespondAppRules(response, userId)
    }
}
