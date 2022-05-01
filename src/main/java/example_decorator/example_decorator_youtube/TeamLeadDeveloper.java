package example_decorator.example_decorator_youtube;

public class TeamLeadDeveloper extends DeveloperDecorator{

    public TeamLeadDeveloper(Developer developer) {
        super(developer);
    }

    public String writeReport(){
        return " Write report";
    }

    @Override
    public String makeJob() {
        return super.makeJob() + writeReport();
    }
}
