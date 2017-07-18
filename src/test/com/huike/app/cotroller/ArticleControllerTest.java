package test.com.huike.app.cotroller;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.smartframework.common.utils.ExHttpClientUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring/applicationContext.xml")
public class ArticleControllerTest {
private final String HOST = "http://127.0.0.1:8080/api/web/rest/article";
	
	@Test
	public void addArticleTest() {
		long btime = System.currentTimeMillis();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("type", "1");
		params.put("originalTime", "2017.4.24");
		params.put("title", "黑龙江省勃利县农技推广中心切实做好备春耕生产指导工作");
		params.put("source", "凤凰网");
		params.put("content", "承办各种业务");
		String url = HOST + "/add";
		String sendPostResult = ExHttpClientUtils.doGet(url, params);
		long etime = System.currentTimeMillis();
		long ttime = (etime - btime);
		System.out.println("查询时间：" + ttime + "--- sendPostResult" + sendPostResult);
	}
	
	@Test
	public void updateArticleTest() {
		long btime = System.currentTimeMillis();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", "1");
		params.put("title", "黑龙江省勃利县农技推广中心切实做好备春耕生产指导工作");
		params.put("source", "凤凰网");
		params.put("content", "承办各种业务。。。");
		String url = HOST + "/update";
		String sendPostResult = ExHttpClientUtils.doGet(url, params);
		long etime = System.currentTimeMillis();
		long ttime = (etime - btime);
		System.out.println("查询时间：" + ttime + "--- sendPostResult" + sendPostResult);
	}
	
	@Test
	public void deleteArticleTest() {
		long btime = System.currentTimeMillis();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", 1);
		String url = HOST + "/delete";
		String sendPostResult = ExHttpClientUtils.doGet(url, params);
		long etime = System.currentTimeMillis();
		long ttime = (etime - btime);
		System.out.println("查询时间：" + ttime + "--- sendPostResult" + sendPostResult);
	}
	
	@Test
	public void deleteBatchTest() {
		long btime = System.currentTimeMillis();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("ids", "4,2,3");
		String url = HOST + "/deleteBatch";
		String sendPostResult = ExHttpClientUtils.doGet(url, params);
		long etime = System.currentTimeMillis();
		long ttime = (etime - btime);
		System.out.println("查询时间：" + ttime + "--- sendPostResult" + sendPostResult);
	}
	
	@Test
	public void queryByIdTest() {
		long btime = System.currentTimeMillis();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", "7");
		String url = HOST + "/queryById";
		String sendPostResult = ExHttpClientUtils.doGet(url, params);
		long etime = System.currentTimeMillis();
		long ttime = (etime - btime);
		System.out.println("查询时间：" + ttime + "--- sendPostResult" + sendPostResult);
	}
	
	@Test
	public void searchPageTest() {
		long btime = System.currentTimeMillis();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("title", "黑");
		params.put("pageSize", 2);
		params.put("pageIndex", 2);
		String url = HOST + "/searchPage";
		String sendPostResult = ExHttpClientUtils.doGet(url, params);
		long etime = System.currentTimeMillis();
		long ttime = (etime - btime);
		System.out.println("查询时间：" + ttime + "--- sendPostResult" + sendPostResult);
	}
	
}
