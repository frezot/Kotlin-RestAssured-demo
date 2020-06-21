package tests

import io.qameta.allure.Description
import io.qameta.allure.Feature
import io.qameta.allure.Severity
import io.qameta.allure.SeverityLevel
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test


@DisplayName("Functional tests")
class FunctionalTests {

    @Test
    @DisplayName("Проверка корректности фильтрации")
    @Description("___")
    @Feature("Api")
    @Severity(SeverityLevel.NORMAL)
    fun testUsersGetting() {
        // поскольку небинарных полов не допускается можно схитрить и проверить что
        // (gender=any) == (gender=male) + (gender=female)
        // сразу же обнаружится баг
        //TODO: implement
    }

    @Test
    @DisplayName("Проверка корректности registrationDate")
    @Description("___")
    @Feature("Api")
    @Severity(SeverityLevel.NORMAL)
    fun testUserResponse1() {
        // 1. дата не может быть в будущем
        // 2. дата не может быть старше чем появление сервиса (уточнять, пока поставить наугад -5 лет)
        //TODO: implement
    }

    @Test
    @DisplayName("Проверка корректности age в профиле")
    @Description("___")
    @Feature("Api")
    @Severity(SeverityLevel.NORMAL)
    fun testUserResponse2() {
        //TODO: implement
    }

    @Test
    @DisplayName("id в запросе и ответе должны совпадать")
    @Description("___")
    @Feature("Api")
    @Severity(SeverityLevel.NORMAL)
    fun testUserResponse3() {
        // точно видел расхождение, добавить тест
        //TODO: implement
    }
}