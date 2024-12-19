package control;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.Cart;
import model.Moperation;

/**
 * カートに商品を追加するサーブレット
 * @author M.Takahashi
 */
@WebServlet("/add-prod-servlet")
public class AddProdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// パラメータ取得
//		request.setCharacterEncoding("UTF-8");
//		String[] idxes = request.getParameterValues("idx");
//		int[] idxArr = null;
//		if(idxes.length > 0) {
//			for(int i=0; i<idxes.length; i++) {
//				idxArr[i] = Integer.parseInt(idxes[i]);
//			}
//		}
//		int idx = Integer.parseInt(request.getParameter("idx"));
//
//		// セッションオブジェクト取得
//		HttpSession session = request.getSession();
//		
//		// カートへの商品追加処理
//		Moperation op = new Moperation();
//		op.addProd(idx, session);
//
//		// 転送先設定
//		String url = "cart.jsp";
//		
//		// 転送
//		RequestDispatcher rd = request.getRequestDispatcher(url);
//		rd.forward(request, response);
//	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // パラメータ取得
	    request.setCharacterEncoding("UTF-8");
	    String[] idxes = request.getParameterValues("idx[]");  // idx[] 배열로 받기
	    int[] idxArr = null;

	    // idxes 배열이 null이 아니고 길이가 0보다 클 때 처리
	    if (idxes != null && idxes.length > 0) {
	        idxArr = new int[idxes.length];
	        for (int i = 0; i < idxes.length; i++) {
	            idxArr[i] = Integer.parseInt(idxes[i]);
	        }
	    }

	    // 세션 객체 얻기
	    HttpSession session = request.getSession();
	    
	    //카트 정보 확인
	    Cart cart = (Cart) session.getAttribute("cart");
	    
	    // 카트에 상품 추가
	    Moperation op = new Moperation();
	    for (int idx : idxArr) {
	        op.addProd(idx, session); 
	    }

	    // 전송할 URL 설정
	    String url = "cart.jsp";
	    
	    // 전송
	    RequestDispatcher rd = request.getRequestDispatcher(url);
	    rd.forward(request, response);
	}

}
