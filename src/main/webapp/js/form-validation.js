// フォーム送信時のバリデーション
document.querySelector("form").addEventListener("submit", validateForm);

// 各入力フィールドにリアルタイムバリデーションを追加
["name", "price", "category", "stock", "description"].forEach(id => {
    document.getElementById(id).addEventListener("input", clearErrorMessage);
});

function validateForm(event) {
	let isValid = true; // バリデーションの状態
	const errors = []; // エラーメッセージを格納
	
	// 商品名のバリデーション	
	const name = document.getElementById("name").value.trim();
	if (name.length === 0 || name.length > 50) {
		isValid = false;
		errors.push("商品名は1～50文字で入力してください。");
		showError("name", "商品名は1～50文字で入力してください。");
	}
	
	// 価格のバリデーション
	const price = document.getElementById("price").value.trim();
	if (price <= 0 || isNaN(price)) {
		isValid = false;
		errors.push("価格は正の数値を入力してください。");
		showError("price", "価格は正の数値を入力してください。");
	}
	
	// カテゴリのバリデーション
	const category = document.getElementById("category").value.trim();
	if (category.length === 0 || category.length > 30) {
		isValid = false;
		errors.push("カテゴリは1～30文字で入力してください。");
		showError("category", "カテゴリは1～30文字で入力してください。");
	}
	
	// 在庫数のバリデーション
	const stock = document.getElementById("stock").value.trim();
	if (stock < 0 || isNaN(stock)) {
		isValid = false;
		errors.push("在庫数は0以上の数値を入力してください。");
		showError("stock", "在庫数は0以上の数値を入力してください。");
	}
	
	// 説明フィールドのバリデーション
	const description = document.getElementById("description").value.trim();
	if (description.length === 0 || description.length > 200) {
	    isValid = false;
	    errors.push("説明は1～200文字で入力してください。");
	    showError("description", "説明は1～200文字で入力してください。");
	}
	
	// 送信時のエラー表示
	const errorDiv = document.getElementById("error-messages");
	errorDiv.innerHTML = ""; // 以前のエラーをクリア
	
	if (!isValid) {
		event.preventDefault(); // フォーム送信をキャンセル
		errors.forEach((error) => {
			const p = document.createElement("p");
			p.textContent = error;
			errorDiv.appendChild(p); // 上部にエラーを表示
		}); 
	}
}

// 個別エラー表示
function showError(inputId, message) {
    const inputElement = document.getElementById(inputId);
    const errorElement = document.createElement("span");
    errorElement.className = "error-message";
    errorElement.textContent = message;

    if (!inputElement.nextElementSibling || inputElement.nextElementSibling.className !== "error-message") {
        inputElement.parentNode.insertBefore(errorElement, inputElement.nextSibling);
    }
}

// リアルタイムでエラーをクリア
function clearErrorMessage(event) {
    const errorElement = event.target.nextElementSibling;
    if (errorElement && errorElement.className === "error-message") {
        errorElement.remove();
    }
}