package model;

import java.util.ArrayList;

import jakarta.servlet.http.HttpSession;

public class Moperation {
	/**
	 * ログイン時の処理
	 * @param userId リクエストパラメータ
	 * @param password リクエストパラメータ
	 * @param session セッションオブジェクト
	 * @return true .. 正常、false .. ID／パスワード誤り
	 */
	public boolean loginProc(String userId, String password, HttpSession session) {

		// ログイン認証
		boolean result = authenticate(userId, password);

		if (result) {
			// 店舗データの作成⇒セッションに格納
			Store store = makeStore();
			session.setAttribute("store", store);
			
			// カート情報の作成（userId設定・商品リストは空）⇒セッションに格納
			Cart cart = new Cart(userId, new ArrayList<Product>());
			session.setAttribute("cart", cart);
		}

		return result;
	}

	/**
	 * 認証する
	 * @param userId ユーザID
	 * @param password パスワード
	 * @return 結果 (true / false)
	 */
	private boolean authenticate(String userId, String password) {

		// ★ここでは password = "pass" であれば true とする
		boolean result = password.equals("1234");

		return result;
	}
	
	/**
	 * 店舗情報（店舗名＋選択データ（リスト））を作成する
	 * @return 店舗情報
	 */
	private Store makeStore() {

		// 店舗情報作成
		Store store = new Store("キャルのガールのギャル向けのキャルサービス", new ArrayList<Product>());
		
		// 商品追加(id, name, price, cartChk, imagePath)
		store.add(new Product("A110", "ザファーストペンアイライナー", 2000, false, "./image/eyeliner.png"));
		store.add(new Product("A120", "メリッソドリ ロンカラマスカラ", 3600, false, "./image/マスカラ.png"));
		store.add(new Product("A130", "ドローイングアイブロー", 3900, false, "./image/アイブロー.png"));
		store.add(new Product("A140", "エアフィットベルベットティント", 2900, false, "./image/エアフィットベルベットティント.png"));
		store.add(new Product("A150", "ダルバセラムグローフィックスカバークッションファクト", 9800, false, "./image/ダルバセラムグローフィックスカバークッションファクト.png"));
		store.add(new Product("A160", "ビューラー", 1900, false, "./image/ビューラー.png"));
		store.add(new Product("A170", "ヘラシルキーステイ24アワーロングウェアファンデーション", 3500, false, "./image/ヘラシルキーステイ24アワーロングウェアファンデーション.png"));
		store.add(new Product("A180", "ボールタッチ", 11000, false, "./image/ボールタッチ.png"));
		store.add(new Product("A190", "LASHPATTOまつげ", 4200, false, "./image/LASHPATTOまつげ.png"));
		
		return store;
	}
	
	/**
	 * ログアウト時の処理
	 * @param session
	 */
	public void logoutProc(HttpSession session) {
		session.invalidate();
	}

	/**
	 * 商品追加処理
	 * @param idx 商品一覧の選択した商品のidx (セッション：store内)
	 * @param session セッションオブジェクト
	 */
	public void addProd(int idx, HttpSession session) {
		
		// 店舗情報・カート情報の取得（セッションより）
		Store store = (Store) session.getAttribute("store");
		Cart cart = (Cart) session.getAttribute("cart");

		if ((store != null) && (cart != null)) {
			if(!(cart.getListProd().contains(store.getListProd().get(idx)))) {
				// カートに指定の商品を追加
				cart.add(store.getListProd().get(idx));
			}
			

			// セッションに再度格納
			session.setAttribute("cart", cart);
		}

	}

	/**
	 * カートから商品削除処理
	 * @param idx カートの中の選択した商品のidx
	 * @param session セッションオブジェクト
	 */
	public void removeProd(int idx, HttpSession session) {
	
		// カート内商品情報の取得（セッションより）
		Cart cart = (Cart) session.getAttribute("cart");
	
		if (cart != null) {
			// カートから指定の商品を削除
			cart.remove(idx);

			// セッションに書き戻す
			session.setAttribute("cart", cart);
		}
			
	}
	
	/**
	 * 精算処理
	 * @param session セッションオブジェクト
	 */
	public void pay(HttpSession session) {

		// カート内商品情報の取得
		Cart cart = (Cart) session.getAttribute("cart");

		if (cart != null) {
			// セッションに格納（精算済みデータ）
			session.setAttribute("pay", cart);

			// カート情報の新規作成⇒セッションに格納
			Cart newCart = new Cart(cart.getUserId(), new ArrayList<Product>());
			session.setAttribute("cart", newCart);
		}

	}

	/**
	 * 精算処理
	 * @param session セッションオブジェクト
	 */
	public void selectContents(HttpSession session) {

		// カート内商品情報の取得
		Cart cart = (Cart) session.getAttribute("cart");

		if (cart != null) {
			for(int i = 0; i < cart.getListProd().size(); i++) {
				cart.getListProd().get(i).setCartChk(true);
			}
			Store store = new Store("already", cart.getListProd());
			// セッションに格納（精算済みデータ）
			session.setAttribute("pay", cart);
			session.setAttribute("already", store);
		}
	}
}
