import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import groovy.json.JsonSlurper
import userValidator.UserValidator

println("")
println("========================================")
println("API TEST: GET ALL USERS")
println("========================================")
println("")

// 1. Send request
def response = WS.sendRequest(findTestObject('GET_All_Users'))
WS.verifyResponseStatusCode(response, 200)

// 2. Parse response
def jsonSlurper = new JsonSlurper()
def users = jsonSlurper.parseText(response.getResponseBodyContent())

println("Status: 200 OK")
println("Total users received: ${users.size()}")
println("Response time: ${response.getElapsedTime()} ms")

// 3. Validate - ONLY SHOW FAILURES
def validator = new UserValidator()
def result = validator.validateAllUsers(users, false)  // false = chi hien thi fail

// 4. Final assertion
if (result.invalid > 0) {
    println("")
    println("TEST FAILED - ${result.invalid} user(s) have validation errors!")
    assert false : "Validation failed for users: ${result.failedIds}"
} else {
    println("")
    println("TEST PASSED - All ${result.valid} users are valid!")
}