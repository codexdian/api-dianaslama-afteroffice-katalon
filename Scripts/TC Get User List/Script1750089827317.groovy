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

import groovy.json.JsonSlurper
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import groovy.json.JsonSlurper
def request = findTestObject('API/User/GET User List Success')
def response = WS.sendRequest(request)
WS.verifyResponseStatusCode(response, 200)

def json = new JsonSlurper().parseText(response.getResponseBodyContent())

json.data.each { user ->
    assert user.containsKey('id') : "Missing 'id' in user: ${user}"
    assert user.containsKey('email') : "Missing 'email' in user: ${user}"
    assert user.containsKey('first_name') : "Missing 'first_name' in user: ${user}"
    assert user.containsKey('last_name') : "Missing 'last_name' in user: ${user}"
    assert user.containsKey('avatar') : "Missing 'avatar' in user: ${user}"
}

json.data.each { user ->
	assert user.id != null
	assert user.email != null
	assert user.first_name != null
	assert user.last_name != null
	assert user.avatar != null
	}
