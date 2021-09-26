# prototype-web
このレポジトリはWebアプリケーション開発における技術調査のプロトタイプをまとめるプロジェクトです。

---
### Demo 1
新しいWindowを起動する際に、セッションを共有しないように制御を入れる。

仕組み  
- Windows起動する際に、一意のIDを作成し、新しいwindowsのパラメタ（subsessionid）に引き渡す。
- HttpSessionをimplementしたSubSessionを作成する。（内部はMapでattributeを格納）
- HttpServletRequestWrapperをextendsし、subsessionidがある場合SubSessionを返却。

### Demo 5
BeanValidationのチェック結果メッセージに、行数を表示する。

仕組み
- 配列項目のindex部分を削除し、削除した部分をメッセージプロパティの項目に適用させる。例：
  - demo5Form.detailList[0].name ⇒　demo5Form.detailList.nameにする
  - demo5Form.detailList.nameのメッセージプロパティを「{0}行目のＸＸＸ」に設定する。
  - 配列のindexは+1で上記メッセージプロパティの引数として渡してメッセージを生成
- 元のバリデーションメッセージに上記生成したメッセージをパラメタとして適用。例：
  - 長さチェックのメッセージ{0}は{1}以下{2}以上で入力してください」の{0}の部分を上記メッセージを適用させる。

その結果1行目のXXXはA以下B以上で入力してください　とのメッセージが表示される。
