package karimbh.filelinesextractor;

import karimbh.filelinesextractor.parser.FileParser;
import karimbh.filelinesextractor.parser.Line;
import karimbh.filelinesextractor.parser.LineType;
import karimbh.filelinesextractor.parser.matchers.AbstractDataRowLineMatcher;
import karimbh.filelinesextractor.parser.matchers.AbstractHeadersRowLineMatcher;
import karimbh.filelinesextractor.parser.matchers.AbstractLineMatcher;
import karimbh.filelinesextractor.parser.matchers.AbstractMetadataRowLineMatcher;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by k.benhmida on 01/07/2016.
 */
public class Main {


    public static void main(String[] args) throws IOException {
        new Main().test();
    }

    private void test() throws IOException {

        File origFile = new File(getClass().getClassLoader().getResource("sample.csv").getFile());

        //complex line matcher
        AbstractLineMatcher complexAbstractLineMatcher =
                new AbstractLineMatcher(LineType.MARKER_ROW) {
                    @Override
                    public boolean matches(String line, int currentLineNum) {
                        return line.contains("Page");
                    }
                }
                .skip() //skip the matched line
                .getNextAtOffset(2, LineType.REC_ROW) //get the line at line num = original matched line num + 2
                .getNextAtOffset(4, LineType.REC_ROW); //get the line at line num = original matched line num + 4


        List<Line> lines =
                new FileParser()
                        .addLineMatcher(complexAbstractLineMatcher)
                        .addLineMatcher(new AbstractMetadataRowLineMatcher() {
                            @Override
                            public boolean matches(String line, int lineNum) {
                                return line.contains("Metadata key");
                            }
                        })
                        .addLineMatcher(new AbstractHeadersRowLineMatcher() {
                            @Override
                            public boolean matches(String line, int lineNum) {
                                return line.contains("Header");
                            }
                        })
                        .addLineMatcher(new AbstractDataRowLineMatcher() {
                            @Override
                            public boolean matches(String line, int lineNum) {
                                return line.contains("Data val 1");
                            }
                        })
                        .addLineMatcher(new AbstractLineMatcher(LineType.DELIMITER_ROW) {
                            @Override
                            public boolean matches(String line, int lineNum) {
                                return line.contains("Delimiter key");
                            }
                        })
                        .parse(origFile);

        for (Line line : lines) {
            System.out.println(line);
        }
    }

}
