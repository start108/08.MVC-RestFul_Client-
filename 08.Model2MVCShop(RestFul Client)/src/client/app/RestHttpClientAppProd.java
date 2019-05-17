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
		// 1.1 Http Get ��� Request : JsonSimple lib ���
		RestHttpClientAppProd.getProductTest_JsonSimple(); //���ư���..�ų���...
		//RestHttpClientAppProd.listProductTest_JsonSimple();//���� ����
		
		System.out.println("\n====================================\n");
		// 1.2 Http Get ��� Request : CodeHaus lib ���
	//	RestHttpClientAppProd.getProductTest_Codehaus();//���ư���? JsonSimple�� ���ư��µ�.....
		//RestHttpClientAppProd.listProductTest_JsonSimple();//���� ����
				
		System.out.println("\n====================================\n");
//		// 1.2 Http Post ��� Request : CodeHaus lib ���
//		RestHttpClientAppProd.addProductTest_Codehaus(); //���ư���....�ų���.......
//		RestHttpClientAppProd.updateProductTest_Codehaus();//���� ����
	
	}
	
	
//================================================================//
	// Http Protocol GET Request : getProductTest
	public static void getProductTest_JsonSimple() throws Exception{//���ư��� �ų���
		System.out.println("���� ���Ͷ�~~~~~~~~~~~");
		// HttpClient : Http Protocol �� client �߻�ȭ 
		HttpClient httpClient = new DefaultHttpClient();
		System.out.println("����");
		String url= "http://127.0.0.1:8080/product/json/getProduct/10001";
		System.out.println("��������");		
		// HttpGet : Http Protocol �� GET ��� Request
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");
		System.out.println("������������");
		// HttpResponse : Http Protocol ���� Message �߻�ȭ
		HttpResponse httpResponse = httpClient.execute(httpGet);
		System.out.println("1818181818181818");
		//==> Response Ȯ��
		System.out.println(httpResponse);
		System.out.println();

		//==> Response �� entity(DATA) Ȯ��
		HttpEntity httpEntity = httpResponse.getEntity();
		System.out.println("191919191919191991");
		//==> InputStream ����
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		System.out.println("2020202020202020");
		System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
		String serverData = br.readLine();
		System.out.println(serverData);
		System.out.println("����������������");
		//==> �����б�(JSON Value Ȯ��)
		JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
		System.out.println(jsonobj);
	}
	
	//1.2 Http Protocol GET Request : JsonSimple + codehaus 3rd party lib ���
	public static void getProductTest_Codehaus() throws Exception{
		
		// HttpClient : Http Protocol �� client �߻�ȭ 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url= 	"http://127.0.0.1:8080/product/json/getProduct/10001";

		// HttpGet : Http Protocol �� GET ��� Request
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");
		
		// HttpResponse : Http Protocol ���� Message �߻�ȭ
		HttpResponse httpResponse = httpClient.execute(httpGet);
		
		//==> Response Ȯ��
		System.out.println(httpResponse);
		System.out.println();

		//==> Response �� entity(DATA) Ȯ��
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream ����
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		//==> �ٸ� ������� serverData ó�� 
		//System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
		//String serverData = br.readLine();
		//System.out.println(serverData);
		
		//==> API Ȯ�� : Stream ��ü�� ���� ���� 
		JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		System.out.println(jsonobj);
	
		ObjectMapper objectMapper = new ObjectMapper();
		 Product product = objectMapper.readValue(jsonobj.toString(), Product.class);
		 System.out.println(product);
	}
	
//================================================================//	
	
	// Http Protocol POST ��� Request : addProductTest 
	public static void addProductTest_Codehaus() throws Exception{//���ư��� �ų���
		
		// HttpClient : Http Protocol �� client �߻�ȭ 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url = "http://127.0.0.1:8080/product/json/addProduct";
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		

		//[ ��� 3 : codehaus ���]
		Product product01 =  new Product();
		product01.setProdName("���ó�12");
		product01.setFileName("���ó�13");
		product01.setManuDate("19931119");
		product01.setPrice(1300000);
		product01.setProdDetail("����");
		product01.setProTranCode(null);
		
		System.out.println("@@@@@@@@");
		ObjectMapper objectMapper01 = new ObjectMapper();
		System.out.println("1111111111");
		//Object ==> JSON Value �� ��ȯ
		String jsonValue = objectMapper01.writeValueAsString(product01);
		System.out.println("jV//////"+jsonValue);
		System.out.println("222222222222");
		HttpEntity httpEntity01 = new StringEntity(jsonValue,"utf-8");
		System.out.println("3333333333333");
		httpPost.setEntity(httpEntity01);
		HttpResponse httpResponse = httpClient.execute(httpPost);
		System.out.println("18181818181818");
		//==> Response Ȯ��
		System.out.println(httpResponse);
		System.out.println();
		
		//==> Response �� entity(DATA) Ȯ��
		HttpEntity httpEntity02 = httpResponse.getEntity();
		System.out.println("3333333333333");
		//==> InputStream ����
		InputStream is = httpEntity02.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		
		//==> API Ȯ�� : Stream ��ü�� ���� ���� 
		JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		System.out.println(jsonobj);
	
		ObjectMapper objectMapper = new ObjectMapper();
		 Product product = objectMapper.readValue(jsonobj.toString(), Product.class);
		 System.out.println(product);
	}	
	

//Http Protocol POST ��� Request : updateProductTest 
	public static void updateProductTest_Codehaus() throws Exception{//���ư��� �ų���
		
		// HttpClient : Http Protocol �� client �߻�ȭ 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url = "http://127.0.0.1:8080/product/json/updateProduct";
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		
		//[ ��� 3 : codehaus ���]
		Product product01 =  new Product();
		product01.setProdNo(10086);
		
		product01.setProdName("���ó�12");
		product01.setFileName("���ó�13");
		product01.setManuDate("19931119");
		product01.setPrice(1300000);
		product01.setProdDetail("����112121");
		product01.setProTranCode(null);
		
		System.out.println("@@@@@@@@");
		ObjectMapper objectMapper01 = new ObjectMapper();
		System.out.println("1111111111");
		//Object ==> JSON Value �� ��ȯ
		String jsonValue = objectMapper01.writeValueAsString(product01);
		System.out.println("jV//////"+jsonValue);
		System.out.println("222222222222");
		HttpEntity httpEntity01 = new StringEntity(jsonValue,"utf-8");
		System.out.println("3333333333333");
		httpPost.setEntity(httpEntity01);
		HttpResponse httpResponse = httpClient.execute(httpPost);
		System.out.println("18181818181818");
		//==> Response Ȯ��
		System.out.println(httpResponse);
		System.out.println();
		
		//==> Response �� entity(DATA) Ȯ��
		HttpEntity httpEntity02 = httpResponse.getEntity();
		System.out.println("3333333333333");
		//==> InputStream ����
		InputStream is = httpEntity02.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		
		//==> API Ȯ�� : Stream ��ü�� ���� ���� 
		JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		System.out.println(jsonobj);
	
		ObjectMapper objectMapper = new ObjectMapper();
		 Product product = objectMapper.readValue(jsonobj.toString(), Product.class);
		 System.out.println(product);
	}	
	
}