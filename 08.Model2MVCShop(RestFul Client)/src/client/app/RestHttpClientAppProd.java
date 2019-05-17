package client.app;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;



public class RestHttpClientAppProd {
	
	// main Method
	public static void main(String[] args) throws Exception{
		
		
		System.out.println("\n====================================\n");
		// 1.1 Http Get 방식 Request : JsonSimple lib 사용
		RestHttpClientAppProd.getProductTest_JsonSimple(); //돌아간다..신난다...
		//RestHttpClientAppProd.listProductTest_JsonSimple();//아직 안함
		
		System.out.println("\n====================================\n");
		// 1.2 Http Get 방식 Request : CodeHaus lib 사용
	//	RestHttpClientAppProd.getProductTest_Codehaus();//돌아갈까? JsonSimple은 돌아가는데.....
		//RestHttpClientAppProd.listProductTest_JsonSimple();//아직 안함
				
		System.out.println("\n====================================\n");
//		// 1.2 Http Post 방식 Request : CodeHaus lib 사용
//		RestHttpClientAppProd.addProductTest_Codehaus(); //돌아간다....신난다.......
//		RestHttpClientAppProd.updateProductTest_Codehaus();//아직 안함
	
	}
	
	
//================================================================//
	// Http Protocol GET Request : getProductTest
	public static void getProductTest_JsonSimple() throws Exception{//돌아간다 신난다
		System.out.println("제발 들어와라~~~~~~~~~~~");
		// HttpClient : Http Protocol 의 client 추상화 
		HttpClient httpClient = new DefaultHttpClient();
		System.out.println("제발");
		String url= "http://127.0.0.1:8080/product/json/getProduct/10001";
		System.out.println("제발제발");		
		// HttpGet : Http Protocol 의 GET 방식 Request
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");
		System.out.println("제발제발제발");
		// HttpResponse : Http Protocol 응답 Message 추상화
		HttpResponse httpResponse = httpClient.execute(httpGet);
		System.out.println("1818181818181818");
		//==> Response 확인
		System.out.println(httpResponse);
		System.out.println();

		//==> Response 중 entity(DATA) 확인
		HttpEntity httpEntity = httpResponse.getEntity();
		System.out.println("191919191919191991");
		//==> InputStream 생성
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		System.out.println("2020202020202020");
		System.out.println("[ Server 에서 받은 Data 확인 ] ");
		String serverData = br.readLine();
		System.out.println(serverData);
		System.out.println("제발제발제발제발");
		//==> 내용읽기(JSON Value 확인)
		JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
		System.out.println(jsonobj);
	}
	
	//1.2 Http Protocol GET Request : JsonSimple + codehaus 3rd party lib 사용
	public static void getProductTest_Codehaus() throws Exception{
		
		// HttpClient : Http Protocol 의 client 추상화 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url= 	"http://127.0.0.1:8080/product/json/getProduct/10001";

		// HttpGet : Http Protocol 의 GET 방식 Request
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");
		
		// HttpResponse : Http Protocol 응답 Message 추상화
		HttpResponse httpResponse = httpClient.execute(httpGet);
		
		//==> Response 확인
		System.out.println(httpResponse);
		System.out.println();

		//==> Response 중 entity(DATA) 확인
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream 생성
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		//==> 다른 방법으로 serverData 처리 
		//System.out.println("[ Server 에서 받은 Data 확인 ] ");
		//String serverData = br.readLine();
		//System.out.println(serverData);
		
		//==> API 확인 : Stream 객체를 직접 전달 
		JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		System.out.println(jsonobj);
	
		ObjectMapper objectMapper = new ObjectMapper();
		 Product product = objectMapper.readValue(jsonobj.toString(), Product.class);
		 System.out.println(product);
	}
	
//================================================================//	
	
	// Http Protocol POST 방식 Request : addProductTest 
	public static void addProductTest_Codehaus() throws Exception{//돌아간다 신난다
		
		// HttpClient : Http Protocol 의 client 추상화 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url = "http://127.0.0.1:8080/product/json/addProduct";
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		

		//[ 방법 3 : codehaus 사용]
		Product product01 =  new Product();
		product01.setProdName("선택남12");
		product01.setFileName("선택남13");
		product01.setManuDate("19931119");
		product01.setPrice(1300000);
		product01.setProdDetail("골라봐");
		product01.setProTranCode(null);
		
		System.out.println("@@@@@@@@");
		ObjectMapper objectMapper01 = new ObjectMapper();
		System.out.println("1111111111");
		//Object ==> JSON Value 로 변환
		String jsonValue = objectMapper01.writeValueAsString(product01);
		System.out.println("jV//////"+jsonValue);
		System.out.println("222222222222");
		HttpEntity httpEntity01 = new StringEntity(jsonValue,"utf-8");
		System.out.println("3333333333333");
		httpPost.setEntity(httpEntity01);
		HttpResponse httpResponse = httpClient.execute(httpPost);
		System.out.println("18181818181818");
		//==> Response 확인
		System.out.println(httpResponse);
		System.out.println();
		
		//==> Response 중 entity(DATA) 확인
		HttpEntity httpEntity02 = httpResponse.getEntity();
		System.out.println("3333333333333");
		//==> InputStream 생성
		InputStream is = httpEntity02.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		
		//==> API 확인 : Stream 객체를 직접 전달 
		JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		System.out.println(jsonobj);
	
		ObjectMapper objectMapper = new ObjectMapper();
		 Product product = objectMapper.readValue(jsonobj.toString(), Product.class);
		 System.out.println(product);
	}	
	

//Http Protocol POST 방식 Request : updateProductTest 
	public static void updateProductTest_Codehaus() throws Exception{//돌아간다 신난다
		
		// HttpClient : Http Protocol 의 client 추상화 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url = "http://127.0.0.1:8080/product/json/updateProduct";
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		
		//[ 방법 3 : codehaus 사용]
		Product product01 =  new Product();
		product01.setProdNo(10086);
		
		product01.setProdName("선택남12");
		product01.setFileName("선택남13");
		product01.setManuDate("19931119");
		product01.setPrice(1300000);
		product01.setProdDetail("골라봐112121");
		product01.setProTranCode(null);
		
		System.out.println("@@@@@@@@");
		ObjectMapper objectMapper01 = new ObjectMapper();
		System.out.println("1111111111");
		//Object ==> JSON Value 로 변환
		String jsonValue = objectMapper01.writeValueAsString(product01);
		System.out.println("jV//////"+jsonValue);
		System.out.println("222222222222");
		HttpEntity httpEntity01 = new StringEntity(jsonValue,"utf-8");
		System.out.println("3333333333333");
		httpPost.setEntity(httpEntity01);
		HttpResponse httpResponse = httpClient.execute(httpPost);
		System.out.println("18181818181818");
		//==> Response 확인
		System.out.println(httpResponse);
		System.out.println();
		
		//==> Response 중 entity(DATA) 확인
		HttpEntity httpEntity02 = httpResponse.getEntity();
		System.out.println("3333333333333");
		//==> InputStream 생성
		InputStream is = httpEntity02.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		
		//==> API 확인 : Stream 객체를 직접 전달 
		JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		System.out.println(jsonobj);
	
		ObjectMapper objectMapper = new ObjectMapper();
		 Product product = objectMapper.readValue(jsonobj.toString(), Product.class);
		 System.out.println(product);
	}	
	
}