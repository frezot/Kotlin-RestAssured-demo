package tests

import io.qameta.allure.Feature
import io.qameta.allure.Severity
import io.qameta.allure.SeverityLevel
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.Matchers.lessThan
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import utils.assertStatusCode
import utils.callGet

@DisplayName("Negative tests")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class NegativeTests {

    @ParameterizedTest(name = "Некорректный gender в запросе /users [{index}]")
    @ValueSource(strings = [
        "*",
        "😎",
        "疼痛",
        "values().forEach%7BThread.sleep(4000L)%7D"
    ])
    @Feature("Api")
    @Severity(SeverityLevel.NORMAL)
    fun testWrongGender(input: String) {
        callGet("/api/test/users?gender=$input")
            .then()
            .assertStatusCode(500)
            .body("message", containsString("Internal Server Error: No enum constant com.coolrocket.app.api.test.TestGender"))
            .time(lessThan(4000L))
    }

    @ParameterizedTest(name = "Некорректный номер в запросе /user/id [{index}]")
    @ValueSource(strings = [
        "-5",
        "0x0F",
        "1.0",
        "144L",
        "2147483648",
        "2_147_483_647",
        "2_147_483_648"
    ])
    @Feature("Api")
    @Severity(SeverityLevel.NORMAL)
    fun testWrongUserNumber(input: String) {
        callGet("/api/test/user/$input")
            .then()
            .assertStatusCode(400)
            .body("message", containsString("Bad Request: Failed to convert value of type 'java.lang.String'"))
    }

}