package com.example.lesson03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.lesson03.bo.ReviewBO;
import com.example.lesson03.model.Review;

@RestController	// @Controller + @ResponseBody => 데이터만 response로 내릴 때 사용
public class ReviewRestController {
	
	@Autowired
	public ReviewBO reviewBO;
	
	// 요청 URL: http://localhost:8080/lesson03/ex01
	@RequestMapping("/lesson03/ex01")
	public Review ex01(
			//@RequestParam(value="id") int id	// 필수파라미터
			//@RequestParam(value="id", required=true) int id	// 필수파라미터
			//@RequestParam(value="id", required=false) Integer id	// 비필수파라미터(값이 없을 수 있기 때문에 null을 저장하기 위해 Integer)
			@RequestParam(value="id", defaultValue="1") int id	// 비필수 파리미터, 디폴트값 1
	) {
		System.out.println("### id : " + id);
		return reviewBO.getReview(id);
	}
	
	// 요청 URL: http://localhost/lesson03/ex02
	@RequestMapping("/lesson03/ex02")
	public String ex02() {
		Review review = new Review();
		review.setStoreName("민준이네");
		review.setMenu("왕갈비");
		review.setUserName("신보람");
		review.setPoint(4.5);
		review.setReview("맛있어요ㅋ");
		
		int row = reviewBO.insertReview(review);	// insert된 row수를 리턴 받는다.
		
		return "success row count:" + row;	// @ResponseBody로 인해 String 값 자체가 ResponseBody에 담긴다
	}
	
	// 요청 URL: http://localhost/lesson03/ex02/2
	@RequestMapping("/lesson03/ex02/2")
	public String ex02_2() {
		int row = reviewBO.insertReviewAsField("도미노피자", "콤피네이션", "신바다", 5.0, "굳이에요ㅎ");
		return "success row count: " + row;
	}
	
	// 요쳥 URL: http://localhost:8080/lesson03/ex03?id=17&review=삼겹살은 맛있엉ㅋ
	@RequestMapping("/lesson03/ex03")
	public String ex03(
			@RequestParam("id") int id,
			@RequestParam("review") String review) {
		
		int row = reviewBO.updateReview(id, review);
		return "변경 완료: " + row;
	}
	
	// 요청 URL: http://localhost:8080/lesson03/ex04?id=17
	@RequestMapping("/lesson03/ex04")
	public String ex04(@RequestParam("id") int id) {
		
		int row = reviewBO.deleteReviewById(id);
		return "삭제 완료: " + row;
	}
	
}
