package net.itfans.prototype.web.demo1.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

public class SubSessionHttpServletRequestWrapper extends HttpServletRequestWrapper {

    /**
     * サブセッションのセッションんキーのprefix
     */
    private static final String SUB_SESSION_KEY_PREFIX = "subsession-";

    /**
     * コンストラクタ
     * @param request リクエスト情報
     */
    public SubSessionHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public HttpSession getSession(boolean create) {

        String subSessionId = getRequest().getParameter("subsessionid");
        if (subSessionId != null) {
            // サブセッションのHTTPパラメタがある場合、サブセッションを作成・取得する
            HttpSession session = super.getSession(false);
            if (session == null) {
                // 親セッションはない状態でSubSessionをアクセスしようとしているため、エラーとする
                throw new IllegalStateException("can not access sub-session without main session");
            } else {
                SubSession subSession = (SubSession)session.getAttribute(SUB_SESSION_KEY_PREFIX + subSessionId);
                if (subSession == null && create) {
                    subSession = new SubSession(subSessionId, session);
                    session.setAttribute(SUB_SESSION_KEY_PREFIX + subSessionId, subSession);
                }
                return subSession;
            }
        } else {
            // サブセッションのHTTPパラメタがない場合、通知のセッションを返却する。
            return super.getSession(create);
        }
    }

    @Override
    public HttpSession getSession() {
        return this.getSession(true);
    }
}
