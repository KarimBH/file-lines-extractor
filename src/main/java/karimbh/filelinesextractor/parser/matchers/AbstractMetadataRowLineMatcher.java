package karimbh.filelinesextractor.parser.matchers;

import karimbh.filelinesextractor.parser.LineType;

/**
 * Created by k.benhmida on 02/07/2016.
 */
public abstract class AbstractMetadataRowLineMatcher extends AbstractLineMatcher {

    public AbstractMetadataRowLineMatcher(){
        this(LineType.METADATA_ROW);
    }

    private AbstractMetadataRowLineMatcher(LineType expectedLineType) {
        super(expectedLineType);
    }

    public abstract boolean matches(String line, int currentLineNum);
}
