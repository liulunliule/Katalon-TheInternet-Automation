import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

// 1. send GET Request to API
def response = WS.sendRequest(findTestObject('GET_User_1'))

// 2. In response Log Viewer
println("=== RESPONSE RECEIVED ===")
println("Status Code: " + response.getStatusCode())
println("Response Body: " + response.getResponseBodyContent())

// 3. Verify Status Code is 200 (OK)
WS.verifyResponseStatusCode(response, 200)

// 4. Verify data response
WS.verifyElementPropertyValue(response, 'name', 'Leanne Graham')
WS.verifyElementPropertyValue(response, 'username', 'Bret')
WS.verifyElementPropertyValue(response, 'email', 'Sincere@april.biz')

println("=== TEST PASSED ===")