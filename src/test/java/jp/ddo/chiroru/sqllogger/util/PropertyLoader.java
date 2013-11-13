package jp.ddo.chiroru.sqllogger.util;

import java.io.IOException;
import java.util.Properties;

public interface PropertyLoader {

    Properties load(String path) throws IOException;
}
