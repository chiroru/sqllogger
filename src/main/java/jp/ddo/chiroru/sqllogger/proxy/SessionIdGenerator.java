package jp.ddo.chiroru.sqllogger.proxy;

import java.util.concurrent.atomic.AtomicLong;

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

        AtomicLong counter = new AtomicLong();
        
        @Override
        public String generate() {

            String count = String.valueOf(counter.getAndAdd(1L));
            StringBuilder buffer = new StringBuilder();
            int countSize = count.getBytes().length;
            for (int i = countSize; i < Long.SIZE; i++) {
                buffer.append("0");
            }
            buffer.append(count);
            return buffer.toString();
        }
    }
}
