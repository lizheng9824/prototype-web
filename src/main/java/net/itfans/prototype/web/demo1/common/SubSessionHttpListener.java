package net.itfans.prototype.web.demo1.common;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Enumeration;

public class SubSessionHttpListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        // 何もしない
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession httpSession = se.getSession();

        // セッションからAttributeを取得
        Enumeration<String> attributeNames = httpSession.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            Object attribute = httpSession.getAttribute(attributeNames.nextElement());

            // attributeにSubSessionがある場合、SubSessionをinvalidateする。
            if (attribute instanceof SubSession) {
                ((SubSession)attribute).invalidate();
            }
        }
    }
}
