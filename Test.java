public class Test
{
    public static void main(String args[])
    {
        MainMenu mm = new MainMenu(true);
        mm.displayMenu();
        String choice = mm.choice("123");
        System.out.println("Your chosen option is: " + choice);
    }
}