package Modal;

public class ProductModal {
	private String product_id, product_name, product_type, product_note, product_state, product_image, product_amount;
	private float product_price;

	public ProductModal(String product_id, String product_name, String product_type, float product_price,
			String product_state, String product_note, String product_image, String product_amount) {
		this.product_id = product_id;
		this.product_name = product_name;
		this.product_type = product_type;
		this.product_price = product_price;
		this.product_note = product_note;
		this.product_state = product_state;
		this.product_image = product_image;
		this.product_amount = product_amount;
	}

	public ProductModal() {
	}

	public String getProduct_amount() {
		return product_amount;
	}

	public void setProduct_amount(String product_amount) {
		this.product_amount = product_amount;
	}

	public String getProduct_image() {
		return product_image;
	}

	public void setProduct_image(String product_image) {
		this.product_image = product_image;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getProduct_type() {
		return product_type;
	}

	public void setProduct_type(String product_type) {
		this.product_type = product_type;
	}

	public float getProduct_price() {
		return product_price;
	}

	public void setProduct_price(float product_price) {
		this.product_price = product_price;
	}

	public String getProduct_note() {
		return product_note;
	}

	public void setProduct_note(String product_note) {
		this.product_note = product_note;
	}

	public String getProduct_state() {
		return product_state;
	}

	public void setProduct_state(String product_state) {
		this.product_state = product_state;
	}
}
