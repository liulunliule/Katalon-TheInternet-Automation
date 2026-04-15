import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import groovy.json.JsonSlurper
import userValidator.UserValidator

println("=== API TEST: GET USER 1 ===\n")

// 1. Send request
def response = WS.sendRequest(findTestObject('GET_User_1'))
WS.verifyResponseStatusCode(response, 200)

// 2. Parse response
def user = new JsonSlurper().parseText(response.getResponseBodyContent())

// 3. Validate user
def validator = new UserValidator()
def isValid = validator.validateUser(user, true)

// 4. Assert
assert isValid : "User validation failed"

println("\n=== TEST PASSED ===")