public class Typewriter {

    private final int delay;

    public Typewriter(int delay) {
        this.delay = delay;
    }

    public void type(String message, boolean useAnimation) throws InterruptedException {
        if (!useAnimation) {
            System.out.println(message);
            return;
        }
        for (char c : message.toCharArray()) {
            System.out.print(c);
            Thread.sleep(delay);
        }
        System.out.println();  // Move to the next line after typing the complete message
    }

}
