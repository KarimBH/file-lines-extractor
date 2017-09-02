package karimbh.filelinesextractor.parser;

import karimbh.filelinesextractor.parser.matchers.AbstractLineMatcher;
import karimbh.filelinesextractor.parser.matchers.LineNumLineMatcher;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by k.benhmida on 02/07/2016.
 */
public class FileParser {

    private List<AbstractLineMatcher> abstractLineMatchers;

    public FileParser() {
        this.abstractLineMatchers = new ArrayList<>();
    }

    public FileParser addLineMatcher(AbstractLineMatcher abstractLineMatcher) {
        this.abstractLineMatchers.add(abstractLineMatcher);
        return this;
    }


    public List<Line> parse(File file) throws IOException {
        List<Line> resultLines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            int lineNum = 0;
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                treatLine(resultLines, lineNum, line);
                lineNum++;
            }
        }
        return resultLines;
    }

    private void treatLine(List<Line> resultLines, final int currentLineNum, String line) {
        List<AbstractLineMatcher> createdAbstractLineMatchers = new LinkedList<>();
        for (AbstractLineMatcher abstractLineMatcher : abstractLineMatchers) {
            if (abstractLineMatcher.matches(line, currentLineNum)) {

                if (abstractLineMatcher.getNewtAbstractLineMatcher() != null) {
                    createdAbstractLineMatchers.add(abstractLineMatcher.getNewtAbstractLineMatcher());
                }

                if (abstractLineMatcher.getOffsets() != null) {
                    Map<Integer, LineType> integerLineTypeMap = abstractLineMatcher.getOffsets();
                    for (final Map.Entry<Integer, LineType> integerLineTypeEntry : integerLineTypeMap.entrySet()) {
                        createdAbstractLineMatchers.add(new LineNumLineMatcher(
                                integerLineTypeEntry.getValue(),
                                integerLineTypeEntry.getKey() + currentLineNum)
                        );
                    }
                }

                if (abstractLineMatcher.isSkip() == false) {
                    resultLines.add(new Line(currentLineNum, line, abstractLineMatcher.getExpectedLineType()));
                }
            }
        }

        abstractLineMatchers.addAll(createdAbstractLineMatchers);
    }

}
