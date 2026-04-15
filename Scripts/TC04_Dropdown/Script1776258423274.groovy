import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.openBrowser('https://the-internet.herokuapp.com/dropdown')

// Chọn Option 1 (value = "1")
WebUI.selectOptionByValue(findTestObject('Page_Dropdown/ddl_Dropdown'), '1', false)
WebUI.verifyOptionSelectedByValue(findTestObject('Page_Dropdown/ddl_Dropdown'), '1', false, 5)
// Chọn Option 2 (value = "2")
WebUI.selectOptionByValue(findTestObject('Page_Dropdown/ddl_Dropdown'), '2', false)
WebUI.verifyOptionSelectedByValue(findTestObject('Page_Dropdown/ddl_Dropdown'), '2', false, 5)

WebUI.closeBrowser()