import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testdata.TestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import groovy.json.JsonSlurper

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
        
        WS.verifyResponseStatusCode(response, 400)
        
        println "RESPON KE-${i}:"
        println response.getResponseText()

        def json = new JsonSlurper().parseText(response.getResponseBodyContent())

        assert json.id != null : "Response tidak memiliki atribut 'id' pada data ke-${i}"

        assert json.token != null && json.token.trim() != "" : "Response tidak memiliki token valid pada data ke-${i}"

        println " Validasi sukses untuk data ke-${i}\n"
        
    } catch (AssertionError ae) {
        println "VALIDASI GAGAL untuk data ke-${i}: ${ae.message}\n"
    } catch (Exception e) {
        println "ERROR saat mengirim data ke-${i}: ${e.message}\n"
    }
}
