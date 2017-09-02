package karimbh.filelinesextractor.parser.matchers;

import karimbh.filelinesextractor.parser.LineType;

/**
 * Created by k.benhmida on 02/07/2016.
 */
public abstract class AbstractDataRowLineMatcher extends AbstractLineMatcher {

    public AbstractDataRowLineMatcher(){
        this(LineType.DATA_ROW);
    }

    private AbstractDataRowLineMatcher(LineType expectedLineType) {
        super(expectedLineType);
    }

    public abstract boolean matches(String line, int currentLineNum);
}
