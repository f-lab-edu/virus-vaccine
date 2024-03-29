import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

import static net.grinder.script.Grinder.grinder
import static org.junit.Assert.*
import static org.hamcrest.Matchers.*
import net.grinder.script.GTest
import net.grinder.script.Grinder
import net.grinder.scriptengine.groovy.junit.GrinderRunner
import net.grinder.scriptengine.groovy.junit.annotation.BeforeProcess
import net.grinder.scriptengine.groovy.junit.annotation.BeforeThread
// import static net.grinder.util.GrinderUtils.* // You can use this if you're using nGrinder after 3.2.3
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.ngrinder.http.HTTPRequest
import org.ngrinder.http.HTTPRequestControl
import org.ngrinder.http.HTTPResponse
import org.ngrinder.http.cookie.Cookie
import org.ngrinder.http.cookie.CookieManager

/**
 * A simple example using the HTTP plugin that shows the retrieval of a single page via HTTP.
 *
 * This script is automatically generated by ngrinder.
 *
 * @author admin
 */
@RunWith(GrinderRunner)
class TestRunner {

    public static GTest test
    public static HTTPRequest request
    public static Map<String, String> headers = [:]
    public static Map<String, Object> params = [:]
    public static List<Cookie> cookies = []
    public static String baseUrl

    public static boolean user
    public static boolean agency
    public static boolean vaccine


    @BeforeProcess
    public static void beforeProcess() {
        HTTPRequestControl.setConnectionTimeout(300000)
        baseUrl = "http://49.50.174.5:8080"
        test = new GTest(1, "Test1")
        request = new HTTPRequest()
        grinder.logger.info("before process.")
    }

    @BeforeThread
    public void beforeThread() {
        test.record(this, "test")
        grinder.statistics.delayReports = true
        grinder.logger.info("before thread.")
    }

    @Before
    public void before() {
        request.setHeaders(headers)
        CookieManager.addCookies(cookies)
        grinder.logger.info("before. init headers and cookies")
    }

    @Test
    public void test() {

        //Random rnd = new Random()
        //Integer num = rnd.nextInt(10)//0<= <10
        for(int i=1; i<500001; i--){
            loginAgency2(i)
            registryVaccine()
            logout()
        }


    }

    public void signupUser(String... emailaddress){

        printstart("signupUser")

        Random rnd = new Random()
        String email = ""
        if (emailaddress.length == 0){
            Integer num = rnd.nextInt(50000000)+1 //우리나라 국민 5천만명
            email = "test"+num.toString() + "@test.test"
        }
        else{
            email = emailaddress[0]
        }

        StringBuilder phonenums = new StringBuilder("010");
        for (int i=0; i<8; i++){
            phonenums.append(rnd.nextInt(10))
        }
        String phoneNumber = phonenums.toString()

        String idNumber = phonenums.substring(0,7)

        grinder.logger.info(String.format("Sign up with -> %s %s %s", email, phoneNumber, idNumber))

        def json = String.format('{"type": "user", "email": "%s",  "password1": "1234", "password2":"1234",' +
                '"name":"newuser", "phoneNumber":"%s", "idNumber":"%s"}', email, phoneNumber, idNumber)

        headers.put("Content-type", "application/json;charset=UTF-8")
        HTTPResponse response = request.POST(baseUrl+"/api/user", json.getBytes(), headers)

        printend("signupUser")
        if (response.statusCode == 200){
            grinder.logger.warn("signupUser success", response.statusCode)
        }
        else{
            grinder.logger.warn("signupUser fail", response.statusCode)
        }
        grinder.logger.info("")

    }

    public void signupUser2(int num){

        printstart("signupUser")

        Random rnd = new Random()
        String email = "test"+num.toString() + "@test.test"

        StringBuilder phonenums = new StringBuilder("010");
        for (int i=0; i<8; i++){
            phonenums.append(rnd.nextInt(10))
        }
        String phoneNumber = phonenums.toString()

        String idNumber = phonenums.substring(0,7)

        grinder.logger.info(String.format("Sign up with -> %s %s %s", email, phoneNumber, idNumber))

        def json = String.format('{"type": "user", "email": "%s",  "password1": "1234", "password2":"1234",' +
                '"name":"newuser", "phoneNumber":"%s", "idNumber":"%s"}', email, phoneNumber, idNumber)

        headers.put("Content-type", "application/json;charset=UTF-8")
        HTTPResponse response = request.POST(baseUrl+"/api/user", json.getBytes(), headers)

        printend("signupUser")
        if (response.statusCode == 200){
            grinder.logger.warn("signupUser success", response.statusCode)
        }
        else{
            grinder.logger.warn("signupUser fail", response.statusCode)
        }
        grinder.logger.info("")

    }

    public void signupAgency(String... emailaddress){

        printstart("signupAgency")

        Random rnd = new Random()
        String email = ''
        Integer num = rnd.nextInt(500000) +1// 우리나라 기관수 약50만개로 추정?
        if(emailaddress.length == 0){
            email = "test"+num.toString() + "@test.test"
        }
        else{
            email = emailaddress[0]
        }

        float lat = rnd.nextFloat() + (rnd.nextInt(15)+24)//24<= <= 38  우리나라 최소 최대 위도
        float lng = rnd.nextFloat() + (rnd.nextInt(110)+20)//20<= <=129 우리나라 최소 최대 경도

        grinder.logger.info(String.format("Sign up with -> %s", email))

        def json = String.format('{"type": "agency", "email": "%s",  "password": "1234", "validPassword":"1234",' +
                '"name":"%s", "phoneNumber":"01022223333", "zipCode":"2351923", "siDo":"seoul", "siGunGu":"seoul", "eupMyeonDong":"seoul"' +
                ', "address":"seoul", "lat":%f, "lng":%f}', email, num.toString(), lat, lng)

        headers.put("Content-type", "application/json;charset=UTF-8")
        HTTPResponse response = request.POST(baseUrl+"/api/user", json.getBytes(), headers)

        printend("signupAgency")
        if (response.statusCode == 200){
            grinder.logger.warn("signupAgency success", response.statusCode)
        }
        else{
            grinder.logger.warn("signupAgency fail", response.statusCode)
        }
        grinder.logger.info("")

    }

    public void signupAgency2(int num){

        printstart("signupAgency")

        Random rnd = new Random()
        String email = "test"+num.toString() + "@test.test"
        //Integer num = rnd.nextInt(500000) +1// 우리나라 기관수 약50만개로 추정?

        float lat = rnd.nextFloat() + (rnd.nextInt(15)+24)//24<= <= 38  우리나라 최소 최대 위도
        float lng = rnd.nextFloat() + (rnd.nextInt(110)+20)//20<= <=129 우리나라 최소 최대 경도

        grinder.logger.info(String.format("Sign up with -> %s", email))

        def json = String.format('{"type": "agency", "email": "%s",  "password": "1234", "validPassword":"1234",' +
                '"name":"%s", "phoneNumber":"01022223333", "zipCode":"2351923", "siDo":"seoul", "siGunGu":"seoul", "eupMyeonDong":"seoul"' +
                ', "address":"seoul", "lat":%f, "lng":%f}', email, num.toString(), lat, lng)

        headers.put("Content-type", "application/json;charset=UTF-8")
        HTTPResponse response = request.POST(baseUrl+"/api/user", json.getBytes(), headers)

        printend("signupAgency")
        if (response.statusCode == 200){
            grinder.logger.warn("signupAgency success", response.statusCode)
        }
        else{
            grinder.logger.warn("signupAgency fail", response.statusCode)
        }
        grinder.logger.info("")

    }

    public void loginUser(){
        printstart("loginUser")

        Random rnd = new Random()

        Integer num = rnd.nextInt(50000000)+1 //우리나라 국민 5천만명
        String email = "test"+num.toString() + "@test.test"

        def json = String.format('{"userEmail": "%s", "userPassword": "1234",  "isAgency": false}', email);
        grinder.logger.info("login with -> "+json)
        headers.put("Content-type", "application/json;charset=UTF-8")
        HTTPResponse response = request.POST(baseUrl+"/api/login", json.getBytes(), headers)

        printend("loginUser")
        if (response.statusCode == 200){
            grinder.logger.warn("loginUser success", response.statusCode)
        }
        else{// 해당 유저가 없어서 로그인실패시 해당 유저 회원가입시키고 다시 로그인
            grinder.logger.warn("loginUser fail", response.statusCode)
            signupUser(email)

            printstart("Re loginUser")
            grinder.logger.info("Re login with -> "+json)
            HTTPResponse response2 = request.POST(baseUrl+"/api/login", json.getBytes(), headers)
            printend("Re loginUser")

            if (response2.statusCode == 200){
                grinder.logger.warn("Re loginUser success", response2.statusCode)
            }
            else{
                grinder.logger.warn("Re loginUser fail", response2.statusCode)
            }

        }
        grinder.logger.info("")

    }

    public void loginAgency(){
        printstart("loginAgency")

        Random rnd = new Random()

        Integer num = rnd.nextInt(500000) +1// 우리나라 기관수 약50만개로 추정?
        String email = "test"+num.toString() + "@test.test"

        def json = String.format('{"userEmail": "%s", "userPassword": "1234",  "isAgency": true}', email);
        grinder.logger.info("login with -> "+json)
        headers.put("Content-type", "application/json;charset=UTF-8")
        HTTPResponse response = request.POST(baseUrl+"/api/login", json.getBytes(), headers)

        printend("loginAgency")
        if (response.statusCode == 200){
            grinder.logger.warn("loginAgency success", response.statusCode)
        }
        else{// 해당 유저가 없어서 로그인실패시 해당 유저 회원가입시키고 다시 로그인
            grinder.logger.warn("loginAgency fail", response.statusCode)
            signupAgency(email)

            printstart("Re loginAgency")
            grinder.logger.info("Re login with -> "+json)
            HTTPResponse response2 = request.POST(baseUrl+"/api/login", json.getBytes(), headers)
            printend("Re loginAgency")

            if (response2.statusCode == 200){
                grinder.logger.warn("Re loginAgency success", response2.statusCode)
            }
            else{
                grinder.logger.warn("Re loginAgency fail", response2.statusCode)
            }

        }
        grinder.logger.info("")

    }

    public void loginAgency2(int num){
        printstart("loginAgency")

        Random rnd = new Random()

        //Integer num = rnd.nextInt(500000) +1// 우리나라 기관수 약50만개로 추정?
        String email = "test"+num.toString() + "@test.test"

        def json = String.format('{"userEmail": "%s", "userPassword": "1234",  "isAgency": true}', email);
        grinder.logger.info("login with -> "+json)
        headers.put("Content-type", "application/json;charset=UTF-8")
        HTTPResponse response = request.POST(baseUrl+"/api/login", json.getBytes(), headers)

        printend("loginAgency")
        if (response.statusCode == 200){
            grinder.logger.warn("loginAgency success", response.statusCode)
        }
        else{// 해당 유저가 없어서 로그인실패시 해당 유저 회원가입시키고 다시 로그인
            grinder.logger.warn("loginAgency fail", response.statusCode)
            signupAgency(email)

            printstart("Re loginAgency")
            grinder.logger.info("Re login with -> "+json)
            HTTPResponse response2 = request.POST(baseUrl+"/api/login", json.getBytes(), headers)
            printend("Re loginAgency")

            if (response2.statusCode == 200){
                grinder.logger.warn("Re loginAgency success", response2.statusCode)
            }
            else{
                grinder.logger.warn("Re loginAgency fail", response2.statusCode)
            }

        }
        grinder.logger.info("")

    }

    public void lookup(){
        printstart("lookup")

        Random rnd = new Random()

        float lat = rnd.nextFloat() + (rnd.nextInt(15)+24)//24<= <= 38  우리나라 최소 최대 위도
        float lng = rnd.nextFloat() + (rnd.nextInt(110)+20)//20<= <=129 우리나라 최소 최대 경도

        List<String> vaccineCode = ["PF","MD","AZ","JS","NV",""]
        String code = vaccineCode[rnd.nextInt(6)]

        String available = rnd.nextInt(2) == 0 ? "false" : "true"

        String futuredate = LocalDate.now().plusDays(rnd.nextInt(31)).toString() //한달까지 미래날짜..
        String date =  rnd.nextInt(2) == 0 ? "" : futuredate

        String url = baseUrl + String.format("/api/agency?lat=%f&lng=%f&code=%s&available=%s&date=%s",
                lat, lng, code, available, date)

        grinder.logger.info("lookup with -> "+url)

        HTTPResponse response = request.GET(url)
        printend("lookup")
        if (response.statusCode == 200){
            grinder.logger.warn("lookup success", response.statusCode)
        }
        else{
            grinder.logger.warn("lookup fail", response.statusCode)
        }
        grinder.logger.info("")
    }

    public void logout(){
        printstart("logout")
        HTTPResponse response = request.PUT(baseUrl+"/api/logout")
        printend("logout")
        if (response.statusCode == 200){
            grinder.logger.warn("logout success", response.statusCode)
        }
        else{
            grinder.logger.warn("logout fail", response.statusCode)
        }
        grinder.logger.info("")
    }

    public void reservation(){
        printstart("reservation")
        HTTPResponse response = request.GET(baseUrl+"/api/reservation/user")
        printend("reservation")
        if (response.statusCode == 200){
            grinder.logger.warn("reservation success", response.statusCode)
        }
        else{
            grinder.logger.warn("reservation fail", response.statusCode)
        }
        grinder.logger.info("")
    }

    public void agencyreservation(){
        printstart("agencyreservation")
        HTTPResponse response = request.GET(baseUrl+"/api/reservation/agency")
        printend("agencyreservation")
        if (response.statusCode == 200){
            grinder.logger.warn("agencyreservation success", response.statusCode)
        }
        else{
            grinder.logger.warn("agencyreservation fail", response.statusCode)
        }
        grinder.logger.info("")
    }

    public void agencyreservationnwithtime(){
        printstart("agencyreservationnwithtime")
        HTTPResponse response = request.GET(baseUrl+"/api/reservation/agency/time")
        printend("agencyreservationnwithtime")
        if (response.statusCode == 200){
            grinder.logger.warn("agencyreservationnwithtime success", response.statusCode)
        }
        else{
            grinder.logger.warn("agencyreservationnwithtime fail", response.statusCode)
        }
        grinder.logger.info("")
    }

    public void getQuantityOfVaccines(){
        printstart("getQuantityOfVaccines")
        HTTPResponse response = request.GET(baseUrl+"/api/stats/vaccines/quantity")
        printend("getQuantityOfVaccines")
        if (response.statusCode == 200){
            grinder.logger.warn("getQuantityOfVaccines success", response.statusCode)
        }
        else{
            grinder.logger.warn("getQuantityOfVaccines fail", response.statusCode)
        }
        grinder.logger.info("")
    }

    public void getQuantityOfBookedVaccines(){
        printstart("getQuantityOfBookedVaccines")
        HTTPResponse response = request.GET(baseUrl+"/api/stats/vaccines/quantity/booked")
        printend("getQuantityOfBookedVaccines")
        if (response.statusCode == 200){
            grinder.logger.warn("getQuantityOfBookedVaccines success", response.statusCode)
        }
        else{
            grinder.logger.warn("getQuantityOfBookedVaccines fail", response.statusCode)
        }
        grinder.logger.info("")
    }

    public void getAgencysWithRestAmount(){
        printstart("getAgencysWithRestAmount")
        HTTPResponse response = request.GET(baseUrl+"/api/stats/agencys/restamount")
        printend("getAgencysWithRestAmount")
        if (response.statusCode == 200){
            grinder.logger.warn("getAgencysWithRestAmount success", response.statusCode)
        }
        else{
            grinder.logger.warn("getAgencysWithRestAmount fail", response.statusCode)
        }
        grinder.logger.info("")
    }

    public void getRegionsWithRestAmount(){
        printstart("getRegionsWithRestAmount")
        HTTPResponse response = request.GET(baseUrl+"/api/stats/regions/restamount")
        printend("getRegionsWithRestAmount")
        if (response.statusCode == 200){
            grinder.logger.warn("getRegionsWithRestAmount success", response.statusCode)
        }
        else{
            grinder.logger.warn("getRegionsWithRestAmount failed", response.statusCode)
        }
        grinder.logger.info("")
    }

    public void getViruses(){
        printstart("getViruses")
        HTTPResponse response = request.GET(baseUrl+"/api/vaccines/viruses")
        printend("getViruses")
        if (response.statusCode == 200){
            grinder.logger.warn("getViruses success", response.statusCode)
        }
        else{
            grinder.logger.warn("getViruses fail", response.statusCode)
        }
        grinder.logger.info("")
    }

    public void getVaccines(){
        printstart("getVaccines")
        HTTPResponse response = request.GET(baseUrl+"/api/vaccines")
        printend("getVaccines")
        if (response.statusCode == 200){
            grinder.logger.warn("getVaccines success", response.statusCode)
        }
        else{
            grinder.logger.warn("getVaccines fail", response.statusCode)
        }
        grinder.logger.info("")
    }

    public void registryVaccine(){
        printstart("registryVaccine")

        Random rnd = new Random()
        int vaccineId = rnd.nextInt(5) + 1
        int amount = rnd.nextInt(101) + 100
        int extraDay = rnd.nextInt(30)+1

        LocalDateTime time = LocalDateTime.now().plusDays(extraDay);
        String now = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(time)

        def json = String.format('{"vaccineId": %d, "amount": %d,  "vaccinateAt": "%s"}', vaccineId, amount, now)
        headers.put("Content-type", "application/json;charset=UTF-8")
        HTTPResponse response = request.POST(baseUrl+"/api/vaccines", json.getBytes(), headers)

        printend("registryVaccine")
        if (response.statusCode == 200){
            grinder.logger.warn("registryVaccine success", response.statusCode)
        }
        else{
            grinder.logger.warn("registryVaccine fail", response.statusCode)
        }
        grinder.logger.info("")
    }

    public void bookVaccine(){
        printstart("bookVaccine")

        Random rnd = new Random()
        int agencyId = rnd.nextInt(500000) + 1

        List<String> vaccineCode = ["PF","MD","AZ","JS","NV",""]
        String code = vaccineCode[rnd.nextInt(6)]

        def json = code.equals("") ? String.format('{"agencyId": %d, "vaccineType": null}', agencyId): String.format('{"agencyId": %d, "vaccineType": "%s"}', agencyId, code)

        grinder.logger.info("bookVaccine with -> "+json)
        headers.put("Content-type", "application/json;charset=UTF-8")
        HTTPResponse response = request.PUT(baseUrl+"/api/reservation", json.getBytes(), headers)

        printend("bookVaccine")
        if (response.statusCode == 200){
            grinder.logger.warn("bookVaccine success", response.statusCode)
        }
        else{
            grinder.logger.warn("bookVaccine fail", response.statusCode)
        }
        grinder.logger.info("")
    }

    public void printstart(String ss){
        grinder.logger.info("start "+ss)
    }

    public void printend(String ss){
        grinder.logger.info("end "+ss)
    }

}