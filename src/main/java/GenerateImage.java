import com.kennycason.kumo.CollisionMode;
import com.kennycason.kumo.WordCloud;
import com.kennycason.kumo.WordFrequency;
import com.kennycason.kumo.bg.*;
import com.kennycason.kumo.font.FontWeight;
import com.kennycason.kumo.font.KumoFont;
import com.kennycason.kumo.font.scale.*;
import com.kennycason.kumo.nlp.FrequencyFileLoader;
import com.kennycason.kumo.palette.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class GenerateImage {
    public static void main(String [] args) throws IOException {

        final FrequencyFileLoader frequencyAnalyzer = new FrequencyFileLoader();  //Initializes FrequencyFileLoader to read in words from a file.
        File input = new File ("WordCount.txt");
        final List<WordFrequency> wordFrequencies = frequencyAnalyzer.load(input);  //Loads in the words from the specified file.

        final Dimension dimension = new Dimension(600, 600);
        final WordCloud wordCloud = new WordCloud(dimension, CollisionMode.PIXEL_PERFECT);

        wordCloud.setPadding(1);
        wordCloud.setKumoFont(new KumoFont("Alef", FontWeight.BOLD));
        wordCloud.setBackground(new PixelBoundryBackground("outline.png"));
        wordCloud.setColorPalette(new LinearGradientColorPalette(new Color(0x4D0FFF), new Color(0xCEDBFA), 20));
        wordCloud.setFontScalar(new SqrtFontScalar(10, 60));

        wordCloud.build(wordFrequencies);
        wordCloud.writeToFile("image.png");  //Outputs the image.
    }
}
