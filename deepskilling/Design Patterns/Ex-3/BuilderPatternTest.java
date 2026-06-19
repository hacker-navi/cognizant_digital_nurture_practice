public class BuilderPatternTest {
    public static void main(String[] args) {
        Computer gamingPC = new Computer.Builder()
                .setCpu("Intel i9")
                .setRam("32 GB")
                .setStorage("1 TB SSD")
                .setGpu("RTX 4090")
                .setMotherboard("ASUS ROG")
                .build();
        
        System.out.println("Gaming PC: " + gamingPC);
        
        Computer officePC = new Computer.Builder()
                .setCpu("Intel i5")
                .setRam("8 GB")
                .setStorage("256 GB SSD")
                .build();
        
        System.out.println("Office PC: " + officePC);
        
        Computer workstationPC = new Computer.Builder()
                .setCpu("Intel Xeon")
                .setRam("64 GB")
                .setStorage("2 TB SSD")
                .setGpu("RTX A6000")
                .setMotherboard("ASUS Pro WS")
                .build();
        
        System.out.println("Workstation PC: " + workstationPC);
    }
}
