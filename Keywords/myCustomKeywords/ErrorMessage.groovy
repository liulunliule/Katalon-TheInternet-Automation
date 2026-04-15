package myCustomKeywords

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.annotation.Keyword

class ErrorMessage {
    
    /**
     * Verify error message on the page
     * @param expectedMessage The message you expect to see (partial match allowed)
     */
    @Keyword
    def verifyErrorMessage(String expectedMessage) {
        // Đợi thông báo lỗi hiện ra (tối đa 5 giây)
        WebUI.waitForElementVisible(findTestObject('Page_The Internet/msg_Error'), 5)
        
        // Lấy text thực tế từ web
        String actualText = WebUI.getText(findTestObject('Page_The Internet/msg_Error'))
        
        // Clean text: xóa dấu × và khoảng trắng thừa
        actualText = actualText.replace('×', '').trim()
        
        // So sánh với expected (dùng regex để khớp một phần)
        WebUI.verifyMatch(actualText, '.*' + expectedMessage + '.*', true)
        
        // In log để debug
        WebUI.comment("✓ Error message verified: '" + expectedMessage + "'")
    }
}