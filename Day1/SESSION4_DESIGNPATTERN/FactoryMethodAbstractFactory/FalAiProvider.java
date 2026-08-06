package SESSION4_DESIGNPATTERN.FactoryMethodAbstractFactory;

public class FalAiProvider implements VideoProvider {
    @Override
    public String generateVideo(String prompt) {
        // Implementation for generating video using Fal AI
        return "Fal AI generated video for prompt: " + prompt;
    }

}
