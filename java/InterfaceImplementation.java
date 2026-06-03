public class InterfaceImplementation {
    public static void main(String[] args) {
        Playable guitar = new Guitar();
        Playable piano = new Piano();

        guitar.play();
        piano.play();
    }

    interface Playable {
        void play();
    }

    static class Guitar implements Playable {
        @Override
        public void play() {
            System.out.println("Playing guitar");
        }
    }

    static class Piano implements Playable {
        @Override
        public void play() {
            System.out.println("Playing piano");
        }
    }
}
