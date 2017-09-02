package karimbh.filelinesextractor.parser;

/**
 * Created by k.benhmida on 02/07/2016.
 */
public class Line {

    private final int LineNum;
    private final String line;
    private final LineType lineType;


    Line(int lineNum, String line, LineType lineType) {
        LineNum = lineNum;
        this.line = line;
        this.lineType = lineType;
    }


    public int getLineNum() {
        return LineNum;
    }

    public String getLine() {
        return line;
    }
    
    public LineType getLineType() {
        return lineType;
    }


    @Override
    public String toString() {
        return "Line{" +
                "LineNum=" + LineNum +
                ", lineType=" + lineType +
                ", line='" + line + "'" +
                '}';
    }
}
