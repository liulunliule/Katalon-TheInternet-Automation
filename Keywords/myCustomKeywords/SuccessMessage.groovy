package myCustomKeywords

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.annotation.Keyword

class SuccessMessage {
    
    /**
     * Verify success message on the page
     * @param expectedMessage The message you expect to see (partial match allowed)
     */
    @Keyword
    def verifySuccessMessage(String expectedMessage) {
        // Đợi thông báo thành công hiện ra
        WebUI.waitForElementVisible(findTestObject('Page_The Internet/msg_SecureArea'), 5)
        
        // Lấy text và clean
        String actualText = WebUI.getText(findTestObject('Page_The Internet/msg_SecureArea'))
        actualText = actualText.replace('×', '').trim()
		
//		println('actualText: '+ actualText +', expectedMessage: '+ expectedMessage)
        
        // Verify
        WebUI.verifyMatch(actualText, '.*' + expectedMessage + '.*', true)
        
        WebUI.comment("✓ Success message verified: '" + expectedMessage + "'")
    }
}