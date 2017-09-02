package karimbh.filelinesextractor.parser.matchers;

import karimbh.filelinesextractor.parser.LineType;

/**
 * Created by k.benhmida on 02/07/2016.
 */
public abstract class AbstractHeadersRowLineMatcher extends AbstractLineMatcher {

    public AbstractHeadersRowLineMatcher(){
        this(LineType.HEADER_ROW);
    }

    private AbstractHeadersRowLineMatcher(LineType expectedLineType) {
        super(expectedLineType);
    }

    public abstract boolean matches(String line, int currentLineNum);
}
