import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testdata.TestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

TestData data = TestDataFactory.findTestData('reglist')

for (int i = 1; i <= data.getRowNumbers(); i++) {
    def email = data.getValue('email', i)
    def password = data.getValue('password', i)

    println "Mengirim data ke-${i}: email=${email}, password=${password}"

    def request = findTestObject('API/Register/POST Register Success', [
        ('email') : email,
        ('password') : password
    ])

    try {
        def response = WS.sendRequest(request)
        
        WS.verifyResponseStatusCode(response, 200)
        
        println "RESPON KE-${i}:"
        println response.getResponseText()
    } catch (Exception e) {
        println "GAGAL mengirim data ke-${i}: ${e.message}"
    }
}
