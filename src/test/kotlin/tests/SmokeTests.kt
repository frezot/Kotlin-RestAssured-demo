package tests

import io.qameta.allure.Description
import io.qameta.allure.Feature
import io.qameta.allure.Severity
import io.qameta.allure.SeverityLevel
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import utils.assertStatusCode
import utils.touchBaseUrl

@DisplayName("Smoke tests")
class SmokeTests {

    @Test
    @DisplayName("Проверяем что host жив")
    @Description("Просто дергаем и ждем 200")
    @Feature("Api")
    @Severity(SeverityLevel.BLOCKER)
    fun testHostAvailability() {
        touchBaseUrl()
            .then()
            .assertStatusCode(200)
    }

}
