package net.itfans.prototype.web.demo5.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Component;
import org.thymeleaf.spring5.expression.Fields;
import org.thymeleaf.spring5.util.DetailedError;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * リストメッセージを加工するためのハンドラ
 */
@Component("listErrorMessageHandler")
public class ListErrorMessageHandler {

    /**
     * メッセージリソース
     */
    @Autowired
    private MessageSource messageSource;

    /**
     * 項目名にある配列を削除する正規表現　
     * 例：form.detailList[0].id　⇒ form.detailList.id
     */
    private static final Pattern pattern = Pattern.compile("\\[(\\d+)\\]");

    /**
     * メッセージの引数を加工する。
     * <li>form.detailList[0].idの引数をform.detailList.idに加工することによりform.detailList.idのメッセージIDを適用</li>
     * <li>listのindexを元の引数の先頭に追加</li>
     * 上記によってform.detailList.idのメッセージを [{0}行目のXXX]に設定した場合、そのメッセージを利用し、「1行目のXXX」に置換される。
     * @param o 加工前の引数
     * @return 加工後の引数
     */
    private Object convertArgument(Object o) {

        if (o instanceof MessageSourceResolvable) {
            // argument自体はメッセージの場合、メッセージの編集を行う。
            MessageSourceResolvable messageSourceResolvable = (MessageSourceResolvable)o;

            String[] codes = messageSourceResolvable.getCodes();

            // codeにあるindexを排除する。
            String[] newCodes = Arrays.stream(codes).
                    map(s -> pattern.matcher(s).replaceAll("")).toArray(String[]::new);

            List<Object> newArgumentList = new ArrayList<>();

            // codeのindexを argumentのメッセージを生成用のargumentの先頭に追加
            if (codes.length > 0) {
                Matcher matcher = pattern.matcher(codes[0]);
                while (matcher.find()) {
                    for (int i = 1; i <= matcher.groupCount(); i++){
                        newArgumentList.add(Integer.valueOf(matcher.group(i)) + 1);
                    }
                }
            }

            if (messageSourceResolvable.getArguments() != null) {
                newArgumentList.addAll(Arrays.asList(messageSourceResolvable.getArguments()));
            }

            // 変更後のcodeとargumentで新しいargumentのobjectを作成し、返却する。
            DefaultMessageSourceResolvable newMessageSourceResolvable =
                    new DefaultMessageSourceResolvable(newCodes, newArgumentList.toArray(),
                            messageSourceResolvable.getDefaultMessage());

            return newMessageSourceResolvable;
        }

        // argumentはメッセージではない場合（固定値の場合）、加工せずそのまま利用する。
        return o;
    }

    /**
     * メッセージを生成する。
     * @param fields すべてのエラー
     * @param locale ロケール
     * @return　メッセージの配列
     */
    public List<String> handleMessage(Fields fields, Locale locale) {
        // エラーの詳細リストを取得
        List<DetailedError> errors =  fields.detailedErrors();


        List<String> messageList = new ArrayList<>();

        // エラー詳細一件ごとに、エラーメッセージを生成し、リストに追加
        for (DetailedError error: errors) {

            // 配列のメッセージを提供できるように、argumentの編集を行う。
            Object[] newArguments = Arrays.stream(error.getArguments()).map(e -> convertArgument(e)).toArray();

            // 編集後のargumentでメッセージを取得する。
            String message = messageSource.getMessage(error.getCode(), newArguments, locale);
            messageList.add(message);
        }
        return messageList;
    }
}