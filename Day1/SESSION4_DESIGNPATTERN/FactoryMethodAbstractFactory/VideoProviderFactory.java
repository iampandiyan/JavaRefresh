package SESSION4_DESIGNPATTERN.FactoryMethodAbstractFactory;

public class VideoProviderFactory {
    static VideoProvider create(String name){
        return switch (name) {
            case "FalAi" -> new FalAiProvider();
            case "TogetherAi" -> new TogetherAiProvider();
            default -> throw new IllegalArgumentException("Unknown provider: " + name);
        };
    }
}
