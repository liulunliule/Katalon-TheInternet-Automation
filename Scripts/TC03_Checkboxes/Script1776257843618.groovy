import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.openBrowser('https://the-internet.herokuapp.com/checkboxes')

// Checkbox 1: Đang unchecked -> Check
WebUI.check(findTestObject('Page_Checkboxes/chk_Checkbox1'))
WebUI.verifyElementChecked(findTestObject('Page_Checkboxes/chk_Checkbox1'), 5)

// Checkbox 2: Đang checked -> Uncheck
WebUI.uncheck(findTestObject('Page_Checkboxes/chk_Checkbox2'))
WebUI.verifyElementNotChecked(findTestObject('Page_Checkboxes/chk_Checkbox2'), 5)

WebUI.closeBrowser()