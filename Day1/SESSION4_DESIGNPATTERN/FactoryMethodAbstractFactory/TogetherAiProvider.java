package SESSION4_DESIGNPATTERN.FactoryMethodAbstractFactory;

public class TogetherAiProvider implements VideoProvider {
    @Override
    public String generateVideo(String prompt) {
        // Implementation for generating video using Together AI
        return "Together AI generated video for prompt: " + prompt;
    }
}
