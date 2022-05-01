package example_decorator.example_decorator_youtube;

public class Task {
    public static void main(String[] args) {
        Developer developer = new JavaDeveloper();
        System.out.println(developer.makeJob());
        Developer JavaSenior = new SeniorDeveloper(new JavaDeveloper());
        System.out.println(JavaSenior.makeJob());
        Developer JavaTeam0 = new TeamLeadDeveloper(new SeniorDeveloper(new JavaDeveloper()));
        System.out.println(JavaTeam0.makeJob());
        Developer JavaTeam1 = new TeamLeadDeveloper(new JavaDeveloper());
        System.out.println(JavaTeam1.makeJob());

        System.out.println("--- --- ---");

        Developer pythonDev = new PythonDev();
        System.out.println(pythonDev.makeJob());
        Developer pythonSenior = new SeniorDeveloper(new PythonDev());
        System.out.println(pythonSenior.makeJob());
        Developer pythonTeam = new TeamLeadDeveloper(new SeniorDeveloper(new PythonDev()));
        System.out.println(pythonTeam.makeJob());
    }
}