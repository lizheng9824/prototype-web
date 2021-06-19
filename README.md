# prototype-web
このレポジトリはWebアプリケーション開発における技術調査のプロトタイプをまとめるプロジェクトです。

---
### Demo 1
新しいWindowを起動する際に、セッションを共有しないように制御を入れる。

仕組み  
- Windows起動する際に、一意のIDを作成し、新しいwindowsのパラメタ（subsessionid）に引き渡す。
- HttpSessionをimplementしたSubSessionを作成する。（内部はMapでattributeを格納）
- HttpServletRequestWrapperをextendsし、subsessionidがある場合SubSessionを返却。