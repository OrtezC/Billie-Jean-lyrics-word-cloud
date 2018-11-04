public class GenerateImage {
    public static void main(String [] args){
        final FrequencyAnalyzer frequencyAnalyzer = new FrequencyAnalyzer();
        frequencyAnalyzer.setWordFrequenciesToReturn(300);
        frequencyAnalyzer.setMinWordLength(4);
        frequencyAnalyzer.setStopWords(loadStopWords());

        final List<WordFrequency> wordFrequencies = frequencyAnalyzer.load("text/datarank.txt");
        final Dimension dimension = new Dimension(500, 312);
        final WordCloud wordCloud = new WordCloud(dimension, CollisionMode.PIXEL_PERFECT);
        wordCloud.setPadding(2);
        wordCloud.setBackground(new PixelBoundryBackground("backgrounds/whale_small.png"));
        wordCloud.setColorPalette(new ColorPalette(new Color(0x4055F1), new Color(0x408DF1), new Color(0x40AAF1), new Color(0x40C5F1), new Color(0x40D3F1), new Color(0xFFFFFF)));
        wordCloud.setFontScalar(new LinearFontScalar(10, 40));
        wordCloud.build(wordFrequencies);
        wordCloud.writeToFile("kumo-core/output/whale_wordcloud_small.png");
    }
}
