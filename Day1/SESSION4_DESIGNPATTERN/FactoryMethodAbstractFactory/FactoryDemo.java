package SESSION4_DESIGNPATTERN.FactoryMethodAbstractFactory;

public class FactoryDemo {
public static void main(String[] args) {
    VideoProvider falAiProvider = VideoProviderFactory.create("FalAi");
    System.out.println(falAiProvider.generateVideo("Create a video about AI."));

    VideoProvider togetherAiProvider = VideoProviderFactory.create("TogetherAi");
    System.out.println(togetherAiProvider.generateVideo("Create a video about teamwork."));
}
}
