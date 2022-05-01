package example_decorator.example_decorator_youtube;

public class DeveloperDecorator implements Developer{

    Developer developer;

    public DeveloperDecorator(Developer developer) {
        this.developer = developer;
    }

    @Override
    public String makeJob() {
        return developer.makeJob();
    }
}
