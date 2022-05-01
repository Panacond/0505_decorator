package example_decorator.example_decorator_youtube;

public class SeniorDeveloper extends DeveloperDecorator{

    public SeniorDeveloper(Developer developer) {
        super(developer);
    }

    public String makeCoderReview(){
        return " Make code review.";
    }

    @Override
    public String makeJob() {
        return super.makeJob() + makeCoderReview();
    }
}
