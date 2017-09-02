package karimbh.filelinesextractor.parser.matchers;

import karimbh.filelinesextractor.parser.LineType;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by k.benhmida on 02/07/2016.
 */
public abstract class AbstractLineMatcher implements LineMatcher {

    private final LineType expectedLineType;
    private boolean skip = false;
    private Map<Integer, LineType> offsets;
    private AbstractLineMatcher newtAbstractLineMatcher;

    protected AbstractLineMatcher(LineType expectedLineType) {
        this.expectedLineType = expectedLineType;
        offsets = new HashMap<>();
    }

    public LineType getExpectedLineType() {
        return expectedLineType;
    }


    public AbstractLineMatcher skip() {
        this.skip = true;
        return this;
    }

    public AbstractLineMatcher getNext() {
        getNextAtOffset(1);
        return this;
    }

    public AbstractLineMatcher getNextAtOffset(int offset) {
        getNextAtOffset(offset, this.expectedLineType);
        return this;
    }

    public AbstractLineMatcher getNextAtOffset(int offset, LineType targetLineType) {
        offsets.put(offset, targetLineType);
        return this;
    }

    public AbstractLineMatcher getNextWhen(AbstractLineMatcher abstractLineMatcher) {
        newtAbstractLineMatcher = abstractLineMatcher;
        return this;
    }

    public boolean isSkip() {
        return skip;
    }

    public AbstractLineMatcher getNewtAbstractLineMatcher() {
        return newtAbstractLineMatcher;
    }

    public Map<Integer, LineType> getOffsets() {
        return offsets;
    }
}
