package jp.ddo.chiroru.sqllogger.proxy;

/**
 * <p>
 * DBとの接続セッションを識別する{@code ID}の生成器のインタフェースを表現するインタフェースです.<br />
 * なお、{@code ID}のフォーマットや一意性といった{@code ID}の性質は実装クラスによって規定されます.
 * </p>
 * @author smts1008@outlook.com
 * @since 1.0.0
 */
public interface SessionIdGenerator {

    String generate();

    class DefaultSessionIdGenerator
            implements SessionIdGenerator {

        @Override
        public String generate() {
            return null;
        }
    }
}
