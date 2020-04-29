package zyCucumber.support;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class AnnotationdealUtils {
    private static  final Logger logger = Logger.getLogger(AnnotationdealUtils.class);

    private static final String CASE_INFO_PRE = "@CASE_INFO";

    /**
     * 获取测试用例脚本名称
     * @param collection
     * @return
     */
    public static String getCaseInfo(Collection<String> collection) {
        Iterator iterator = collection.iterator();
        Pattern pattern = Pattern.compile("@CASE_INFO\\((.+)\\)");
        String caseTag = null;
        while(iterator.hasNext()) {
            String tag = (String)iterator.next();
            if(tag.contains(CASE_INFO_PRE)) {
                Matcher m = pattern.matcher(tag);
                if(m.find()) {
                    caseTag = m.group(1);
                    return  caseTag;
                }
            }
        }
        return caseTag;
    }
}
