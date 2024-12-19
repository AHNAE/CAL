package model;

/**
 * 商品クラス
 * @author M.Takahashi
 */
public class Product {
	/******** フィールド ******************************************/
	/**
	 * 商品ID
	 */
	private String id;
	
	/**
	 * 商品名
	 */
	private String name;
	
	/**
	 * 価格
	 */
	private int price;
	
	/**
	 * カート確認 
	 */
	private boolean 	 cartChk;
	
	/**
	 * imagePath 
	 */
	private String imagePath;

	/******** コンストラクタ **************************************/
	/**
	 * フィールド初期化コンストラクタ
	 * @param id
	 * @param name
	 * @param price
	 */
	public Product(String id, String name, int price, boolean cartChk, String imagePath) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.cartChk = cartChk;
		this.imagePath = imagePath;
	}

	public boolean isCartChk() {
		return cartChk;
	}

	public void setCartChk(boolean cartChk) {
		this.cartChk = cartChk;
	}

	/******** メソッド ******************************************/
	/*--------------------getter/setter--------------------*/
	/**
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * 価格を文字列で返す（３桁カンマ区切り＋"円"）
	 * @return 価格 + 円
	 */
	public String getPriceString() {
		return String.format("%,d", price) + "円";
	}

	/**
	 * @return true/false
	 */
	public boolean getcartChk() {
		return cartChk;
	}
	
	/**
	 * @return String imagePath
	 */
	public String getImagePath() {
		return imagePath;
	}
}
