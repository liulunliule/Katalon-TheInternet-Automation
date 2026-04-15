import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testdata.TestData
//import hàm từ Customkeywods
import myCustomKeywords.ErrorMessage
import myCustomKeywords.SuccessMessage

// Đọc dữ liệu từ file Excel
TestData testData = TestDataFactory.findTestData('Data Files/Login_Test_Data')

// Lấy số dòng dữ liệu
int totalRows = testData.getRowNumbers()

println("=== BẮT ĐẦU TEST VỚI " + totalRows + " BỘ DỮ LIỆU ===")

for (int i = 1; i <= totalRows; i++) {
    String username = testData.getValue('Username', i)
    String password = testData.getValue('Password', i)
    String expectedText = testData.getValue('Expected_Text', i)
    String testType = testData.getValue('Test_Type', i)
    
//    println("--- Dòng " + i + ": Test " + testType + " ---")
	println("Row " + i +
		" | username=" + username +
		" | password=" + password +
		" | expectedText=" + expectedText +
		" | testType=" + testType)
    WebUI.openBrowser('https://the-internet.herokuapp.com/login')
    
    // Nhập Username (nếu có)
    if (username != null && !username.isEmpty()) {
        WebUI.setText(findTestObject('Page_The Internet/txt_Username'), username)
    }
    
    // Nhập Password (nếu có) - DÙNG setText THAY VÌ setEncryptedText
    if (password != null && !password.isEmpty()) {
        WebUI.setText(findTestObject('Page_The Internet/txt_Password'), password)
    }
    
    WebUI.click(findTestObject('Page_The Internet/btn_Login'))
    
//	code lặp
//    if (testType == 'Success') {
//        WebUI.waitForElementVisible(findTestObject('Page_The Internet/msg_SecureArea'), 5)
//        String actualText = WebUI.getText(findTestObject('Page_The Internet/msg_SecureArea'))
//        actualText = actualText.replace('×', '').trim()
//        WebUI.verifyMatch(actualText, '.*' + expectedText + '.*', true)
//        WebUI.click(findTestObject('Page_The Internet/btn_Logout'))
//    } else if (testType == 'Error') {
//        WebUI.waitForElementVisible(findTestObject('Page_The Internet/msg_Error'), 5)
//        String actualText = WebUI.getText(findTestObject('Page_The Internet/msg_Error'))
//        actualText = actualText.replace('×', '').trim()
//        WebUI.verifyMatch(actualText, '.*' + expectedText + '.*', true)
//    }
	
//	Tối ưu, gọi từ Keywords custom
	if (testType == 'Success') {
		// Gọi Custom Keyword (chỉ 1 dòng!)
//		println('Verify testType == Success')
//		println('expectedText: '+ expectedText)
		new SuccessMessage().verifySuccessMessage(expectedText)
		WebUI.click(findTestObject('Page_The Internet/btn_Logout'))
//		println('isTestTypeSuccess')
	} else if (testType == 'Error') {
		// Gọi Custom Keyword (chỉ 1 dòng!)
		new ErrorMessage().verifyErrorMessage(expectedText)
	}
    
    WebUI.closeBrowser()
    println("✓ Dòng " + i + " PASSED")
}

println("=== TẤT CẢ " + totalRows + " TEST CASE ĐỀU PASSED ===")