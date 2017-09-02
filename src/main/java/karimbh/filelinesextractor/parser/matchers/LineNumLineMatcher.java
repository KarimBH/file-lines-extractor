package karimbh.filelinesextractor.parser.matchers;

import karimbh.filelinesextractor.parser.LineType;

/**
 * Created by k.benhmida on 02/07/2016.
 */
public  class LineNumLineMatcher extends AbstractLineMatcher {

    private final int lineNum;

    public LineNumLineMatcher(LineType expectedLineType, int lineNum) {
        super(expectedLineType);
        this.lineNum = lineNum;
    }

    @Override
    public boolean matches(String line, int currentLineNum) {
        return currentLineNum == lineNum;
    }
}
