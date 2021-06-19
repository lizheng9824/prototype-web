package net.itfans.prototype.web.demo1.common;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * サブ画面のセッションクラス
 * <pre>
 *     サブ画面のセッションは標準HTTPセッションではなく、
 *     HttpSessionインタフェースを実装したオブジェクト
 *     サブセッションはメインセッションに格納され、メインセッションにあるUUID
 * </pre>
 */
public class SubSession implements HttpSession {

    /**
     *
     */
    private Map<String, Object> attributes = new ConcurrentHashMap<>();
    private Map<String, Object> values = new ConcurrentHashMap<>();
    private long creationTime;
    private String id;
    private String subSessionId;
    private boolean isNew;
    private long lastAccessedTime;
    private HttpSession originalSession;

    /**
     * コンストラクタ
     * @param subSessionId サブセッションのUUID
     * @param session 親セッション
     */
    public SubSession(String subSessionId, HttpSession session) {
        this.subSessionId = subSessionId;
        this.originalSession = session;
        this.creationTime = new Date().getTime();
        this.isNew = true;

        // 親セッションからデータをコピーする。
        Enumeration<String> attributeNames = session.getAttributeNames();
        while(attributeNames.hasMoreElements()) {
            String attributeName = attributeNames.nextElement();
            this.attributes.put(attributeName, session.getAttribute(attributeName));
        }

        
    }

    @Override
    public long getCreationTime() {
        return this.creationTime;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public long getLastAccessedTime() {
        return 0;
    }

    @Override
    public ServletContext getServletContext() {
        return this.originalSession.getServletContext();
    }

    @Override
    public void setMaxInactiveInterval(int interval) {
        // 何もしない。SubSessionのinvalidateは親セッションをinvalidateタイミングで行うため。
    }

    @Override
    public int getMaxInactiveInterval() {
        // Subsessionのinvalidateは親セッションから行うため、
        return Integer.MAX_VALUE;
    }

    @Override
    @Deprecated
    public HttpSessionContext getSessionContext() {
        return this.originalSession.getSessionContext();
    }

    @Override
    public Object getAttribute(String name) {
        return attributes.get(name);
    }

    @Override
    @Deprecated
    public Object getValue(String name) {
        return values.get(name);
    }

    @Override
    public Enumeration<String> getAttributeNames() {
        return Collections.enumeration(attributes.keySet());
    }

    @Override
    @Deprecated
    public String[] getValueNames() {
        return values.keySet().toArray(new String[values.size()]);
    }

    @Override
    public void setAttribute(String name, Object value) {
        attributes.put(name, value);
    }

    @Override
    @Deprecated
    public void putValue(String name, Object value) {
        values.put(name, value);
    }

    @Override
    public void removeAttribute(String name) {
        attributes.remove(name);
    }

    @Override
    @Deprecated
    public void removeValue(String name) {
        values.remove(name);
    }

    @Override
    public void invalidate() {
        // 親セッションとのつながりを切断し、GC対象とする。
        this.originalSession = null;
    }

    @Override
    public boolean isNew() {
        return this.isNew;
    }

    /**
     * 最終アクセス時間を設定する
     */
    public void setLastAccessTime() {
        this.lastAccessedTime = new Date().getTime();
    }
}
