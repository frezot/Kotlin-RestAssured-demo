package tests

import io.qameta.allure.Feature
import io.qameta.allure.Severity
import io.qameta.allure.SeverityLevel
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import utils.assertBodyCorrespondToScheme
import utils.assertHeadersIsCorrect
import utils.callGet


@DisplayName("Format tests")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FormatTests {


    @ParameterizedTest(name = "Проверяем[{index}] {arguments}")
    @Feature("Api")
    @Severity(SeverityLevel.BLOCKER)
    @ValueSource(strings = [
        "/api/test/user/0 ⇢ user_schema.json",
        "/api/test/user/300 ⇢ user_schema.json",
        "/api/test/users?gender=male ⇢ users_schema.json",
        "/api/test/user/ ⇢ error_schema.json",
        "/api/test/users ⇢ error_schema.json"
    ])
    fun testResponseConformsToSchema(params: String) {
        val path = params.split(" ⇢ ")[0]
        val schema = params.split(" ⇢ ")[1]

        callGet(path)
            .then()
            .assertHeadersIsCorrect()
            .assertBodyCorrespondToScheme(schema)
//            .assertThat().body(matchesJsonSchemaInClasspath(schema))
    }

}