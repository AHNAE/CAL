package control;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.Moperation;

/**
 * 精算するサーブレット
 * @author M.Takahashi
 */
@WebServlet("/select-servlet")
public class SelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// セッションオブジェクト取得
		HttpSession session = request.getSession();

		// 精算処理
		Moperation op = new Moperation();
		op.selectContents(session);
		
		// 転送先設定
		String url = "select.jsp";
		
		// 転送
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
