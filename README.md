# product-management-system
# 商品管理システム

## 📌 概要
このシステムは、商品を管理するためのWebアプリケーションです。
ログイン機能、商品登録、検索、並び替え、ページネーションなどを実装しています。
さらに、管理者専用ページを追加し、一般ユーザーと管理者のアクセス制限を強化しました。

## 💻 使用技術
- Java（Servlet & JSP）
- MySQL（データベース）
- Tomcat（Webサーバー）
- HTML / CSS / JavaScript
- JSTL（JSP タグライブラリ）

## 🚀 機能一覧
- ユーザー認証（ログイン / ログアウト）
- 商品の登録 / 編集 / 削除
- 商品の検索機能
- 商品の価格 / 名前順ソート
- ページネーション（5件ずつ表示）
- 画像アップロード機能

## 🟠 管理者機能（NEW!）
- 管理者専用ページ（Admin Dashboard）
- 管理者のみが商品を編集・削除可能
- 一般ユーザーの管理者ページへのアクセス制限
- セッション管理の強化

## 📂 インストール方法
1. `git clone https://github.com/your-username/your-repository.git`
2. `MySQL` にデータベースを作成し、`schema.sql` を実行
3. `管理者アカウント`を手動で追加（初期管理者をデータベースに登録）
      INSERT INTO users (username, password, role) 
      VALUES ('admin', 'ハッシュ化されたパスワード', 'admin');
      ※ハッシュ化されたパスワードは、`AdminPasswordGenerator.java`を実行して生成
4. `Tomcat` にデプロイ
5. `http://localhost:8080/product-management-system` にアクセス

## 🎯 今後の改良点
- Spring Boot に移行
- ユーザー登録機能の改善（管理者がユーザーを管理）
- 商品の詳細ページを作成
- API を導入し、フロントエンドを Vue.js / React で構築
