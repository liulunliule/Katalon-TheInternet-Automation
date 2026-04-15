import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.openBrowser('https://the-internet.herokuapp.com/login')

WebUI.setText(findTestObject('Page_The Internet/txt_Username'), 'tomsmith')

WebUI.setEncryptedText(findTestObject('Page_The Internet/txt_Password'), '')

WebUI.click(findTestObject('Page_The Internet/btn_Login'))

WebUI.waitForElementVisible(findTestObject('Page_The Internet/msg_Error'), 5)

//WebUI.verifyElementText(findTestObject('Page_The Internet/msg_Error'), '(?s).*Your password.*')
////Cách 1 : pass
//	// Get text
//	String actual = WebUI.getText(findTestObject('Page_The Internet/msg_Error'))
//	
//	// Clean text (loại bỏ dấu ×)
//	actual = actual.replace('×', '').trim()
//
//		// Verify
//	WebUI.verifyMatch(actual, '.*Your password is invalid.*', true)

//Cách 2: làm gọn

WebUI.verifyMatch(WebUI.getText(findTestObject('Page_The Internet/msg_Error')), '.*Your password is invalid.*', true)
//WebUI.verifyMatch(WebUI.getText(findTestObject('Page_The Internet/msg_Error')).replace('×', '').trim(), '.*Your password is invalid.*', true)

WebUI.closeBrowser()

