package karimbh.filelinesextractor.parser.matchers;

/**
 * Created by k.benhmida on 03/07/2016.
 */
public interface LineMatcher {

     boolean matches(String line, int currentLineNum);
}
